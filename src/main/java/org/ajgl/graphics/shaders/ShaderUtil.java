/**
 * 
 */
package org.ajgl.graphics.shaders;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.opengl.GL20;


/**
 * This class is designed to provide direct access 
 * to OpenGL shader functions.
 * @author Tyler Bucher
 */
public final class ShaderUtil {
    
    public static final int VERTEX_SHADER = GL20.GL_VERTEX_SHADER;      // Vertex Shader
    public static final int FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER;  // Fragment shader
    
    /**
     * Single String version of {@link #shaderSource(int, CharSequence) shaderSource}.
     * @param shaderID - The id of the shader (given by OpenGL).
     * @param source - The source of a shader file.
     */
    public static void shaderSource(int shaderID, CharSequence source) {
        GL20.glShaderSource(shaderID, source);
    }
    
    /**
     * String Array version of {@link #shaderSource(int, CharSequence) shaderSource}.
     * @param shaderID - The id of the shader (given by OpenGL).
     * @param source - The array source of a shader file.
     */
    public static void shaderSource(int shaderID, CharSequence... source) {
        GL20.glShaderSource(shaderID, source);
    }
    
    /**
     * Alternate String version of {@link #shaderSource(int, CharSequence) shaderSource}.
     * @param shaderID - The id of the shader (given by OpenGL).
     * @param strings - .
     * @param length - .
     */
    public static void shaderSource(int shaderID, PointerBuffer strings, IntBuffer length) {
        GL20.glShaderSource(shaderID, strings, length);
    }
    
    /**
     * Sets the source code in shader to the source code in the array of strings specified 
     * by strings. Any source code previously stored in the shader object is completely replaced.
     * @param shaderID - The id of the shader (given by OpenGL).
     * @param source - The source of a shader file.
     * @param count - The number of elements in the string and length arrays.
     * @param strings - An array of pointers to strings containing the source code to be loaded into the shader.
     * @param length - An array of string lengths.
     */
    public static void shaderSource(int shaderID, int count, ByteBuffer strings, ByteBuffer length) {
        GL20.glShaderSource(shaderID, count, strings, length);
    }
    
    /**
     * Complies a shader.
     * @param shaderID - The id of a shader to be compiled.
     */
    public static void compileShader(int shaderID) {
        GL20.glCompileShader(shaderID);
    }
    
    /**
     * Deletes a shader.
     * @param shaderID - The id of a shader to be deleted.
     */
    public static void deleteShader(int shaderID) {
        GL20.glDeleteShader(shaderID);
    }
}
