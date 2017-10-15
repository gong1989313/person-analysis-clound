package com.person.parser.persistent;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.person.parser.queue.MsgBlockingQueue;

public class PersistentService {
	private static volatile long index = 0;
	private static Logger logger = Logger.getLogger(PersistentService.class);
	private BlockingQueue<String> queue = MsgBlockingQueue.getInstance().getJsonQueue();
	// private List<String> jsonList = new ArrayList<String>();

	public void save2Queue(String[] sourceData) {
		// logger.info(" save2Queue sourceData is "+sourceData);
		if (ArrayUtils.isNotEmpty(sourceData)) {
			String json = this.convert2Json(sourceData);
			logger.info((++index) + " save2Queue sourceData is " + json);
			if (null != json) {
				// jsonList.add(json);
				/*
				 * try { queue.put(json); } catch (InterruptedException e) {
				 * e.printStackTrace(); }
				 */
			}
		}
	}

	private String convert2Json(String[] sourceData) {
		return JSON.toJSONString(sourceData);
	}
}
