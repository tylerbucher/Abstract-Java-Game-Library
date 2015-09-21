/**
 * 
 */
package org.ajgl.game;

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
import org.lwjgl.glfw.GLFWCharModsCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;


/**
 * @author Tyler
 *
 */
public class MainWindow extends Window {
	
	private GLFWKeyCallback   keyCallback;      // key callback reference instance
    
    /**
     * Default window constructor.
     */
    public MainWindow() {
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
    public MainWindow(int width, int height, String title, long monitor, long share) {
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
                if(key == GLFW.GLFW_KEY_GRAVE_ACCENT && action == GLFW_RELEASE) {
                    MainGameTest.drawConsole ^= true;
                    MainGameTest.consoleActive ^= true;
                    //GLFW.glfwSetCursor(MainGameTest.windowTest.getWindowHandler(), MainGameTest.iBeamCursor);
                }
                
                if(!MainGameTest.consoleActive) {
                    if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                        Tasker.executeASyncTask("GLFW_MAIN_THREAD");
                        GLFW.glfwSetWindowShouldClose(MainGameTest.windowTest.getWindowHandler(), 1);
                    }
                    return;
                }
                
                if(key == GLFW.GLFW_KEY_BACKSPACE && action != GLFW.GLFW_PRESS) {
                    MainGameTest.console.text.removeLastChar();
                    System.out.println("backspace");
                }
            }
        });
        
        this.setCharModsCallback(new GLFWCharModsCallback() {
			@Override
			public void invoke(long window, int codepoint, int mods) {
				if(MainGameTest.consoleActive) {
					if(codepoint >= 32 && codepoint < 128 && codepoint != GLFW.GLFW_KEY_GRAVE_ACCENT) {
	                    MainGameTest.console.text.addChar((char) codepoint);
	                }
				}
			}
        });
        super.callbackSetup();
    }
    
    public void charCallbackSetup() {
    	
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
