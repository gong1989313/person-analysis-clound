package com.person.parser.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by Ivan on 10/15/2017.
 */
public class ProducerPartitioner implements Partitioner {

    /**
     * 无参构造函数
     */
    public ProducerPartitioner() {
        this(new VerifiableProperties());
    }

    /**
     * 构造函数，必须给定
     *
     * @param properties 上下文
     */
    public ProducerPartitioner(VerifiableProperties properties) {
        // nothings
    }

    @Override
    public int partition(Object key, int numPartitions) {
        int num = Integer.valueOf(((String) key).replaceAll("key_", "").trim());
        return num % numPartitions;
    }
}