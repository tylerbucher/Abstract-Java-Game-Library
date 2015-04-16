/**
 * 
 */
package org.ajgl.test.graphics.shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;


/**
 * @author Tyler
 *
 */
public class ShaderProgram {
    
    private int id;
    
    public ShaderProgram() {
        id = GL20.glCreateProgram();
        if(id == 0) {
            System.out.println("sakjdnkljasnf;kajsdnf;kjsda");
        }
    }
    
    public void attachShader(Shader shader) {
        GL20.glAttachShader(id, shader.getID());
    }
    
    public void link() {
        GL20.glLinkProgram(id);
        verify();
    }
    
    public void use() {
        GL20.glUseProgram(id);
    }
    
    public void verify() {
        int status = GL20.glGetProgrami(id, GL20.GL_LINK_STATUS);
        if (status != GL11.GL_TRUE) {
            throw new RuntimeException(GL20.glGetProgramInfoLog(id));
        }
    }
    
    public void delete() {
        GL20.glDeleteProgram(id);
    }
    
    public int getID() {
        return id;
    }
}
