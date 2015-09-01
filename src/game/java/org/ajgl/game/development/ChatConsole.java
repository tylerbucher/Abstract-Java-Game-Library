/**
 * 
 */
package org.ajgl.game.development;

import org.ajgl.game.OpenGL;
import org.ajgl.game.core2d.Rectangle;
import org.ajgl.game.core2d.text.Text;
import org.lwjgl.opengl.GL20;


/**
 * @author Tyler
 *
 */
public class ChatConsole {
    
    public static float[] consoleColor = {128,128,128,0.75f, 128,128,128,0.75f, 128,128,128,0.75f, 128,128,128,0.75f};
    public static float[] caretColor = {0,0,0,1.0f, 0,0,0,1.0f, 0,0,0,1.0f, 0,0,0,1.0f};
    
    public static double CARET_BLINK_TIME = 5*Math.pow(10, 8);
    public static double CARET_DRAW_TIME = 5*Math.pow(10, 8);
    
    
    public float x, y;
    public float width, height;

    public Rectangle console;
    public Rectangle caret;
    public Text text;
    
    /**
     * @param graphicsData
     */
    public ChatConsole(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.console = new Rectangle(Rectangle.CreateGraphicsData(x, y, width, height, consoleColor));
        this.caret = new Rectangle(Rectangle.CreateGraphicsData(x+5, y+5, 1.5f, 20.0f, caretColor));
        text = new Text("", "src/game/java/resources/ttf/ARIAL.TTF", 25.0f, new float[]{x+7, -(y+7)}, caretColor);
    }
    
    public void draw() {
        console.draw();
        caret.draw();
        GL20.glUseProgram(OpenGL.shaderProgram_VCT.id);
        text.draw();
    }
    
    long curTime = System.nanoTime();
    long drawTime = 0;
    boolean setDrawTime = true;
    public void drawCaret() {
        if((System.nanoTime() - curTime) >= CARET_BLINK_TIME) {
            if(setDrawTime) {
                drawTime = System.nanoTime();
                setDrawTime = false;
            }
            if((System.nanoTime() - drawTime) <= CARET_DRAW_TIME) {
                caret.draw();
            } else {
                setDrawTime = true;
                curTime = System.nanoTime();
            }
        }
    }
}
