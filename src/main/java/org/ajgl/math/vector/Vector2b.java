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

package org.ajgl.math.vector;

import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;

/**
 * This class is designed to be a 2d vector.
 * @author Tyler Bucher
 */
public class Vector2b {
    
    public byte x;    // 1st dimensional value
    public byte y;    // 2nd dimensional value
    
    public ByteBuffer buffer;
    
    /**
     * Default Vector2d constructor.
     */
    public Vector2b() {
        this.x = 0;
        this.y = 0;
        
        buffer = MemoryUtil.memAlloc(2);
        updateBuffer();
    }
    
    /**
     * Creates a Vector2d with three values.
     * @param x - x value.
     * @param y - y value.
     */
    public Vector2b(byte x, byte y) {
        this.x = x;
        this.y = y;
        
        buffer = MemoryUtil.memAlloc(2);
        updateBuffer();
    }
    
    /**
     * Duplicates a vector to the current vector.
     * @param vector - Vector to be duplicated.
     */
    public Vector2b(Vector2b vector) {
        this.x = vector.x;
        this.y = vector.y;
        
        buffer = MemoryUtil.memAlloc(2);
        updateBuffer();
    }
    
    /**
     * Adds two vectors together.
     * @param vector - the vector to be added.
     * @return This vector.
     */
    public Vector2b add(Vector2b vector) {
        this.x += vector.x;
        this.y += vector.y;
        
        return this;
    }
    
    /**
     * Subtracts to vectors from each other.
     * @param vector - Vector to be subtracted.
     * @return This vector.
     */
    public Vector2b subtract(Vector2b vector) {
        this.x += -vector.x;
        this.y += -vector.y;
        
        return this;
    }
    
    /**
     * Performs the dot product on this vector.
     * @param vector - Vector to be dotted with.
     * @return The resulting number.
     */
    public byte dot(Vector2b vector) {
        return (byte) ((this.x *= vector.x) + (this.y *= vector.y));
    }
    
    /**
     * Performs the cross product on this vector.
     * @param vector - Vector to be crossed with.
     * @return The resulting vector.
     */
    public Vector3b cross(Vector2b vector) {
        byte cz = (byte) ((this.x * vector.y) - (this.y * vector.x));
        
        return new Vector3b((byte) 0,(byte)  0, cz);
    }
    
    /**
     * Negates this vector.
     */
    public Vector2b negate() {
        x = (byte) -x;
        y = (byte) -y;
        
        return this;
    }
    
    /**
     * Normalizes this vector.
     */
    public Vector2b normalize() {
        byte mag = this.getMagnitude();
        this.x /= mag;
        this.y /= mag;
        
        return this;
    }
    
    /**
     * Scales this vector by a value scalar value.
     */
    public Vector2b scale(byte value) {
        this.x *= value;
        this.y *= value;
        
        return this;
    }
    
    /**
     * Returns the magnitude of this vector.
     */
    public byte getMagnitude() {
        return (byte) Math.hypot(x, y);
    }
    
    /**
     * Updates the buffer version of this matrix.
     */
    public void updateBuffer() {
        buffer.clear();
        buffer.put(x).put(y);
        buffer.flip();
    }

    @Override
    public String toString() {
        return "Vector2b [x=" + x + ", y=" + y + "]";
    }
    
    @Override
    protected void finalize() {
        MemoryUtil.memFree(buffer);
    }
}
