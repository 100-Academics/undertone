package com.academics.undertone.entity.client;

import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.GrockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrockRenderer extends MobRenderer<GrockEntity, GrockModel<GrockEntity>> {

    public GrockRenderer(EntityRendererProvider.Context context) {
        super(context, new GrockModel<>(context.bakeLayer(GrockModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GrockEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "textures/entity/grock/gRockTexture.png");
    }

    @Override
    public void render(GrockEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.5f, 0.5f, 0.5f); // Scale down the model for baby entities
        } else {
            poseStack.scale(1.0f, 1.0f, 1.0f); // Normal scale for adult entities
        }

        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

}
