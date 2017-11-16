package com.person.common.util;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TraceThreadPoolExecutorTest {

	@Test
	public void test() {
		TraceThreadPoolExecutor pool = new TraceThreadPoolExecutor(0, 10, 0L, TimeUnit.SECONDS,
				new SynchronousQueue<>());

		for (int i = 0; i < 10; i++) {
			pool.execute(new DivTask(100, i));
		}
	}

	class DivTask implements Runnable {
		int a, b;

		public DivTask(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			double re = a / b;
			System.out.println(re);

		}
	}

}
