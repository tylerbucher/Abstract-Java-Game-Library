/**
 * 
 */
package org.ajgl.concurrent;


/**
 * @author Tyler
 *
 */
public abstract class Task {
    
    private String flag;
    
    public Task(String flag) {
        this.flag = flag;
    }
    
    public abstract void execute();
    
    public String getFlag() {
        return flag;
    }
}
