/**
 * 
 */
package org.ajgl.game;

import org.ajgl.graphics.shaders.Shader;
import org.ajgl.graphics.shaders.ShaderProgram;
import org.ajgl.math.matrix.Matrix4f;
import org.ajgl.test.MainTest;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;


/**
 * @author Tyler
 *
 */
public class OpenGL {
    
    /*** Shader program for modern OpenGL. */
    public static ShaderProgram shaderProgram_VC;
    public static ShaderProgram shaderProgram_VCT;
    
    public static Matrix4f pureIdentity = new Matrix4f();
    public static Matrix4f identityProjection = Matrix4f.orthographic(0f, 1200f, 0f, 800f, 1f, -1f);
    
    public static int UNI_VC_MODEL;
    public static int UNI_VC_VIEW;
    public static int UNI_VC_PROJECTION;
    
    public static int UNI_VCT_MODEL;
    public static int UNI_VCT_VIEW;
    public static int UNI_VCT_PROJECTION;
    public static int UNI_VCT_TEX_SAMPLER;
    public static int UNI_VCT_TEX_COLOR;
    
    public static void standardSetup() {
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glShadeModel(GL11.GL_SMOOTH);
    }
    
    public static void shaderSetup() {
        try {
            Shader vertex_shader_vc = Shader.loadShader(GL20.GL_VERTEX_SHADER, "src/game/java/org/ajgl/game/VERTEX_SHADER_VC.glsl");
            Shader fragment_shader_vc = Shader.loadShader(GL20.GL_FRAGMENT_SHADER, "src/game/java/org/ajgl/game/FRAGMENT_SHADER_VC.glsl");
            Shader vertex_shader_vct = Shader.loadShader(GL20.GL_VERTEX_SHADER, "src/game/java/org/ajgl/game/VERTEX_SHADER_VCT.glsl");
            Shader fragment_shader_vct = Shader.loadShader(GL20.GL_FRAGMENT_SHADER, "src/game/java/org/ajgl/game/FRAGMENT_SHADER_VCT.glsl");
            
            if(!vertex_shader_vc.verify() || !fragment_shader_vc.verify())
                throw new Exception("shader load error");
            if(!vertex_shader_vct.verify() || !fragment_shader_vct.verify())
                throw new Exception("shader load error");
            
            shaderProgram_VC = new ShaderProgram();
            shaderProgram_VC.attachShader(vertex_shader_vc);
            shaderProgram_VC.attachShader(fragment_shader_vc);
            
            shaderProgram_VCT = new ShaderProgram();
            shaderProgram_VCT.attachShader(vertex_shader_vct);
            shaderProgram_VCT.attachShader(fragment_shader_vct);
            
            GL20.glBindAttribLocation(shaderProgram_VC.id, 0, "position");
            GL20.glBindAttribLocation(shaderProgram_VC.id, 1, "color");
          
            GL20.glBindAttribLocation(shaderProgram_VCT.id, 0, "position");
            GL20.glBindAttribLocation(shaderProgram_VCT.id, 1, "color");
            GL20.glBindAttribLocation(shaderProgram_VCT.id, 2, "texcoord");
          
            shaderProgram_VC.link();
            shaderProgram_VC.validate();
            
            shaderProgram_VCT.link();
            shaderProgram_VCT.validate();
            
            if(!shaderProgram_VC.verify())
                throw new Exception("shader program error");
            if(!shaderProgram_VCT.verify())
                throw new Exception("shader program error");
            
            // ================ shader uniform setup ========================
            GL20.glUseProgram(shaderProgram_VC.id);
            
            UNI_VC_MODEL = GL20.glGetUniformLocation(shaderProgram_VC.id, "model");
            if(UNI_VC_MODEL != -1) {
                GL20.glUniformMatrix4fv(UNI_VC_MODEL, false, pureIdentity.getBuffer());
            }

            UNI_VC_VIEW = GL20.glGetUniformLocation(shaderProgram_VC.id, "view");
            if(UNI_VC_VIEW != -1) {
                GL20.glUniformMatrix4fv(UNI_VC_VIEW, false, pureIdentity.getBuffer());
            }

            UNI_VC_PROJECTION = GL20.glGetUniformLocation(shaderProgram_VC.id, "projection");
            if(UNI_VC_PROJECTION != -1) {
                GL20.glUniformMatrix4fv(UNI_VC_PROJECTION, false, identityProjection.getBuffer());
            }
            // ================ shader uniform setup ====
            GL20.glUseProgram(shaderProgram_VCT.id);
            
            UNI_VCT_MODEL = GL20.glGetUniformLocation(shaderProgram_VCT.id, "model");
            if(UNI_VCT_MODEL != -1) {
                GL20.glUniformMatrix4fv(UNI_VCT_MODEL, false, pureIdentity.getBuffer());
            }

            UNI_VCT_VIEW = GL20.glGetUniformLocation(shaderProgram_VCT.id, "view");
            if(UNI_VCT_VIEW != -1) {
                GL20.glUniformMatrix4fv(UNI_VCT_VIEW, false, pureIdentity.getBuffer());
            }

            UNI_VCT_PROJECTION = GL20.glGetUniformLocation(shaderProgram_VCT.id, "projection");
            if(UNI_VCT_PROJECTION != -1) {
                GL20.glUniformMatrix4fv(UNI_VCT_PROJECTION, false, identityProjection.getBuffer());
            }
            
            UNI_VCT_TEX_COLOR = GL20.glGetUniformLocation(shaderProgram_VCT.id, "texColMul");
            if(UNI_VCT_TEX_COLOR != -1) {
                GL20.glUniform4f(UNI_VCT_TEX_COLOR, 0.0f, 0.0f, 0.0f, 0.0f);
            }
            
            UNI_VCT_TEX_SAMPLER = GL20.glGetUniformLocation(shaderProgram_VCT.id, "texSampler");
            if(UNI_VCT_TEX_SAMPLER != -1) {
                GL20.glUniform1i(UNI_VCT_TEX_SAMPLER, 0);
            }
            
            GL20.glUseProgram(0);
            // ================ shader uniform setup ========================
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void glSetMatrix(int matrixId, Matrix4f matrix) {
        GL20.glUniformMatrix4fv(matrixId, false, matrix.getBuffer());
    }
    
    public static void glColor4f(float r, float g, float b, float a) {
        GL20.glUniform4f(UNI_VCT_TEX_COLOR, r, g, b, a);
    }
}
