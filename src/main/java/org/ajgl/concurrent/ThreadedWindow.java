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

package org.ajgl.concurrent;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import org.ajgl.Display;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;


/**
 * @author Tyler
 *
 */
public abstract class ThreadedWindow extends Thread implements Display {
    
    private int height;     // Display height
    private int width;      // Display width
    private String title;   // Display title
    private long monitor;   // Monitor to use
    private long share;     // Window handler to share OpenGL context with
    private long window;    // The window handler
    
    private GLFWErrorCallback errorCallback;    // error callback reference instance
    private GLFWKeyCallback   keyCallback;      // key callback reference instance
    
    /**
     * Default window constructor.
     */
    public ThreadedWindow() {
        this.height = 800;
        this.width = 1200;
        this.title = "Abstract Java Game Library";
        this.monitor = 0;
        this.share = 0;
    } 
    
    /**
     * Main window constructor.
     * @param width - Display width.
     * @param height - Display height.
     * @param title - Display title.
     * @param monitor - Monitor to use.
     * @param share - Window handler to share OpenGL context with.
     */
    public ThreadedWindow(int width, int height, String title, long monitor, long share) {
        this.height = height;
        this.width = width;
        this.title = title;
        this.monitor = monitor;
        this.share = share;
    }
    
    @Override
    public boolean setup() {
        errorCallbackSetup();
        if(!windowSetup())
            return false;
        return true;
    }
    
    @Override
    public void errorCallbackSetup() {
        // Setup an error callback
        if(errorCallback == null)
            errorCallback = errorCallbackPrint(System.err);
        GLFW.glfwSetErrorCallback(errorCallback);
    }
    
    @Override
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
    
    @Override
    public void preWindowCreation() {
        GLFW.glfwDefaultWindowHints();
    }
    
    @Override
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
    
    @Override
    public void postWindowCreation() {
        
    }
    
    @Override
    public synchronized GLFWErrorCallback getErrorCallback() {
        return errorCallback;
    }
    
    @Override
    public synchronized void setErrorCallback(GLFWErrorCallback errorCallback) {
        if(this.errorCallback != null)
            if(!this.errorCallback.isDestroyed())
                this.errorCallback.release();
        this.errorCallback = errorCallback;
    }
    
    @Override
    public synchronized GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }
    
    @Override
    public synchronized void setKeyCallback(GLFWKeyCallback keyCallback) {
        if(this.keyCallback != null)
            if(!this.keyCallback.isDestroyed())
                this.keyCallback.release();
        this.keyCallback = keyCallback;
    }
    
    public void preInitGL() {
        
    }
    
    public abstract void initGL();
    
    public void init() {
        
    }
    
    @Override
    public abstract void run();
    
    @Override
    public synchronized int getHeight() {
        return height;
    }
    
    @Override
    public synchronized int getWidth() {
        return width;
    }
    
    @Override
    public synchronized void setWindowSize(int width, int height) {
        GLFW.glfwSetWindowSize(window, width, height);
    }
    
    @Override
    public synchronized String getTitle() {
        return title;
    }
    
    @Override
    public synchronized void setTitle(String title) {
        GLFW.glfwSetWindowTitle(window, title);
    }
    
    @Override
    public synchronized long getMonitor() {
        return monitor;
    }
    
    @Override
    public synchronized long getShare() {
        return share;
    }
    
    @Override
    public synchronized long getWindowHandler() {
        return window;
    }
    
}
