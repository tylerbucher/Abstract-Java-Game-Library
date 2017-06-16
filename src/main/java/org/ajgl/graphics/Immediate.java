package org.ajgl.graphics;

import org.ajgl.graphics.UtilAnnotations.GlBeginFunction;
import org.ajgl.graphics.UtilAnnotations.GlBeginMode;
import org.lwjgl.opengl.GL11;

/**
 * This class draws shapes to the screen through OpenGL. The
 * rendering method that this class uses is "Primitive". Please
 * note that this class should never be used unless you have some
 * god-awful reason to use it. This rendering method has also been
 * deprecated in modern OpenGL.
 *
 * @author Tyler Bucher
 * @see GlBeginFunction
 */
@GlBeginFunction
public final class Immediate {

    /**
     * Begin a new drawing mode.
     *
     * @param beginMode the {@link UtilAnnotations.GlBeginMode BeginMode} to use.
     * @see GlBeginMode
     */
    public static void beginDraw(@GlBeginMode int beginMode) {
        GL11.glBegin(beginMode);
    }

    /**
     * Ends the current drawing mode.
     */
    public static void endDraw() {
        GL11.glEnd();
    }
}
