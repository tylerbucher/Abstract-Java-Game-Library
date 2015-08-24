/**
 * 
 */
package org.ajgl.collision.axisaligned;


/**
 * @author Tyler
 *
 */
public class Intersect {
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 0---3
     * |   |
     * |   |
     * 1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the rectangles are intersecting false otherwise.
     */
    public static boolean RvR2D(double[] vertices, double... values) {
        return vertices[4] > values[0] || vertices[0] < values[4] 
                || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 0---3
     * |   |
     * |   |
     * 1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the rectangles are intersecting false otherwise.
     */
    public static int RvR2D_MVT(double[] vertices, double... values) {
        double xMTV;
        if(vertices[4] > values[0]){
            xMTV =  vertices[4] - values[0];
        }
        //|| vertices[0] < values[4] || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 0---3
     * |   |
     * |   |
     * 1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the rectangles are intersecting false otherwise.
     */
    public static boolean RvR2D(float[] vertices, float... values) {
        return vertices[4] > values[0] || vertices[0] < values[4] 
                || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 0---3
     * |   |
     * |   |
     * 1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the rectangles are intersecting false otherwise.
     */
    public static boolean RvR2D(long[] vertices, long... values) {
        return vertices[4] > values[0] || vertices[0] < values[4] 
                || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 0---3
     * |   |
     * |   |
     * 1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the rectangles are intersecting false otherwise.
     */
    public static boolean RvR2D(int[] vertices, int... values) {
        return vertices[4] > values[0] || vertices[0] < values[4] 
                || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 0---3
     * |   |
     * |   |
     * 1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the rectangles are intersecting false otherwise.
     */
    public static boolean RvR2D(short[] vertices, short... values) {
        return vertices[4] > values[0] || vertices[0] < values[4] 
                || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 0---3
     * |   |
     * |   |
     * 1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the rectangles are intersecting false otherwise.
     */
    public static boolean RvR2D(byte[] vertices, byte... values) {
        return vertices[4] > values[0] || vertices[0] < values[4] 
                || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a cube. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the cubes are intersecting false otherwise.
     */
    public static boolean RvR3D(double[] vertices, double... values) {
        return vertices[6] > values[0] || vertices[0] < values[6] ||
                vertices[4] < values[1] || vertices[1] > values[4] ||
                vertices[14] > values[2] || vertices[2] < values[14];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a cube. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the cubes are intersecting false otherwise.
     */
    public static boolean RvR3D(float[] vertices, float... values) {
        return vertices[6] > values[0] || vertices[0] < values[6] ||
                vertices[4] < values[1] || vertices[1] > values[4] ||
                vertices[14] > values[2] || vertices[2] < values[14];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a cube. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the cubes are intersecting false otherwise.
     */
    public static boolean RvR3D(long[] vertices, long... values) {
        return vertices[6] > values[0] || vertices[0] < values[6] ||
                vertices[4] < values[1] || vertices[1] > values[4] ||
                vertices[14] > values[2] || vertices[2] < values[14];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a cube. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the cubes are intersecting false otherwise.
     */
    public static boolean RvR3D(int[] vertices, int... values) {
        return vertices[6] > values[0] || vertices[0] < values[6] ||
                vertices[4] < values[1] || vertices[1] > values[4] ||
                vertices[14] > values[2] || vertices[2] < values[14];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a cube. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the cubes are intersecting false otherwise.
     */
    public static boolean RvR3D(short[] vertices, short... values) {
        return vertices[6] > values[0] || vertices[0] < values[6] ||
                vertices[4] < values[1] || vertices[1] > values[4] ||
                vertices[14] > values[2] || vertices[2] < values[14];
    }
    
    /**
     * Checks to see if the first set of points intersects the seconds 
     * set of points that create a cube. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if the cubes are intersecting false otherwise.
     */
    public static boolean RvR3D(byte[] vertices, byte... values) {
        return vertices[6] > values[0] || vertices[0] < values[6] ||
                vertices[4] < values[1] || vertices[1] > values[4] ||
                vertices[14] > values[2] || vertices[2] < values[14];
    }
}
