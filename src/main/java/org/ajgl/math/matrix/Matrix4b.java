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

import java.nio.ByteBuffer;

import org.ajgl.math.vector.Vector4b;
import org.lwjgl.BufferUtils;

/**
 * This class is designed to be a 4x4 matrix.
 * @author Tyler Bucher
 */
public class Matrix4b extends Matrix3b {

    public byte /*m00, m01, m02,*/ m03;   // First row
    public byte /*m10, m11, m12,*/ m13;   // Second row
    public byte /*m20, m21, m22,*/ m23;   // Third row
    public byte   m30, m31, m32,   m33;   // Forth row
    
    /**
     * Default Matrix constructor.
     */
    public Matrix4b() {
        this.loadIdentity();
    }
    
    /**
     * Copies a matrix to this matrix.
     * @param matrix - Matrix to be copied.
     */
    public Matrix4b(Matrix4b matrix) {
        Matrix4b.copyMatrix(matrix, this);
    }
    
    /**
     * Loads the identity matrix.
     */
    @Override
    public Matrix4b loadIdentity() {
        super.loadIdentity();      m03 = 0;
                                   m13 = 0;
                                   m23 = 0;
        m30 = 0; m31 = 0; m32 = 0; m33 = 1;
        
        return this;
    }
    
    /**
     * Adds a matrix to this matrix.
     * @param matrix - Matrix to be added.
     * @return This matrix.
     */
    public Matrix4b add(Matrix4b matrix) {
        super.add(matrix);                                       m03 += matrix.m03;
                                                                 m13 += matrix.m13;
                                                                 m23 += matrix.m23;
        m30 += matrix.m30; m31 += matrix.m31; m32 += matrix.m32; m33 += matrix.m33;
        
        return this;
    }
    
