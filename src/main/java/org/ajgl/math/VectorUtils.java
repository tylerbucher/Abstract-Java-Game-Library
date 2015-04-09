/**
 * 
 */
package org.ajgl.math;

import java.nio.Buffer;


/**
 * @author Tyler
 *
 */
public class VectorUtils {
    
    @SafeVarargs
    public static <T extends Number> Buffer glGenDataBuffer(T... values) {
        Class<?> clazz = values.getClass().getComponentType();
        if(clazz == Byte.class)
            return MathUtils.createByteBuffer((Byte[]) values);
        else if(clazz == Short.class)
            return MathUtils.createShortBuffer((Short[]) values);
        else if(clazz == Integer.class)
            return MathUtils.createIntegerBuffer((Integer[]) values);
        else if(clazz == Long.class)
            return MathUtils.createLongBuffer((Long[]) values);
        else if(clazz == Float.class)
            return MathUtils.createFloatBuffer((Float[]) values);
        else if(clazz == Double.class)
            return MathUtils.createDoubleBuffer((Double[]) values);
        return null;
    }
}
