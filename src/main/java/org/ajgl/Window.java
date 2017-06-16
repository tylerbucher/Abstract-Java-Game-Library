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
     * {@link Window#window Window handler}.
     */
    private long window;

    /**
     * {@link Window} error callback instance.
     */
    private GLFWErrorCallback errorCallback;

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

    @Override
    public boolean setup() {
        errorCallbackSetup();
        return windowSetup();
    }

    @Override
    public void errorCallbackSetup() {
        // Setup an error callback
        if (errorCallback == null)
            errorCallback = GLFWErrorCallback.createPrint(System.err);
        GLFW.glfwSetErrorCallback(errorCallback);
    }

    @Override
    public boolean windowSetup() {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            errorCallback.free();
            return false;
        }
        // Setup window properties
        preWindowCreation();
        // Create the window
        window = glfwCreateWindow(width, height, title, monitor, share);
        if (window == 0) {
            errorCallback.free();
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
    public GLFWErrorCallback getErrorCallback() {
        return errorCallback;
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
    public void setErrorCallback(GLFWErrorCallback errorCallback) {
        if (this.errorCallback != null)
            this.errorCallback.free();
        this.errorCallback = errorCallback;
    }

    @Override
    public void setKeyCallback(GLFWKeyCallback keyCallback) {
        if (this.keyCallback != null)
            this.keyCallback.free();
        this.keyCallback = keyCallback;
    }

    @Override
    public void setCharCallback(GLFWCharCallback charCallback) {
        if (this.charCallback != null)
            this.charCallback.free();
        this.charCallback = charCallback;
    }

    @Override
    public void setCharModsCallback(GLFWCharModsCallback charModsCallback) {
        if (this.charModsCallback != null)
            this.charModsCallback.free();
        this.charModsCallback = charModsCallback;
    }

    @Override
    public void setMouseButtonCallback(GLFWMouseButtonCallback mouseButtonCallback) {
        if (this.mouseButtonCallback != null)
            this.mouseButtonCallback.free();
        this.mouseButtonCallback = mouseButtonCallback;
    }

    @Override
    public void setCursorPosCallback(GLFWCursorPosCallback cursorPosCallback) {
        if (this.cursorPosCallback != null)
            this.cursorPosCallback.free();
        this.cursorPosCallback = cursorPosCallback;
    }

    @Override
    public void setCursorEnterCallback(GLFWCursorEnterCallback cursorEnterCallback) {
        if (this.cursorEnterCallback != null)
            this.cursorEnterCallback.free();
        this.cursorEnterCallback = cursorEnterCallback;
    }

    @Override
    public void setScrollCallback(GLFWScrollCallback scrollCallback) {
        if (this.scrollCallback != null)
            this.scrollCallback.free();
        this.scrollCallback = scrollCallback;
    }

    @Override
    public void setDropCallback(GLFWDropCallback dropCallback) {
        if (this.dropCallback != null)
            this.dropCallback.free();
        this.dropCallback = dropCallback;
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
}
