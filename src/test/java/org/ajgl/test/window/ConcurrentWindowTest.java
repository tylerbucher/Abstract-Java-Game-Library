/**
 * 
 */
package org.ajgl.test.window;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glClear;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import org.ajgl.concurrent.Task;
import org.ajgl.concurrent.Tasker;
import org.ajgl.concurrent.ThreadedWindow;
import org.ajgl.test.MainTest;
import org.ajgl.test.graphics.GraphicsTest;
import org.ajgl.test.graphics.TextureTest;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;


/**
 * @author Tyler
 *
 */
public class ConcurrentWindowTest extends ThreadedWindow {
    
    /**
     * Default window constructor.
     */
    public ConcurrentWindowTest() {
        super();
    } 
    
    /**
     * Main window constructor.
     * @param width - Display width.
     * @param height - Display height.
     * @param title - Display title.
     * @param monitor - Monitor to use.
     * @param share - Window handler to share OpenGL context with.
     */
    public ConcurrentWindowTest(int width, int height, String title, long monitor, long share) {
        super(width, height, title, monitor, share);
    }
    
    @Override
    public void initGL() {
        // Bind context
        glfwMakeContextCurrent(this.getWindowHandler());     // Make the OpenGL context current
        glfwShowWindow(this.getWindowHandler());             // Make the window visible
        GLContext.createFromCurrent();                       // Bind lwjgl with GLFW
        // Initialize openGl
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, 1200, 800);
        GL11.glOrtho(0, 1200, 0, 800, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        // Enable alpha transparency (for overlay image)
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }
    
    @Override
    public void run() {
        preInitGL();
        initGL();
        init();
        
        long handle = this.getWindowHandler();
        
        
      //================
        int BITMAP_W = 512;
        int BITMAP_H = 512;

        int texID = GL11.glGenTextures();
        ByteBuffer cdata = BufferUtils.createByteBuffer(96 * STBTTBakedChar.SIZEOF);

        try {
            ByteBuffer ttf = ioResourceToByteBuffer("src/game/java/resources/ttf/ARIAL.TTF", 160 * 1024);

            ByteBuffer bitmap = BufferUtils.createByteBuffer(BITMAP_W * BITMAP_H);
            STBTruetype.stbtt_BakeFontBitmap(ttf, 24, bitmap, BITMAP_W, BITMAP_H, 32, cdata);

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_ALPHA, BITMAP_W, BITMAP_H, 0, GL11.GL_ALPHA, GL11.GL_UNSIGNED_BYTE, bitmap);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //GL11.glClearColor(43f / 255f, 43f / 255f, 43f / 255f, 0f); // BG color
         // Text color

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //GL11.glEnable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        FloatBuffer x = BufferUtils.createFloatBuffer(1);
        FloatBuffer y = BufferUtils.createFloatBuffer(1);
        STBTTAlignedQuad q = new STBTTAlignedQuad();
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        //===============
        
        
        while ( glfwWindowShouldClose(handle) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            GL11.glPushMatrix();
            GL11.glColor3f(1,1,1);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
            
            GL11.glScalef(1, -1, 1);
            
            String text = "This is a test";
            x.put(0, 100.0f);
            y.put(0, -100.0f);
            GL11.glBegin(GL11.GL_QUADS);
            for ( int i = 0; i < text.length(); i++ ) {
                char c = text.charAt(i);
                if ( c == '\n' ) {
                    y.put(0, y.get(0) + 24);
                    x.put(0, 0.0f);
                    continue;
                } else if ( c < 32 || 128 <= c )
                    continue;

                STBTruetype.stbtt_GetBakedQuad(cdata, BITMAP_W, BITMAP_H, c - 32, x, y, q.buffer(), 1);

                GL11.glTexCoord2f(q.getS0(), q.getT0());
                GL11.glVertex2f(q.getX0(), q.getY0());

                GL11.glTexCoord2f(q.getS1(), q.getT0());
                GL11.glVertex2f(q.getX1(), q.getY0());

                GL11.glTexCoord2f(q.getS1(), q.getT1());
                GL11.glVertex2f(q.getX1(), q.getY1());

                GL11.glTexCoord2f(q.getS0(), q.getT1());
                GL11.glVertex2f(q.getX0(), q.getY1());
            }
            GL11.glEnd();
            
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            
            GL11.glPopMatrix();
            
            // Run Cycles
            input();
            update();
            render();
            
            // Display Buffer swap
            glfwSwapBuffers(handle);
        }
        
        // Release window and window call backs
        this.getKeyCallback().release();
        exit();
    }
    
    private void input() {
        
    }
    
    private void update() {
        
    }

    private void render() {
        GraphicsTest.immidateDraw();
        GraphicsTest.displayListDraw();
        GraphicsTest.vertexArrayDraw();
        GraphicsTest.vboDraw();
        GraphicsTest.vaoDraw();
        
        TextureTest.immidateDraw();
        TextureTest.displayListDraw();
        TextureTest.vertexArrayDraw();
        TextureTest.vboDraw();
        TextureTest.vaoDraw();
    }
    
    public void exit() {
        final long handle = this.getWindowHandler();
        
        this.getErrorCallback().release();
        Tasker.postASyncTask(new Task("GLFW_MAIN_THREAD", 0) {
            @Override
            public void execute() {
                glfwDestroyWindow(handle);
                GLFW.glfwSetWindowShouldClose(MainTest.windowTest.getWindowHandler(), 1);
            }
         });
    }
    
  //==========
    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;

        File file = new File(resource);
        if ( file.isFile() ) {
            FileChannel fc = new FileInputStream(file).getChannel();
            buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);

            while ( fc.read(buffer) != -1 ) ;

            fc.close();
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
    
    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
    //============
}
