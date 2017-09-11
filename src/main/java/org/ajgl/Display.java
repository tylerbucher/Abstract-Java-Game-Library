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

/**
 * This class is designed to provide a abstract display reference.
 *
 * @author Tyler Bucher
 */
public interface Display {

    /**
     * @return true if the {@link Display} was created false otherwise.
     */
    boolean setup();

    /**
     * Error call back setup.
     */
    void errorCallbackSetup();

    /**
     * @return true if the display was created false otherwise.
     */
    boolean windowSetup();

    /**
     * Pre-window creation.
     */
    void preWindowCreation();

    /**
     * Setup all callbacks for the window. Should be called right before {@link #postWindowCreation()}.
     */
    void callbackSetup();

    /**
     * Executes after the created window is returned from {@link GLFW}.
     */
    void postWindowCreation();

    /**
     * @return the {@link Display}'s {@link GLFWErrorCallback}.
     */
    GLFWErrorCallback getErrorCallback();

    /**
     * @return the {@link Display}'s {@link GLFWKeyCallback}..
     */
    GLFWKeyCallback getKeyCallback();

    /**
     * @return the {@link Display}'s {@link GLFWCharCallback}.
     */
    GLFWCharCallback getCharCallback();

    /**
     * @return the {@link Display}'s {@link GLFWCharModsCallback}.
     */
    GLFWCharModsCallback getCharModsCallback();

    /**
     * @return the {@link Display}'s {@link GLFWMouseButtonCallback}.
     */
    GLFWMouseButtonCallback getMouseButtonCallback();

    /**
     * @return the {@link Display}'s {@link GLFWCursorPosCallback}.
     */
    GLFWCursorPosCallback getCursorPosCallback();

    /**
     * @return the {@link Display}'s {@link GLFWCursorEnterCallback}.
     */
    GLFWCursorEnterCallback getCursorEnterCallback();

    /**
     * @return the {@link Display}'s {@link GLFWScrollCallback}.
     */
    GLFWScrollCallback getScrollCallback();

    /**
     * @return the {@link Display}'s {@link GLFWDropCallback}.
     */
    GLFWDropCallback getDropCallback();

    /**
     * Sets the {@link Display}'s {@link GLFWErrorCallback}.
     *
     * @param errorCallback the {@link GLFWErrorCallback} of the {@link Display}.
     */
    void setErrorCallback(GLFWErrorCallback errorCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWKeyCallback}.
     *
     * @param keyCallback the {@link GLFWKeyCallback} of the {@link Display}.
     */
    void setKeyCallback(GLFWKeyCallback keyCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWCharCallback}.
     *
     * @param charCallback the {@link GLFWCharCallback} of the {@link Display}.
     */
    void setCharCallback(GLFWCharCallback charCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWCharModsCallback}.
     *
     * @param charModsCallback the {@link GLFWCharModsCallback} of the {@link Display}.
     */
    void setCharModsCallback(GLFWCharModsCallback charModsCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWMouseButtonCallback}.
     *
     * @param mouseButtonCallback the {@link GLFWMouseButtonCallback} of the {@link Display}.
     */
    void setMouseButtonCallback(GLFWMouseButtonCallback mouseButtonCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWCursorPosCallback}.
     *
     * @param cursorPosCallback the {@link GLFWCursorPosCallback} of the {@link Display}.
     */
    void setCursorPosCallback(GLFWCursorPosCallback cursorPosCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWCursorEnterCallback}.
     *
     * @param cursorEnterCallback the {@link GLFWCursorEnterCallback} of the {@link Display}.
     */
    void setCursorEnterCallback(GLFWCursorEnterCallback cursorEnterCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWScrollCallback}.
     *
     * @param scrollCallback the {@link GLFWScrollCallback} of the {@link Display}.
     */
    void setScrollCallback(GLFWScrollCallback scrollCallback);

    /**
     * Sets the {@link Display}'s {@link GLFWDropCallback}.
     *
     * @param dropCallback the {@link GLFWDropCallback} of the {@link Display}.
     */
    void setDropCallback(GLFWDropCallback dropCallback);

    /**
     * @return the height of the {@link Display}.
     */
    int getHeight();

    /**
     * @return the width of the {@link Display}.
     */
    int getWidth();

    /**
     * Sets the height of the {@link Display}.
     *
     * @param height the height of the {@link Display}.
     */
    void setHeight(int height);

    /**
     * Sets the width of the {@link Display}.
     *
     * @param width the width of the {@link Display}.
     */
    void setWidth(int width);

    /**
     * Sets the {@link Display}s width and height.
     *
     * @param width  the width of the {@link Display}.
     * @param height the height of the {@link Display}.
     */
    void setWindowSize(int width, int height);

    /**
     * @return the title of the {@link Display}.
     */
    String getTitle();

    /**
     * Sets the {@link Display}s title.
     *
     * @param title the new title of the {@link Display}.
     */
    void setTitle(String title);

    /**
     * @return the monitor that the {@link Display} is on.
     */
    long getMonitor();

    /**
     * @return the window handler that this display is sharing a OpenGL context with.
     */
    long getShare();

    /**
     * @return the window handler id.
     */
    long getWindowHandler();
}
