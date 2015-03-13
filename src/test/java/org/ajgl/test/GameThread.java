/**
 * 
 */
package org.ajgl.test;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glClear;

import java.nio.FloatBuffer;

import org.ajgl.concurrent.Task;
import org.ajgl.concurrent.Tasker;
import org.ajgl.graphics.Graphics;
import org.ajgl.graphics.VertexBufferedObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public class GameThread extends Thread {
    
    //private long windowHandler;
    private Window window;
    private int triangleData, triangleColor;
    
    public GameThread(Window window) {
        this.window = window;
    }
    
    protected void initGL() {
        
        glfwMakeContextCurrent(window.getWindow());     // Make the OpenGL context current
        GLContext.createFromCurrent();      // Bind lwjgl with GLFW
        // Initialize openGl
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 1200, 0, 800, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }
    
    private void setupTriangle() {
        float[] array = new float[]{50, 50, 50, 100, 100, 50};
        FloatBuffer buffer = BufferUtils.createFloatBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        
        float[] arrayC = new float[]{1,0,0, 0,1,0, 0,0,1};
        FloatBuffer bufferC = BufferUtils.createFloatBuffer(arrayC.length);
        bufferC.put(arrayC);
        bufferC.flip();
        
        triangleData = VertexBufferedObject.createVboHandler(GL15.GL_DYNAMIC_DRAW, buffer);
        triangleColor = VertexBufferedObject.createVboHandler(GL15.GL_DYNAMIC_DRAW, bufferC);
    }
    
    public void run() {
        initGL();
        setupTriangle();
        
        while (glfwWindowShouldClose(window.getWindow()) == GL_FALSE) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            // Run Cycles
            input();
            update();
            render();
            // Display Buffer swap
            glfwSwapBuffers(window.getWindow());
        }
        
        exit();
    }
    
    private void input() {
        glfwPollEvents();
    }
    
    private void update() {
        
    }
    
    private void render() {
        primitiveRender();
    }
    
    private void primitiveRender() {
        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        
        VertexBufferedObject.colorPointer(triangleColor, 3, 0, 0, GL11.GL_FLOAT);
        VertexBufferedObject.drawVboArrays(triangleData, 2, 3, 0, 0, 0, GL11.GL_FLOAT, GL11.GL_TRIANGLES);
        
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
    }
    
    public void exit() {
        Tasker.postASyncTask(new Task("GLFW_MAIN_THREAD") {
           @Override
           public void execute() {
               glfwDestroyWindow(window.getWindow());
               window.getErrorCallback().release();
           }
        });
    }
}
