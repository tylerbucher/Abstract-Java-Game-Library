package org.ajgl.graphics.shaders;

import org.lwjgl.PointerBuffer;
import org.lwjgl.opengl.GL20;

import java.nio.IntBuffer;

/**
 * This class is designed to provide direct access
 * to OpenGL shader functions.
 *
 * @author Tyler Bucher
 */
public final class ShaderUtil {

    /**
     * Vertex Shader type.
     */
    public static final int VERTEX_SHADER = GL20.GL_VERTEX_SHADER;

    /**
     * Fragment shader type.
     */
    public static final int FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER;

    /**
     * Single String version of {@link #shaderSource(int, CharSequence...)}.
     *
     * @param shaderID the id of the shader (given by OpenGL).
     * @param source   the source of a shader file.
     */
    public static void shaderSource(int shaderID, CharSequence source) {
        GL20.glShaderSource(shaderID, source);
    }

    /**
     * String Array version of {@link #shaderSource(int, CharSequence)}.
     *
     * @param shaderID the id of the shader (given by OpenGL).
     * @param source   the array source of a shader file.
     */
    public static void shaderSource(int shaderID, CharSequence... source) {
        GL20.glShaderSource(shaderID, source);
    }

    /**
     * Alternate String version of {@link #shaderSource(int, CharSequence)}.
     *
     * @param shaderID the id of the shader (given by OpenGL).
     * @param strings  an array of pointers to strings containing the source code to be loaded into the shader.
     * @param length   an array of string lengths.
     */
    public static void shaderSource(int shaderID, PointerBuffer strings, IntBuffer length) {
        GL20.glShaderSource(shaderID, strings, length);
    }

    /**
     * Sets the source code in shader to the source code in the array of strings specified
     * by strings. Any source code previously stored in the shader object is completely replaced.
     *
     * @param shaderID the id of the shader (given by OpenGL).
     * @param count    the number of elements in the string and length arrays.
     * @param strings  an array of pointers to strings containing the source code to be loaded into the shader.
     * @param length   an array of string lengths.
     */
    public static void shaderSource(int shaderID, int count, PointerBuffer strings, IntBuffer length) {
        GL20.glShaderSource(shaderID, strings, length);
    }

    /**
     * Complies a shader.
     *
     * @param shaderID the id of a shader to be compiled.
     */
    public static void compileShader(int shaderID) {
        GL20.glCompileShader(shaderID);
    }

    /**
     * Deletes a shader.
     *
     * @param shaderID the id of a shader to be deleted.
     */
    public static void deleteShader(int shaderID) {
        GL20.glDeleteShader(shaderID);
    }
}
