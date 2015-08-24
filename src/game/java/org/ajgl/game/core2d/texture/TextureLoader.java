/**
 * 
 */
package org.ajgl.game.core2d.texture;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.ajgl.util.IOUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;


/**
 * @author Tyler
 *
 */
public class TextureLoader {
    
    public static Texture LoadTexture(String path, int bufferSize) {
        ByteBuffer imageBuffer;
        IntBuffer width = BufferUtils.createIntBuffer(1);;
        IntBuffer height = BufferUtils.createIntBuffer(1);;
        IntBuffer comp = BufferUtils.createIntBuffer(1);;
        Texture tex = null;
        
        try {
            imageBuffer = IOUtil.ioResourceToByteBuffer(path, bufferSize);
            tex = new Texture(STBImage.stbi_load_from_memory(imageBuffer, width, height, comp, 0), width.get(0), height.get(0), comp.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tex;
    }
}
