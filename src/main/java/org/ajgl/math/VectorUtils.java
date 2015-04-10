/**
 * 
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
 * @author Tyler
 *
 */
public class VectorUtils {
    
    //@SafeVarargs
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
    
    public static byte[] arrayCastByte(double... values) {
        byte[] array = new byte[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (byte) values[i];
        return array;
    }
    
    public static short[] arrayCastShort(double... values) {
        short[] array = new short[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (short) values[i];
        return array;
    }
    
    public static int[] arrayCastInt(double... values) {
        int[] array = new int[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (int) values[i];
        return array;
    }
    
    public static long[] arrayCastLong(double... values) {
        long[] array = new long[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (long) values[i];
        return array;
    }
    
    public static float[] arrayCastFloat(double... values) {
        float[] array = new float[values.length];
        for(int i=0;i<values.length;i++)
            array[i] = (float) values[i];
        return array;
    }
}
