package org.ajgl.test;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

import org.lwjgl.glfw.GLFW;

public class WindowUtils {
    
    public void showWindow(long window) {
        GLFW.glfwShowWindow(window);    // Make the window visible
    }
    
    public void windowHint(int hint, int value) {
        GLFW.glfwWindowHint(hint, value);
    }
    
    public void closeGLFW() {
        glfwTerminate();
    }
}
