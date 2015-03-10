/**
 * 
 */
package org.ajgl.test;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_REFRESH_RATE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public abstract class Window {
    
    private int HEIGHT = 800;
    private int WIDTH = 1200;
    private int RESIZABLE = GL11.GL_FALSE;
    private int REFRESH_RATE = 60;
    private int VSYNC = 1;
    private String TITLE = "AJGL TEST";
    // The window handler
    public long window;
    
    // callback reference instances
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    
    public Window(float x, float y) {
        System.out.println("callback");
        errorCallbackSetup();
        System.out.println("window");
        windowSetup();
        System.out.println("callback 2");
        callbackSetup();
        System.out.println("glfw context");
        glfwContext();
    }
    
    private void errorCallbackSetup() {
        // Setup an error callback
        GLFW.glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
    }
    
    private boolean windowSetup() {
        // Initialize GLFW
        if (GLFW.glfwInit() != 1) {
            errorCallback.release();
            return false;
        }
        
        // Setup window properties
        windowHintSetup();
        
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
        if (window == 0) {
            errorCallback.release();
            return false;
        }
        
        // Setup window position
        windowPosition();
        
        return true;
    }
    
    private void windowHintSetup() {
        // Configure Window Properties
        glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // Keep the window hidden
        glfwWindowHint(GLFW_RESIZABLE, RESIZABLE); // Do not allow resizing
        glfwWindowHint(GLFW_REFRESH_RATE, REFRESH_RATE); // Refresh rate
    }
    
    private void windowPosition() {
     // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (GLFWvidmode.width(vidmode) - WIDTH) / 2,
            (GLFWvidmode.height(vidmode) - HEIGHT) / 2
        );
    }
    
    public void closeGLFW() {
        glfwTerminate();
        errorCallback.release();
    }
    
    private abstract void callbackSetup(); 
//    {
//        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
//        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
//            @Override
//            public void invoke(long window, int key, int scancode, int action, int mods) {//TODO Dispatch key events
//                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
//                    glfwSetWindowShouldClose(window, GL_TRUE); 
//            }
//        });
//    }
    
    public abstract void initGL(); 
//    {
//        // Initialize openGl
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        //GL11.glOrtho(0, WIDTH, 0, HEIGHT, WIDTH, -HEIGHT);
//        GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        
//        // Enable alpha transparency (for overlay image)
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
//        GL11.glDepthFunc(GL11.GL_LEQUAL);
//    }
    
    public void glfwContext() {
        glfwMakeContextCurrent(window);     // Make the OpenGL context current
        glfwShowWindow(window);             // Make the window visible
        GLContext.createFromCurrent();      // Bind lwjgl with GLFW
    }
}
