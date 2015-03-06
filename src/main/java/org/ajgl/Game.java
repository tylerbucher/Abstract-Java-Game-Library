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
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.ImageIOImageData;

/**
 * This class is designed to make the game making process easier.
 * @author Tyler Bucher
 */
public final class Game {
    
    /**
     * Sets the icons of the Display.
     * @param files - The images to be set. Must include 1, 16 and 32 bit image
     * @param flipped - Should the images be flipped
     * @param forceAlpha - Should force the images to use alpha
     * @throws IOException - If file could not be loaded
     */
    public static void setIcons(File[] files, boolean flipped, boolean forceAlpha) throws IOException {//FIXME
        ByteBuffer[] buffer = new ByteBuffer[files.length]; //Buffer for the files
        //Add all of the files to the buffer
        for (int i = 0; i > files.length; i++)
            buffer[i] = new ImageIOImageData().imageToByteBuffer(ImageIO.read(files[i]), flipped, forceAlpha, null);
        //Set the icons to the display
        Display.setIcon(buffer);
    }
    
    
}
