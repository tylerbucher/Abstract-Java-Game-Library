package org.ajgl.math;

import java.nio.Buffer;

import org.lwjgl.BufferUtils;

public class MathUtil {

    public static <T extends Number> Buffer glGenDataBuffer(T object, int size) {
        if(object instanceof Byte) {
            
        } else if(object instanceof Short) {
            
        } else if(object instanceof Integer) {
            
        } else if(object instanceof Long) {
            
        } else if(object instanceof Float) {
            
        } else if(object instanceof Double) {
            return BufferUtils.createDoubleBuffer(size);
        }
        return null;
    }
}
