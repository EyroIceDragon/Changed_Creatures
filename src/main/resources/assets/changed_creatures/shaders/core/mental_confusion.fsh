#version 150

uniform sampler2D DiffuseSampler;
uniform float Time;
uniform float Intensity;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec2 uv = texCoord;

    vec2 centerUV = vec2(0.5);
    vec2 dir = uv - centerUV;
    float dist = length(dir);

    float dispersionStrength = 0.06 * Intensity;
    float dynamicOffset = sin(dist * 10.0 - Time * 3.0) * 0.005 * Intensity;

    vec2 offsetVec = dir * (dispersionStrength + dynamicOffset);

    float r = texture(DiffuseSampler, uv + offsetVec).r;
    float g = texture(DiffuseSampler, uv).g;
    float b = texture(DiffuseSampler, uv - offsetVec).b;

    vec3 scene = vec3(r, g, b);

    float blurSize = (0.0015 + 0.0015) * Intensity;
    vec3 blur = vec3(0.0);
    blur += texture(DiffuseSampler, uv + vec2(-2.0, 0.0) * blurSize).rgb * 0.05;
    blur += texture(DiffuseSampler, uv + vec2(-1.0, 0.0) * blurSize).rgb * 0.15;
    blur += texture(DiffuseSampler, uv                          ).rgb * 0.60;
    blur += texture(DiffuseSampler, uv + vec2( 1.0, 0.0) * blurSize).rgb * 0.15;
    blur += texture(DiffuseSampler, uv + vec2( 2.0, 0.0) * blurSize).rgb * 0.05;

    scene = mix(scene, blur, 0.5 * Intensity);

    fragColor = vec4(clamp(scene, 0.0, 1.0), 1.0);
}