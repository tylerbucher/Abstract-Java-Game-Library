/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Tyler Bucher
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.ajgl.math;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;

/**
 * This class is a utility class designed to provide
 * functionality for math tasks.
 * @author Tyler Bucher
 */
public class MathUtils {
    
    /**
     * Creates a buffer from the following values.
     * @param values - The values to be put into a buffer.
     * @return The buffer.
     */
    public static ByteBuffer createByteBuffer(byte... values) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a buffer from the following values.
     * @param values - The values to be put into a buffer.
     * @return The buffer.
     */
    public static ShortBuffer createShortBuffer(short... values) {
        ShortBuffer buffer = BufferUtils.createShortBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a buffer from the following values.
     * @param values - The values to be put into a buffer.
     * @return The buffer.
     */
    public static IntBuffer createIntegerBuffer(int... values) {
        IntBuffer buffer = BufferUtils.createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a buffer from the following values.
     * @param values - The values to be put into a buffer.
     * @return The buffer.
     */
    public static LongBuffer createLongBuffer(long... values) {
        LongBuffer buffer = BufferUtils.createLongBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a buffer from the following values.
     * @param values - The values to be put into a buffer.
     * @return The buffer.
     */
    public static FloatBuffer createFloatBuffer(float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a buffer from the following values.
     * @param values - The values to be put into a buffer.
     * @return The buffer.
     */
    public static DoubleBuffer createDoubleBuffer(double... values) {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}
