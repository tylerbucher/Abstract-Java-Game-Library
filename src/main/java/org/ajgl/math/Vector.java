/**
 * 
 */
package org.ajgl.math;

import java.nio.Buffer;


/**
 * @author Tyler
 *
 */
public abstract class Vector<N extends Number> {
    
    public abstract <B extends Buffer> B getBuffer(Class<B> bufferClass);
}
