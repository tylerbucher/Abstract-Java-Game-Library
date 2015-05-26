package org.ajgl.test.math;

import org.ajgl.math.vector.Vector2b;
import org.ajgl.math.vector.Vector2d;
import org.ajgl.math.vector.Vector2f;
import org.ajgl.math.vector.Vector2i;
import org.ajgl.math.vector.Vector2l;
import org.ajgl.math.vector.Vector2s;
import org.ajgl.math.vector.Vector3b;
import org.ajgl.math.vector.Vector3d;
import org.ajgl.math.vector.Vector3f;
import org.ajgl.math.vector.Vector3i;
import org.ajgl.math.vector.Vector3l;
import org.ajgl.math.vector.Vector3s;
import org.ajgl.math.vector.Vector4b;
import org.ajgl.math.vector.Vector4d;
import org.ajgl.math.vector.Vector4f;
import org.ajgl.math.vector.Vector4i;
import org.ajgl.math.vector.Vector4l;
import org.ajgl.math.vector.Vector4s;

public class VectorTest {

    public static void add() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).add(new Vector2b((byte) 1.23, (byte) 1.23)));
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).add(new Vector2s((short) 1.23, (short) 1.23)));
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).add(new Vector2i((int) 1.23, (int) 1.23)));
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).add(new Vector2l((long) 1.23, (long) 1.23)));
        System.out.println(new Vector2f(1.23f, 1.23f).add(new Vector2f(1.23f, 1.23f)));
        System.out.println(new Vector2d(1.23, 1.23).add(new Vector2d(1.23, 1.23)));
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).add(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23)));
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).add(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23)));
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).add(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23)));
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).add(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23)));
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).add(new Vector3f(1.23f, 1.23f, 1.23f)));
        System.out.println(new Vector3d(1.23, 1.23, 1.23).add(new Vector3d(1.23, 1.23, 1.23)));
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).add(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23)));
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).add(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23)));
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).add(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23)));
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).add(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23)));
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).add(new Vector4f(1.23f, 1.23f,1.23f,1.23f)));
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).add(new Vector4d(1.23, 1.23, 1.23, 1.23)));
    }
    
    public static void subtract() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).subtract(new Vector2b((byte) 0.23, (byte) 0.23)));
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).subtract(new Vector2s((short) 0.23, (short) 0.23)));
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).subtract(new Vector2i((int) 0.23, (int) 0.23)));
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).subtract(new Vector2l((long) 0.23, (long) 0.23)));
        System.out.println(new Vector2f(1.23f, 1.23f).subtract(new Vector2f(0.23f, 0.23f)));
        System.out.println(new Vector2d(1.23, 1.23).subtract(new Vector2d(0.23, 0.23)));
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).subtract(new Vector3b((byte) 0.23, (byte) 0.23, (byte) 0.23)));
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).subtract(new Vector3s((short) 0.23, (short) 0.23, (short) 0.23)));
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).subtract(new Vector3i((int) 0.23, (int) 0.23, (int) 0.23)));
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).subtract(new Vector3l((long) 0.23, (long) 0.23, (long) 0.23)));
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).subtract(new Vector3f(0.23f, 0.23f, 0.23f)));
        System.out.println(new Vector3d(1.23, 1.23, 1.23).subtract(new Vector3d(0.23, 0.23, 0.23)));
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).subtract(new Vector4b((byte) 0.23, (byte) 0.23, (byte) 0.23, (byte) 0.23)));
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).subtract(new Vector4s((short) 0.23, (short) 0.23, (short) 0.23, (short) 0.23)));
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).subtract(new Vector4i((int) 0.23, (int) 0.23, (int) 0.23, (int) 0.23)));
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).subtract(new Vector4l((long) 0.23, (long) 0.23, (long) 0.23, (long) 0.23)));
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).subtract(new Vector4f(0.23f, 0.23f,0.23f,0.23f)));
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).subtract(new Vector4d(0.23, 0.23, 0.23, 0.23)));
    }

    public static void dot() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).dot(new Vector2b((byte) 0.23, (byte) 0.23)));
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).dot(new Vector2s((short) 0.23, (short) 0.23)));
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).dot(new Vector2i((int) 0.23, (int) 0.23)));
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).dot(new Vector2l((long) 0.23, (long) 0.23)));
        System.out.println(new Vector2f(1.23f, 1.23f).dot(new Vector2f(0.23f, 0.23f)));
        System.out.println(new Vector2d(1.23, 1.23).dot(new Vector2d(0.23, 0.23)));
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).dot(new Vector3b((byte) 0.23, (byte) 0.23, (byte) 0.23)));
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).dot(new Vector3s((short) 0.23, (short) 0.23, (short) 0.23)));
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).dot(new Vector3i((int) 0.23, (int) 0.23, (int) 0.23)));
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).dot(new Vector3l((long) 0.23, (long) 0.23, (long) 0.23)));
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).dot(new Vector3f(0.23f, 0.23f, 0.23f)));
        System.out.println(new Vector3d(1.23, 1.23, 1.23).dot(new Vector3d(0.23, 0.23, 0.23)));
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).dot(new Vector4b((byte) 0.23, (byte) 0.23, (byte) 0.23, (byte) 0.23)));
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).dot(new Vector4s((short) 0.23, (short) 0.23, (short) 0.23, (short) 0.23)));
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).dot(new Vector4i((int) 0.23, (int) 0.23, (int) 0.23, (int) 0.23)));
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).dot(new Vector4l((long) 0.23, (long) 0.23, (long) 0.23, (long) 0.23)));
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).dot(new Vector4f(0.23f, 0.23f,0.23f,0.23f)));
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).dot(new Vector4d(0.23, 0.23, 0.23, 0.23)));
    }
    
    public static void cross() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).cross(new Vector2b((byte) 1.23, (byte) 1.23)));
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).cross(new Vector2s((short) 1.23, (short) 1.23)));
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).cross(new Vector2i((int) 1.23, (int) 1.23)));
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).cross(new Vector2l((long) 1.23, (long) 1.23)));
        System.out.println(new Vector2f(1.23f, 1.23f).cross(new Vector2f(1.23f, 1.23f)));
        System.out.println(new Vector2d(1.23, 1.23).cross(new Vector2d(1.23, 1.23)));
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).cross(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23)));
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).cross(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23)));
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).cross(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23)));
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).cross(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23)));
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).cross(new Vector3f(1.23f, 1.23f, 1.23f)));
        System.out.println(new Vector3d(1.23, 1.23, 1.23).cross(new Vector3d(1.23, 1.23, 1.23)));
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).cross(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23)));
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).cross(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23)));
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).cross(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23)));
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).cross(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23)));
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).cross(new Vector4f(1.23f, 1.23f,1.23f,1.23f)));
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).cross(new Vector4d(1.23, 1.23, 1.23, 1.23)));
    }
    
    public static void negate() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).negate());
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).negate());
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).negate());
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).negate());
        System.out.println(new Vector2f(1.23f, 1.23f).negate());
        System.out.println(new Vector2d(1.23, 1.23).negate());
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).negate());
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).negate());
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).negate());
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).negate());
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).negate());
        System.out.println(new Vector3d(1.23, 1.23, 1.23).negate());
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).negate());
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).negate());
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).negate());
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).negate());
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).negate());
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).negate());
    }

    public static void normalize() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).normalize());
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).normalize());
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).normalize());
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).normalize());
        System.out.println(new Vector2f(1.23f, 1.23f).normalize());
        System.out.println(new Vector2d(1.23, 1.23).normalize());
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).normalize());
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).normalize());
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).normalize());
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).normalize());
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).normalize());
        System.out.println(new Vector3d(1.23, 1.23, 1.23).normalize());
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).normalize());
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).normalize());
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).normalize());
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).normalize());
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).normalize());
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).normalize());
    }

    public static void scale() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).scale((byte) 1.23));
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).scale((short) 1.23));
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).scale((int) 1.23));
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).scale((long) 1.23));
        System.out.println(new Vector2f(1.23f, 1.23f).scale(1.23f));
        System.out.println(new Vector2d(1.23, 1.23).scale(1.23));
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).scale((byte) 1.23));
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).scale((short) 1.23));
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).scale((int) 1.23));
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).scale((long) 1.23));
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).scale(1.23f));
        System.out.println(new Vector3d(1.23, 1.23, 1.23).scale(1.23));
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).scale((byte) 1.23));
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).scale((short) 1.23));
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).scale((int) 1.23));
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).scale((long) 1.23));
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).scale(1.23f));
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).scale(1.23));
    }

    public static void magnitude() {
        System.out.println(new Vector2b((byte) 1.23, (byte) 1.23).getMagnitude());
        System.out.println(new Vector2s((short) 1.23, (short) 1.23).getMagnitude());
        System.out.println(new Vector2i((int) 1.23, (int) 1.23).getMagnitude());
        System.out.println(new Vector2l((long) 1.23, (long) 1.23).getMagnitude());
        System.out.println(new Vector2f(1.23f, 1.23f).getMagnitude());
        System.out.println(new Vector2d(1.23, 1.23).getMagnitude());
        
        System.out.println(new Vector3b((byte) 1.23, (byte) 1.23, (byte) 1.23).getMagnitude());
        System.out.println(new Vector3s((short) 1.23, (short) 1.23, (short) 1.23).getMagnitude());
        System.out.println(new Vector3i((int) 1.23, (int) 1.23, (int) 1.23).getMagnitude());
        System.out.println(new Vector3l((long) 1.23, (long) 1.23, (long) 1.23).getMagnitude());
        System.out.println(new Vector3f(1.23f, 1.23f, 1.23f).getMagnitude());
        System.out.println(new Vector3d(1.23, 1.23, 1.23).getMagnitude());
        
        System.out.println(new Vector4b((byte) 1.23, (byte) 1.23, (byte) 1.23, (byte) 1.23).getMagnitude());
        System.out.println(new Vector4s((short) 1.23, (short) 1.23, (short) 1.23, (short) 1.23).getMagnitude());
        System.out.println(new Vector4i((int) 1.23, (int) 1.23, (int) 1.23, (int) 1.23).getMagnitude());
        System.out.println(new Vector4l((long) 1.23, (long) 1.23, (long) 1.23, (long) 1.23).getMagnitude());
        System.out.println(new Vector4f(1.23f, 1.23f,1.23f,1.23f).getMagnitude());
        System.out.println(new Vector4d(1.23, 1.23, 1.23, 1.23).getMagnitude());
    }
}
