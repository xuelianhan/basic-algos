package org.ict.util;

import java.util.concurrent.TimeUnit;


import com.google.common.base.Stopwatch;

public class StopwatchTest {

	public void testStopwatch() {
		Stopwatch watch = Stopwatch.createStarted();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		watch.stop();
		System.out.println(watch.elapsed(TimeUnit.MILLISECONDS));
	}
}
