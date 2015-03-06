package org.ajgl.concurrent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Executable {
    Priority value();
}

enum Priority {
    LOW,
    MEDIUM,
    HIGH;
}
