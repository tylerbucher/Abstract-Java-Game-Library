/**
 * 
 */
package org.ajgl.test.math;

import org.ajgl.math.matrix.Matrix2b;
import org.ajgl.math.matrix.Matrix2d;
import org.ajgl.math.matrix.Matrix2f;
import org.ajgl.math.matrix.Matrix2i;
import org.ajgl.math.matrix.Matrix2l;
import org.ajgl.math.matrix.Matrix2s;
import org.ajgl.math.matrix.Matrix3b;
import org.ajgl.math.matrix.Matrix3d;
import org.ajgl.math.matrix.Matrix3f;
import org.ajgl.math.matrix.Matrix3i;
import org.ajgl.math.matrix.Matrix3l;
import org.ajgl.math.matrix.Matrix3s;
import org.ajgl.math.matrix.Matrix4b;
import org.ajgl.math.matrix.Matrix4d;
import org.ajgl.math.matrix.Matrix4f;
import org.ajgl.math.matrix.Matrix4i;
import org.ajgl.math.matrix.Matrix4l;
import org.ajgl.math.matrix.Matrix4s;
import org.ajgl.math.vector.Vector4b;
import org.ajgl.math.vector.Vector4d;
import org.ajgl.math.vector.Vector4f;
import org.ajgl.math.vector.Vector4i;
import org.ajgl.math.vector.Vector4l;
import org.ajgl.math.vector.Vector4s;


/**
 * @author Tyler
 *
 */
public class MatrixTest {
    
    private static Vector4b col1b = new Vector4b((byte) 1, (byte) 5, (byte) 9, (byte) 13);
    private static Vector4b col2b = new Vector4b((byte) 2, (byte) 6, (byte) 10, (byte) 14);
    private static Vector4b col3b = new Vector4b((byte) 3, (byte) 7, (byte) 11, (byte) 15);
    private static Vector4b col4b = new Vector4b((byte) 4, (byte) 8, (byte) 12, (byte) 16);
    
    private static Vector4s col1s = new Vector4s((short) 1, (short) 5, (short) 9, (short) 13);
    private static Vector4s col2s = new Vector4s((short) 2, (short) 6, (short) 10, (short) 14);
    private static Vector4s col3s = new Vector4s((short) 3, (short) 7, (short) 11, (short) 15);
    private static Vector4s col4s = new Vector4s((short) 4, (short) 8, (short) 12, (short) 16);
    
    private static Vector4i col1i = new Vector4i(1, 5, 9, 13);
    private static Vector4i col2i = new Vector4i(2, 6, 10, 14);
    private static Vector4i col3i = new Vector4i(3, 7, 11, 15);
    private static Vector4i col4i = new Vector4i(4, 8, 12, 16);
    
    private static Vector4l col1l = new Vector4l(1l, 5l, 9l, 13l);
    private static Vector4l col2l = new Vector4l(2l, 6l, 10l, 14l);
    private static Vector4l col3l = new Vector4l(3l, 7l, 11l, 15l);
    private static Vector4l col4l = new Vector4l(4l, 8l, 12l, 16l);
    
    private static Vector4f col1f = new Vector4f(1.0f, 5.0f, 9.0f, 13.0f);
    private static Vector4f col2f = new Vector4f(2.0f, 6.0f, 10.0f, 14.0f);
    private static Vector4f col3f = new Vector4f(3.0f, 7.0f, 11.0f, 15.0f);
    private static Vector4f col4f = new Vector4f(4.0f, 8.0f, 12.0f, 16.0f);
    
    private static Vector4d col1d = new Vector4d(1.0, 5.0, 9.0, 13.0);
    private static Vector4d col2d = new Vector4d(2.0, 6.0, 10.0, 14.0);
    private static Vector4d col3d = new Vector4d(3.0, 7.0, 11.0, 15.0);
    private static Vector4d col4d = new Vector4d(4.0, 8.0, 12.0, 16.0);
    
