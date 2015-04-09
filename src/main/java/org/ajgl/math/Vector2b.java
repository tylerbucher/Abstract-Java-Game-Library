package org.ajgl.math;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Vector2b extends Vector<ByteBuffer>{
    
    public byte x;
    public byte y;
    
     
    
    public Vector2b(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = BufferUtils.createByteBuffer(2);
        buffer.put(x).put(y);
        buffer.flip();
        
        return buffer;
    }
}
