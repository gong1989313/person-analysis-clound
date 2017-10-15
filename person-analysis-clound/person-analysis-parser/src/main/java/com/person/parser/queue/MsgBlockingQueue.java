package com.person.parser.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MsgBlockingQueue {
	private MsgBlockingQueue() {
	}

	private static MsgBlockingQueue instance = new MsgBlockingQueue();

	public static MsgBlockingQueue getInstance() {
		return null == instance ? new MsgBlockingQueue() : instance;
	}

	public BlockingQueue<String> getJsonQueue() {
		return JsonQueue;
	}

	private volatile static LinkedBlockingQueue<String> JsonQueue = new LinkedBlockingQueue<String>(1000);
}
