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
    public @interface GlBeginMode {
        
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
    public @interface GlDrawMode {
        
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
    public @interface GlCompileMode {
        
    }
    
    /**
     * The following constants are OpenGL Data-type pointers.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11#GL_BYTE GL_BYTE}
     * <li>{@link org.lwjgl.opengl.GL11#GL_UNSIGNED_BYTE GL_UNSIGNED_BYTE}
     * <li>{@link org.lwjgl.opengl.GL11#GL_SHORT GL_SHORT}
     * <li>{@link org.lwjgl.opengl.GL11#GL_UNSIGNED_SHORT GL_UNSIGNED_SHORT}
     * <li>{@link org.lwjgl.opengl.GL11#GL_INT GL_INT}
     * <li>{@link org.lwjgl.opengl.GL11#GL_UNSIGNED_INT GL_UNSIGNED_INT}
     * <li>{@link org.lwjgl.opengl.GL11#GL_FLOAT GL_FLOAT}
     * <li>{@link org.lwjgl.opengl.GL11#GL_2_BYTES GL_2_BYTES}
     * <li>{@link org.lwjgl.opengl.GL11#GL_3_BYTES GL_3_BYTES}
     * <li>{@link org.lwjgl.opengl.GL11#GL_4_BYTES GL_4_BYTES}
     * <li>{@link org.lwjgl.opengl.GL11#GL_DOUBLE GL_DOUBLE}
     * </ul>
     * @author Tyler Bucher
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface GlDataType {
        
    }
    
    /**
     * The following are OpenGL texture constants.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_1D TEXTURE_1D}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_2D TEXTURE_2D}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_RECTANGLE TEXTURE_RECTANGLE}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_3D TEXTURE_3D}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_BUFFER TEXTURE_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}
     * <li>{@link org.lwjgl.opengl.GL11#TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}
     * </ul>
     * @author Tyler Bucher
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER})
    public @interface GlTextureFormat {
        
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
    public @interface GlBeginFunction {
        
    }
    
    /**
     * For GL buffer binding the following functions should
     * be applicable. To use the buffer functions you need to call
     * {@link org.lwjgl.opengl.GL15#glBindBuffer(int, int) glBindBuffer} 
     * then the function don't forget to call 
     * {@link org.lwjgl.opengl.GL15#glBindBuffer(int, int) glBindBuffer(int, 0)} 
     * again when you are done.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11 glVertexPointer}
     * <li>{@link org.lwjgl.opengl.GL11 glColorPointer}
     * <li>{@link org.lwjgl.opengl.GL14 glSecondaryColorPointer}
     * <li>{@link org.lwjgl.opengl.GL11 glIndexPointer}
     * <li>{@link org.lwjgl.opengl.GL11 glNormalPointer}
     * <li>{@link org.lwjgl.opengl.GL14 glFogCoordPointer}
     * <li>{@link org.lwjgl.opengl.GL11 glTexCoordPointer}
     * <li>{@link org.lwjgl.opengl.GL30 glVertexAttribPointer}
     * <li>{@link org.lwjgl.opengl.GL11#glEdgeFlagPointer(int, java.nio.ByteBuffer) glEdgeFlagPointer}
     * <li>{@link org.lwjgl.opengl.GL15 glBufferData}
     * <li>{@link org.lwjgl.opengl.GL15 glBufferSubData}
     * </ul>
     * @author Tyler Bucher
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface GlBufferFunction {
        
    }
}
