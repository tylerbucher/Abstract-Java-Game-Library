/**
 * 
 */
package org.ajgl.test.graphics;

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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.ajgl.concurrent.Tasker;
import org.ajgl.test.graphics.shaders.Shader;
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
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GLContext;


/**
 * @author Tyler
 *
 */
public class Test {
    
    /** Window Properties **/
    private int 
        HEIGHT = 800,
        WIDTH = 1200,
        RESIZABLE = GL11.GL_FALSE,
        REFRESH_RATE = 60;
    
    private String TITLE = "test";
    
    // The window handler
    private long window;
    
    // callback reference instances
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    
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
    
    public void initGL() {
        preWindowSetup();
        windowSetup();
        callbackSetup();
        
        glfwMakeContextCurrent(window);     // Make the OpenGL context current
        glfwShowWindow(window);             // Make the window visible
        GLContext.createFromCurrent();      // Bind lwjgl with GLFW
        
        // Initialize openGl
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glShadeModel(GL11.GL_SMOOTH);
    }
    
    public void gameStart() {
        
        System.out.println("LWJGL Version: ["+Sys.getVersion()+"]");
        System.out.println("OpenGL Version: ["+GL11.glGetString(GL11.GL_VERSION)+"]");
        
        // ===============================================================================================
        
        // =================== VAO Setup ========================
        FloatBuffer vertexBufferVAO = BufferUtils.createFloatBuffer(9);
        vertexBufferVAO.put(new float[]{-0.95f,-0.95f,0, -0.5f,-0.95f,0, -0.95f,-0.5f,0});//{-0.95f,-0.95f,0, -0.5f,-0.95f,0, -0.95f,-0.5f,0}
        vertexBufferVAO.flip();
        
        FloatBuffer colorBufferVAO = BufferUtils.createFloatBuffer(9);
        colorBufferVAO.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBufferVAO.flip();
        
        IntBuffer indicesBufferVAO = BufferUtils.createIntBuffer(3);
        indicesBufferVAO.put(new int[]{0, 1, 2});
        indicesBufferVAO.flip();
        
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        {
            int vertHandle = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertHandle);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBufferVAO, GL15.GL_STATIC_DRAW);
            GL20.glEnableVertexAttribArray(0);
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, true, 0, 0);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            
            int colorHandle = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorHandle);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorBufferVAO, GL15.GL_STATIC_DRAW);
            GL20.glEnableVertexAttribArray(1);
            GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        }
        GL30.glBindVertexArray(0);
        
        int indicesHandle = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesHandle);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferVAO, GL15.GL_STATIC_DRAW);
        //GL20.glEnableVertexAttribArray(2);
        //GL20.glVertexAttribPointer(2, 1, GL11.GL_UNSIGNED_INT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        
        // =================== VAO Setup ========================
        
        // =================== Shader Setup =====================
        String vertPath = "src/test/java/org/ajgl/test/graphics/shaders/VertexShaderTest.glsl";
        String fragPath = "src/test/java/org/ajgl/test/graphics/shaders/FragmentShaderTest.glsl";
        
        int sahderVert = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(sahderVert, Shader.loadShader(vertPath));
        GL20.glCompileShader(sahderVert);
        
        int sahderFrag = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(sahderFrag, Shader.loadShader(fragPath));
        GL20.glCompileShader(sahderFrag);
        
        System.out.println("v: "+sahderVert+"\n"+"f: "+sahderFrag);
        // =================== Shader Setup =====================
        
        // =================== Shader Check =====================
        int status = GL20.glGetShaderi(sahderVert, GL20.GL_COMPILE_STATUS);
        if (status != GL11.GL_TRUE) {
            throw new RuntimeException(GL20.glGetShaderInfoLog(sahderVert));
        }
        
        int statusN = GL20.glGetShaderi(sahderFrag, GL20.GL_COMPILE_STATUS);
        if (statusN != GL11.GL_TRUE) {
            throw new RuntimeException(GL20.glGetShaderInfoLog(sahderFrag));
        }
        // =================== Shader Check =====================
        
        // =================== Shader Program ===================
        int programID = GL20.glCreateProgram();
        
        GL20.glAttachShader(programID, sahderVert);
        GL20.glAttachShader(programID, sahderFrag);
        
        GL20.glBindAttribLocation(programID, 0, "position");
        GL20.glBindAttribLocation(programID, 1, "color");
        
        int uniModel = GL20.glGetUniformLocation(programID, "model");
        Matrix4f model = new Matrix4f();
        GL40.glUniformMatrix4(uniModel, false, model.getBuffer());

        int uniView = GL20.glGetUniformLocation(programID, "view");
        Matrix4f view = new Matrix4f();
        GL40.glUniformMatrix4(uniView, false, view.getBuffer());

        int uniProjection = GL20.glGetUniformLocation(programID, "projection");
        float ratio = 1200f / 800f;
        Matrix4f projection = Matrix4f.orthographic(-ratio, ratio, -1f, 1f, -1f, 1f);
        GL40.glUniformMatrix4(uniProjection, false, projection.getBuffer());
        
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        // =================== Shader Program ===================
        
        // =============== Shader Program Check =================
        int statusP = GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS);
        if (statusP != GL11.GL_TRUE) {
            throw new RuntimeException(GL20.glGetProgramInfoLog(programID));
        }
        // =============== Shader Program Check =================
        GL20.glUseProgram(programID);
        
