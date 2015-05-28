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

package org.ajgl.math.matrix;

import java.nio.LongBuffer;

import org.ajgl.math.vector.Vector2l;
import org.lwjgl.BufferUtils;

/**
 * This class is designed to be a 2x2 matrix.
 * @author Tyler Bucher
 */
public class Matrix2l {
    
    public long m00, m01; // First row
    public long m10, m11; // Second row
    
    /**
     * Default Matrix constructor.
     */
    public Matrix2l() {
        this.loadIdentity();
    }
    
    /**
     * Copies a matrix to this matrix.
     * @param matrix - Matrix to be copied.
     */
    public Matrix2l(Matrix2l matrix) {
        Matrix2l.copyMatrix(matrix, this);
    }
    
    /**
     * Loads the identity matrix.
     */
    public Matrix2l loadIdentity() {
        m00 = 1; m01 = 0;
        m10 = 0; m11 = 1;
        
        return this;
    }
    
    /**
     * Adds a matrix to this matrix.
     * @param matrix - Matrix to be added.
     * @return This matrix.
     */
    public Matrix2l add(Matrix2l matrix) {
        m00 += matrix.m00; m01 += matrix.m01;
        m10 += matrix.m10; m11 += matrix.m11;
        
        return this;
    }
    
    /**
     * Subtracts a matrix from this matrix.
     * @param matrix - Matrix to be subtracted.
     * @return This matrix.
     */
    public Matrix2l subtract(Matrix2l matrix) {
        m00 -= matrix.m00; m01 -= matrix.m01;
        m10 -= matrix.m10; m11 -= matrix.m11;
        
        return this;
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * @param matrix - Matrix to be multiplied.
     * @return This Matrix.
     */
    public Matrix2l multiply(Matrix2l matrix) {
        Matrix2l orig = new Matrix2l(this);
        
        m00 = (orig.m00*matrix.m00)+(orig.m01*matrix.m10);
        m10 = (orig.m10*matrix.m00)+(orig.m11*matrix.m10);
        
        m01 = (orig.m00*matrix.m01)+(orig.m01*matrix.m11);
        m11 = (orig.m10*matrix.m01)+(orig.m11*matrix.m11);
        
        return this;
    }
    
    /**
     * Multiplies this matrix by a scalar value.
     */
    public Vector2l multiply(Vector2l vector) {
        Vector2l newVector = new Vector2l();
        newVector.x = (vector.x*this.m00) + (vector.y*this.m10);
        newVector.y = (vector.x*this.m01) + (vector.y*this.m11);
        
        return newVector;
    }
    
    public Matrix2l multiply(long value) {
        m00 *= value; m01 *= value;
        m10 *= value; m11 *= value;
        
        return this;
    }
    
    /**
     * Divides this matrix by a scalar value.
     */
    public Matrix2l divide(long value) {
        m00 /= value; m01 /= value;
        m10 /= value; m11 /= value;
        
        return this;
    }
    
    /**
     * Negates this matrix.
     */
    public Matrix2l negate() {
        return this.multiply(-1);
    }
    
    /**
     * Returns the buffer version of this matrix.
     */
    public LongBuffer getBuffer() {
        long[] array = {m00, m10,
                          m01, m11};
        LongBuffer buffer = BufferUtils.createLongBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a matrix by four vectors.
     * @param col1 - First column.
     * @param col2 - Second column.
     * @param col3 - Third column.
     * @return The new matrix.
     */
    public static Matrix2l createMatrix(Vector2l col1, Vector2l col2) {
        Matrix2l matrix = new Matrix2l();
        matrix.m00 = col1.x; matrix.m01 = col2.x;
        matrix.m10 = col1.y; matrix.m11 = col2.y;
        
        return matrix;
    }
    
    /**
     * Copies a matrix.
     * @param src - Source matrix.
     * @param des - destination matrix.
     * @return The destination matrix.
     */
    public static Matrix2l copyMatrix(Matrix2l src, Matrix2l des) {
        des.m00 = src.m00; des.m01 = src.m01;
        des.m10 = src.m10; des.m11 = src.m11;
        
        return des;
    }
    
    @Override
    public String toString() {
        return "Matrix2b [m00=" + m00 + ", m01=" + m01 + ",\n" +
                "          m10=" + m10 + ", m11=" + m11 + "]";
    }
}
