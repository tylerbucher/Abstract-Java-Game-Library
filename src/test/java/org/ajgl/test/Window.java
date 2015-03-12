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
    
    private int height;
    private int width;
    private String title;
    private long monitor;
    private long share;
    private long window;   // The window handler
    
    private GLFWErrorCallback errorCallback;   // callback reference instances
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
        return true;
    }
    
    public void startThread() {
        if(!thread.isAlive()) {
            try{
                thread.start();
            } catch(IllegalThreadStateException e) {
                return;
            }
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
        preWindowCreation();
        
        // Create the window
        window = glfwCreateWindow(width, height, title, monitor, share);
        if (window == 0) {
            errorCallback.release();
            return false;
        }
        
        // Setup window position
        postWindowCreation();
        
        return true;
    }
    
    protected void preWindowCreation() {
        GLFW.glfwDefaultWindowHints();
    }
    
    protected void postWindowCreation() {
        
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
        if(!this.errorCallback.isDestroyed())
            this.errorCallback.release();
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
        if(this.thread != null)
            this.thread.interrupt();
        this.thread = thread;
    }
    
    /**
     * @return the height
     */
    public synchronized int getHeight() {
        return height;
    }
    
    /**
     * @return the width
     */
    public synchronized int getWidth() {
        return width;
    }
    
    public synchronized void setWindowSize(int width, int height) {
        GLFW.glfwSetWindowSize(window, width, height);
    }
    
    /**
     * @return the title
     */
    public synchronized String getTitle() {
        return title;
    }
    
    public synchronized void setTitle(String title) {
        GLFW.glfwSetWindowTitle(window, title);
    }
    
    /**
     * @return the monitor
     */
    public synchronized long getMonitor() {
        return monitor;
    }
    
    /**
     * @return the share
     */
    public synchronized long getShare() {
        return share;
    }
    
    /**
     * @return the window
     */
    public synchronized long getWindow() {
        return window;
    }
    
}
