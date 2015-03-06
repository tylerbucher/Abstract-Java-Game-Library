/**
 * 
 */
package org.ajgl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 * @author Tyler
 *
 */
@Target({ElementType.METHOD})
public @interface OpenGLInfo {
    
    /**
     * States the minimum OpenGL version in which this
     * method needs to work.
     */
    String openGLVersion();
    
    /**
     * States the current status of this method.
     */
    String status();
    
    /**
     * States weather or not this method is forward 
     * Compatible.
     */
    boolean fwdCompatible();
}
