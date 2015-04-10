package org.ajgl.math;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;

public class MathUtils {
    
    public static ByteBuffer createByteBuffer(byte... values) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    public static ShortBuffer createShortBuffer(short... values) {
        ShortBuffer buffer = BufferUtils.createShortBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    public static IntBuffer createIntegerBuffer(int... values) {
        IntBuffer buffer = BufferUtils.createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    public static LongBuffer createLongBuffer(long... values) {
        LongBuffer buffer = BufferUtils.createLongBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    public static FloatBuffer createFloatBuffer(float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    public static DoubleBuffer createDoubleBuffer(double... values) {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}