    public static void add() {
        System.out.println(new Matrix2b().add(Matrix2b.createMatrix(col1b, col2b)));
        System.out.println(new Matrix2s().add(Matrix2s.createMatrix(col1s, col2s)));
        System.out.println(new Matrix2i().add(Matrix2i.createMatrix(col1i, col2i)));
        System.out.println(new Matrix2l().add(Matrix2l.createMatrix(col1l, col2l)));
        System.out.println(new Matrix2f().add(Matrix2f.createMatrix(col1f, col2f)));
        System.out.println(new Matrix2d().add(Matrix2d.createMatrix(col1d, col2d)));
        System.out.println(new Matrix3b().add(Matrix3b.createMatrix(col1b, col2b, col3b)));
        System.out.println(new Matrix3s().add(Matrix3s.createMatrix(col1s, col2s, col3s)));
        System.out.println(new Matrix3i().add(Matrix3i.createMatrix(col1i, col2i, col3i)));
        System.out.println(new Matrix3l().add(Matrix3l.createMatrix(col1l, col2l, col3l)));
        System.out.println(new Matrix3f().add(Matrix3f.createMatrix(col1f, col2f, col3f)));
        System.out.println(new Matrix3d().add(Matrix3d.createMatrix(col1d, col2d, col3d)));
        System.out.println(new Matrix4b().add(Matrix4b.createMatrix(col1b, col2b, col3b, col4b)));
        System.out.println(new Matrix4s().add(Matrix4s.createMatrix(col1s, col2s, col3s, col4s)));
        System.out.println(new Matrix4i().add(Matrix4i.createMatrix(col1i, col2i, col3i, col4i)));
        System.out.println(new Matrix4l().add(Matrix4l.createMatrix(col1l, col2l, col3l, col4l)));
        System.out.println(new Matrix4f().add(Matrix4f.createMatrix(col1f, col2f, col3f, col4f)));
        System.out.println(new Matrix4d().add(Matrix4d.createMatrix(col1d, col2d, col3d, col4d)));
    }
    
    public static void subtract() {
        System.out.println(new Matrix2b().subtract(Matrix2b.createMatrix(col1b, col2b)));
        System.out.println(new Matrix2s().subtract(Matrix2s.createMatrix(col1s, col2s)));
        System.out.println(new Matrix2i().subtract(Matrix2i.createMatrix(col1i, col2i)));
        System.out.println(new Matrix2l().subtract(Matrix2l.createMatrix(col1l, col2l)));
        System.out.println(new Matrix2f().subtract(Matrix2f.createMatrix(col1f, col2f)));
        System.out.println(new Matrix2d().subtract(Matrix2d.createMatrix(col1d, col2d)));
        System.out.println(new Matrix3b().subtract(Matrix3b.createMatrix(col1b, col2b, col3b)));
        System.out.println(new Matrix3s().subtract(Matrix3s.createMatrix(col1s, col2s, col3s)));
        System.out.println(new Matrix3i().subtract(Matrix3i.createMatrix(col1i, col2i, col3i)));
        System.out.println(new Matrix3l().subtract(Matrix3l.createMatrix(col1l, col2l, col3l)));
        System.out.println(new Matrix3f().subtract(Matrix3f.createMatrix(col1f, col2f, col3f)));
        System.out.println(new Matrix3d().subtract(Matrix3d.createMatrix(col1d, col2d, col3d)));
        System.out.println(new Matrix4b().subtract(Matrix4b.createMatrix(col1b, col2b, col3b, col4b)));
        System.out.println(new Matrix4s().subtract(Matrix4s.createMatrix(col1s, col2s, col3s, col4s)));
        System.out.println(new Matrix4i().subtract(Matrix4i.createMatrix(col1i, col2i, col3i, col4i)));
        System.out.println(new Matrix4l().subtract(Matrix4l.createMatrix(col1l, col2l, col3l, col4l)));
        System.out.println(new Matrix4f().subtract(Matrix4f.createMatrix(col1f, col2f, col3f, col4f)));
        System.out.println(new Matrix4d().subtract(Matrix4d.createMatrix(col1d, col2d, col3d, col4d)));
    }

