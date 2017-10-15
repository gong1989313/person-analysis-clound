package com.person.parser.kafka;

import org.apache.log4j.Logger;

import com.person.parser.task.KafkaProducerTask;
import com.person.parser.utils.ThreadPoolUtil;

/**
 * Created by Ivan on 10/15/2017.
 */
public class KafkaProducerService {
    private static Logger logger = Logger.getLogger(KafkaProducerService.class);
    
    public KafkaProducerService() {
    	logger.info("start KafkaProducerService");
        for (int i = 0; i < 5; i++) {
        	ThreadPoolUtil.submitTask(new KafkaProducerTask());

        }
    }
}