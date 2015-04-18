/**
 * 
 */
package org.ajgl.test;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import org.ajgl.concurrent.Tasker;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;


/**
 * @author Tyler
 *
 */
public class Window implements Display{
    
    private int height;
    private int width;
    private String title;
    private long monitor;
    private long share;
    private long window;   // The window handler
    
    private GLFWErrorCallback errorCallback;   // callback reference instances
    private GLFWKeyCallback   keyCallback;
    
    public Window() {
        this.height = 800;
        this.width = 1200;
        this.title = "Abstract Java Game Library";
        this.monitor = 0;
        this.share = 0;
    } 
    
    public Window(int width, int height, String title, long monitor, long share) {
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

    public void errorCallbackSetup() {
        // Setup an error callback
        if(errorCallback == null)
            errorCallback = errorCallbackPrint(System.err);
        GLFW.glfwSetErrorCallback(errorCallback);
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
        // Callback setup
        keyCallbackSetup();
        // Setup window position
        postWindowCreation();
        return true;
    }
    
    public void preWindowCreation() {
        GLFW.glfwDefaultWindowHints();
    }
    
    public void keyCallbackSetup() {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        if(keyCallback == null)
            keyCallback = new GLFWKeyCallback() {
                @Override
                public void invoke(long window, int key, int scancode, int action, int mods) {
                    
                }
            };
        glfwSetKeyCallback(window, keyCallback);
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
        if(this.errorCallback != null)
            if(!this.errorCallback.isDestroyed())
                this.errorCallback.release();
        this.errorCallback = errorCallback;
    }
    
    public synchronized GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }
    
    public synchronized void setKeyCallback(GLFWKeyCallback keyCallback) {
        if(this.keyCallback != null)
            if(!this.keyCallback.isDestroyed())
                this.keyCallback.release();
        this.keyCallback = keyCallback;
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
