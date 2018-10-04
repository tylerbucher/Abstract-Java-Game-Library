/*
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

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;

/**
 * This class is designed to to provide an independent module for window creation.
 *
 * @author Tyler Bucher
 */
public class Window implements Display {

    /**
     * {@link Window} height.
     */
    private int height;

    /**
     * {@link Window} width.
     */
    private int width;

    /**
     * {@link Window} title.
     */
    private String title;

    /**
     * {@link Window} monitor number.
     */
    private long monitor;

    /**
     * {@link Window#window Window handler} to share OpenGL context with.
     */
    private long share;

    /**
     * {@link Window Window} handler.
     */
    private long window;

    /**
     * {@link Window} window pos callback instance.
     */
    private GLFWWindowPosCallback windowPosCallback;

    /**
     * {@link Window} window size callback instance.
     */
    private GLFWWindowSizeCallback windowSizeCallback;

    /**
     * {@link Window} window close callback instance.
     */
    private GLFWWindowCloseCallback windowCloseCallback;

    /**
     * {@link Window} window refresh callback instance.
     */
    private GLFWWindowRefreshCallback windowRefreshCallback;

    /**
     * {@link Window} window focus callback instance.
     */
    private GLFWWindowFocusCallback windowFocusCallback;

    /**
     * {@link Window} window iconify callback instance.
     */
    private GLFWWindowIconifyCallback windowIconifyCallback;

    /**
     * {@link Window} window maximize callback instance.
     */
    private GLFWWindowMaximizeCallback windowMaximizeCallback;

    /**
     * {@link Window} frame buffer size callback instance.
     */
    private GLFWFramebufferSizeCallback framebufferSizeCallback;

    /**
     * {@link Window} key callback instance.
     */
    private GLFWKeyCallback keyCallback;

    /**
     * {@link Window} char callback instance.
     */
    private GLFWCharCallback charCallback;

    /**
     * {@link Window} char modifications callback instance.
     */
    private GLFWCharModsCallback charModsCallback;

    /**
     * {@link Window} mouse button callback instance.
     */
    private GLFWMouseButtonCallback mouseButtonCallback;

    /**
     * {@link Window} cursor position callback instance.
     */
    private GLFWCursorPosCallback cursorPosCallback;

    /**
     * {@link Window} cursor enter callback instance.
     */
    private GLFWCursorEnterCallback cursorEnterCallback;

    /**
     * {@link Window} scroll callback instance.
     */
    private GLFWScrollCallback scrollCallback;

    /**
     * {@link Window} drop callback instance.
     */
    private GLFWDropCallback dropCallback;

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
     *
     * @param width   width of the {@link Window}.
     * @param height  height of the {@link Window}.
     * @param title   title of the {@link Window}.
     * @param monitor monitor for the {@link Window} to use.
     * @param share   {@link Window} handler to share OpenGL context with.
     */
    public Window(int width, int height, String title, long monitor, long share) {
        this.height = height;
        this.width = width;
        this.title = title;
        this.monitor = monitor;
        this.share = share;
    }

    /**
     * Must be called from the main thread.
     *
     * @return true if the {@link Display} was created false otherwise.
     */
    @Override
    public boolean setup() {
        return windowSetup();
    }

    /**
     * Must be called from the main thread.
     *
     * @return true if the display was created false otherwise.
     */
    @Override
    public boolean windowSetup() {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            return false;
        }
        // Setup window properties
        preWindowCreation();
        // Create the window
        window = glfwCreateWindow(width, height, title, monitor, share);
        if (window == 0) {
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
        GLFW.glfwSetWindowPosCallback(window, windowPosCallback);
        GLFW.glfwSetWindowSizeCallback(window, windowSizeCallback);
        GLFW.glfwSetWindowCloseCallback(window, windowCloseCallback);
        GLFW.glfwSetWindowRefreshCallback(window, windowRefreshCallback);
        GLFW.glfwSetWindowFocusCallback(window, windowFocusCallback);
        GLFW.glfwSetWindowIconifyCallback(window, windowIconifyCallback);
        GLFW.glfwSetWindowMaximizeCallback(window, windowMaximizeCallback);
        GLFW.glfwSetFramebufferSizeCallback(window, framebufferSizeCallback);
    }

    @Override
    public void postWindowCreation() {
    }

    @Override
    public GLFWWindowPosCallback getWindowPosCallback() {
        return windowPosCallback;
    }

    @Override
    public GLFWWindowSizeCallback getWindowSizeCallback() {
        return windowSizeCallback;
    }

    @Override
    public GLFWWindowCloseCallback getWindowCloseCallback() {
        return windowCloseCallback;
    }

    @Override
    public GLFWWindowRefreshCallback getWindowRefreshCallback() {
        return windowRefreshCallback;
    }

    @Override
    public GLFWWindowFocusCallback getWindowFocusCallback() {
        return windowFocusCallback;
    }

    @Override
    public GLFWWindowIconifyCallback getWindowIconifyCallback() {
        return windowIconifyCallback;
    }

    @Override
    public GLFWWindowMaximizeCallback getWindowMaximizeCallback() {
        return windowMaximizeCallback;
    }

    @Override
    public GLFWFramebufferSizeCallback getFramebufferSizeCallback() {
        return framebufferSizeCallback;
    }

