package org.ajgl.graphics;

import org.ajgl.graphics.UtilAnnotations.GlBeginMode;
import org.ajgl.graphics.UtilAnnotations.GlBufferFunction;
import org.ajgl.graphics.UtilAnnotations.GlDataType;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

/**
 * <p>This class draws shapes to the screen through OpenGL. The
 * rendering method that this class uses is "Vertex Arrays". Please
 * note that of this moment (03/8/2015) OpenGL has not deprecated vertex
 * arrays. But they are not often used in modern OpenGL.</p>
 * <p>The VertexArrays class has been tagged with the GlBufferFunction
 * because it uses all of the same functions. There in one major difference
 * that you should take note of, the difference is that you do not need to call
 * {@link org.lwjgl.opengl.GL15#glBindBuffer(int, int) glBindBuffer} for any
 * reason. The reason is because vertex arrays pass the buffer directly to
 * the pointer function.</p>
 *
 * @author Tyler Bucher
 */
@GlBufferFunction
public final class VertexArrays {

    /**
     * Draws the vertex array; Uses redundant vertices.
     *
     * @param beginMode    the OpenGL begin mode.
     * @param vertexNumber the number of vertices.
     * @param first        the start point of the array.
     * @see GlBeginMode
     */
    public static void drawArrays(@GlBeginMode int beginMode, int vertexNumber, int first) {
        GL11.glDrawArrays(beginMode, first, vertexNumber);
    }

    /**
     * Draws the vertex array; Does not use redundant vertices.
     *
     * @param beginMode the OpenGL begin mode.
     * @param dataType  the OpenGL dataType.
     * @param indices   the index vertices.
     * @see GlBeginMode
     * @see GlDataType
     */
    public static void drawElements(@GlBeginMode int beginMode, @GlDataType int dataType, ByteBuffer indices) {
        GL11.glDrawElements(beginMode, dataType, indices);
    }
}
