package org.ajgl.test;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.ajgl.AJGLData;
import org.ajgl.Logger;
import org.ajgl.collision.QuadTree;
import org.ajgl.event.EventDispatcher;
import org.ajgl.event.keyboard.KeyboardPressEvent;
import org.ajgl.event.keyboard.KeyboardReleaseEvent;
import org.ajgl.event.mouse.MouseMoveEvent;
import org.ajgl.event.mouse.MousePressEvent;
import org.ajgl.event.mouse.MouseReleaseEvent;
import org.ajgl.graphics.Graphics;
import org.ajgl.primary.GameObject;
import org.ajgl.util.Configuration;
import org.ajgl.util.PhysicalAlteration;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * 
 * This class is intended to be a test class and nothing more
 * @author Tyler
 * 
 */
public class JavaGame {
    // Game data
    static int xSize, ySize;
    static String title;
    static boolean fullscreen;
    static boolean resizable;
    static boolean vSync;
    
    // Configuration
    static Configuration config;
    
    // Primitive drawing objects
    static int triangleData, triangleColor;
    static int squareData, squareTexData;
    static Texture squareTexture;
    
    // Vertex Buffered Object (VBO)
    //Drawable drawableObject;
    static GameObject gameObject;
    static GameObject mouseBox;
    static ArrayList<GameObject> objectList;
    
    // Event Dispatcher
    static EventDispatcher eventDispatcher;
    
    // QuadTree collision detection
    static QuadTree quadTree;

    static void input() {
        mouseInput();
        keyboardInput();
    }
    
    private static void mouseInput() {
        while(Mouse.next()) {
            if(Mouse.getEventButtonState()) {
                eventDispatcher.dispatchEvent(new MousePressEvent(Mouse.getEventButton()));
            } else {
                if(Mouse.getEventButton() != -1)
                    eventDispatcher.dispatchEvent(new MouseReleaseEvent(Mouse.getEventButton()));
            }
            // Mouse move
            if(Mouse.getEventDX() != 0 || Mouse.getEventDY() != 0) {
                eventDispatcher.dispatchEvent(new MouseMoveEvent(Mouse.getEventDX(), Mouse.getEventDY()));
            }
        }
    }
    
    private static void keyboardInput() {
        while(Keyboard.next()) {
            if(Keyboard.getEventKeyState()) {
                eventDispatcher.dispatchEvent(new KeyboardPressEvent(Keyboard.getEventKey()));
            } else {
                eventDispatcher.dispatchEvent(new KeyboardReleaseEvent(Keyboard.getEventKey()));
            }
        }
    }
    
    private static void update() {
        vertexBufferedObjectUpdate();
        quadTreeUpdate();
        handleCollision();
        mouseBox.finalizeUpdate();
    }
    
    private static void vertexBufferedObjectUpdate() {
        gameObject.updatePosition();
        
        mouseBox.updatePosition();
        //mouseBox.finalizeUpdate();
        
        // Update list of objects
        if(objectList.size() != 0)
            for(GameObject o : objectList)
                o.updatePosition();
    }
    
    private static void quadTreeUpdate() {
        if(objectList.size() != 0)
            for(GameObject o : objectList)
                quadTree.updateObject(o);
        quadTree.updateObject(mouseBox);
    }
    
    private static void handleCollision() {
        List<GameObject> tempList = quadTree.getPossibleCollision(mouseBox);
        for(int i=0;i<tempList.size();i++) {
            if(tempList.get(i).contains(mouseBox.getVertices())) {
                tempList.get(i).setColor(new float[]{1,0,0,1,0,0,1,0,0,1,0,0});
            } else {
                tempList.get(i).setColor(new float[]{1,1,1,1,1,1,1,1,1,1,1,1});
            }
        }
    }

    
    private static void render() {
        //primitiveRender();
        vertexBufferedObjectRender();
        quadTreeRender();
    }
    
    private static void primitiveRender() {
        // Draw Immediate VBO
        Graphics.drawShape(triangleData, triangleColor, 2, 3, 3, GL11.GL_TRIANGLES);
        Graphics.drawShape(squareData, squareTexData, 2, 2, 4, GL_QUADS, squareTexture);
    }
    
    private static void vertexBufferedObjectRender() {
        //drawableObject.draw();
        //gameObject.draw();
        mouseBox.draw();
        mouseBox.getAabb().draw();
        // Draw list of objects
        if(objectList.size() != 0)
            for(GameObject o : objectList)
                o.draw();
    }
    
