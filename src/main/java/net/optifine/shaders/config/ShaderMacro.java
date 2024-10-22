package net.optifine.shaders.config;

import lombok.Getter;

@Getter
public class ShaderMacro {
    private String name;
    private String value;

    public ShaderMacro(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getSourceLine() {
        return "#define " + this.name + " " + this.value;
    }

    public String toString() {
        return this.getSourceLine();
    }
}
