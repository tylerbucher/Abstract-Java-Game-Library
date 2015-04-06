/**
 * 
 */
package org.ajgl.graphics.shaders;

import org.lwjgl.opengl.GL20;


/**
 * @author Tyler Bucher
 */
public final class ShaderUtil {
    
    public static final int VERTEX_SHADER = GL20.GL_VERTEX_SHADER;
    public static final int FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER;
    
    /**
     * Single String version of {@link #shaderSource(int, CharSequence) shaderSource}.
     * @param shaderID - 
     * @param source
     */
    public static void shaderSource(int shaderID, CharSequence source) {
        GL20.glShaderSource(shaderID, source);
    }
}
