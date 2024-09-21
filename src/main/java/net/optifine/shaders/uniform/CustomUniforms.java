package net.optifine.shaders.uniform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionCached;

public class CustomUniforms {
    private CustomUniform[] uniforms;
    private IExpressionCached[] expressionsCached;

    public CustomUniforms(CustomUniform[] uniforms, Map<String, IExpression> mapExpressions) {
        this.uniforms = uniforms;
        List<IExpressionCached> list = new ArrayList();

        for (String s : mapExpressions.keySet()) {
            IExpression iexpression = mapExpressions.get(s);

            if (iexpression instanceof IExpressionCached) {
                IExpressionCached iexpressioncached = (IExpressionCached) iexpression;
                list.add(iexpressioncached);
            }
        }

        this.expressionsCached = list.toArray(new IExpressionCached[list.size()]);
    }

    public void setProgram(int program) {
        for (CustomUniform customuniform : this.uniforms) {
            customuniform.setProgram(program);
        }
    }

    public void update() {
        this.resetCache();

        for (CustomUniform customuniform : this.uniforms) {
            customuniform.update();
        }
    }

    private void resetCache() {
        for (IExpressionCached iexpressioncached : this.expressionsCached) {
            iexpressioncached.reset();
        }
    }

    public void reset() {
        for (CustomUniform customuniform : this.uniforms) {
            customuniform.reset();
        }
    }
}
