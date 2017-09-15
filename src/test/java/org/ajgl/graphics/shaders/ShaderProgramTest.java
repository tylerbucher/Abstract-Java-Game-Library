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

public class ShaderProgramTest {

    private Shader shader;

    @Before
    public void setUp() throws Exception {
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 1);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 0);
        final Window window = new Window();
        window.setup();

        GLFW.glfwMakeContextCurrent(window.getWindowHandler());
        GL.createCapabilities();
        final String VERTEX_SHADER_STRING = "#version 400\n" +
                "\n" +
                "layout(location=0) in vec3 position;\n" +
                "layout(location=1) in vec3 color;\n" +
                "\n" +
                "uniform mat4 model;\n" +
                "uniform mat4 view;\n" +
                "uniform mat4 projection;\n" +
                "\n" +
                "out vec3 oColor;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "    oColor = color;\n" +
                "    mat4 mvp = projection * view * model;\n" +
                "    gl_Position = mvp * vec4(position, 1.0f);\n" +
                "}";
        shader = new Shader(ShaderUtil.VERTEX_SHADER, VERTEX_SHADER_STRING);
    }

    @Test
    public void testShaderProgramLoadsCorrectly() throws Exception {
        final ShaderProgram program = new ShaderProgram();
        program.attachShader(shader);
        program.link();
        program.validate();
        Assert.assertTrue("ShaderProgram did not initialize correctly", program.verify());
    }
}