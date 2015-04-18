/**
 * 
 */
package org.ajgl.test;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;


/**
 * @author Tyler
 *
 */
public abstract class ThreadedWindow extends Thread implements Display {
    
    private int height;
    private int width;
    private String title;
    private long monitor;
    private long share;
    private long window;   // The window handler
    
    private GLFWErrorCallback errorCallback;   // callback reference instances
    
    public ThreadedWindow() {
        this.height = 800;
        this.width = 1200;
        this.title = "Abstract Java Game Library";
        this.monitor = 0;
        this.share = 0;
    } 
    
    public ThreadedWindow(int width, int height, String title, long monitor, long share, Thread thread) {
        this.height = height;
        this.width = width;
        this.title = title;
        this.monitor = monitor;
        this.share = share;
    }
    
    public boolean setup() {
        errorCallbackSetup();
        if(!windowSetup())
            return false;
        return true;
    }
    
    public abstract void run();
    
    public void startThread() {
        if(!this.isAlive()) {
            try{
                this.start();
            } catch(IllegalThreadStateException e) {
                return;
            }
        }
    }

    public void errorCallbackSetup() {
        // Setup an error callback
        GLFW.glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
    }
    
    public boolean windowSetup() {
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
    
    public void preWindowCreation() {
        GLFW.glfwDefaultWindowHints();
    }
    
    public void postWindowCreation() {
        
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
    
    @Override
    public synchronized long getWindowHandler() {
        return window;
    }   
}
