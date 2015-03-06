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

/**
 * The Logger class is designed to provide a way to output information in way
 * that the user can easily read.
 * @author Tyler Bucher
 */
public class Logger {
    
    /**
     * Logs/outputs a info message with the specified string.
     * @param s - The message to be logged
     */
    public static void log(String s) {
        System.out.println("[Log::INFO]: " + s);
    }
    
    /**
     * Logs/outputs a warning message with the specified string.
     * @param s - The message to be logged
     */
    public static void warning(String s) {
        System.out.println("[Log::WARNING]: " + s);
    }
    
    /**
     * Logs/outputs a severe message with the specified string.
     * @param s - The message to be logged
     */
    public static void severe(String s) {
        System.out.println("[Log::SEVERE]: " + s);
    }
}
