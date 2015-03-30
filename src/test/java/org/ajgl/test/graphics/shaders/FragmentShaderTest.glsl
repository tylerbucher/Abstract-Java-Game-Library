#version 410 core

in vec3 oColor;

out vec4 FragColor;

void main()
{
    FragColor = vec4(oColor, 1.0f);
}