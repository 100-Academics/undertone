package com.academics.undertone.entity.client.Ailake;

import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.AilakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AilakeRenderer extends MobRenderer<AilakeEntity, AilakeModel<AilakeEntity>> {

    public AilakeRenderer(EntityRendererProvider.Context context) {
        super(context, new AilakeModel<>(context.bakeLayer(AilakeModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(AilakeEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "textures/entity/rudahh/rudahhtexture.png");
    }

    @Override
    public void render(AilakeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.5f, 1.0f, 0.5f); // Scale down the model for baby entities
        } else {
            poseStack.scale(10f, 10f, 10f); // Normal scale for adult entities TODO scale right just like hitbox
        }

        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

}
