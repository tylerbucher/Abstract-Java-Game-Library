/**
 * 
 */
package org.ajgl.test;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public class Window {
    
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
    } 
    
    public Window(int width, int height, String title, long monitor, long share, Thread thread) {
        this.height = height;
        this.width = width;
        this.title = title;
        this.monitor = monitor;
        this.share = share;
        this.thread = thread;
    }
    
    public boolean setup() {
        errorCallbackSetup();
        if(!windowSetup())
            return false;
        //glfwContext();
        //initGL();
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
        
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }
    
    protected void glfwContext() {
        glfwMakeContextCurrent(window);     // Make the OpenGL context current
        GLContext.createFromCurrent();      // Bind lwjgl with GLFW
    }

    
    /**
     * @return the window
     */
    public synchronized long getWindow() {
        return window;
    }

    
    /**
     * @param window the window to set
     */
    public synchronized void setWindow(long window) {
        this.window = window;
    }

    
    /**
     * @return the errorCallback
     */
    public synchronized GLFWErrorCallback getErrorCallback() {
        return errorCallback;
    }

    
    /**
     * @param errorCallback the errorCallback to set
     */
    public synchronized void setErrorCallback(GLFWErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }

    
    /**
     * @return the thread
     */
    public synchronized Thread getThread() {
        return thread;
    }

    
    /**
     * @param thread the thread to set
     */
    public synchronized void setThread(Thread thread) {
        this.thread = thread;
    }
    
}
