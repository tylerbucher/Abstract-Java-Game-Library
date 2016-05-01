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

package org.ajgl.concurrent;

/**
 * This class is designed to create tasks.
 * @author Tyler Bucher
 */
public abstract class Task implements Comparable<Task> {
    
    private String flag;    // Flag for the task
    private int priority;   // 1: lowest | 10: highest
    
    /**
     * Creates a new task.
     * @param flag - Flag for the task.
     * @param priority - 1: lowest | 10: highest
     */
    public Task(String flag, int priority) {
        this.flag = flag;
        this.priority = priority;
    }
    
    /**
     * Executes the tasks.
     */
    public abstract void execute();
    
    /**
     * Returns the flag.
     * @return the falg.
     */
    public String getFlag() {
        return flag;
    }
    
    /**
     * Returns the priority.
     * @return the priority.
     */
    public int getPriority() {
        return priority;
    }
    
    @Override
    public int compareTo(Task task){
         return this.priority - task.priority;
    }
}
