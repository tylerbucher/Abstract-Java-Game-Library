package org.ajgl.graphics;

import org.lwjgl.opengl.GL30;

/**
 * This class is designed to be supporting class for Vertex Buffered Objects.
 * This class encapsulates the vertex buffered object, and does not directly
 * draw any objects.
 *
 * @author Tyler Bucher
 */
public class VertexArrayObject {

    /**
     * @return the vertex array object handler.
     */
    public static int createVaoHandler() {
        return GL30.glGenVertexArrays();
    }

    /**
     * Binds a Vertex array object.
     *
     * @param arrayHandler the vertex array handler.
     */
    public static void bindVao(int arrayHandler) {
        GL30.glBindVertexArray(arrayHandler);
    }
}
