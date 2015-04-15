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

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;


/**
 * This class is a utility class designed to 
 * help with vector related tasks.
 * @author Tyler Bucher
 */
public class VectorUtils {
    
    /**
     * Generates a specific buffer with values.
     * @param bufferClass - The buffer to receive.
     * @param values - Values to be added to the buffer.
     * @return The generated buffer.
     */
    public static <B extends Buffer> Buffer glGenDataBuffer(Class<B> bufferClass, double... values) {
        Class<?> clazz = bufferClass.getClass();
        if(clazz == ByteBuffer.class) return MathUtils.createByteBuffer(VectorUtils.arrayCastByte(values));
        else if(clazz == ShortBuffer.class) return MathUtils.createShortBuffer(VectorUtils.arrayCastShort(values));
        else if(clazz == IntBuffer.class) return MathUtils.createIntegerBuffer(VectorUtils.arrayCastInt(values));
        else if(clazz == LongBuffer.class) return MathUtils.createLongBuffer(VectorUtils.arrayCastLong(values));
        else if(clazz == FloatBuffer.class) return MathUtils.createFloatBuffer(VectorUtils.arrayCastFloat(values));
        else if(clazz == DoubleBuffer.class) return MathUtils.createDoubleBuffer(values);
        else return null;
    }
    
    /**
     * Casts a double array to a byte array.
     * @param values - Values to be casted.
     * @return the casted values.
     */
    public static byte[] arrayCastByte(double... values) {
        byte[] array = new byte[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (byte) values[i];
        return array;
    }
    
    /**
     * Casts a double array to a short array.
     * @param values - Values to be casted.
     * @return the casted values.
     */
    public static short[] arrayCastShort(double... values) {
        short[] array = new short[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (short) values[i];
        return array;
    }
    
    /**
     * Casts a double array to a int array.
     * @param values - Values to be casted.
     * @return the casted values.
     */
    public static int[] arrayCastInt(double... values) {
        int[] array = new int[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (int) values[i];
        return array;
    }
    
    /**
     * Casts a double array to a long array.
     * @param values - Values to be casted.
     * @return the casted values.
     */
    public static long[] arrayCastLong(double... values) {
        long[] array = new long[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (long) values[i];
        return array;
    }
    
    /**
     * Casts a double array to a float array.
     * @param values - Values to be casted.
     * @return the casted values.
     */
    public static float[] arrayCastFloat(double... values) {
        float[] array = new float[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (float) values[i];
        return array;
    }
}
