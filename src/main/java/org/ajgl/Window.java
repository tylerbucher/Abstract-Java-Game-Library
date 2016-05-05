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

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;

import org.lwjgl.glfw.GLFW;
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
 * This class is designed to to provide an independent module
 * for window creation. 
 * @author Tyler Bucher
 */
public class Window implements Display {
    
    private int    height;      // Display height
    private int    width;       // Display width
    private String title;       // Display title
    private long   monitor;     // Monitor to use
    private long   share;       // Window handler to share OpenGL context with
    private long   window;      // The window handler
    
    private GLFWErrorCallback 		errorCallback;    		// error callback reference instance
    private GLFWKeyCallback   		keyCallback;      		// key callback reference instance
    private GLFWCharCallback		charCallback;			// char callback reference instance
    private GLFWCharModsCallback	charModsCallback;		// char modifications callback reference instance
    private GLFWMouseButtonCallback mouseButtonCallback;	// mouse button callback reference instance
    private GLFWCursorPosCallback 	cursorPosCallback;		// cursor position callback reference instance
    private GLFWCursorEnterCallback cursorEnterCallback;	// cursor enter callback reference instance
    private GLFWScrollCallback		scrollCallback;			// scroll callback reference instance
    private GLFWDropCallback		dropCallback;			// drop callback reference instance
    
    /**
     * Default window constructor.
     */
    public Window() {
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
    public Window(int width, int height, String title, long monitor, long share) {
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
            errorCallback = GLFWErrorCallback.createPrint(System.err);
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
        callbackSetup();
        // Setup window position
        postWindowCreation();
        return true;
    }
    
    @Override
    public void preWindowCreation() {
        GLFW.glfwDefaultWindowHints();
    }
    
    @Override
    public void callbackSetup() {
        GLFW.glfwSetKeyCallback(window, keyCallback);
        GLFW.glfwSetCharCallback(window, charCallback);
        GLFW.glfwSetCharModsCallback(window, charModsCallback);
        GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallback);
        GLFW.glfwSetCursorPosCallback(window, cursorPosCallback);
        GLFW.glfwSetCursorEnterCallback(window, cursorEnterCallback);
        GLFW.glfwSetScrollCallback(window, scrollCallback);
        GLFW.glfwSetDropCallback(window, dropCallback);
    }
    
    @Override
    public void postWindowCreation() {
        
    }
    
    @Override
    public synchronized GLFWErrorCallback getErrorCallback() {
        return errorCallback;
    }
    
    @Override
    public synchronized GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }
    
    @Override
    public synchronized GLFWCharCallback getCharCallback() {
		return charCallback;
	}
    
    @Override
	public synchronized GLFWCharModsCallback getCharModsCallback() {
		return charModsCallback;
	}
    
    @Override
	public synchronized GLFWMouseButtonCallback getMouseButtonCallback() {
		return mouseButtonCallback;
	}
    
    @Override
	public synchronized GLFWCursorPosCallback getCursorPosCallback() {
		return cursorPosCallback;
	}
    
    @Override
	public synchronized GLFWCursorEnterCallback getCursorEnterCallback() {
		return cursorEnterCallback;
	}
    
    @Override
	public synchronized GLFWScrollCallback getScrollCallback() {
		return scrollCallback;
	}
    
    @Override
	public synchronized GLFWDropCallback getDropCallback() {
		return dropCallback;
	}
    
    @Override
    public synchronized void setErrorCallback(GLFWErrorCallback errorCallback) {
        if(this.errorCallback != null)
            if(!this.errorCallback.isDestroyed())
                this.errorCallback.release();
        this.errorCallback = errorCallback;
    }

	@Override
    public synchronized void setKeyCallback(GLFWKeyCallback keyCallback) {
        if(this.keyCallback != null)
            if(!this.keyCallback.isDestroyed())
                this.keyCallback.release();
        this.keyCallback = keyCallback;
    }
    
	@Override
    public synchronized void setCharCallback(GLFWCharCallback charCallback) {
		if(this.charCallback != null)
            if(!this.charCallback.isDestroyed())
                this.charCallback.release();
		this.charCallback = charCallback;
	}
	
	@Override
	public synchronized void setCharModsCallback(GLFWCharModsCallback charModsCallback) {
		if(this.charModsCallback != null)
            if(!this.charModsCallback.isDestroyed())
                this.charModsCallback.release();
		this.charModsCallback = charModsCallback;
	}
	
	@Override
	public synchronized void setMouseButtonCallback(GLFWMouseButtonCallback mouseButtonCallback) {
		if(this.mouseButtonCallback != null)
            if(!this.mouseButtonCallback.isDestroyed())
                this.mouseButtonCallback.release();
		this.mouseButtonCallback = mouseButtonCallback;
	}
	
	@Override
	public synchronized void setCursorPosCallback(GLFWCursorPosCallback cursorPosCallback) {
		if(this.cursorPosCallback != null)
            if(!this.cursorPosCallback.isDestroyed())
                this.cursorPosCallback.release();
		this.cursorPosCallback = cursorPosCallback;
	}
	
	@Override
	public synchronized void setCursorEnterCallback(GLFWCursorEnterCallback cursorEnterCallback) {
		if(this.cursorEnterCallback != null)
            if(!this.cursorEnterCallback.isDestroyed())
                this.cursorEnterCallback.release();
		this.cursorEnterCallback = cursorEnterCallback;
	}
	
	@Override
	public synchronized void setScrollCallback(GLFWScrollCallback scrollCallback) {
		if(this.scrollCallback != null)
            if(!this.scrollCallback.isDestroyed())
                this.scrollCallback.release();
		this.scrollCallback = scrollCallback;
	}
	
	@Override
	public synchronized void setDropCallback(GLFWDropCallback dropCallback) {
		if(this.dropCallback != null)
            if(!this.dropCallback.isDestroyed())
                this.dropCallback.release();
		this.dropCallback = dropCallback;
	}

	@Override
    public synchronized int getHeight() {
        return height;
    }
    
    @Override
    public synchronized int getWidth() {
        return width;
    }
    
    @Override
    public synchronized void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public synchronized void setWidth(int width) {
        this.width = width;
    }
    
    @Override
    public synchronized void setWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
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
