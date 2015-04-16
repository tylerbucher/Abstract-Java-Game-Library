/**
 * 
 */
package org.ajgl.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Tyler
 *
 */
public final class Tasker {
    
    private static Map<String, List<Task>> aSyncMap = new HashMap<>();
    
    public static synchronized void postASyncTask(Task task) {
        String flag = task.getFlag();
        List<Task> flagList = aSyncMap.get(flag);
        
        if(flagList == null)
            aSyncMap.put(flag, new ArrayList<Task>());
        aSyncMap.get(flag).add(task);
    }
    
    public static synchronized void executeASyncTasks() {
        Set<String> keySet = aSyncMap.keySet();
        for(String s : keySet) {
            List<Task> taskList = aSyncMap.get(s);
            for(Task t : taskList)
                t.execute();
            aSyncMap.remove(s);
        }    
    }
    
    public static synchronized void executeASyncTask(String flag) {
        List<Task> taskList = aSyncMap.get(flag);
        if(taskList != null) {
            for(Task t : taskList)
                t.execute();
            aSyncMap.remove(flag);
        }
    }
}
