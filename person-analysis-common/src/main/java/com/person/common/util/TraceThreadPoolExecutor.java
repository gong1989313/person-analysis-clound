package com.person.common.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	public void execute(Runnable task) {
		super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
	}

	private Exception clientTrace() {
		return new Exception("Client stack trace");
	}
	
	private Runnable wrap(final Runnable task, final Exception clientStack, String threadName) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					clientStack.printStackTrace();
					throw e;
				}
			}
		};
	}
}
