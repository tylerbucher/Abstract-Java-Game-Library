package org.ajgl.math;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;

public class MathUtils {
    
    public static ByteBuffer createByteBuffer(Byte... values) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(values.length);
        for(Byte b : values)
            buffer.put(b.byteValue());
        buffer.flip();
        return buffer;
    }
    
    public static ShortBuffer createShortBuffer(Short... values) {
        ShortBuffer buffer = BufferUtils.createShortBuffer(values.length);
        for(Short s : values)
            buffer.put(s.shortValue());
        buffer.flip();
        return buffer;
    }
    
    public static IntBuffer createIntegerBuffer(Integer... values) {
        IntBuffer buffer = BufferUtils.createIntBuffer(values.length);
        for(Integer i : values)
            buffer.put(i.intValue());
        buffer.flip();
        return buffer;
    }
    
    public static LongBuffer createLongBuffer(Long... values) {
        LongBuffer buffer = BufferUtils.createLongBuffer(values.length);
        for(Long l : values)
            buffer.put(l.longValue());
        buffer.flip();
        return buffer;
    }
    
    public static FloatBuffer createFloatBuffer(Float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        for(Float f : values)
            buffer.put(f.floatValue());
        buffer.flip();
        return buffer;
    }
    
    public static DoubleBuffer createDoubleBuffer(Double... values) {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(values.length);
        for(Double d : values)
            buffer.put(d.doubleValue());
        buffer.flip();
        return buffer;
    }
}
