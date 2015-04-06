/**
 * 
 */
package org.ajgl.graphics.shaders;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.opengl.GL20;


/**
 * @author Tyler Bucher
 */
public final class ShaderUtil {
    
    public static final int VERTEX_SHADER = GL20.GL_VERTEX_SHADER;
    public static final int FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER;
    
    /**
     * Single String version of {@link #shaderSource(int, CharSequence) shaderSource}.
     * @param shaderID - The id of the shader (given by OpenGL).
     * @param source - The source of a shader file.
     */
    public static void shaderSource(int shaderID, CharSequence source) {
        GL20.glShaderSource(shaderID, source);
    }
    
    public static void shaderSource(int shaderID, CharSequence... source) {
        GL20.glShaderSource(shaderID, source);
    }
    
    public static void shaderSource(int shaderID, PointerBuffer strings, IntBuffer length) {
        GL20.glShaderSource(shaderID, strings, length);
    }
    
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
