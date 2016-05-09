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

import org.ajgl.math.vector.Vector3b;
import org.lwjgl.system.MemoryUtil;

/**
 * This class is designed to be a 3x3 matrix.
 * @author Tyler Bucher
 */
public class Matrix3b extends Matrix2b {
    
    public byte /*m00, m01,*/ m02;    // First row
    public byte /*m10, m11,*/ m12;    // Second row
    public byte   m20, m21,   m22;    // Third row
    
    /**
     * Default Matrix constructor.
     */
    public Matrix3b() {
        this.loadIdentity();
        buffer = MemoryUtil.memAlloc(9);
        updateBuffer();
    }
    
    /**
     * Copies a matrix to this matrix.
     * @param matrix - Matrix to be copied.
     */
    public Matrix3b(Matrix3b matrix) {
        Matrix3b.copyMatrix(matrix, this);
        buffer = MemoryUtil.memAlloc(9);
        updateBuffer();
    }
    
    /**
     * Loads the identity matrix.
     */
    @Override
    public Matrix3b loadIdentity() {
        super.loadIdentity(); m02 = 0;
                              m12 = 0;
        m20 = 0; m21 = 0;     m22 = 1;
        
        return this;
    }
    
    /**
     * Adds a matrix to this matrix.
     * @param matrix - Matrix to be added.
     * @return This matrix.
     */
    public Matrix3b add(Matrix3b matrix) {
        super.add(matrix);                    m02 += matrix.m02;
                                              m12 += matrix.m12;
        m20 += matrix.m20; m21 += matrix.m21; m22 += matrix.m22;
        
        return this;
    }
    
    /**
     * Subtracts a matrix from this matrix.
     * @param matrix - Matrix to be subtracted.
     * @return This matrix.
     */
    public Matrix3b subtract(Matrix3b matrix) {
        super.subtract(matrix);               m02 -= matrix.m02;
                                              m12 -= matrix.m12;
        m20 -= matrix.m20; m21 -= matrix.m21; m22 -= matrix.m22;
        
        return this;
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * @param matrix - Matrix to be multiplied.
     * @return This Matrix.
     */
    public Matrix3b multiply(Matrix3b matrix) {
        Matrix3b orig = new Matrix3b(this);
        
        m00 = (byte) ((orig.m00*matrix.m00)+(orig.m01*matrix.m10)+(orig.m02*matrix.m20));
        m10 = (byte) ((orig.m10*matrix.m00)+(orig.m11*matrix.m10)+(orig.m12*matrix.m20));
        m20 = (byte) ((orig.m20*matrix.m00)+(orig.m21*matrix.m10)+(orig.m22*matrix.m20));
        
        m01 = (byte) ((orig.m00*matrix.m01)+(orig.m01*matrix.m11)+(orig.m02*matrix.m21));
        m11 = (byte) ((orig.m10*matrix.m01)+(orig.m11*matrix.m11)+(orig.m12*matrix.m21));
        m21 = (byte) ((orig.m20*matrix.m01)+(orig.m21*matrix.m11)+(orig.m22*matrix.m21));
        
        m02 = (byte) ((orig.m00*matrix.m02)+(orig.m01*matrix.m12)+(orig.m02*matrix.m22));
        m12 = (byte) ((orig.m10*matrix.m02)+(orig.m11*matrix.m12)+(orig.m12*matrix.m22));
        m22 = (byte) ((orig.m20*matrix.m02)+(orig.m21*matrix.m12)+(orig.m22*matrix.m22));
        
        return this;
    }
    
    /**
     * Multiplies this matrix by a scalar value.
     */
    @Override
    public Matrix3b multiply(byte value) {
        super.multiply(value);      m02 *= value;
                                    m12 *= value;
        m20 *= value; m21 *= value; m22 *= value;
        
        return this;
    }
    
    /**
     * Divides this matrix by a scalar value.
     */
    @Override
    public Matrix3b divide(byte value) {
        super.divide(value);        m02 /= value;
                                    m12 /= value;
        m20 /= value; m21 /= value; m22 /= value;
        
        return this;
    }
    
    /**
     * Negates this matrix.
     */
    @Override
    public Matrix3b negate() {
        return this.multiply((byte) -1);
    }
    
    /**
     * Updates the buffer version of this matrix.
     */
    @Override
    public void updateBuffer() {
        buffer.clear();
        buffer.put(m00).put(m10).put(m20)
                .put(m01).put(m11).put(m21)
                .put(m02).put(m12).put(m22);
        buffer.flip();
    }
    
    /**
     * Creates a matrix by four vectors.
     * @param col1 - First column.
     * @param col2 - Second column.
     * @param col3 - Third column.
     * @return The new matrix.
     */
    public static Matrix3b createMatrix(Vector3b col1, Vector3b col2, Vector3b col3) {
        Matrix3b matrix = new Matrix3b();
        matrix.m00 = col1.x; matrix.m01 = col2.x; matrix.m02 = col3.x;
        matrix.m10 = col1.y; matrix.m11 = col2.y; matrix.m12 = col3.y;
        matrix.m20 = col1.z; matrix.m21 = col2.z; matrix.m22 = col3.z;
        
        return matrix;
    }
    
    /**
     * Copies a matrix.
     * @param src - Source matrix.
     * @param des - destination matrix.
     * @return The destination matrix.
     */
    public static Matrix3b copyMatrix(Matrix3b src, Matrix3b des) {
        des.m00 = src.m00; des.m01 = src.m01; des.m02 = src.m02;
        des.m10 = src.m10; des.m11 = src.m11; des.m12 = src.m12;
        des.m20 = src.m20; des.m21 = src.m21; des.m22 = src.m22;
        
        return des;
    }
    
    @Override
    public String toString() {
        return "Matrix3b [m00=" + m00 + ", m01=" + m01 + ", m02=" + m02 + ",\n" +
        		"          m10=" + m10 + ", m11=" + m11 + ", m12=" + m12 + ",\n" +
        		"          m20=" + m20 + ", m21=" + m21 + ", m22=" + m22 + "]";
    }
    
    @Override
    protected void finalize() {
        MemoryUtil.memFree(buffer);
    }
}
