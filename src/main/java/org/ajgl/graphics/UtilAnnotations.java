package org.ajgl.graphics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class adds annotated description to most of the OpenGL
 * parameters.
 *
 * @author Tyler Bucher
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
     *
     * @author Tyler Bucher
     */
    @Retention (RetentionPolicy.SOURCE)
    @Target ({ElementType.PARAMETER})
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
     *
     * @author Tyler Bucher
     */
    @Retention (RetentionPolicy.SOURCE)
    @Target ({ElementType.PARAMETER})
    public @interface GlDrawMode {

    }

    /**
     * The following constants are compile modes used with display lists.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11#GL_COMPILE GL_COMPILE}
     * <li>{@link org.lwjgl.opengl.GL11#GL_COMPILE_AND_EXECUTE GL_COMPILE_AND_EXECUTE}
     * </ul>
     *
     * @author Tyler Bucher
     * @see org.lwjgl.opengl.GL11#glNewList(int, int)
     */
    @Retention (RetentionPolicy.SOURCE)
    @Target ({ElementType.PARAMETER})
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
     *
     * @author Tyler Bucher
     */
    @Retention (RetentionPolicy.SOURCE)
    @Target ({ElementType.PARAMETER})
    public @interface GlDataType {

    }

    /**
     * The following are OpenGL texture constants.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL11#GL_TEXTURE_1D}
     * <li>{@link org.lwjgl.opengl.GL11#GL_TEXTURE_2D}
     * <li>{@link org.lwjgl.opengl.GL12#GL_TEXTURE_3D}
     * <li>{@link org.lwjgl.opengl.GL13#GL_TEXTURE_CUBE_MAP}
     * <li>{@link org.lwjgl.opengl.GL30#GL_TEXTURE_1D_ARRAY}
     * <li>{@link org.lwjgl.opengl.GL30#GL_TEXTURE_2D_ARRAY}
     * <li>{@link org.lwjgl.opengl.GL31#GL_TEXTURE_RECTANGLE}
     * <li>{@link org.lwjgl.opengl.GL31#GL_TEXTURE_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL32#GL_TEXTURE_2D_MULTISAMPLE}
     * <li>{@link org.lwjgl.opengl.GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY}
     * <li>{@link org.lwjgl.opengl.GL40#GL_TEXTURE_CUBE_MAP_ARRAY}
     * </ul>
     *
     * @author Tyler Bucher
     */
    @Retention (RetentionPolicy.SOURCE)
    @Target ({ElementType.PARAMETER})
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
     *
     * @author Tyler Bucher
     */
    @Retention (RetentionPolicy.SOURCE)
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
     *
     * @author Tyler Bucher
     */
    @Retention (RetentionPolicy.SOURCE)
    public @interface GlBufferFunction {

    }

    /**
     * The Following constants are OpenGL buffer data targets.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL15#GL_ARRAY_BUFFER GL_ARRAY_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL42#GL_ATOMIC_COUNTER_BUFFER GL_ATOMIC_COUNTER_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL31#GL_COPY_READ_BUFFER GL_COPY_READ_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL31#GL_COPY_WRITE_BUFFER GL_COPY_WRITE_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL43#GL_DISPATCH_INDIRECT_BUFFER GL_DISPATCH_INDIRECT_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL40#GL_DRAW_INDIRECT_BUFFER GL_DRAW_INDIRECT_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL15#GL_ELEMENT_ARRAY_BUFFER GL_ELEMENT_ARRAY_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL21#GL_PIXEL_PACK_BUFFER GL_PIXEL_PACK_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL21#GL_PIXEL_UNPACK_BUFFER GL_PIXEL_UNPACK_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL44#GL_QUERY_BUFFER GL_QUERY_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL43#GL_SHADER_STORAGE_BUFFER GL_SHADER_STORAGE_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL31#GL_TEXTURE_BUFFER GL_TEXTURE_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL30#GL_TRANSFORM_FEEDBACK_BUFFER GL_TRANSFORM_FEEDBACK_BUFFER}
     * <li>{@link org.lwjgl.opengl.GL31#GL_UNIFORM_BUFFER GL_UNIFORM_BUFFER}
     * </ul>
     *
     * @author Tyler Bucher
     * @see org.lwjgl.opengl.GL15#glBufferData
     */
    @Retention (RetentionPolicy.SOURCE)
    @Target ({ElementType.PARAMETER})
    public @interface GlBufferTarget {

    }

    /**
     * For OpenGL Shaders the following functions are acceptable.
     * <ul>
     * <li>{@link org.lwjgl.opengl.GL20 glBindAttribLocation}
     * <li>{@link org.lwjgl.opengl.GL30 glBindFragDataLocation}
     * <li>{@link org.lwjgl.opengl.GL33 glBindFragDataLocationIndexed}
     * <li>{@link org.lwjgl.opengl.GL20 glGetActive}
     * <li>{@link org.lwjgl.opengl.GL31 glGetActive}
     * <li>{@link org.lwjgl.opengl.GL40 glGetActive}
     * <li>{@link org.lwjgl.opengl.GL20 glGetAttachedShaders}
     * <li>{@link org.lwjgl.opengl.GL20 glGetAttribLocation}
     * <li>{@link org.lwjgl.opengl.GL30 glGetFragDataLocation}
     * <li>{@link org.lwjgl.opengl.GL33 glGetFragDataIndex}
     * <li>{@link org.lwjgl.opengl.GL20 glGetProgram}
     * <li>{@link org.lwjgl.opengl.GL43 glGetProgram}
     * <li>{@link org.lwjgl.opengl.GL20 glGetShader}
     * <li>{@link org.lwjgl.opengl.GL41 glGetShader}
     * <li>{@link org.lwjgl.opengl.GL40 glGetSubroutine}
     * <li>{@link org.lwjgl.opengl.GL20 glGetUniform}
     * <li>{@link org.lwjgl.opengl.GL30 glGetUniform}
     * <li>{@link org.lwjgl.opengl.GL31 glGetUniform}
     * <li>{@link org.lwjgl.opengl.GL40 glGetUniform}
     * <li>{@link org.lwjgl.opengl.GL20 glUnifrom}
     * <li>{@link org.lwjgl.opengl.GL30 glUnifrom}
     * <li>{@link org.lwjgl.opengl.GL40 glUnifrom}
     * </ul>
     *
     * @author Tyler Bucher
     */
    @Retention (RetentionPolicy.SOURCE)
    public @interface GlShaderFunction {

    }
}
