package net.optifine.player;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerItemModel {
    private Dimension textureSize = null;
    @Getter
    private boolean usePlayerTexture = false;
    private PlayerItemRenderer[] modelRenderers = new PlayerItemRenderer[0];
    @Setter
    @Getter
    private ResourceLocation textureLocation = null;
    @Setter
    @Getter
    private BufferedImage textureImage = null;
    @Getter
    private DynamicTexture texture = null;
    private ResourceLocation locationMissing = new ResourceLocation("textures/blocks/wool_colored_red.png");
    public static final int ATTACH_BODY = 0;
    public static final int ATTACH_HEAD = 1;
    public static final int ATTACH_LEFT_ARM = 2;
    public static final int ATTACH_RIGHT_ARM = 3;
    public static final int ATTACH_LEFT_LEG = 4;
    public static final int ATTACH_RIGHT_LEG = 5;
    public static final int ATTACH_CAPE = 6;

    public PlayerItemModel(Dimension textureSize, boolean usePlayerTexture, PlayerItemRenderer[] modelRenderers) {
        this.textureSize = textureSize;
        this.usePlayerTexture = usePlayerTexture;
        this.modelRenderers = modelRenderers;
    }

    public void render(ModelBiped modelBiped, AbstractClientPlayer player, float scale, float partialTicks) {
        TextureManager texturemanager = Config.getTextureManager();

        if (this.usePlayerTexture) {
            texturemanager.bindTexture(player.getLocationSkin());
        } else if (this.textureLocation != null) {
            if (this.texture == null && this.textureImage != null) {
                this.texture = new DynamicTexture(this.textureImage);
                Minecraft.getMinecraft().getTextureManager().loadTexture(this.textureLocation, this.texture);
            }

            texturemanager.bindTexture(this.textureLocation);
        } else {
            texturemanager.bindTexture(this.locationMissing);
        }

        for (PlayerItemRenderer playeritemrenderer : this.modelRenderers) {
            GlStateManager.pushMatrix();

            if (player.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            playeritemrenderer.render(modelBiped, scale);
            GlStateManager.popMatrix();
        }
    }

    public static ModelRenderer getAttachModel(ModelBiped modelBiped, int attachTo) {
        switch (attachTo) {
            case 0:
                return modelBiped.bipedBody;

            case 1:
                return modelBiped.bipedHead;

            case 2:
                return modelBiped.bipedLeftArm;

            case 3:
                return modelBiped.bipedRightArm;

            case 4:
                return modelBiped.bipedLeftLeg;

            case 5:
                return modelBiped.bipedRightLeg;

            default:
                return null;
        }
    }

}
