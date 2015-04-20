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
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.ajgl.Window;
import org.ajgl.concurrent.Tasker;
import org.ajgl.concurrent.ThreadedWindow;
import org.ajgl.graphics.Immediate;
import org.ajgl.graphics.shaders.Shader;
import org.ajgl.graphics.shaders.ShaderProgram;
import org.ajgl.math.Matrix4d;
import org.ajgl.test.graphics.GraphicsTest;
import org.ajgl.test.graphics.shaders.ShaderTest;
import org.ajgl.test.window.ConcurrentWindowTest;
import org.ajgl.test.window.WindowTest;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
@SuppressWarnings("unused")
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
                Matrix4d model = new Matrix4d();
                GL20.glUniformMatrix4(uniModel, false, model.getBuffer(FloatBuffer.class));
            }

            int uniView = GL20.glGetUniformLocation(shaderProgram.id, "view");
            if(uniView != -1) {
                Matrix4d view = new Matrix4d();
                GL20.glUniformMatrix4(uniView, false, view.getBuffer(FloatBuffer.class));
            }

            int uniProjection = GL20.glGetUniformLocation(shaderProgram.id, "projection");
            if(uniProjection != -1) {
                Matrix4d projection = Matrix4d.orthographic(0f, 1200f, 0f, 800f, 1f, -1f);
                GL20.glUniformMatrix4(uniProjection, false, projection.getBuffer(FloatBuffer.class));
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
        
        GL20.glUseProgram(0);
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