    public static void multiplyMatrix() {
        System.out.println(new Matrix2b().multiply(Matrix2b.createMatrix(col1b, col2b)));
        System.out.println(new Matrix2s().multiply(Matrix2s.createMatrix(col1s, col2s)));
        System.out.println(new Matrix2i().multiply(Matrix2i.createMatrix(col1i, col2i)));
        System.out.println(new Matrix2l().multiply(Matrix2l.createMatrix(col1l, col2l)));
        System.out.println(new Matrix2f().multiply(Matrix2f.createMatrix(col1f, col2f)));
        System.out.println(new Matrix2d().multiply(Matrix2d.createMatrix(col1d, col2d)));
        System.out.println(new Matrix3b().multiply(Matrix3b.createMatrix(col1b, col2b, col3b)));
        System.out.println(new Matrix3s().multiply(Matrix3s.createMatrix(col1s, col2s, col3s)));
        System.out.println(new Matrix3i().multiply(Matrix3i.createMatrix(col1i, col2i, col3i)));
        System.out.println(new Matrix3l().multiply(Matrix3l.createMatrix(col1l, col2l, col3l)));
        System.out.println(new Matrix3f().multiply(Matrix3f.createMatrix(col1f, col2f, col3f)));
        System.out.println(new Matrix3d().multiply(Matrix3d.createMatrix(col1d, col2d, col3d)));
        System.out.println(new Matrix4b().multiply(Matrix4b.createMatrix(col1b, col2b, col3b, col4b)));
        System.out.println(new Matrix4s().multiply(Matrix4s.createMatrix(col1s, col2s, col3s, col4s)));
        System.out.println(new Matrix4i().multiply(Matrix4i.createMatrix(col1i, col2i, col3i, col4i)));
        System.out.println(new Matrix4l().multiply(Matrix4l.createMatrix(col1l, col2l, col3l, col4l)));
        System.out.println(new Matrix4f().multiply(Matrix4f.createMatrix(col1f, col2f, col3f, col4f)));
        System.out.println(new Matrix4d().multiply(Matrix4d.createMatrix(col1d, col2d, col3d, col4d)));
    }
    
    public static void multiplyVector() {
        System.out.println(new Matrix2b().multiply(col1b));
        System.out.println(new Matrix2s().multiply(col1s));
        System.out.println(new Matrix2i().multiply(col1i));
        System.out.println(new Matrix2l().multiply(col1l));
        System.out.println(new Matrix2f().multiply(col1f));
        System.out.println(new Matrix2d().multiply(col1d));
        System.out.println(new Matrix3b().multiply(col1b));
        System.out.println(new Matrix3s().multiply(col1s));
        System.out.println(new Matrix3i().multiply(col1i));
        System.out.println(new Matrix3l().multiply(col1l));
        System.out.println(new Matrix3f().multiply(col1f));
        System.out.println(new Matrix3d().multiply(col1d));
        System.out.println(new Matrix4b().multiply(col1b));
        System.out.println(new Matrix4s().multiply(col1s));
        System.out.println(new Matrix4i().multiply(col1i));
        System.out.println(new Matrix4l().multiply(col1l));
        System.out.println(new Matrix4f().multiply(col1f));
        System.out.println(new Matrix4d().multiply(col1d));
    }
    
    public static void multiplyValue() {
        System.out.println(new Matrix2b().multiply((byte) 2));
        System.out.println(new Matrix2s().multiply((short) 2));
        System.out.println(new Matrix2i().multiply(2));
        System.out.println(new Matrix2l().multiply(2l));
        System.out.println(new Matrix2f().multiply(2.0f));
        System.out.println(new Matrix2d().multiply(2.0));
        System.out.println(new Matrix3b().multiply((byte) 2)); 
        System.out.println(new Matrix3s().multiply((short) 2));
        System.out.println(new Matrix3i().multiply(2));
        System.out.println(new Matrix3l().multiply(2l));
        System.out.println(new Matrix3f().multiply(2.0f));
        System.out.println(new Matrix3d().multiply(2.0));
        System.out.println(new Matrix4b().multiply((byte) 2));
        System.out.println(new Matrix4s().multiply((short) 2));
        System.out.println(new Matrix4i().multiply(2));
        System.out.println(new Matrix4l().multiply(2l));
        System.out.println(new Matrix4f().multiply(2.0f));
        System.out.println(new Matrix4d().multiply(2.0));
    }
    
    public static void divide() {
        System.out.println(new Matrix2b().divide((byte) 2));
        System.out.println(new Matrix2s().divide((short) 2));
        System.out.println(new Matrix2i().divide(2));
        System.out.println(new Matrix2l().divide(2l));
        System.out.println(new Matrix2f().divide(2.0f));
        System.out.println(new Matrix2d().divide(2.0));
        System.out.println(new Matrix3b().divide((byte) 2)); 
        System.out.println(new Matrix3s().divide((short) 2));
        System.out.println(new Matrix3i().divide(2));
        System.out.println(new Matrix3l().divide(2l));
        System.out.println(new Matrix3f().divide(2.0f));
        System.out.println(new Matrix3d().divide(2.0));
        System.out.println(new Matrix4b().divide((byte) 2));
        System.out.println(new Matrix4s().divide((short) 2));
        System.out.println(new Matrix4i().divide(2));
        System.out.println(new Matrix4l().divide(2l));
        System.out.println(new Matrix4f().divide(2.0f));
        System.out.println(new Matrix4d().divide(2.0));
    }
}
