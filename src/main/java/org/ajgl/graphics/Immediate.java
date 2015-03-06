/**
 * 
 */
package org.ajgl.graphics;

import java.nio.FloatBuffer;

import org.ajgl.graphics.Graphics.BeginMode;
import org.lwjgl.opengl.GL11;


/**
 * @author Tyler
 *
 */
public class Immediate {
    
    public static void draw(@BeginMode int beginMode, int verticeCount,FloatBuffer s) {
        GL11.glBegin(beginMode);
        
        GL11.glEnd();
    }
}
