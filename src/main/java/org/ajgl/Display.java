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

import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWCharModsCallback;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;


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
     * General call back setup.
     */
    public void callbackSetup();
    
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
     * Gets the current key callback.
     * @return The current key callback.
     */
    public GLFWKeyCallback getKeyCallback();
    
    /**
     * Gets the current char callback.
     * @return The current char callback.
     */
    public GLFWCharCallback getCharCallback();
    
    /**
     * Gets the current char modifications callback.
     * @return The current char modifications callback.
     */
    public GLFWCharModsCallback getCharModsCallback();
    
    /**
     * Gets the current mouse button callback.
     * @return The current mouse button callback.
     */
    public GLFWMouseButtonCallback getMouseButtonCallback();
    
    /**
     * Gets the current cursor position callback.
     * @return The current cursor position callback.
     */
    public GLFWCursorPosCallback getCursorPosCallback();
    
    /**
     * Gets the current cursor enter callback.
     * @return The current cursor enter callback.
     */
    public GLFWCursorEnterCallback getCursorEnterCallback();
    
    /**
     * Gets the current scroll callback.
     * @return The current scroll callback.
     */
    public GLFWScrollCallback getScrollCallback();
    
    /**
     * Gets the current drop callback.
     * @return The current drop callback.
     */
    public GLFWDropCallback getDropCallback();
    
    /**
     * Sets the displays error callback.
     * @param errorCallback - The error Callback to set.
     */
    public void setErrorCallback(GLFWErrorCallback errorCallback);
    
    /**
     * Sets the displays key callback.
     * @param keyCallback - The key callback to be set.
     */
    public void setKeyCallback(GLFWKeyCallback keyCallback);
    
    /**
     * Sets the displays char callback.
     * @param charCallback - The char callback to be set.
     */
    public void setCharCallback(GLFWCharCallback charCallback);
    
    /**
     * Sets the displays char modifications callback.
     * @param charModsCallback - The char modifications callback to be set.
     */
    public void setCharModsCallback(GLFWCharModsCallback charModsCallback);
    
    /**
     * Sets the displays mouse button callback.
     * @param mouseButtonCallback - The mouse button callback to be set.
     */
    public void setMouseButtonCallback(GLFWMouseButtonCallback mouseButtonCallback);
    
    /**
     * Sets the displays cursor position callback.
     * @param cursorPosCallback - The cursor position callback to be set.
     */
    public void setCursorPosCallback(GLFWCursorPosCallback cursorPosCallback);
    
    /**
     * Sets the displays cursor enter callback.
     * @param cursorEnterCallback - The cursor enter callback to be set.
     */
    public void setCursorEnterCallback(GLFWCursorEnterCallback cursorEnterCallback);
    
    /**
     * Sets the displays scroll callback.
     * @param scrollCallback - The scroll callback to be set.
     */
    public void setScrollCallback(GLFWScrollCallback scrollCallback);
    
    /**
     * Sets the displays drop callback.
     * @param dropCallback - The drop callback to be set.
     */
    public void setDropCallback(GLFWDropCallback dropCallback);
    
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
