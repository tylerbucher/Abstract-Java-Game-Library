/**
 * 
 */
package org.ajgl.test;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glClear;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.ajgl.concurrent.Tasker;
import org.ajgl.graphics.shaders.Shader;
import org.ajgl.graphics.shaders.ShaderProgram;
import org.ajgl.math.matrix.Matrix4d;
import org.ajgl.math.matrix.Matrix4f;
import org.ajgl.test.graphics.shaders.ShaderTest;
import org.ajgl.test.window.ConcurrentWindowTest;
import org.ajgl.test.window.WindowTest;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public class MainTest {
    
    private static WindowTest windowTest;  // Concurrent windows
    private static ConcurrentWindowTest threadedWindowTest;  // Concurrent windows
    
    private static ShaderProgram shaderProgram;  // Shader program for modern OpenGL
    
    /**
     * Pre OpenGL-initialization.
     */
    public static void preInitGL() {
        
    }
    
    /**
     * OpenGL initialization.
     */
    public static void initGL() {
        // Bind context
        glfwMakeContextCurrent(windowTest.getWindowHandler());     // Make the OpenGL context current
        glfwShowWindow(windowTest.getWindowHandler());             // Make the window visible
        GLContext.createFromCurrent();                          // Bind lwjgl with GLFW
        // Initialize openGl
        //MainTest.legacySetup();
        MainTest.modernSetup();
        
    }
    
    /**
     * Setup modern OpenGL.
     */
    private static void modernSetup() {
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        
        MainTest.shaderInit();
    }
    
    /**
     * Shader setup.
     */
    private static void shaderInit() {
        try {
            Shader vertexShader = Shader.loadShader(GL20.GL_VERTEX_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/VertexShaderTest.glsl");
            Shader fragmentShader = Shader.loadShader(GL20.GL_FRAGMENT_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/FragmentShaderTest.glsl");
            
            if(!vertexShader.verify() || !fragmentShader.verify())
                throw new Exception("shader load error");
            
            shaderProgram = new ShaderProgram();
            shaderProgram.attachShader(vertexShader);
            shaderProgram.attachShader(fragmentShader);
          
            GL20.glBindAttribLocation(shaderProgram.id, 0, "position");
            GL20.glBindAttribLocation(shaderProgram.id, 1, "color");
          
            shaderProgram.link();
            shaderProgram.validate();
            
            if(!shaderProgram.verify())
                throw new Exception("shader program error");
            
            // ================ shader uniform setup ========================
            GL20.glUseProgram(shaderProgram.id);
            
            int uniModel = GL20.glGetUniformLocation(shaderProgram.id, "model");
            if(uniModel != -1) {
                Matrix4f model = new Matrix4f();
                GL20.glUniformMatrix4(uniModel, false, model.getBuffer());
            }

            int uniView = GL20.glGetUniformLocation(shaderProgram.id, "view");
            if(uniView != -1) {
                Matrix4f view = new Matrix4f();
                GL20.glUniformMatrix4(uniView, false, view.getBuffer());
            }

            int uniProjection = GL20.glGetUniformLocation(shaderProgram.id, "projection");
            if(uniProjection != -1) {
                Matrix4f projection = Matrix4f.orthographic(0f, 1200f, 0f, 800f, 1f, -1f);
                GL20.glUniformMatrix4(uniProjection, false, projection.getBuffer());
            }
            
            GL20.glUseProgram(0);
            // ================ shader uniform setup ========================
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Program initialization.
     */
    public static void init() {
        
    }
    
    /**
     * Starts the game process.
     */
    public static void gameStart() {
        
        System.out.println("LWJGL Version: ["+Sys.getVersion()+"]");
        System.out.println("OpenGL Version: ["+GL11.glGetString(GL11.GL_VERSION)+"]");
        
        long handle = windowTest.getWindowHandler();
        
        ArrayList<Double> keyList = new ArrayList<>();
        ArrayList<Double> valueList = new ArrayList<>();
        
        for(double d=0.0;d<=6.28;d+=0.01256) {
            double theta = d;
            double radius = 200*Math.cos(5*theta);
            double x = radius*Math.cos(theta);
            double y = radius*Math.sin(theta);
            
            keyList.add(x+500);
            valueList.add(y+500);
        }
        
        
        while ( glfwWindowShouldClose(handle) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Run Cycles
            input();
            update();
            render();
            
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            GL11.glLineWidth(1.0f);
            GL11.glBegin(GL11.GL_POLYGON); {
                for(int i=0;i<keyList.size();i++) {
                    GL20.glVertexAttrib3d(1, 1f, 1f, 1f);
                    GL20.glVertexAttrib3d(0, keyList.get(i).doubleValue(), valueList.get(i).doubleValue(), 0f);
                }
            } GL11.glEnd();
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            GL20.glUseProgram(0);
            
            // Display Buffer swap
            glfwSwapBuffers(handle);
        }
        
        // Release window and window call backs
        glfwDestroyWindow(handle);
        windowTest.getKeyCallback().release();
        exit();
    }
    
    /**
     * Input Method
     */
    private static void input() {
        glfwPollEvents();
        Tasker.executeASyncTask("GLFW_MAIN_THREAD");
    }
    
    /**
     * Update method for moving objects.
     */
    private static void update() {
        
    }
    
    /**
     * Render method.
     */
    private static void render() {
        //testRender();
        shaderTestRender();
    }
    
    /**
     * Shader test render.
     */
    private static void shaderTestRender() {
        GL20.glUseProgram(shaderProgram.id);
        
        ShaderTest.immidateDraw();
        ShaderTest.displayListDraw();
        ShaderTest.vertexArrayDraw();
        ShaderTest.vboDraw();
        ShaderTest.vaoDraw();
        
        //GL20.glUseProgram(0);
    }
    
    /**
     * Exits the program.
     */
    public static void exit() {
        // Terminate GLFW and release the GLFWerrorfun
        glfwTerminate();
        windowTest.getErrorCallback().release();
        System.exit(1);
    }
    
    /**
     * Main Method.
     * @param args - Arguments.
     */
    public static void main(String[] args) {
        
        threadedWindowTest = new ConcurrentWindowTest(1200, 800, "AJGL_2 Concurrent Window Test", 0, 0);
        threadedWindowTest.setup();
        threadedWindowTest.start();
        
        
        windowTest = new WindowTest(1200, 800, "AJGL_2 Test", 0, 0);
        windowTest.setup();
        
        MainTest.preInitGL();
        MainTest.initGL();
        MainTest.init();
        MainTest.gameStart();
    }
}