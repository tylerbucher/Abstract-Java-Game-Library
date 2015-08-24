/**
 * 
 */
package org.ajgl.collision.axisaligned;


/**
 * @author Tyler
 *
 */
public class Contain {
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if one rectangle contains the other false otherwise.
     */
    public static boolean RvR2D(double[] vertices, double... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1]
                && values[4] <= vertices[4] && values[5] >= vertices[5];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
     * set of points that create a rectangle. The points will be checked 
     * in accordance with the figure below; please make sure to pass the points 
     * in that order.
     * <pre>
     * 3---2
     * |   |
     * |   |
     * 0---1
     * </pre>
     * @param vertices - The first set of points.
     * @param values - The second set of points; Also the points to be checked against.
     * @return True if one rectangle contains the other false otherwise.
     */
    public static boolean RvR2D(float[] vertices, float... values) {
        return values[6] >= vertices[6] && values[7] <= vertices[7]
                && values[2] <= vertices[2] && values[3] >= vertices[3];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if one rectangle contains the other false otherwise.
     */
    public static boolean RvR2D(long[] vertices, long... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1]
                && values[4] <= vertices[4] && values[5] >= vertices[5];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if one rectangle contains the other false otherwise.
     */
    public static boolean RvR2D(int[] vertices, int... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1]
                && values[4] <= vertices[4] && values[5] >= vertices[5];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if one rectangle contains the other false otherwise.
     */
    public static boolean RvR2D(short[] vertices, short... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1]
                && values[4] <= vertices[4] && values[5] >= vertices[5];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if one rectangle contains the other false otherwise.
     */
    public static boolean RvR2D(byte[] vertices, byte... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1]
                && values[4] <= vertices[4] && values[5] >= vertices[5];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if the cube is containing the other false otherwise.
     */
    public static boolean RvR3D(double[] vertices, double... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1] && values[2] >= vertices[2]
                && values[18] <= vertices[18] && values[19] >= vertices[19] && values[20] <= vertices[20];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if the cube is containing the other false otherwise.
     */
    public static boolean RvR3D(float[] vertices, float... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1] && values[2] >= vertices[2]
                && values[18] <= vertices[18] && values[19] >= vertices[19] && values[20] <= vertices[20];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if the cube is containing the other false otherwise.
     */
    public static boolean RvR3D(long[] vertices, long... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1] && values[2] >= vertices[2]
                && values[18] <= vertices[18] && values[19] >= vertices[19] && values[20] <= vertices[20];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if the cube is containing the other false otherwise.
     */
    public static boolean RvR3D(int[] vertices, int... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1] && values[2] >= vertices[2]
                && values[18] <= vertices[18] && values[19] >= vertices[19] && values[20] <= vertices[20];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if the cube is containing the other false otherwise.
     */
    public static boolean RvR3D(short[] vertices, short... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1] && values[2] >= vertices[2]
                && values[18] <= vertices[18] && values[19] >= vertices[19] && values[20] <= vertices[20];
    }
    
    /**
     * Checks to see if the first set of points contains the seconds 
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
     * @return True if the cube is containing the other false otherwise.
     */
    public static boolean RvR3D(byte[] vertices, byte... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1] && values[2] >= vertices[2]
                && values[18] <= vertices[18] && values[19] >= vertices[19] && values[20] <= vertices[20];
    }
}
