package com.person.analysis.flow

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import java.sql.Timestamp
import com.person.analysis.dto.GenderCount
import com.person.analysis.utils.JsonUtil

object PersonAnalysisMain {
  def main(args: Array[String]): Unit = {
    val runMode = "local"
    val sparkSession = SparkSession.builder().master(runMode)
      .appName("PersonAnalysis")
      .config("spark.mongodb.input.uri", "mongodb://192.168.2.13:27017,192.168.2.14:27017,192.168.2.15:27017/bigdata")
      .config("spark.mongodb.output.uri", "mongodb://192.168.2.13:27017,192.168.2.14:27017,192.168.2.15:27017/bigdata")
      .config("spark.mongodb.output.database", "bigdata")
      .config("spark.mongodb.output.collection", "test").getOrCreate()

    val streamingContext = new StreamingContext(sparkSession.sparkContext, Seconds(1))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.2.13:9092,192.168.2.14:9092,192.168.2.15:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean))

    val topics = Array("test")
    val stream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams))
    val lines = stream.map(_.value()).map(line => JsonUtil.json2PersonObj(line))
    //.map(p => (p.getGender(), 1))
    val words = lines.map({p =>
      import com.person.common.entity.Person.implicits._
      (p.getGender(), 1)})
    // val words = lines.flatMap(_.getGender)
    // val words = lines.flatMap(_.split(" "))
    // val pairs = words.map(word => (word, 1))
    // val wordCounts = pairs.reduceByKey(_ + _)

    wordCounts.foreachRDD({ rdd =>
      import sparkSession.implicits._
      val wordCounts = rdd.map({
        case (word: String, count: Int) => GenderCount(word, count)
      }).toDF()
      wordCounts.write.mode("append").format("com.mongodb.spark.sql").save()
    })

    /*val lines = stream.map(_.value())
    lines.foreachRDD({
      rdd =>
        {
          import sparkSession.implicits
          val mongoDF = rdd.map({
            case (timeStamp: Timestamp, gender: String, quantity: Int) => SexualityModel(timeStamp, gender, quantity)
          }).toDF
          mongoDF.write.mode("append").format("com.mongodb.spark.sql").mongo()
        }
    })*/

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}