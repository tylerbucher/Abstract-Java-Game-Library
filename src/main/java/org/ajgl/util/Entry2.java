package org.ajgl.util;

import java.util.Objects;

public class Entry2<A, B> {
	public A first;
    public B second;

    public Entry2(A first, B second) {
    	this.first = first;
    	this.second = second;
    }

    public int hashCode() {
    	return Objects.hashCode(first) ^ Objects.hashCode(second);
    }

    public boolean equals(Object other) {
    	if (other == this) return true;
        if (other instanceof Entry2) {
            Entry2<?,?> e = (Entry2<?,?>)other;
            if (Objects.equals(first, e.first) &&
                Objects.equals(second, e.second))
                return true;
        }
        return false;
    }
}
