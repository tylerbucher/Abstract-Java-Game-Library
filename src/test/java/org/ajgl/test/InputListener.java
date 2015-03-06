/**
 * 
 */
package org.ajgl.test;

import org.ajgl.event.EventHandler;
import org.ajgl.event.keyboard.KeyboardReleaseEvent;
import org.ajgl.event.mouse.MouseEvent;
import org.ajgl.event.mouse.MouseMoveEvent;
import org.ajgl.event.mouse.MouseReleaseEvent;
import org.ajgl.primary.GameObject;
import org.ajgl.util.PhysicalAlteration;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;


/**
 * @author Tyler
 *
 */
public class InputListener {
    
    Vector2f orig;
    
    private void moveToCenter(GameObject object) {
        orig = object.getOrigin();
        for (int i = 0; i < object.getVertices().length - 1; i += 2) {
            object.getVertices()[i] -= object.getOrigin().x;
            object.getVertices()[i + 1] -= object.getOrigin().y;
        }
    }
    
    private void moveBack(GameObject object) {
        for (int i = 0; i < object.getVertices().length - 1; i += 2) {
            object.getVertices()[i] += orig.x;
            object.getVertices()[i + 1] += orig.y;
        }
        object.setVertices(object.getVertices());
    }
    
    @EventHandler(MouseReleaseEvent.class)
    public void mouseReleaseEvent(MouseReleaseEvent event) {
        // Mouse Right click
        if(event.getButtonReleased() == 1) {
            // Creates the new Object
            GameObject object = new GameObject(
                    new float[] {Mouse.getX(), Mouse.getY(), Mouse.getX(), Mouse.getY() + 50, Mouse.getX() + 50, 
                            Mouse.getY() + 50, Mouse.getX() + 50, Mouse.getY()}, GL11.GL_QUADS);
//            moveToCenter(object);
//            object.setVertices(PhysicalAlteration.rotateMatrix(object.getVertices(), 110.3f));
//            moveBack(object);
//            object.finalizeUpdate();
            
            // Create new GameObject and add it to a list
            JavaGame.objectList.add(object);
            // Add new GameObject to the QuadTree
            JavaGame.quadTree.add(JavaGame.objectList.get(JavaGame.objectList.size()-1));
        }
    }
    
    @EventHandler(MouseMoveEvent.class)
    public void mouseMoveEvent(MouseMoveEvent event) {
        // Update mouse box
        JavaGame.mouseBox.dx += Mouse.getEventDX();
        JavaGame.mouseBox.dy += Mouse.getEventDY();
    }
    
    @EventHandler(MouseEvent.class)
    public void mouseEvent(MouseEvent event) {
        // Update mouse box
        //System.out.println("Event");
    }
    
    @EventHandler(KeyboardReleaseEvent.class)
    public void keyboardReleaseEvent(KeyboardReleaseEvent event) {
        if(event.getKeyReleased() == Keyboard.KEY_SPACE) {
            
        }
    }
}
