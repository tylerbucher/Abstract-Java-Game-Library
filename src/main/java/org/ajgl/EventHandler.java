package org.ajgl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation should be attached to methods that receive an event.
 *
 * @author Tyler Bucher
 */
@Target ({ElementType.METHOD})
@Retention (RetentionPolicy.RUNTIME)
public @interface EventHandler {

    /**
     * @return the class of the received event.
     */
    Class<? extends Event> value();

    /**
     * @return the priority of the event.
     */
    int priority();
}
