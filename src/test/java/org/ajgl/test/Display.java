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
public interface Display {
    
    public boolean setup();

    public void errorCallbackSetup();
    
    public boolean windowSetup();
    
    public void preWindowCreation()
    
    public void postWindowCreation();
    
    /**
     * @return the errorCallback
     */
    public GLFWErrorCallback getErrorCallback();
    
    /**
     * @param errorCallback the errorCallback to set
     */
    public void setErrorCallback(GLFWErrorCallback errorCallback);
    
    /**
     * @return the height
     */
    public int getHeight();
    
    /**
     * @return the width
     */
    public int getWidth();
    
    public void setWindowSize(int width, int height);
    
    /**
     * @return the title
     */
    public String getTitle();
    
    public void setTitle(String title);
    
    /**
     * @return the monitor
     */
    public long getMonitor();
    
    /**
     * @return the share
     */
    public long getShare();
    
    /**
     * @return the window
     */
    public long getWindowHandler();
}
