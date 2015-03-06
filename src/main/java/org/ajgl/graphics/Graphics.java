/**
 * 
 */
package org.ajgl.graphics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 * @author Tyler
 *
 */
public class Graphics {
    
    /**
     * 
     * @author Tyler Bucher
     * @see org.lwjgl.opengl.GL11                    GL11
     * @see org.lwjgl.opengl.GL11#GL_POINTS          GL_POINTS        
     * @see org.lwjgl.opengl.GL11#GL_LINES           GL_LINES         
     * @see org.lwjgl.opengl.GL11#GL_LINE_LOOP       GL_LINE_LOOP     
     * @see org.lwjgl.opengl.GL11#GL_LINE_STRIP      GL_LINE_STRIP    
     * @see org.lwjgl.opengl.GL11#GL_TRIANGLES       GL_TRIANGLES     
     * @see org.lwjgl.opengl.GL11#GL_TRIANGLE_STRIP  GL_TRIANGLE_STRIP
     * @see org.lwjgl.opengl.GL11#GL_TRIANGLE_FAN    GL_TRIANGLE_FAN  
     * @see org.lwjgl.opengl.GL11#GL_QUADS           GL_QUADS         
     * @see org.lwjgl.opengl.GL11#GL_QUAD_STRIP      GL_QUAD_STRIP    
     * @see org.lwjgl.opengl.GL11#GL_POLYGON         GL_POLYGON       
     */
    @Target({ElementType.PARAMETER})
    public @interface BeginMode {
        
    }
}
