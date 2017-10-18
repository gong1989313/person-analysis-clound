package com.person.parser.task;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.person.parser.queue.MsgBlockingQueue;
import com.person.parser.utils.ConsultUtil;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducerTask implements Runnable {
	private static Logger logger = Logger.getLogger(KafkaProducerTask.class);
    private Producer<String, String> producer = null;
    private BlockingQueue<String> jsonQueue = MsgBlockingQueue.getInstance().getInstance().getJsonQueue();
	public KafkaProducerTask(){
		logger.info("KafkaProducerTask init...");
    	String brokerList = "192.168.2.13:9092,192.168.2.14:9092,192.168.2.15:9092";
        Properties props = new Properties();
        props.put("metadata.broker.list", brokerList);
        /**
         * 0表示不等待结果返回<br/>
         * 1表示等待至少有一个服务器返回数据接收标识<br/>
         * -1表示必须接收到所有的服务器返回标识，及同步写入<br/>
         * */
        props.put("request.required.acks", "0");
        /**
         * 内部发送数据是异步还是同步
         * sync：同步, 默认
         * async：异步
         */
        props.put("producer.type", "async");
        /**
         * 设置序列化的类
         * 可选：kafka.serializer.StringEncoder
         * 默认：kafka.serializer.DefaultEncoder
         */
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        /**
         * 设置分区类
         * 根据key进行数据分区
         * 默认是：kafka.producer.DefaultPartitioner ==> 安装key的hash进行分区
         * 可选:kafka.serializer.ByteArrayPartitioner ==> 转换为字节数组后进行hash分区
         */
        props.put("partitioner.class", "com.person.parser.kafka.ProducerPartitioner");

        // 重试次数
        props.put("message.send.max.retries", "3");

        // 异步提交的时候(async)，并发提交的记录数
        props.put("batch.num.messages", "200");

        // 设置缓冲区大小，默认10KB
        props.put("send.buffer.bytes", "102400");

        // 2. 构建Kafka Producer Configuration上下文
        ProducerConfig config = new ProducerConfig(props);

        // 3. 构建Producer对象
        producer = new Producer<String, String>(config);
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!jsonQueue.isEmpty()) {
					String msg = jsonQueue.take();
					if(msg != null) {
						producer.send(generateKeyedMessage(msg));
						TimeUnit.MILLISECONDS.sleep(100);
					}
				} else {
					TimeUnit.MILLISECONDS.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			logger.error("Exception in thread:" + Thread.currentThread().getName() + ".", e);
		}
	}
	
    private static KeyedMessage<String, String> generateKeyedMessage(String msg) {
        String message = msg.trim();
        logger.info(" generateKeyedMessage :"+message);
        return new KeyedMessage(ConsultUtil.TOPIC_NAME, message);
    }
}
