/**
 * 
 */
package org.ajgl.test.graphics.shaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;


/**
 * @author Tyler
 *
 */
public class Shader {
    
    private int id;
    
    public Shader(int type, CharSequence source) {
        id = GL20.glCreateShader(type);
        GL20.glShaderSource(id, source);
        GL20.glCompileShader(id);
        
        verify();
    }
    
    public void verify() {
        int status = GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS);
        if (status != GL11.GL_TRUE) {
            throw new RuntimeException(GL20.glGetShaderInfoLog(id));
        }
    }
    
    public void delete() {
        GL20.glDeleteShader(id);
    }
    
    public int getID() {
        return id;
    }
    
    public static Shader loadShader(int type, String path) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID = 0;
         
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
            System.exit(-1);
        }

        //CharSequence source = builder.toString();
        return new Shader(type, shaderSource);
    }
    
    public static CharSequence loadShader(String path) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID = 0;
         
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
            System.exit(-1);
        }

        //CharSequence source = builder.toString();
        return shaderSource;
    }
}