    /**
     * Subtracts a matrix from this matrix.
     * @param matrix - Matrix to be subtracted.
     * @return This matrix.
     */
    public Matrix4b subtract(Matrix4b matrix) {
        super.add(matrix);                                       m03 -= matrix.m03;
                                                                 m13 -= matrix.m13;
                                                                 m23 -= matrix.m23;
        m30 -= matrix.m30; m31 -= matrix.m31; m32 -= matrix.m32; m33 -= matrix.m33;
        
        return this;
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * @param matrix - Matrix to be multiplied.
     * @return This Matrix.
     */
    public Matrix4b multiply(Matrix4b matrix) {
        Matrix4b orig = new Matrix4b(this);
        
        m00 = (byte) ((orig.m00*matrix.m00)+(orig.m01*matrix.m10)+(orig.m02*matrix.m20)+(orig.m03*matrix.m30));
        m10 = (byte) ((orig.m10*matrix.m00)+(orig.m11*matrix.m10)+(orig.m12*matrix.m20)+(orig.m13*matrix.m30));
        m20 = (byte) ((orig.m20*matrix.m00)+(orig.m21*matrix.m10)+(orig.m22*matrix.m20)+(orig.m23*matrix.m30));
        m30 = (byte) ((orig.m20*matrix.m00)+(orig.m21*matrix.m10)+(orig.m22*matrix.m20)+(orig.m33*matrix.m30));
        
        m01 = (byte) ((orig.m00*matrix.m01)+(orig.m01*matrix.m11)+(orig.m02*matrix.m21)+(orig.m03*matrix.m31));
        m11 = (byte) ((orig.m10*matrix.m01)+(orig.m11*matrix.m11)+(orig.m12*matrix.m21)+(orig.m13*matrix.m31));
        m21 = (byte) ((orig.m20*matrix.m01)+(orig.m21*matrix.m11)+(orig.m22*matrix.m21)+(orig.m23*matrix.m31));
        m31 = (byte) ((orig.m30*matrix.m01)+(orig.m31*matrix.m11)+(orig.m32*matrix.m21)+(orig.m33*matrix.m31));
        
        m02 = (byte) ((orig.m00*matrix.m02)+(orig.m01*matrix.m12)+(orig.m02*matrix.m22)+(orig.m03*matrix.m32));
        m12 = (byte) ((orig.m10*matrix.m02)+(orig.m11*matrix.m12)+(orig.m12*matrix.m22)+(orig.m13*matrix.m32));
        m22 = (byte) ((orig.m20*matrix.m02)+(orig.m21*matrix.m12)+(orig.m22*matrix.m22)+(orig.m23*matrix.m32));
        m32 = (byte) ((orig.m30*matrix.m02)+(orig.m31*matrix.m12)+(orig.m32*matrix.m22)+(orig.m33*matrix.m32));
        
        m03 = (byte) ((orig.m00*matrix.m03)+(orig.m01*matrix.m13)+(orig.m02*matrix.m23)+(orig.m03*matrix.m33));
        m13 = (byte) ((orig.m10*matrix.m03)+(orig.m11*matrix.m13)+(orig.m12*matrix.m23)+(orig.m13*matrix.m33));
        m23 = (byte) ((orig.m20*matrix.m03)+(orig.m21*matrix.m13)+(orig.m22*matrix.m23)+(orig.m23*matrix.m33));
        m33 = (byte) ((orig.m30*matrix.m03)+(orig.m31*matrix.m13)+(orig.m32*matrix.m23)+(orig.m33*matrix.m33));
        
        return this;
    }
    
    /**
     * Multiplies this matrix by a scalar value.
     */
    @Override
    public Matrix4b multiply(byte value) {
        super.multiply(value);                     m03 *= value;
                                                   m13 *= value;
                                                   m23 *= value;
         m30 *= value; m31 *= value; m32 *= value; m33 *= value;
         
        return this;
    }
    
    /**
     * Divides this matrix by a scalar value.
     */
    @Override
    public Matrix4b divide(byte value) {
        super.divide(value);                      m03 /= value;
                                                  m13 /= value;
                                                  m23 /= value;
        m30 /= value; m31 /= value; m32 /= value; m33 /= value;
        
        return this;
    }
    
    /**
     * Negates this matrix.
     */
    @Override
    public Matrix4b negate() {
        return this.multiply((byte) -1);
    }
    
    /**
     * Returns the buffer version of this matrix.
     */
    @Override
    public ByteBuffer getBuffer() {
        byte[] array = {m00, m10, m20, m30,
                          m01, m11, m21, m31,
                          m02, m12, m22, m32,
                          m03, m13, m32, m33};
        ByteBuffer buffer = BufferUtils.createByteBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a matrix by four vectors.
     * @param col1 - First column.
     * @param col2 - Second column.
     * @param col3 - Third column.
     * @param col4 - Fourth column.
     * @return The new matrix.
     */
    public static Matrix4b createMatrix(Vector4b col1, Vector4b col2, Vector4b col3, Vector4b col4) {
        Matrix4b matrix = new Matrix4b();
        matrix.m00 = col1.x; matrix.m01 = col2.x; matrix.m02 = col3.x; matrix.m03 = col4.x;
        matrix.m10 = col1.y; matrix.m11 = col2.y; matrix.m12 = col3.y; matrix.m13 = col4.y;
        matrix.m20 = col1.z; matrix.m21 = col2.z; matrix.m22 = col3.z; matrix.m23 = col4.z;
        matrix.m30 = col1.w; matrix.m31 = col2.w; matrix.m32 = col3.w; matrix.m33 = col4.w;
        
        return matrix;
    }
    
    /**
     * Copies a matrix.
     * @param src - Source matrix.
     * @param des - destination matrix.
     * @return The destination matrix.
     */
    public static Matrix4b copyMatrix(Matrix4b src, Matrix4b des) {
        des.m00 = src.m00; des.m01 = src.m01; des.m02 = src.m02; des.m03 = src.m03;
        des.m10 = src.m10; des.m11 = src.m11; des.m12 = src.m12; des.m13 = src.m13;
        des.m20 = src.m20; des.m21 = src.m21; des.m22 = src.m22; des.m23 = src.m23;
        des.m30 = src.m30; des.m31 = src.m31; des.m32 = src.m32; des.m33 = src.m33;
        
        return des;
    }
    
    /**
     * Creates a orthographic matrix.
     * @param left - Left clipping plane.
     * @param right - Right clipping plane.
     * @param bottom - Bottom clipping plane.
     * @param top - Top clipping plane.
     * @param near - Near clipping plane.
     * @param far - Far clipping plane.
     * @return The orthographic matrix.
     */
    public static Matrix4b orthographic(byte left, byte right, byte bottom, byte top, byte near, byte far) {
        Matrix4b ortho = new Matrix4b();

        ortho.m00 = (byte) (2 / (right - left)); ortho.m03 = (byte) (-(right + left) / (right - left));
        ortho.m11 = (byte) (2 / (top - bottom)); ortho.m13 = (byte) (-(top + bottom) / (top - bottom));
        ortho.m22 = (byte) (-2 / (far - near));  ortho.m23 = (byte) (-(far + near) / (far - near));
        ortho.m33 = 1;

        return ortho;
    }
    
    /**
     * Creates a frustrum matrix.
     * @param left - Left frustrum plane.
     * @param right - Right frustrum plane.
     * @param bottom - Bottom frustrum plane.
     * @param top - Top frustrum plane.
     * @param near - Near frustrum plane.
     * @param far - Far frustrum plane.
     * @return The frustrum matrix.
     */
    public static Matrix4b frustum(byte left, byte right, byte bottom, byte top, byte near, byte far) {
        Matrix4b frustum = new Matrix4b();

        frustum.m00 = (byte) ((2 * near) / (right - left)); frustum.m02 = (byte) ((right + left) / (right - left));
        frustum.m11 = (byte) ((2 * near) / (top - bottom)); frustum.m12 = (byte) ((top + bottom) / (top - bottom));
        frustum.m22 = (byte) (-(far + near) / (far - near)); frustum.m23 = (byte) (-(2 * far * near) / (far - near));
        frustum.m32 = -1;                          frustum.m33 = 0;

        return frustum;
    }
    
    /**
     * Creates a perspective matrix.
     * @param fovy - Field of view in the y direction.
     * @param aspect - Aspect ratio.
     * @param near - Near clipping plane.
     * @param far - Far clipping plane.
     * @return The perspective matrix.
     */
    public static Matrix4b perspective(byte fovy, byte aspect, byte near, byte far) {
        Matrix4b perspective = new Matrix4b();

        byte f = (byte) (1 / Math.tan(Math.toRadians(fovy) / 2));

        perspective.m00 = (byte) (f / aspect);
        perspective.m11 = f;
        perspective.m22 = (byte) ((far + near) / (near - far)); perspective.m23 = (byte) ((2 * far * near) / (near - far));
        perspective.m32 = -1;                         perspective.m33 = 0;

        return perspective;
    }
}
