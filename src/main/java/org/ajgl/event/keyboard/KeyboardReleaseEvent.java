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
package org.ajgl.event.keyboard;

/**
 * This class is designed to be dispatched when a key is released.
 * @author Tyler Bucher
 */
public class KeyboardReleaseEvent extends KeyboardEvent {
    
    private int keyReleased;    // The integer value of the key released
    
    /**
     * Creates a new key released event.
     * @param keyReleased - The key released
     */
    public KeyboardReleaseEvent(int keyReleased) {
        this.keyReleased = keyReleased;
    }

    /**
     * return the key released.
     * @return the keyReleased
     */
    public int getKeyReleased() {
        return keyReleased;
    }

    @Override
    public String getName() {
        return "KeyboardReleaseEvent";
    }
}
