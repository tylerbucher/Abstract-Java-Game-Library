#version 400

layout(location=0) in vec3 position;
layout(location=1) in vec4 color;
layout(location=2) in vec2 texcoord;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

out vec4 oColor;
out vec2 oTexcoord;

void main()
{
    oColor = color;
    oTexcoord = texcoord;
    mat4 mvp = projection * view * model;
    gl_Position = mvp * vec4(position, 1.0f);
}