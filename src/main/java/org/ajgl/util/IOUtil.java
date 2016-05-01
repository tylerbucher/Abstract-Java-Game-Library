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

package org.ajgl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import org.lwjgl.BufferUtils;

/**
 * This class is designed to handle extra IO functions.
 * @author Tyler Bucher
 */
public class IOUtil {
    
    /**
     * Converts a resource path into a byte buffer.
     * @param resource - The path of the resource.
     * @param bufferSize - Size of the buffer.
     * @return The resource as a ByteBuffer.
     * @throws IOException
     */
    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;

        File file = new File(resource);
        if ( file.isFile() ) {
            FileInputStream stream = new FileInputStream(file);
            FileChannel fc = stream.getChannel();
            buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);

            while ( fc.read(buffer) != -1 ) ;

            fc.close();
            stream.close();
        } else {
            buffer = BufferUtils.createByteBuffer(bufferSize);

            java.io.InputStream source = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            if ( source == null )
                throw new java.io.FileNotFoundException(resource);

            try {
                ReadableByteChannel rbc = Channels.newChannel(source);
                try {
                    while ( true ) {
                        int bytes = rbc.read(buffer);
                        if ( bytes == -1 )
                            break;
                        if ( buffer.remaining() == 0 )
                            buffer = resizeBuffer(buffer, buffer.capacity() * 2);
                    }
                } finally {
                    rbc.close();
                }
            } finally {
                source.close();
            }
        }

        buffer.flip();
        return buffer;
    }
    
    /**
     * Resizes a buffer.
     * @param buffer - buffer to resize.
     * @param newCapacity - New size for the buffer.
     * @return The resized buffer.
     */
    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
}
