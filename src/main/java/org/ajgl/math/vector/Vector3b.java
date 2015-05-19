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

import java.nio.Buffer;

import org.ajgl.math.VectorUtils;


/**
 * This class is designed to be a 3d vector.
 * @author Tyler Bucher
 */
public class Vector3b extends Vector2b {

    public byte z;    // 3rd dimensional value
    
    /**
     * Default Vector3d constructor.
     */
    public Vector3b() {
        super();
        this.z = 0;
    }
    
    /**
     * Creates a Vector3d with three values.
     * @param x - x value.
     * @param y - y value.
     * @param z - z value.
     */
    public Vector3b(byte x, byte y, byte z) {
        super(x, y);
        this.z = z;
    }
    
    /**
     * Duplicates a vector to the current vector.
     * @param vector - Vector to be duplicated.
     */
    public Vector3b(Vector3b vector) {
        super(vector);
        this.z = vector.z;
    }
    
    /**
     * Adds two vectors together.
     * @param vector - the vector to be added.
     * @return This vector.
     */
    public Vector3b add(Vector3b vector) {
        super.add(vector);
        this.z += vector.z;
        
        return this;
    }
    
    /**
     * Subtracts to vectors from each other.
     * @param vector - Vector to be subtracted.
     * @return This vector.
     */
    public Vector3b subtract(Vector3b vector) {
        super.subtract(vector);
        this.z += -vector.z;
        
        return this;
    }
    
    /**
     * Performs the dot product on this vector.
     * @param vector - Vector to be dotted with.
     * @return The resulting number.
     */
    public byte dot(Vector3b vector) {
        return (byte) (super.dot(vector) + (this.z *= vector.z));
    }
    
    /**
     * Performs the cross product on this vector.
     * @param vector - Vector to be crossed with.
     * @return The resulting vector.
     */
    public Vector3b cross(Vector3b vector) {
        byte cx = (byte) ((this.y * vector.z) - (this.z * vector.y));
        byte cy = (byte) ((this.z * vector.x) - (this.x * vector.z));
        byte cz = (byte) ((this.x * vector.y) - (this.y * vector.x));
        
        return new Vector3b(cx, cy, cz);
    }
    
    /**
     * Negates this vector.
     */
    @Override
    public Vector3b negate() {
        super.negate();
        z = (byte) -z;
        
        return this;
    }
    
    /**
     * Normalizes this vector.
     */
    @Override
    public Vector3b normalize() {
        byte mag = this.getMagnitude();
        this.x /= mag;
        this.y /= mag;
        this.z /= mag;
        
        return this;
    }
    
    /**
     * Scales this vector by a value scalar value.
     */
    @Override
    public Vector3b scale(byte value) {
        super.scale(value);
        this.z *= value;
        
        return this;
    }
    
    /**
     * Returns the magnitude of this vector.
     */
    @Override
    public byte getMagnitude() {
        return (byte) Math.sqrt((x*x)+(y*y)+(z*z));
    }
    
    /**
     * Returns the buffer version of this vector.
     */
    @Override
    public <B extends Buffer> B getBuffer(Class<B> bufferClass) {
        return bufferClass.cast(VectorUtils.glGenDataBuffer(bufferClass, x, y, z));
    }
}
