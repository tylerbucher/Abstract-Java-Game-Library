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

import java.util.ArrayList;
import java.util.Collections;
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
            if(taskList != null) {
                Collections.sort(taskList);
                for(Task t : taskList)
                    t.execute();
                aSyncMap.remove(s);
            }
        }    
    }
    
    public static synchronized void executeASyncTask(String flag) {
        List<Task> taskList = aSyncMap.get(flag);
        if(taskList != null) {
            Collections.sort(taskList);
            for(Task t : taskList)
                t.execute();
            aSyncMap.remove(flag);
        }
    }
}
