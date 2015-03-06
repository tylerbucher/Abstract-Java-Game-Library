package org.ajgl.concurrent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolExecuter {
    
    private int threads;
    
    private PoolThread[] threadArray;
    volatile ConcurrentMap<Class<?>, List<Method>> map;
    volatile LinkedBlockingQueue<Object> objectList;
    
    public ThreadPoolExecuter(int threads) {
        this.threads = threads;
        
        this.threadArray = ThreadPoolExecuter.createThreads(threads, this);
        this.map = new ConcurrentHashMap<>();
        this.objectList = new LinkedBlockingQueue<Object>();
    }
    
    /**
     * Starts all the threads in the thread pool.
     */
    public void startThreads() {
        // Start all threads by calling their run method
        for(PoolThread t : threadArray) {
            t.run();
        }
    }
    
    /**
     * Stops a thread as soon as possible.
     * @param thread - The thread number to stop
     */
    public void stopThread(int thread) {
        threadArray[thread].stopThread();
    }
    
    /**
     * Stops all the Threads as soon as possible.
     */
    public void stopThreads() {
        for(PoolThread t : threadArray)
            t.stopThread();
    }
    
    /**
     * Executes the objects methods denoted by the
     * Executable annotation, as soon as a thread is available.
     * @param object - The object to be executed.
     * @return True if the object is to be executed false otherwise.
     */
    public boolean executeLater(Object object) {
        // Get class of the object
        Class<?> clazz = object.getClass();
        // If object class is a template add it
        for(Class<?> c : map.keySet())
            if(clazz == c) {
                objectList.add(object);
                return true;
            }
        // Return false because object is discarded
        return false;
    }
    
    /**
     * Adds a class as a new template.
     * @param clazz -  The class to be added
     */
    public void addTemplate(Class<?> clazz) {
        // Add the class and its methods to the map
        map.put(clazz, ThreadPoolExecuter.getMethods(clazz));
    }
    
    /**
     * Adds multiple classes as new templates.
     * @param classList - The class list to be added
     */
    public void addTemplates(List<Class<?>> classList) {
        // Add all classes to map
        for(Class<?> clazz : classList) {
            this.addTemplate(clazz);
        }
    }
    
    /**
     * Returns the methods of a class that have the Executable 
     * annotation present.
     * @param clazz - The class to be checked.
     * @return The list of methods that have the Executable annotation present.
     */
    private static List<Method> getMethods(Class<?> clazz) {
        // Method list
        List<Method> methods = new ArrayList<>();
        // Loop through methods
        for(Method m : clazz.getMethods()) 
            if(m.isAnnotationPresent(Executable.class)) 
                methods.add(m);
        // Return list
        return methods;
    }
    
    /**
     * Creates a new thread array with a specified amount.
     * @param amount - The number of threads to be created.
     * @return The new thread array
     */
    private static PoolThread[] createThreads(int amount, ThreadPoolExecuter executer) {
        // Create thread array
        PoolThread[] array = new PoolThread[amount];
        // Add new threads to array
        for(int i=0;i<amount;i++)
            array[i] = new PoolThread(executer);
        // Return array
        return array;
    }
}
