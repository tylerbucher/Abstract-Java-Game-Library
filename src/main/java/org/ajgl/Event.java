package org.ajgl;

/**
 * Highest level event class.
 *
 * @author Tyler Bucher
 */
public class Event {

    /**
     * States weather the event has been canceled
     */
    public boolean canceled = false;

    /**
     * States the name of the {@link Event}.
     */
    public final String name;

    public Event() {
        this.name = "Event";
    }

    /**
     * Creates an {@link Event} with a name.
     *
     * @param name the name of the {@link Event}.
     */
    public Event(String name) {
        this.name = name;
    }
}