    @Override
    public GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }

    @Override
    public GLFWCharCallback getCharCallback() {
        return charCallback;
    }

    @Override
    public GLFWCharModsCallback getCharModsCallback() {
        return charModsCallback;
    }

    @Override
    public GLFWMouseButtonCallback getMouseButtonCallback() {
        return mouseButtonCallback;
    }

    @Override
    public GLFWCursorPosCallback getCursorPosCallback() {
        return cursorPosCallback;
    }

    @Override
    public GLFWCursorEnterCallback getCursorEnterCallback() {
        return cursorEnterCallback;
    }

    @Override
    public GLFWScrollCallback getScrollCallback() {
        return scrollCallback;
    }

    @Override
    public GLFWDropCallback getDropCallback() {
        return dropCallback;
    }

    @Override
    public void setWindowPosCallback(GLFWWindowPosCallback windowPosCallback) {
        if (this.windowPosCallback != null)
            this.windowPosCallback.free();
        GLFW.glfwSetWindowPosCallback(window, this.windowPosCallback = windowPosCallback);
    }

    @Override
    public void setWindowSizeCallback(GLFWWindowSizeCallback windowSizeCallback) {
        if (this.windowSizeCallback != null)
            this.windowSizeCallback.free();
        GLFW.glfwSetWindowSizeCallback(window, this.windowSizeCallback = windowSizeCallback);
    }

    @Override
    public void setWindowCloseCallback(GLFWWindowCloseCallback windowCloseCallback) {
        if (this.windowCloseCallback != null)
            this.windowCloseCallback.free();
        GLFW.glfwSetWindowCloseCallback(window, this.windowCloseCallback = windowCloseCallback);
    }

    @Override
    public void setWindowRefreshCallback(GLFWWindowRefreshCallback windowRefreshCallback) {
        if (this.windowRefreshCallback != null)
            this.windowRefreshCallback.free();
        GLFW.glfwSetWindowRefreshCallback(window, this.windowRefreshCallback = windowRefreshCallback);
    }

    @Override
    public void setWindowFocusCallback(GLFWWindowFocusCallback windowFocusCallback) {
        if (this.windowFocusCallback != null)
            this.windowFocusCallback.free();
        GLFW.glfwSetWindowFocusCallback(window, this.windowFocusCallback = windowFocusCallback);
    }

    @Override
    public void setWindowIconifyCallback(GLFWWindowIconifyCallback windowIconifyCallback) {
        if (this.windowIconifyCallback != null)
            this.windowIconifyCallback.free();
        GLFW.glfwSetWindowIconifyCallback(window, this.windowIconifyCallback = windowIconifyCallback);
    }

    @Override
    public void setWindowMaximizeCallback(GLFWWindowMaximizeCallback windowMaximizeCallback) {
        if (this.windowMaximizeCallback != null)
            this.windowMaximizeCallback.free();
        GLFW.glfwSetWindowMaximizeCallback(window, this.windowMaximizeCallback = windowMaximizeCallback);
    }

    @Override
    public void setFramebufferSizeCallback(GLFWFramebufferSizeCallback framebufferSizeCallback) {
        if (this.framebufferSizeCallback != null)
            this.framebufferSizeCallback.free();
        GLFW.glfwSetFramebufferSizeCallback(window, this.framebufferSizeCallback = framebufferSizeCallback);
    }

    @Override
    public void setKeyCallback(GLFWKeyCallback keyCallback) {
        if (this.keyCallback != null)
            this.keyCallback.free();
        GLFW.glfwSetKeyCallback(window, this.keyCallback = keyCallback);
    }

    @Override
    public void setCharCallback(GLFWCharCallback charCallback) {
        if (this.charCallback != null)
            this.charCallback.free();
        this.charCallback = charCallback;
        GLFW.glfwSetCharCallback(window, charCallback);
    }

    @Override
    public void setCharModsCallback(GLFWCharModsCallback charModsCallback) {
        if (this.charModsCallback != null)
            this.charModsCallback.free();
        GLFW.glfwSetCharModsCallback(window, this.charModsCallback = charModsCallback);
    }

    @Override
    public void setMouseButtonCallback(GLFWMouseButtonCallback mouseButtonCallback) {
        if (this.mouseButtonCallback != null)
            this.mouseButtonCallback.free();
        GLFW.glfwSetMouseButtonCallback(window, this.mouseButtonCallback = mouseButtonCallback);
    }

    @Override
    public void setCursorPosCallback(GLFWCursorPosCallback cursorPosCallback) {
        if (this.cursorPosCallback != null)
            this.cursorPosCallback.free();
        GLFW.glfwSetCursorPosCallback(window, this.cursorPosCallback = cursorPosCallback);
    }

    @Override
    public void setCursorEnterCallback(GLFWCursorEnterCallback cursorEnterCallback) {
        if (this.cursorEnterCallback != null)
            this.cursorEnterCallback.free();
        GLFW.glfwSetCursorEnterCallback(window, this.cursorEnterCallback = cursorEnterCallback);
    }

    @Override
    public void setScrollCallback(GLFWScrollCallback scrollCallback) {
        if (this.scrollCallback != null)
            this.scrollCallback.free();
        GLFW.glfwSetScrollCallback(window, this.scrollCallback = scrollCallback);
    }

    @Override
    public void setDropCallback(GLFWDropCallback dropCallback) {
        if (this.dropCallback != null)
            this.dropCallback.free();
        GLFW.glfwSetDropCallback(window, this.dropCallback = dropCallback);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setWindowSize(int width, int height) {
        GLFW.glfwSetWindowSize(window, this.width = width, this.height = height);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(window, title);
    }

    @Override
    public long getMonitor() {
        return monitor;
    }

    @Override
    public long getShare() {
        return share;
    }

    @Override
    public long getWindowHandler() {
        return window;
    }

    @Override
    public void destroyWindow() {
        GLFW.glfwDestroyWindow(window);
    }

    @Override
    public void finalize() {
        GLFW.glfwDestroyWindow(window);
        Callbacks.glfwFreeCallbacks(window);
    }
}
