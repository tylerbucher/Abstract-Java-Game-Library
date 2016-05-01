/**
 * 
 */
package org.ajgl.test.window;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_REFRESH_RATE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_FALSE;

import org.ajgl.Window;
import org.ajgl.concurrent.Tasker;
import org.ajgl.test.MainTest;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;


/**
 * @author Tyler
 *
 */
public class WindowTest extends Window {
    
    /**
     * Default window constructor.
     */
    public WindowTest() {
        super();
    } 
    
    /**
     * Main window constructor.
     * @param width - Display width.
     * @param height - Display height.
     * @param title - Display title.
     * @param monitor - Monitor to use.
     * @param share - Window handler to share OpenGL context with.
     */
    public WindowTest(int width, int height, String title, long monitor, long share) {
        super(width, height, title, monitor, share);
    }
    
    @Override
    public void preWindowCreation() {
        GLFW.glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);         // Keep the window hidden
        //glfwWindowHint();
        glfwWindowHint(GLFW_RESIZABLE, GL11.GL_FALSE);  // Do not allow resizing
        glfwWindowHint(GLFW_REFRESH_RATE, 60);          // Window refresh rate
    }
    
    @Override
    public void callbackSetup() {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        this.setKeyCallback(new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    Tasker.executeASyncTask("GLFW_MAIN_THREAD");
                    GLFW.glfwSetWindowShouldClose(MainTest.threadedWindowTest.getWindowHandler(), 1);
                }
            }
        });
        super.callbackSetup();
    }
    
    @Override
    public void postWindowCreation() {
        /*
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            this.getWindowHandler(),
            (GLFWvidmode.width(vidmode) - this.getWidth()) / 2,
            (GLFWvidmode.height(vidmode) - this.getHeight()) / 2
        );
        */
    }
}
