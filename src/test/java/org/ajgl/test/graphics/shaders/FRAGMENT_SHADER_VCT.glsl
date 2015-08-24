#version 400

in vec3 oColor;
in vec2 oTexcoord;

out vec4 FragColor;

uniform vec4 texColMul;
uniform sampler2D texImage;

void main()
{
    vec4 textureColor = texture(texImage, oTexcoord);
    
    textureColor.r = abs(texColMul.r - textureColor.r);
    textureColor.g = abs(texColMul.g - textureColor.g);
    textureColor.b = abs(texColMul.b - textureColor.b);
    textureColor.a = abs(texColMul.a - textureColor.a);
    
    FragColor = vec4(oColor, 1.0f) * textureColor;
}