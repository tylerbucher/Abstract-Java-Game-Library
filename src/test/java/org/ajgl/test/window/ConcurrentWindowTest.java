/**
 * 
 */
package org.ajgl.test.window;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glClear;

import org.ajgl.concurrent.Task;
import org.ajgl.concurrent.Tasker;
import org.ajgl.concurrent.ThreadedWindow;
import org.ajgl.test.graphics.GraphicsTest;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public class ConcurrentWindowTest extends ThreadedWindow {
    
    /**
     * Default window constructor.
     */
    public ConcurrentWindowTest() {
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
    public ConcurrentWindowTest(int width, int height, String title, long monitor, long share) {
        super(width, height, title, monitor, share);
    }
    
    @Override
    public void initGL() {
        // Bind context
        glfwMakeContextCurrent(this.getWindowHandler());     // Make the OpenGL context current
        glfwShowWindow(this.getWindowHandler());             // Make the window visible
        GLContext.createFromCurrent();                       // Bind lwjgl with GLFW
        // Initialize openGl
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, 1200, 800);
        GL11.glOrtho(0, 1200, 0, 800, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }
    
    @Override
    public void run() {
        preInitGL();
        initGL();
        init();
        
        long handle = this.getWindowHandler();
        
        while ( glfwWindowShouldClose(handle) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Run Cycles
            input();
            update();
            render();
            
            // Display Buffer swap
            glfwSwapBuffers(handle);
        }
        
        // Release window and window call backs
        this.getKeyCallback().release();
        exit();
    }
    
    private void input() {
        
    }
    
    private void update() {
        
    }

    private void render() {
        GraphicsTest.immidateDraw();
        GraphicsTest.displayListDraw();
        GraphicsTest.vertexArrayDraw();
        GraphicsTest.vboDraw();
        GraphicsTest.vaoDraw();
    }
    
    private void exit() {
        final long handle = this.getWindowHandler();
        
        this.getErrorCallback().release();
        Tasker.postASyncTask(new Task("GLFW_MAIN_THREAD", 0) {
            @Override
            public void execute() {
                glfwDestroyWindow(handle);
            }
         });
    }
}
