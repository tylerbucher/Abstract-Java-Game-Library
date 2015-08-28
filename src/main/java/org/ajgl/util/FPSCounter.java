package org.ajgl.util;

public class FPSCounter {
	private static long startTime = System.currentTimeMillis();
//	private static elapsiedTime;
	
	public static int getFPS() {
		int fps = (int) (1.0 / (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		return fps/1000;
	}
}
