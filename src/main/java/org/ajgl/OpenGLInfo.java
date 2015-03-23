/**
 * 
 */
package org.ajgl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Tyler
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD})
public @interface OpenGLInfo {
    
    /**
     * States the minimum OpenGL version in which this
     * method needs to work.
     */
    String openGLVersion();
    
    /**
     * States the OpenGL profile.
     */
    String profile();
    
    /**
     * True if this method has been documented on the wiki.
     */
    boolean doc();
}
