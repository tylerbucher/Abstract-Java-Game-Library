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
package org.ajgl.util;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is intended to load configurations of various types.
 * @author Tyler Bucher
 */
public class Configuration {
    
    /**
     * Loads and returns a new YamlConfiguration.
     * @param file - The configuration file to be loaded
     * @return A new YamlConfiguration object with the configuration file
     * specified
     */
    public static Configuration loadConfiguration(File file) {
        Configuration config = new Configuration(file);
        config.configMap = config.toMap();
        return config;
    }
    
    private File file;                      // The file used in the configuration
    private File defaultsFile;              // The file used as the default configuration
    private Map<String, String> configMap;  // The current configuration in map form
    
    /**
     * Creates a new YamlConfiguration object with the configuration file
     * specified.
     * @param file - The configuration file to be loaded
     */
    private Configuration(File file) {
        this.file = file;
    }
    
    /**
     * Return the configuration as a Map.
     * @return The Current configuration as a map
     */
    private Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            while (reader.ready()) {
                // Split string as [<string>: <string>]
                String[] strArray = reader.readLine().split("[:][ ]");
                // Read string as [<string>: <string>]
                map.put(strArray[0], strArray[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * Copies the map specified to the file and overrides it.
     * @param map - The map to be copied to the file
     */
    public void copyMap(Map<String, String> map) {
        try (PrintWriter writer = new PrintWriter(file);) {
            for (String s : map.keySet())
                // Write string as [<string>: <string>]
                writer.write(s + ": " + map.get(s) + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves the current configuration to the file.
     */
    public void saveConfiguration() {
        this.copyMap(configMap);
    }
    
    /**
     * Loads the file configuration used as the default configuration file.
     * @param file - The file to be used as the default configuration
     */
    public void loadDefaults(File file) {
        defaultsFile = file;
    }
    
    /**
     * Copies the default configuration to the current configuration. !You need
     * to call loadDefaults(File) first or this will through an exception.
     */
    public void copyDefaults() {
        try {
            // Attempts to write the defaults file to the main file
            Files.copy(defaultsFile.toPath(), file.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Attempts to save the configuration file if and only if it has not been
     * created.
     * @return true if and only if the file did not exist and has been created
     */
    public boolean save() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Returns the file used as the primary configuration.
     * @return The primary configuration file
     */
    public File getFile() {
        return file;
    }
    
    /**
     * Returns the file used as the default configuration.
     * @return The default configuration file
     */
    public File getDefaultFile() {
        return defaultsFile;
    }
    
    /**
     * Attempts to get a byte form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored byte or zero if there is not a byte at that key
     */
    public byte getByte(String key){
        return this.getByte(key, (byte) 0);
    }
    
    /**
     * Attempts to get a short form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored short or zero if there is not a short at that key
     */
    public short getShort(String key){
        return this.getShort(key, (short) 0);
    }
    
    /**
     * Attempts to get a integer form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored integer or zero if there is not a integer at that key
     */
    public int getInt(String key){
        return this.getInt(key, 0);
    }
    
    /**
     * Attempts to get a long form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored long or zero if there is not a long at that key
     */
    public long getLong(String key){
        return this.getLong(key, 0);
    }
    
    /**
     * Attempts to get a float form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored float or zero if there is not a float at that key
     */
    public float getFloat(String key){
        return this.getFloat(key, 0f);
    }
    
    /**
     * Attempts to get a double form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored double or zero if there is not a double at that key
     */
    public double getDouble(String key){
        return this.getDouble(key, 0d);
    }
    
    /**
     * Attempts to get a boolean form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored boolean or zero if there is not a boolean at that key
     */
    public boolean getBoolean(String key){
        return this.getBoolean(key, false);
    }
    
    /**
     * Attempts to get a String form the corresponding key.
     * @param key - The key form witch to get the value from
     * @return The stored String or "" if there is not a String at that key
     */
    public String getString(String key){
        return this.getString(key, "");
    }
    
    /**
     * Attempts to get a byte form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored byte or the provided precaution if there is not a byte at that key
     */
    public byte getByte(String key, byte precaution){
        String value = configMap.get(key);
        return value == null ? precaution : Byte.parseByte(value);
    }
    
    /**
     * Attempts to get a short form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored short or the provided precaution if there is not a short at that key
     */
    public short getShort(String key, short precaution){
        String value = configMap.get(key);
        return value == null ? precaution : Short.parseShort(value);
    }
    
    /**
     * Attempts to get a integer form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored integer or the provided precaution if there is not a integer at that key
     */
    public int getInt(String key, int precaution){
        String value = configMap.get(key);
        return value == null ? precaution : Integer.parseInt(value);
    }
    
    /**
     * Attempts to get a long form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored long or the provided precaution if there is not a long at that key
     */
    public long getLong(String key, long precaution){
        String value = configMap.get(key);
        return value == null ? precaution : Long.parseLong(value);
    }
    
    /**
     * Attempts to get a float form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored float or the provided precaution if there is not a float at that key
     */
    public float getFloat(String key, float precaution){
        String value = configMap.get(key);
        return value == null ? precaution : Float.parseFloat(value);
    }
    
    /**
     * Attempts to get a double form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored double or the provided precaution if there is not a double at that key
     */
    public double getDouble(String key, double precaution){
        String value = configMap.get(key);
        return value == null ? precaution : Double.parseDouble(value);
    }
    
    /**
     * Attempts to get a boolean form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored boolean or the provided precaution if there is not a boolean at that key
     */
    public boolean getBoolean(String key, boolean precaution){
        String value = configMap.get(key);
        return value == null ? precaution : Boolean.parseBoolean(value);
    }
    
    /**
     * Attempts to get a String form the corresponding key.
     * @param key - The key form witch to get the value from
     * @param precaution - The precautionary value to return if a value does not exist at the key
     * @return The stored String or the provided precaution if there is not a String at that key
     */
    public String getString(String key, String precaution){
        String value = configMap.get(key);
        return value == null ? precaution : value;
    }
}