    private static void quadTreeRender() {
        quadTree.draw();
    }
    
    /**
     * Is intended to initializes main game thread. | methods below ran in order
     * | while no close request{ input(), update(), render()} exit.
     */
    private static void gameStart() {
        while (!Display.isCloseRequested()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            // Run cycles
            input();
            update();
            render();
            // Display synchronization
            Display.update();
            Display.sync(60);
        }
        Graphics.clearVboHandlers();
        exit();
    }
    
    /**
     * Is intended to exit and close the program.
     */
    public static void exit() {
        Display.destroy();
        System.exit(0);
    }

    private static void preInitGl() {
        configurationPreInitGl();
        gameDataPreInitGL();
    }
    
    private static void configurationPreInitGl() {
        config = Configuration.loadConfiguration(new File("src/settings.yml"));
    }
    
    private static void gameDataPreInitGL() {
        xSize = config.getInt("xSize", 1200);
        ySize = config.getInt("ySize", 800);
        title = config.getString("Title", "Abstract Java Game Library");
        fullscreen = config.getBoolean("FullScreen", false);
        resizable = config.getBoolean("Resizable", false);
        vSync = config.getBoolean("VSync", true);
    }
    
    private static void initGL(int x, int y) {
        try {
            // Initialize display information
            Display.setDisplayMode(new DisplayMode(xSize, ySize));
            Display.setTitle(title);
            Display.setFullscreen(fullscreen);
            Display.setResizable(resizable);
            Display.setVSyncEnabled(vSync);
            // Set display icons
            Display.setIcon(new ByteBuffer[]{AJGLData.Icon_16, AJGLData.Icon_32});
            // Create display
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);// FIXME
        }
        // Initialize openGl
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, xSize, 0, ySize, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        // Enable extra OpenGL stuff
        glEnable(GL_TEXTURE_2D);
    }

    private static void init() {
        primitiveRenderInit();
        vertexBufferedObjectInit();
        quadTreeInit();
        eventDispatcherInit();
    }
    
    private static void primitiveRenderInit() {
        // Square Texture setup
        /*try {
            squareTexture = TextureLoader.getTexture("PNG", JavaGame.class.getResourceAsStream("/res/AJGL_LOGO.png"));
        } catch (IOException e) {
            Logger.severe(e.getMessage());
            e.printStackTrace();
        }*/
        // Immediate VBO setup
        // Triangle setup: coordinates, color
        triangleData = Graphics.createVboHandler(new float[] {50.0f, 600.0f, 150.0f, 800.0f, 250.0f, 600.0f});
        triangleColor = Graphics.createVboHandler(new float[] {1, 0, 0, 0, 1, 0, 0, 0, 1});
        // Square setup: coordinates, color
        squareData = Graphics.createVboHandler(new float[] {50.0f, 50.0f, 50.0f, 562.0f, 1074.0f, 562.0f, 1074.0f, 50.0f});
        squareTexData = Graphics.createVboHandler(new float[] {-1.0f, 0, -1.0f, -1.0f, 0, -1.0f, 0, 0});
        
    }
    
    private static void vertexBufferedObjectInit() {
        // Create a drawable object
        //drawableObject = new Drawable(
                //new float[]{10 ,10 ,5 , 600, 600, 800, 100, 100}, GL11.GL_POLYGON);
        //create a GameObject object
        gameObject = new GameObject(
                new float[] {200.0f, 200.0f, 200.0f, 350.0f, 550.0f, 350.0f, 550.0f, 200.0f}, 
                new float[] {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0}, GL11.GL_QUADS);
        // Create a GameObject list
        objectList = new ArrayList<>();
        // Creates a GameObject box for the mouse
        mouseBox = new GameObject(
                new float[] {0, 0, 0, 1, 1, 1, 1, 0}, GL11.GL_QUADS);
        
        //mouseBox.setVertices(PhysicalAlteration.rotateMatrix(mouseBox.getVertices(), 35.2f));
    }
    
    private static void quadTreeInit() {
        quadTree = new QuadTree(xSize, ySize, 10);
        quadTree.add(mouseBox);
    }
    
    private static void eventDispatcherInit() {
        eventDispatcher = new EventDispatcher();
        eventDispatcher.registerEvents(InputListener.class);
    }
    
    public static EventDispatcher getDispatcher() {
        return eventDispatcher;
    }
    
    public static void main(String[] args) {
        
        JavaGame.preInitGl();
        JavaGame.initGL(1200, 800);
        JavaGame.init();
        JavaGame.gameStart();
    }
}
