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
     * The following are all valid begin modes for use in
     * {@link org.lwjgl.opengl.GL11#glBegin(int) glBegin}.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11#GL_POINTS GL_POINTS}
     * <li>{@link org.lwjgl.opengl.GL11#GL_LINES GL_LINES}
     * <li>{@link org.lwjgl.opengl.GL11#GL_LINE_LOOP GL_LINE_LOOP}
     * <li>{@link org.lwjgl.opengl.GL11#GL_LINE_STRIP GL_LINE_STRIP}
     * <li>{@link org.lwjgl.opengl.GL11#GL_TRIANGLES GL_TRIANGLES}
     * <li>{@link org.lwjgl.opengl.GL11#GL_TRIANGLE_STRIP GL_TRIANGLE_STRIP}
     * <li>{@link org.lwjgl.opengl.GL11#GL_TRIANGLE_FAN GL_TRIANGLE_FAN}
     * <li>{@link org.lwjgl.opengl.GL11#GL_QUADS GL_QUADS}
     * <li>{@link org.lwjgl.opengl.GL11#GL_QUAD_STRIP GL_QUAD_STRIP}
     * <li>{@link org.lwjgl.opengl.GL11#GL_POLYGON GL_POLYGON}
     * </ul>
     * @author Tyler Bucher
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface BeginMode {
        
    }
    
    /**
     * The following constants are OpenGL storage patterns. 
     * Commonly used with buffer-data.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL15#GL_STREAM_DRAW GL_STREAM_DRAW}
     * <li>{@link org.lwjgl.opengl.GL15#GL_STREAM_READ GL_STREAM_READ}
     * <li>{@link org.lwjgl.opengl.GL15#GL_STREAM_COPY GL_STREAM_COPY}
     * <li>{@link org.lwjgl.opengl.GL15#GL_STATIC_DRAW GL_STATIC_DRAW}
     * <li>{@link org.lwjgl.opengl.GL15#GL_STATIC_READ GL_STATIC_READ}
     * <li>{@link org.lwjgl.opengl.GL15#GL_STATIC_COPY GL_STATIC_COPY}
     * <li>{@link org.lwjgl.opengl.GL15#GL_DYNAMIC_DRAW GL_DYNAMIC_DRAW}
     * <li>{@link org.lwjgl.opengl.GL15#GL_DYNAMIC_READ GL_DYNAMIC_READ}
     * <li>{@link org.lwjgl.opengl.GL15#GL_DYNAMIC_COPY GL_DYNAMIC_COPY}
     * </ul>
     * @author Tyler Bucher
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface DrawMode {
        
    }
    
    /**
     * The following constants are compile modes used with display lists.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11#GL_COMPILE GL_COMPILE}
     * <li>{@link org.lwjgl.opengl.GL11#GL_COMPILE_AND_EXECUTE GL_COMPILE_AND_EXECUTE}
     * </ul>
     * @author Tyler Bucher
     * @see {@link org.lwjgl.opengl.GL11#glNewList(int, int) glNewList}
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface CompileMode {
        
    }
    
    /**
     * 
     * @author Tyler Bucher
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
    
    /**
     * Between {@link org.lwjgl.opengl.GL11#glBegin(int) glBegin} and 
     * {@link org.lwjgl.opengl.GL11#glEnd() glEnd} all of
     * the following are acceptable.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11 glVertex}
     * <li>{@link org.lwjgl.opengl.GL11 glColor}
     * <li>{@link org.lwjgl.opengl.GL14 glSecondaryColor}
     * <li>{@link org.lwjgl.opengl.GL11 glIndex}
     * <li>{@link org.lwjgl.opengl.GL11 glNormal}
     * <li>{@link org.lwjgl.opengl.GL14 glFogCoord}
     * <li>{@link org.lwjgl.opengl.GL11 glTexCoord}
     * <li>{@link org.lwjgl.opengl.GL13 glMultiTexCoord}
     * <li>{@link org.lwjgl.opengl.GL20 glVertexAttrib}
     * <li>{@link org.lwjgl.opengl.GL11 glEvalCoord}
     * <li>{@link org.lwjgl.opengl.GL11 glEvalPoint}
     * <li>{@link org.lwjgl.opengl.GL11#glArrayElement(int) glArrayElement}
     * <li>{@link org.lwjgl.opengl.GL11 glMaterial}
     * <li>{@link org.lwjgl.opengl.GL11 glEdgeFlag}
     * <li>{@link org.lwjgl.opengl.GL11#glCallList(int) glCallList}
     * <li>{@link org.lwjgl.opengl.GL11 glCallLists}
     * </ul>
     * @author Tyler Bucher
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface GLBegin {
        
    }
}
