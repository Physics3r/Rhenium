package net.optifine.shaders.uniform;

import lombok.Getter;
import net.optifine.expr.IExpression;
import net.optifine.shaders.SMCLog;

@Getter
public class CustomUniform {
    private String name;
    private UniformType type;
    private IExpression expression;
    private ShaderUniformBase shaderUniform;

    public CustomUniform(String name, UniformType type, IExpression expression) {
        this.name = name;
        this.type = type;
        this.expression = expression;
        this.shaderUniform = type.makeShaderUniform(name);
    }

    public void setProgram(int program) {
        this.shaderUniform.setProgram(program);
    }

    public void update() {
        if (this.shaderUniform.isDefined()) {
            try {
                this.type.updateUniform(this.expression, this.shaderUniform);
            } catch (RuntimeException runtimeexception) {
                SMCLog.severe("Error updating custom uniform: " + this.shaderUniform.getName());
                SMCLog.severe(runtimeexception.getClass().getName() + ": " + runtimeexception.getMessage());
                this.shaderUniform.disable();
                SMCLog.severe("Custom uniform disabled: " + this.shaderUniform.getName());
            }
        }
    }

    public void reset() {
        this.shaderUniform.reset();
    }

    public String toString() {
        return this.type.name().toLowerCase() + " " + this.name;
    }
}
