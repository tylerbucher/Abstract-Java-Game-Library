/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Tyler Bucher
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.ajgl.util;

import java.util.Objects;

/**
 * This class is a two way entry comparison class.
 * @author Tyler Bucher
 *
 * @param <A> - First Object.
 * @param <B> - Second Object.
 */
public class Entry2<A, B> {
    
	public A first;     // First Object
    public B second;    // Second Object
    
    /**
     * Constructor for a two way entry class.
     * @param first - First Object
     * @param second - Second Object
     */
    public Entry2(A first, B second) {
    	this.first = first;
    	this.second = second;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hashCode(first) ^ Objects.hashCode(second);
    }
    
    @Override
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
