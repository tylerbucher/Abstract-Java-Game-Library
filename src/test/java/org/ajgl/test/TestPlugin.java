/**
 * 
 */
package org.ajgl.test;

import org.ajgl.Plugin;

/**
 * 
 * This class was designed to test the plugin class
 * @author Tyler
 */
public class TestPlugin extends Plugin {
    
    @Override
    public void onEnable() {
        JavaGame.getDispatcher().registerEvents(EventTest.class);
    }
    
    @Override
    public void onDisable() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public String getName() {
        return "TestPlugin";
    }
    
}
