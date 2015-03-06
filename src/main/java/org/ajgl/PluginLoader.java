/**
 * AJGL, an abstract java game library that provides useful functions for making a game.
 * Copyright (C) 2014 Tyler Bucher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ajgl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This class is designed to load plugins of any type into the game.
 * @author Tyler
 */
public class PluginLoader {
    
    private static Map<String, Plugin> pluginMap = new HashMap<>(); // A List of all the loaded plugins
    
    /**
     * Loads all of the plugins in the "plugins" directory.
     * @throws IOException - If the directory could not be found
     */
    public static void loadPlugins() throws IOException {
        // Load the plugin directory
        File dir = new File(System.getProperty("java.class.path")).getAbsoluteFile().getParentFile();
        String path = dir.toString();
        // Cycle through all the plugins and invoke the "onEnable" method
        File file = new File(path, "plugins");
        for (File f : file.listFiles()) {
            try (JarFile jar = new JarFile(f)) {
                // Jar file setup
                Enumeration<JarEntry> enumeration = jar.entries();
                URL[] urls = {new URL("jar:file:" + f.getPath()+"!/")};
                URLClassLoader loader = URLClassLoader.newInstance(urls);
                // Checks all of the class files
                while (enumeration.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) enumeration.nextElement();
                    if(jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
                        continue;
                    // Class setup
                    String className = jarEntry.getName().substring(0,jarEntry.getName().length()-6);
                    className = className.replace('/', '.');
                    Object tempClass = loader.loadClass(className).newInstance();
                    // Object setup
                    if(tempClass instanceof Plugin) {
                        Plugin plugin = (Plugin) tempClass;
                        pluginMap.put(plugin.getName(), plugin);
                        plugin.onEnable();
                    }
                }
            } catch (InstantiationException e) {
                Logger.warning("Unable to create plugin");
            } catch (IllegalAccessException e) {
                Logger.warning("Unable to access JarFile '" + f.getName() + "' Or 'onEnable' Method");
            } catch (IllegalArgumentException e) {
                Logger.severe("'onEnable' Method Error in JarFile '" + f.getName() + "'");
            } catch (SecurityException e) {
                Logger.warning("Security Warning");
            } catch (ClassNotFoundException e) {
               Logger.severe("Plugin class not found");
            }
        }
    }
    
    /**
     * Loads a specific plugin created with the game its self.
     * @param plugin - The plugin to be loaded
     * @return True if and only if the plugin was successfully loaded
     */
    public static boolean loadPlugin(Plugin plugin) {
        try {
            pluginMap.put(plugin.getName(), plugin);
            plugin.onEnable();
        } catch (Exception e) {
            Logger.warning(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Loads a specific plugin with a given name.
     * @param name - The name of the plugin
     * @return True if the plugin was successfully loaded false otherwise
     * @throws IOException - If the directory or file could not be found
     */
    public static boolean loadPlugin(String name) throws IOException {
        // Load the plugins directory
        File dir = new File(System.getProperty("java.class.path")).getAbsoluteFile().getParentFile();
        String path = dir.toString();
        // Invoke the "onEnable" method of a plugin based on its name
        File file = new File(path, "plugins");
        for (String s : file.list()) {
            if (s.equals(name)) {
                try (JarFile jar = new JarFile(new File(file, name))) {
                    // Jar file setup
                    Enumeration<JarEntry> enumeration = jar.entries();
                    URL[] urls = {new URL("jar:file:" + file.getPath()+"!/")};
                    URLClassLoader loader = URLClassLoader.newInstance(urls);
                    // Checks all of the class files
                    while (enumeration.hasMoreElements()) {
                        JarEntry jarEntry = (JarEntry) enumeration.nextElement();
                        if(jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
                            continue;
                        // Class setup
                        String className = jarEntry.getName().substring(0,jarEntry.getName().length()-6);
                        className = className.replace('/', '.');
                        Object tempClass = loader.loadClass(className).newInstance();
                        // Object setup
                        if(tempClass instanceof Plugin) {
                            Plugin plugin = (Plugin) tempClass;
                            pluginMap.put(plugin.getName(), plugin);
                            plugin.onEnable();
                        }
                    }
                } catch (IllegalAccessException e) {
                    Logger.warning("Unable to access 'onEnable' Method");
                    return false;
                } catch (IllegalArgumentException e) {
                    Logger.severe("'onEnable' Method Error in Plugin '" + name + "'");
                    return false;
                } catch (SecurityException e) {
                    Logger.warning("Security Warning");
                    return false;
                } catch (InstantiationException e) {
                    Logger.severe("Cloud not instantiate the plugin: " + name);
                    return false;
                } catch (ClassNotFoundException e) {
                    Logger.severe("Plugin class not found");
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Unloads all of the plugins that have been loaded.
     * @throws IOException - If the directory or file(s) could not be found
     */
    public static void unloadPlugins() throws IOException {
        // Unloads all of the current loaded plugins
        Set<String> keySet = pluginMap.keySet();
        for(String name : keySet) {
            try {
                pluginMap.get(name).onDisable();
                pluginMap.remove(name);
            } catch(Exception e) {
                Logger.warning("Error while unloading plugin"+name+": "+e.getMessage());
            }
        }
    }
    
    /**
     * Unloads the specified plugin that resides within the game.
     * @param plugin - The plugin to be unloaded
     * @return True if the plugin was unloaded successfully false otherwise
     */
    public static boolean unloadPlugin(Plugin plugin) {
        try {
            Plugin temp = pluginMap.get(plugin.getName());
            if(temp != null) {
                temp.onDisable();
                pluginMap.remove(temp.getName());
            }
        } catch (Exception e) {
            Logger.warning("Error while unloading plugin"+plugin.getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Unloads a plugin specified by the name.
     * @param name - The name of the plugin
     * @return True if the plugin was successfully unloaded false otherwise
     * @throws IOException - if the directory or file could not be found
     */
    public static boolean unloadPlugin(String name) throws IOException {
        // Unloads all of the current loaded plugins
        try {
            Plugin plugin = pluginMap.get(name);
            if(plugin != null) {
                plugin.onDisable();
                pluginMap.remove(plugin.getName());
            }
        } catch (Exception e) {
            Logger.warning("Error while unloading plugin"+name+": "+e.getMessage());
            return false;
        }
        return true;
    }
}
