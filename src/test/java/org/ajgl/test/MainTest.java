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
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.ajgl.concurrent.Tasker;
import org.ajgl.graphics.DisplayList;
import org.ajgl.graphics.Graphics;
import org.ajgl.graphics.VertexArrayObject;
import org.ajgl.graphics.VertexArrays;
import org.ajgl.graphics.VertexBufferedObject;
import org.ajgl.graphics.shaders.Shader;
import org.ajgl.graphics.shaders.ShaderProgram;
import org.ajgl.graphics.shaders.ShaderProgramUtil;
import org.ajgl.math.Matrix4d;
import org.ajgl.test.graphics.GraphicsTest;
import org.ajgl.test.graphics.shaders.ShaderTest;
import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public class MainTest {
    
    public static ShaderProgram shaderProgram;
    
    public static volatile boolean close = false;
    static boolean closed = false;
    static Window windowN;
    static Window windowp;
    
    /** Window Properties **/
    private int 
        HEIGHT = 800,
        WIDTH = 1200,
        RESIZABLE = GL11.GL_FALSE,
        REFRESH_RATE = 60,
        VSYNC = 1;
    
    private String TITLE = "AJGL TEST";
    
    // The window handler
    private long window;
    
    // callback reference instances
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    
    int triangleData, triangleColor;
    int vaoHandler;
    
    public void preInitGL() {
        
    }
    
    public void initGL() {
        preWindowSetup();
        windowSetup();
        callbackSetup();
        
        glfwMakeContextCurrent(window);     // Make the OpenGL context current
//        glfwSwapInterval(VSYNC);            // Enable v-sync
        glfwShowWindow(window);             // Make the window visible
        GLContext.createFromCurrent();      // Bind lwjgl with GLFW
        
        // Initialize openGl
//        this.legacySetup();
        this.modernSetup();
    }
    
    private void legacySetup() {
        // Initialize openGl
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }
    
    private void modernSetup() {
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glShadeModel(GL11.GL_SMOOTH);
    }
    
    private void preWindowSetup() {
        // Setup an error callback
        GLFW.glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
 
        // Initialize GLFW
        if (GLFW.glfwInit() != GL11.GL_TRUE)
            exit();
    }
    
    private void windowSetup() {
        // Configure Window Properties
        glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // Keep the window hidden
        glfwWindowHint(GLFW_RESIZABLE, RESIZABLE); // Do not allow resizing
        glfwWindowHint(GLFW_REFRESH_RATE, REFRESH_RATE); // Refresh rate
        glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 1);
        glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 0);
        
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
        if ( window == NULL )
            exit();
        
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (GLFWvidmode.width(vidmode) - WIDTH) / 2,
            (GLFWvidmode.height(vidmode) - HEIGHT) / 2
        );
    }
    
    private void callbackSetup() {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {//TODO Dispatch key events
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    Tasker.executeASyncTask("GLFW_MAIN_THREAD");
            }
        });
    }
    
    public void init() {
        this.shaderInit();
    }
    
    private void shaderInit() {
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
    
    public void gameStart() {
        
        System.out.println("LWJGL Version: ["+Sys.getVersion()+"]");
        System.out.println("OpenGL Version: ["+GL11.glGetString(GL11.GL_VERSION)+"]");
        
        
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Run Cycles
            input();
            update();
            render();
            
            // Display Buffer swap
            glfwSwapBuffers(window);
        }
        
        // Release window and window call backs
        glfwDestroyWindow(window);
        keyCallback.release();
        exit();
    }
    
    private void input() {
        glfwPollEvents();
        Tasker.executeASyncTask("GLFW_MAIN_THREAD");
    }
    
    private void update() {
        
    }
    
    private void render() {
//        testRender();
        shaderTestRender();
    }
    
    private void testRender() {
        GraphicsTest.immidateDraw();
        GraphicsTest.displayListDraw();
        GraphicsTest.vertexArrayDraw();
        GraphicsTest.vboDraw();
        GraphicsTest.vaoDraw();
    }
    
    private void shaderTestRender() {
        GL20.glUseProgram(shaderProgram.id);
        
        ShaderTest.immidateDraw();
        
        GL20.glUseProgram(0);
    }
    
    public void exit() {
        // Terminate GLFW and release the GLFWerrorfun
        glfwTerminate();
        errorCallback.release();
        System.exit(1);
    }
    
    public static void main(String[] args) {
//        windowN = new Window(1f, 1f);
//        windowN.initGL();
//        windowp = new Window(1f, 1f);
//        windowp.initGL();
//        
        
        
        
//        windowN = new Window(1200, 800, "Test", 0, 0, null);
//        windowN.setup();
//        windowN.setThread(new GameThread(windowN));
//        windowN.startThread();
        
        MainTest test = new MainTest();
        test.preInitGL();
        test.initGL();
        test.init();
        test.gameStart();
    }
}
