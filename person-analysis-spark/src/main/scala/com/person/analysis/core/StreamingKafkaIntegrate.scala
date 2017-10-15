package com.person.analysis.core

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object StreamingKafkaIntegrate {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("kafkaTest").setMaster("local")
    val streamingContext = new StreamingContext(conf, Seconds(1))
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.2.13:9092,192.168.2.14:9092,192.168.2.15:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      /**
       * earliest  当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
			 * latest  当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
			 * none  topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
       */
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean))

    val topics = Array("test")
    val stream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams))

    //val result =  stream.map(record => (record.key, record.value))
    // stream.map(record => (record.value().toString()))
    stream.map(record => (record.value().toString)).print
    println("-----------------------------")

    streamingContext.start()

    streamingContext.awaitTermination()
  }
}