#version 400

layout(location=0) in vec3 position;
layout(location=1) in vec4 color;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

out vec4 oColor;

void main()
{
    oColor = color;
    mat4 mvp = projection * view * model;
    gl_Position = mvp * vec4(position, 1.0f);
}