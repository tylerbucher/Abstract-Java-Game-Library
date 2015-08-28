package org.ajgl.util;

import org.lwjgl.glfw.GLFW;

public class FPSCounter {
	
	public static int getFPS() {
		int fps = (int) (1.0 / GLFW.glfwGetTime());
		GLFW.glfwSetTime(0.0);
		return fps;
	}
}
