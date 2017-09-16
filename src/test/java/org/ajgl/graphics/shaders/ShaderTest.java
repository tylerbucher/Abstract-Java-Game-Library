/*
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
package org.ajgl.graphics.shaders;

import org.ajgl.Window;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

public class ShaderTest {

    private Window window;

    @Before
    public void setUp() throws Exception {
        window = new Window();
        window.setup();
        GLFW.glfwMakeContextCurrent(window.getWindowHandler());
        GL.createCapabilities();
    }

    @Test
    public void testShaderLoadsCorrectly() throws Exception {
        final String VERTEX_SHADER_STRING = "#version 100\n" +
                "\n" +
                "uniform mat4 projTrans;\n" +
                "\n" +
                "attribute vec2 Position;\n" +
                "attribute vec2 TexCoord;\n" +
                "\n" +
                "varying vec2 vTexCoord;\n" +
                "\n" +
                "void main() {\n" +
                "    vTexCoord = TexCoord;\n" +
                "    gl_Position = 80.0 * vec4(Position, 0.0, 1.0);\n" +
                "}";
        final Shader shader = new Shader(ShaderUtil.VERTEX_SHADER, VERTEX_SHADER_STRING);
        Assert.assertTrue("Shader did not compile", shader.verify());
    }
}