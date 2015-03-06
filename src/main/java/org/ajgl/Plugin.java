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
 * This class was designed to be extended for plugins, so they can be loaded in
 * to the game.
 * @author Tyler Bucher
 */
public abstract class Plugin {
    
    /**
     * This method is called by the PluginLoader when a plugin is loaded into
     * the game.
     */
    public abstract void onEnable();
    
    /**
     * This method is called by the PluginLoader when a plugin is unloaded from
     * the game.
     */
    public abstract void onDisable();
    
    /**
     * Returns the name of the plugin.
     * @return The String from of the plugin name
     */
    public abstract String getName();
}
