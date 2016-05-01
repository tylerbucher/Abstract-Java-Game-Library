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

import java.util.ArrayList;

import org.ajgl.concurrent.Event;
import org.ajgl.concurrent.EventDispatcher;
import org.ajgl.concurrent.Tasker;
import org.ajgl.game.core2d.text.Text;
import org.ajgl.graphics.shaders.Shader;
import org.ajgl.graphics.shaders.ShaderProgram;
import org.ajgl.math.matrix.Matrix4f;
import org.ajgl.test.concurrent.EventTest;
import org.ajgl.test.graphics.shaders.ShaderTest;
import org.ajgl.test.graphics.shaders.ShaderTextureTest;
import org.ajgl.test.math.MatrixTest;
import org.ajgl.test.math.VectorTest;
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
    
    public static WindowTest windowTest;  // Concurrent windows
    public static ConcurrentWindowTest threadedWindowTest;  // Concurrent windows
    
    private static ShaderProgram shaderProgram_VC;  // Shader program for modern OpenGL
    private static ShaderProgram shaderProgram_VCT;  // Shader program for modern OpenGL
    
    // polar values
    private static ArrayList<Double> keyList = new ArrayList<>();
    private static ArrayList<Double> valueList = new ArrayList<>();
    
    private static Text text;
    
    public static Matrix4f identity = Matrix4f.orthographic(0f, 1200f, 0f, 800f, 1f, -1f);
    public static int uniVCTModel;
    public static int uniVCTTexColMul;
    
    /**
     * Pre OpenGL-initialization.
     */
    public static void preInitGL() {
        //VectorTest();
        //matrixTest();
        EventDispatcher.registerEvents(EventTest.class);
    }
    
    public static void VectorTest() {
        VectorTest.add();
        VectorTest.subtract();
        VectorTest.cross();
        VectorTest.dot();
        VectorTest.scale();
        VectorTest.negate();
        VectorTest.normalize();
        VectorTest.magnitude();
    }
    
    public static void matrixTest() {
        MatrixTest.add();
        MatrixTest.subtract();
        MatrixTest.multiplyMatrix();
        MatrixTest.multiplyVector();
        MatrixTest.multiplyValue();
        MatrixTest.divide();
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
        //MainGameTest.legacySetup();
        MainTest.modernSetup();
        
    }
    
    /**
     * Setup modern OpenGL.
     */
    private static void modernSetup() {
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
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
            Shader vertex_shader_vc = Shader.loadShader(GL20.GL_VERTEX_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/VERTEX_SHADER_VC.glsl");
            Shader fragment_shader_vc = Shader.loadShader(GL20.GL_FRAGMENT_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/FRAGMENT_SHADER_VC.glsl");
            Shader vertex_shader_vct = Shader.loadShader(GL20.GL_VERTEX_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/VERTEX_SHADER_VCT.glsl");
            Shader fragment_shader_vct = Shader.loadShader(GL20.GL_FRAGMENT_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/FRAGMENT_SHADER_VCT.glsl");
            
            if(!vertex_shader_vc.verify() || !fragment_shader_vc.verify())
                throw new Exception("shader load error");
            if(!vertex_shader_vct.verify() || !fragment_shader_vct.verify())
                throw new Exception("shader load error");
            
            shaderProgram_VC = new ShaderProgram();
            shaderProgram_VC.attachShader(vertex_shader_vc);
            shaderProgram_VC.attachShader(fragment_shader_vc);
            
            shaderProgram_VCT = new ShaderProgram();
            shaderProgram_VCT.attachShader(vertex_shader_vct);
            shaderProgram_VCT.attachShader(fragment_shader_vct);
            
            GL20.glBindAttribLocation(shaderProgram_VC.id, 0, "position");
            GL20.glBindAttribLocation(shaderProgram_VC.id, 1, "color");
          
            GL20.glBindAttribLocation(shaderProgram_VCT.id, 0, "position");
            GL20.glBindAttribLocation(shaderProgram_VCT.id, 1, "color");
            GL20.glBindAttribLocation(shaderProgram_VCT.id, 2, "texcoord");
          
            shaderProgram_VC.link();
            shaderProgram_VC.validate();
            
            shaderProgram_VCT.link();
            shaderProgram_VCT.validate();
            
            if(!shaderProgram_VC.verify())
                throw new Exception("shader program error");
            if(!shaderProgram_VCT.verify())
                throw new Exception("shader program error");
            
            // ================ shader uniform setup ========================
            GL20.glUseProgram(shaderProgram_VC.id);
            
            int uniModel = GL20.glGetUniformLocation(shaderProgram_VC.id, "model");
            if(uniModel != -1) {
                Matrix4f model = new Matrix4f();
                GL20.glUniformMatrix4fv(uniModel, false, model.getBuffer());
            }

            int uniView = GL20.glGetUniformLocation(shaderProgram_VC.id, "view");
            if(uniView != -1) {
                Matrix4f view = new Matrix4f();
                GL20.glUniformMatrix4fv(uniView, false, view.getBuffer());
            }

            int uniProjection = GL20.glGetUniformLocation(shaderProgram_VC.id, "projection");
            if(uniProjection != -1) {
                GL20.glUniformMatrix4fv(uniProjection, false, identity.getBuffer());
            }
            // ================ shader uniform setup ====
            GL20.glUseProgram(shaderProgram_VCT.id);
            
            uniVCTModel = GL20.glGetUniformLocation(shaderProgram_VCT.id, "model");
            if(uniVCTModel != -1) {
                Matrix4f model = new Matrix4f();
                GL20.glUniformMatrix4fv(uniVCTModel, false, model.getBuffer());
            }

            int uniVCTView = GL20.glGetUniformLocation(shaderProgram_VCT.id, "view");
            if(uniVCTView != -1) {
                Matrix4f view = new Matrix4f();
                GL20.glUniformMatrix4fv(uniVCTView, false, view.getBuffer());
            }

            int uniVCTProjection = GL20.glGetUniformLocation(shaderProgram_VCT.id, "projection");
            if(uniVCTProjection != -1) {
                Matrix4f projection = Matrix4f.orthographic(0f, 1200f, 0f, 800f, 1f, -1f);
                GL20.glUniformMatrix4fv(uniVCTProjection, false, projection.getBuffer());
            }
            
            uniVCTTexColMul = GL20.glGetUniformLocation(shaderProgram_VCT.id, "texColMul");
            if(uniVCTTexColMul != -1) {
                GL20.glUniform4f(uniVCTTexColMul, 0.0f, 0.0f, 0.0f, 0.0f);
            }
            
            int uniVCTTex = GL20.glGetUniformLocation(shaderProgram_VCT.id, "texImage");
            if(uniVCTTex != -1) {
                GL20.glUniform1i(uniVCTTex, 0);
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
        EventDispatcher.dispatchEvent(new Event());
        text = new Text("This is a basic test.", "src/game/java/resources/ttf/ARIAL.TTF", 24, new float[]{500.0f, -500.0f}, new float[]{1.0f, 1.0f, 1.0f});
    }
    
    /**
     * Starts the game process.
     */
    public static void gameStart() {
        
        System.out.println("LWJGL Version: ["+Sys.getVersion()+"]");
        System.out.println("OpenGL Version: ["+GL11.glGetString(GL11.GL_VERSION)+"]");
        
        long handle = windowTest.getWindowHandler();
        //GL11.glClearColor(1, 1, 1, 1);
        polarTest();
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
    
    private static void polarTest() {
        for(double d=0.0;d<=6.28;d+=0.01256) {
            double theta = d;
            double radius = 200*Math.sin(5*theta);
            double x = radius*Math.cos(theta);
            double y = radius*Math.sin(theta);
            
            keyList.add(x+500);
            valueList.add(y+500);
        }
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
        shaderTestRender();
        polarRender();
    }
    
    private static void polarRender() {
        GL20.glUseProgram(shaderProgram_VC.id);
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
    }
    
    /**
     * Shader test render.
     */
    private static void shaderTestRender() {
        GL20.glUseProgram(shaderProgram_VC.id);
        ShaderTest.immidateDraw();
        ShaderTest.displayListDraw();
        ShaderTest.vertexArrayDraw();
        ShaderTest.vboDraw();
        ShaderTest.vaoDraw();
        ShaderTest.vaoDraw2();
        
        GL20.glUseProgram(shaderProgram_VCT.id);
        ShaderTextureTest.immidateDraw();
        ShaderTextureTest.displayListDraw();
        ShaderTextureTest.vertexArrayDraw();
        ShaderTextureTest.vboDraw();
        ShaderTextureTest.vaoDraw();
        
        text.draw();
        
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
        System.out.println("start");
        
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
    
    public static void MultiplyMatrix(Matrix4f matrix) {
        setMatrix(uniVCTModel, new Matrix4f().multiply(matrix));
    }
    
    public static void setMatrix(int location, Matrix4f matrix) {
        GL20.glUniformMatrix4fv(location, false, matrix.getBuffer());
    }
}