/**
 * 
 */
package org.ajgl.game;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import org.ajgl.collision.axisaligned.Contain;
import org.ajgl.concurrent.Event;
import org.ajgl.concurrent.EventDispatcher;
import org.ajgl.concurrent.Tasker;
import org.ajgl.game.core2d.MRectangle;
import org.ajgl.game.core2d.Rectangle;
import org.ajgl.game.development.ChatConsole;
import org.ajgl.util.FPSCounter;
import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;


/**
 * @author Tyler
 *
 */
public class MainGameTest {
    
    protected static MainWindow windowTest;  // Concurrent windows
    protected static long iBeamCursor;
    
    private static long windowHandle;
    public static boolean drawConsole;
    public static boolean drawCaret;
    public static boolean drawText;
    
    public static MRectangle rect;
    public static ChatConsole console;
    
    public static boolean consoleActive;
    public static String text = "test";
    
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
        glfwMakeContextCurrent(windowHandle);     // Make the OpenGL context current
        glfwShowWindow(windowHandle);             // Make the window visible
        GLContext.createFromCurrent();                          // Bind lwjgl with GLFW
        // Initialize openGl
        OpenGL.standardSetup();
        OpenGL.shaderSetup();
        
        GLFW.glfwSwapInterval(1);
    }
    
    /**
     * Program initialization.
     */
    public static void init() {
        EventDispatcher.dispatchEvent(new Event());
        
        rect = new MRectangle(Rectangle.CreateGraphicsData(50, 50, 200, 200, new float[]{1,0,0,1.0f, 0,1,0,1.0f, 0,0,1,1.0f, 1,1,1,1.0f}));
        console = new ChatConsole(25.0f, windowTest.getHeight()-55.0f, windowTest.getWidth()/3.0f, 30.0f);
    }
    
    /**
     * Starts the game process.
     */
    public static void gameStart() {
        
        System.out.println("LWJGL Version: ["+Sys.getVersion()+"]");
        System.out.println("OpenGL Version: ["+GL11.glGetString(GL11.GL_VERSION)+"]");
        
        while (glfwWindowShouldClose(windowHandle) == GL_FALSE) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Run Cycles
            input();
            update();
            render();
            
            // Display Buffer swap
            glfwSwapBuffers(windowHandle);
        }
        
        // Release window and window call backs
        glfwDestroyWindow(windowHandle);
        windowTest.getKeyCallback().release();
        exit();
    }
    
    /**
     * Input Method
     */
    private static void input() {
        glfwPollEvents();
        
        if(!consoleActive) {
        	if(GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) rect.dy += 1.0f;
        	if(GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) rect.dx -= 1.0f;
        	if(GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) rect.dy -= 1.0f;
        	if(GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) rect.dx += 1.0f;
        }
        
//        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
//        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);
//        GLFW.glfwGetCursorPos(windowHandle, x, y);
//        System.out.println("Xpos: "+x.get(0)+" Ypos: "+y.get(0));
        
        Tasker.executeASyncTask("GLFW_MAIN_THREAD");
    }
    
    /**
     * Update method for moving objects.
     */
    private static void update() {
        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(windowHandle, x, y);
        float xpos = (float) x.get(0);
        float ypos = (float) y.get(0);
        if(Contain.RvR2D(new float[]{console.x, console.y, console.x+console.width, console.y, 
                console.x+console.width, console.y+console.height, console.x, console.y+console.height}, 
                new float[]{xpos, ypos, xpos, ypos, xpos, ypos, xpos, ypos})) {
            if(GLFW.glfwGetMouseButton(windowHandle, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                drawCaret ^= true;
                drawText ^= true;
            }
        }
        rect.move();
    }
    
    /**
     * Render method.
     */
    private static void render() {
        GL20.glUseProgram(OpenGL.shaderProgram_VC.id);
        
        rect.draw();
        
        if(drawConsole)
            console.console.draw();
//        if(drawCaret)
//            console.caret.draw();
        console.drawCaret();
        if(drawConsole) {
            GL20.glUseProgram(OpenGL.shaderProgram_VCT.id);
            console.text.draw();
        }
        
        GL20.glUseProgram(0);
        //System.out.println("FPS: "+FPSCounter.getFPS());
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
        windowTest = new MainWindow(1200, 800, "AJGL_2 Test (GAME)", 0, 0);
        windowTest.setup();
        
        windowHandle = windowTest.getWindowHandler();
        iBeamCursor = GLFW.glfwCreateStandardCursor(GLFW.GLFW_IBEAM_CURSOR);
        
        MainGameTest.preInitGL();
        MainGameTest.initGL();
        MainGameTest.init();
        MainGameTest.gameStart();
    }
}