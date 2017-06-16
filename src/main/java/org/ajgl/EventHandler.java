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

    Class<? extends Event> value(); // The class of the received event

    int priority();                 // States the priority of the event
}
