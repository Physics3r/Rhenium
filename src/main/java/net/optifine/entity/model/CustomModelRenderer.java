package net.optifine.entity.model;

import lombok.Getter;
import net.minecraft.client.model.ModelRenderer;
import net.optifine.entity.model.anim.ModelUpdater;

@Getter
public class CustomModelRenderer {
    private String modelPart;
    private boolean attach;
    private ModelRenderer modelRenderer;
    private ModelUpdater modelUpdater;

    public CustomModelRenderer(String modelPart, boolean attach, ModelRenderer modelRenderer, ModelUpdater modelUpdater) {
        this.modelPart = modelPart;
        this.attach = attach;
        this.modelRenderer = modelRenderer;
        this.modelUpdater = modelUpdater;
    }

}
