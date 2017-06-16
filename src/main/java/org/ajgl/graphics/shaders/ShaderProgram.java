package org.ajgl.graphics.shaders;

import org.ajgl.graphics.UtilAnnotations.GlShaderFunction;
import org.lwjgl.opengl.GL20;

/**
 * This class is designed to represent a shader program in OpenGL.
 *
 * @author Tyler Bucher
 * @see GlShaderFunction
 */
@GlShaderFunction
public class ShaderProgram {

    /**
     * The shader program id.
     */
    public final int id;

    /**
     * Creates a new {@link GL20#glCreateProgram() Shader Program} object.
     */
    public ShaderProgram() {
        id = GL20.glCreateProgram();
    }

    /**
     * @param shader the {@link Shader} to be attached to the {@link ShaderProgram}.
     */
    public void attachShader(Shader shader) {
        GL20.glAttachShader(id, shader.id);
    }

    /**
     * @param shader the {@link Shader} to be detached from the {@link ShaderProgram}.
     */
    public void detachShader(Shader shader) {
        GL20.glDetachShader(id, shader.id);
    }

    /**
     * Links the {@link GL20#glLinkProgram shader program}.
     */
    public void link() {
        GL20.glLinkProgram(id);
    }

    /**
     * Validates the {@link GL20#glValidateProgram shader program}.
     */
    public void validate() {
        GL20.glValidateProgram(id);
    }

    /**
     * @return true if the shader program was compiled false otherwise.
     */
    public boolean verify() {
        int status = GL20.glGetProgrami(id, GL20.GL_LINK_STATUS);

        return status != 0;
    }
}
