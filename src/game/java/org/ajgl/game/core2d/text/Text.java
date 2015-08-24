/**
 * 
 */
package org.ajgl.game.core2d.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.ajgl.game.OpenGL;
import org.ajgl.game.core2d.Rectangle;
import org.ajgl.math.MathUtils;
import org.ajgl.math.matrix.Matrix4f;
import org.ajgl.test.MainTest;
import org.ajgl.util.IOUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;


/**
 * @author Tyler
 *
 */
public class Text {
    
    public int BITMAP_W = 512;
    public int BITMAP_H = 512;
    public float fontDropH = 24;
    public float fontHeight;
    private int textureID;
    
    public float[] fontPosition;
    public float[] fontColor;
    
    private String text;
    private String ttfPath;
    
    private ByteBuffer characterData;
    private ByteBuffer trueTypeFont;
    private ByteBuffer bitmap;
    
    private ArrayList<Float> graphicsData;
    private TextVAO vao;
    
    private FloatBuffer x = BufferUtils.createFloatBuffer(1);
    private FloatBuffer y = BufferUtils.createFloatBuffer(1);
    private STBTTAlignedQuad q = new STBTTAlignedQuad();
    
    public Text() {
        this.text = "Basic Text";
        this.ttfPath = "src/game/java/resources/ttf/ARIAL.TTF";
        this.fontHeight = 24;
        this.fontPosition = new float[]{0.0f, 0.0f};
        this.fontColor = new float[]{1,1,1};
        graphicsData = new ArrayList<>();
        
        this.textureSetup();
        this.setText(text);
    }
    
    public Text(String text, float fontHeight, float[] fontPosition, float[] fontColor) {
        this.text = text;
        this.fontHeight = fontHeight;
        this.fontPosition = fontPosition;
        this.fontColor = fontColor;
        graphicsData = new ArrayList<>();
        
        this.textureSetup();
        this.setText(text);
    }
    
    public Text(String text, String ttfPath, float fontHeight, float[] fontPosition, float[] fontColor) {
        this.text = text;
        this.ttfPath = ttfPath;
        this.fontHeight = fontHeight;
        this.fontPosition = fontPosition;
        this.fontColor = fontColor;
        graphicsData = new ArrayList<>();
        
        this.textureSetup();
        this.setText(text);
    }
    
    private void textureSetup() {
        try {
            textureID = GL11.glGenTextures();
            characterData = BufferUtils.createByteBuffer(96 * STBTTBakedChar.SIZEOF);
            
            trueTypeFont = IOUtil.ioResourceToByteBuffer(ttfPath, 160 * 1024);
            
            bitmap = BufferUtils.createByteBuffer(BITMAP_W * BITMAP_H);
            STBTruetype.stbtt_BakeFontBitmap(trueTypeFont, fontHeight, bitmap, BITMAP_W, BITMAP_H, 32, characterData);

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_ALPHA, BITMAP_W, BITMAP_H, 0, GL11.GL_ALPHA, GL11.GL_UNSIGNED_BYTE, bitmap);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setText(String text) {
        this.text = text;
        
        x.put(0, fontPosition[0]);
        y.put(0, fontPosition[1]);
        for (int i=0;i<text.length();i++) {
            char c=text.charAt(i);
            if (c=='\n') {
                y.put(0, y.get(0)+fontDropH);
                x.put(0, fontPosition[0]);
                continue;
            } 
            else if ( c < 32 || 128 <= c ) continue;
            
            STBTruetype.stbtt_GetBakedQuad(characterData, BITMAP_W, BITMAP_H, c - 32, x, y, q.buffer(), 1);
            
            graphicsData.add(q.getX0()); graphicsData.add(q.getY0()); graphicsData.add(0.0f);
            graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
            graphicsData.add(q.getS0()); graphicsData.add(q.getT0());
            
            graphicsData.add(q.getX1()); graphicsData.add(q.getY0()); graphicsData.add(0.0f);
            graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
            graphicsData.add(q.getS1()); graphicsData.add(q.getT0());
            
            graphicsData.add(q.getX1()); graphicsData.add(q.getY1()); graphicsData.add(0.0f);
            graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
            graphicsData.add(q.getS1()); graphicsData.add(q.getT1());
            
            graphicsData.add(q.getX0()); graphicsData.add(q.getY1()); graphicsData.add(0.0f);
            graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
            graphicsData.add(q.getS0()); graphicsData.add(q.getT1());
        }
        vao = new TextVAO(MathUtils.convertFloat(graphicsData));
    }
    
    public void addChar(char c) {
        if (c=='\n') {
            y.put(0, y.get(0)+fontDropH);
            x.put(0, fontPosition[0]);
        }
        STBTruetype.stbtt_GetBakedQuad(characterData, BITMAP_W, BITMAP_H, c - 32, x, y, q.buffer(), 1);
        
        graphicsData.add(q.getX0()); graphicsData.add(q.getY0()); graphicsData.add(0.0f);
        graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
        graphicsData.add(q.getS0()); graphicsData.add(q.getT0());
        
        graphicsData.add(q.getX1()); graphicsData.add(q.getY0()); graphicsData.add(0.0f);
        graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
        graphicsData.add(q.getS1()); graphicsData.add(q.getT0());
        
        graphicsData.add(q.getX1()); graphicsData.add(q.getY1()); graphicsData.add(0.0f);
        graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
        graphicsData.add(q.getS1()); graphicsData.add(q.getT1());
        
        graphicsData.add(q.getX0()); graphicsData.add(q.getY1()); graphicsData.add(0.0f);
        graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(0.0f); graphicsData.add(1.0f);
        graphicsData.add(q.getS0()); graphicsData.add(q.getT1());
        
        vao.bufferUpdate(MathUtils.convertFloat(graphicsData));
    }
    
    public void draw() {
        Matrix4f matrix = new Matrix4f();
        matrix.m11 = -1;
        
        OpenGL.glSetMatrix(OpenGL.UNI_VCT_MODEL, matrix);
        OpenGL.glColor4f(1, 1, 1, 0);
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        vao.draw();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        
        OpenGL.glColor4f(0, 0, 0, 0);
        OpenGL.glSetMatrix(OpenGL.UNI_VCT_MODEL, OpenGL.pureIdentity);
    }
}