//        int uniModel = GL20.glGetUniformLocation(programID, "model");
//        Matrix4f model = new Matrix4f();
//        GL40.glUniformMatrix4(uniModel, false, model.getBuffer());
//
//        int uniView = GL20.glGetUniformLocation(programID, "view");
//        Matrix4f view = new Matrix4f();
//        GL40.glUniformMatrix4(uniView, false, view.getBuffer());
//
//        int uniProjection = GL20.glGetUniformLocation(programID, "projection");
//        float ratio = 1200f / 800f;
//        Matrix4f projection = Matrix4f.orthographic(-ratio, ratio, -1f, 1f, -1f, 1f);
//        GL40.glUniformMatrix4(uniProjection, false, projection.getBuffer());
        
        
        GL20.glUseProgram(0);
        // ===============================================================================================
        
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Run Cycles
            input();
            
            GL20.glUseProgram(programID);
            
//            int uniModel = GL20.glGetUniformLocation(programID, "model");
//            Matrix4f model = new Matrix4f();
//            GL40.glUniformMatrix4(uniModel, false, model.getBuffer());
//
//            int uniView = GL20.glGetUniformLocation(programID, "view");
//            Matrix4f view = new Matrix4f();
//            GL40.glUniformMatrix4(uniView, false, view.getBuffer());
//
//            int uniProjection = GL20.glGetUniformLocation(programID, "projection");
//            float ratio = 1200f / 800f;
//            Matrix4f projection = Matrix4f.orthographic(-ratio, ratio, -1f, 1f, -1f, 1f);
//            GL40.glUniformMatrix4(uniProjection, false, projection.getBuffer());
            
            GL30.glBindVertexArray(vaoID);
            {
                GL20.glEnableVertexAttribArray(0);
                GL20.glEnableVertexAttribArray(1);
                
                GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesHandle);
                
//                GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
                GL11.glDrawElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_INT, 0);
                
                GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
                
                GL20.glDisableVertexAttribArray(0);
                GL20.glDisableVertexAttribArray(1);
            }
            GL30.glBindVertexArray(0);
            
//            GL11.glBegin(GL11.GL_TRIANGLES); {
//                GL11.glIndexi(0);
//                GL11.glColor3f(1, 0, 0);
//                GL11.glVertex3f(10, 10, 0);
//                
//                GL11.glIndexi(1);
//                GL11.glColor3f(0, 1, 0);
//                GL11.glVertex3f(50, 10, 0);
//                
//                GL11.glIndexi(2);
//                GL11.glColor3f(0, 0, 1);
//                GL11.glVertex3f(10, 50, 0);
//            } GL11.glEnd();
            
            GL20.glUseProgram(0);
//            GraphicsTest.vboDraw();
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
    
    public void exit() {
        // Terminate GLFW and release the GLFWerrorfun
        glfwTerminate();
        errorCallback.release();
        System.exit(1);
    }
    
    public static void main(String[] args) {
        Test test = new Test();
        test.initGL();
        test.gameStart();
    }

}
