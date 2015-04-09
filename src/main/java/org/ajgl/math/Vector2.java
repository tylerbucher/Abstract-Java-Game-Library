package org.ajgl.math;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Vector2<N extends Number> extends Vector<N>{
    
    public N x;
    public N y;
    
    Class<? extends Buffer> clazz;
    
    
    public Vector2(N x, N y) {
        this.x = x;
        this.y = y;
        
        clazz = IntBuffer.class;
    }

    @Override
    public <B extends Buffer> B getBuffer(Class<B> bufferClass) {
        return bufferClass.cast(VectorUtils.glGenDataBuffer(x, y));
    }
}
