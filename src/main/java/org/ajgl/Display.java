/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Tyler Bucher
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.ajgl;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;


/**
 * This class is designed to provide a abstract display reference.
 * @author Tyler Bucher
 */
public interface Display {
    
    /**
     * Display setup.
     * @return True if the display was created false otherwise.
     */
    public boolean setup();
    
    /**
     * Error call back setup.
     */
    public void errorCallbackSetup();
    
    /**
     * Window setup.
     * @return True if the display was created false otherwise.
     */
    public boolean windowSetup();
    
    /**
     * Pre window creation.
     */
    public void preWindowCreation();
    
    /**
     * Key call back setup.
     */
    public void keyCallbackSetup();
    
    /**
     * Post window creation.
     */
    public void postWindowCreation();
    
    /**
     * Gets the current error callback.
     * @return The current error Callback.
     */
    public GLFWErrorCallback getErrorCallback();
    
    /**
     * Sets the displays error callback.
     * @param errorCallback - The error Callback to set.
     */
    public void setErrorCallback(GLFWErrorCallback errorCallback);
    
    /**
     * Gets the current key callback.
     * @return The current key callback.
     */
    public GLFWKeyCallback getKeyCallback();
    
    /**
     * Sets the displays key callback.
     * @param keyCallback - The key callback to be set.
     */
    public void setKeyCallback(GLFWKeyCallback keyCallback);
    
    /**
     * Returns the height of the display.
     * @return The height.
     */
    public int getHeight();
    
    /**
     * Returns the width of the display.
     * @return The width.
     */
    public int getWidth();
    
    /**
     * Sets the displays width and height.
     * @param width - The new width.
     * @param height - The new height.
     */
    public void setWindowSize(int width, int height);
    
    /**
     * Returns the title of the display.
     * @return The title.
     */
    public String getTitle();
    
    /**
     * Sets the displays title.
     * @param title - The new title to be set.
     */
    public void setTitle(String title);
    
    /**
     * Return the monitor that the display is on.
     * @return The monitor.
     */
    public long getMonitor();
    
    /**
     * Return the window handler that this display is sharing a 
     * OpenGL context with.
     * @return The share context handler.
     */
    public long getShare();
    
    /**
     * Returns the displays window handler.
     * @return The window handler.
     */
    public long getWindowHandler();
}
