package org.ajgl.graphics;

import org.ajgl.graphics.UtilAnnotations.GlTextureFormat;
import org.lwjgl.opengl.GL11;

/**
 * This class is designed to provide basic and general graphical functions.
 *
 * @author Tyler Bucher
 */
public class Graphics {

    /**
     * Enables an OpenGL state.
     *
     * @param state the list of OpenGL states to enable.
     */
    public static void enableClientSideState(int... state) {
        for (int i = 0; i < state.length; i++)
            GL11.glEnableClientState(state[i]);
    }

    /**
     * Disables an OpenGL state.
     *
     * @param state the list of OpenGL states to disable.
     */
    public static void disableClientSideState(int... state) {
        for (int i = 0; i < state.length; i++)
            GL11.glDisableClientState(state[i]);
    }

    /**
     * Binds the textureID to the current OpenGL context.
     *
     * @param textureID the id of the texture.
     * @see GlTextureFormat
     */
    public static void bindTexture(@GlTextureFormat int textureFormat, int textureID) {
        GL11.glBindTexture(textureFormat, textureID);
    }
}
