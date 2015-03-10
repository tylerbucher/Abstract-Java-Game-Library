/**
 * 
 */
package org.ajgl.test;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_REFRESH_RATE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public abstract class Window {
    
    private volatile int height;
    private volatile int width;
    private volatile String title;
    private volatile long monitor;
    private volatile long share;
    // The window handler
    private volatile long window;
    
    // callback reference instances
    private volatile GLFWErrorCallback errorCallback;
    
    private Thread thread;
    
    public Window() {
        this.height = 800;
        this.width = 1200;
        this.title = "Abstract Java Game Library";
        this.monitor = 0;
        this.share = 0;
        this.thread = new Thread();
        
        setup();
    } 
    
    public Window(int height, int width, String title, long monitor, long share, Thread thread) {
        this.height = height;
        this.width = width;
        this.title = title;
        this.monitor = monitor;
        this.share = share;
        this.thread = thread;
        
        setup();
    }
    
    public boolean setup() {
        errorCallbackSetup();
        if(!windowSetup())
            return false;
        glfwContext();
        initGL();
        return true;
    }
    
    public void startThread() {
        try{
            thread.start();
        } catch(IllegalThreadStateException e) {
            return;
        }
    }

    protected void errorCallbackSetup() {
        // Setup an error callback
        GLFW.glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
    }
    
    protected boolean windowSetup() {
        // Initialize GLFW
        if (GLFW.glfwInit() != 1) {
            errorCallback.release();
            return false;
        }
        
        // Setup window properties
        //windowHintSetup();
        
        // Create the window
        window = glfwCreateWindow(width, height, title, monitor, share);
        if (window == 0) {
            errorCallback.release();
            return false;
        }
        
        // Setup window position
        //windowPosition();
        
        return true;
    }
    
    protected void initGL() {
        // Initialize openGl
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, 0, height, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    
    protected void glfwContext() {
        glfwMakeContextCurrent(window);     // Make the OpenGL context current
        GLContext.createFromCurrent();      // Bind lwjgl with GLFW
    }
}
