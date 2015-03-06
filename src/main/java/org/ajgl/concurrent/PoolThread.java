/**
 * 
 */
package org.ajgl.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @author Tyler Bucher
 *
 */
public class PoolThread extends Thread {
    
    private boolean stop;
    private ThreadPoolExecuter executer;
    
    public PoolThread(ThreadPoolExecuter executer) {
        this.executer = executer;
        this.stop = false;
    }
    
    public void stopThread() {
        stop = true;
    }
    
    @Override
    public void run() {
        while(!stop) {
            try {
                Object object = executer.objectList.take();
                List<Method> methodList = executer.map.get(object.getClass());
                
                for(Method m : methodList) {
                    m.invoke(object, (Object[])null);
                }
            } catch (InterruptedException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
