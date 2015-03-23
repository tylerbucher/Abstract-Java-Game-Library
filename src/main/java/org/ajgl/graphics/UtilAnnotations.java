/**
 * 
 */
package org.ajgl.graphics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Tyler
 *
 */
public class UtilAnnotations {
    
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
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface BeginMode {
        
    }
    
    /**
     * 
     * @author Tyler Bucher
     * @see org.lwjgl.opengl.GL15                  GL15
     * @see org.lwjgl.opengl.GL15#GL_STREAM_DRAW   GL_STREAM_DRAW
     * @see org.lwjgl.opengl.GL15#GL_STREAM_READ   GL_STREAM_READ
     * @see org.lwjgl.opengl.GL15#GL_STREAM_COPY   GL_STREAM_COPY
     * @see org.lwjgl.opengl.GL15#GL_STATIC_DRAW   GL_STATIC_DRAW
     * @see org.lwjgl.opengl.GL15#GL_STATIC_READ   GL_STATIC_READ
     * @see org.lwjgl.opengl.GL15#GL_STATIC_COPY   GL_STATIC_COPY
     * @see org.lwjgl.opengl.GL15#GL_DYNAMIC_DRAW  GL_DYNAMIC_DRAW
     * @see org.lwjgl.opengl.GL15#GL_DYNAMIC_READ  GL_DYNAMIC_READ
     * @see org.lwjgl.opengl.GL15#GL_DYNAMIC_COPY  GL_DYNAMIC_COPY
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface DrawMode {
        
    }
    
    /**
     * 
     * @author Tyler Bucher
     * @see org.lwjgl.opengl.GL11                         GL11
     * @see org.lwjgl.opengl.GL11#GL_COMPILE              GL_COMPILE
     * @see org.lwjgl.opengl.GL11#GL_COMPILE_AND_EXECUTE  GL_COMPILE_AND_EXECUTE
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface CompileMode {
        
    }
    
    /**
     * 
     * @author Tyler Bucher
     * @see org.lwjgl.opengl.GL11                    GL11
     * @see org.lwjgl.opengl.GL11#GL_BYTE            GL_BYTE
     * @see org.lwjgl.opengl.GL11#GL_UNSIGNED_BYTE   GL_UNSIGNED_BYTE
     * @see org.lwjgl.opengl.GL11#GL_SHORT           GL_SHORT
     * @see org.lwjgl.opengl.GL11#GL_UNSIGNED_SHORT  GL_UNSIGNED_SHORT
     * @see org.lwjgl.opengl.GL11#GL_INT             GL_INT
     * @see org.lwjgl.opengl.GL11#GL_UNSIGNED_INT    GL_UNSIGNED_INT
     * @see org.lwjgl.opengl.GL11#GL_FLOAT           GL_FLOAT
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface GLDataType {
        
    }
    
    /**
     * 
     * @author Tyler Bucher
     * @see org.lwjgl.opengl.GL11                               GL11
     * @see org.lwjgl.opengl.GL11#TEXTURE_1D                    TEXTURE_1D
     * @see org.lwjgl.opengl.GL11#TEXTURE_2D                    TEXTURE_2D
     * @see org.lwjgl.opengl.GL11#TEXTURE_1D_ARRAY              TEXTURE_1D_ARRAY
     * @see org.lwjgl.opengl.GL11#TEXTURE_RECTANGLE             TEXTURE_RECTANGLE
     * @see org.lwjgl.opengl.GL11#TEXTURE_CUBE_MAP              TEXTURE_CUBE_MAP
     * @see org.lwjgl.opengl.GL11#TEXTURE_3D                    TEXTURE_3D
     * @see org.lwjgl.opengl.GL11#TEXTURE_2D_ARRAY              TEXTURE_2D_ARRAY
     * @see org.lwjgl.opengl.GL11#TEXTURE_CUBE_MAP_ARRAY        TEXTURE_CUBE_MAP_ARRAY
     * @see org.lwjgl.opengl.GL11#TEXTURE_BUFFER                TEXTURE_BUFFER
     * @see org.lwjgl.opengl.GL11#TEXTURE_2D_MULTISAMPLE        TEXTURE_2D_MULTISAMPLE
     * @see org.lwjgl.opengl.GL11#TEXTURE_2D_MULTISAMPLE_ARRAY  TEXTURE_2D_MULTISAMPLE_ARRAY
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface TextureFormat {
        
    }
}
