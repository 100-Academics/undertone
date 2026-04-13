package com.academics.undertone.entity.client.Rudahh;

import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.RudahhEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
//JJ was here.
public class RudahhRenderer extends MobRenderer<RudahhEntity, RudahhModel<RudahhEntity>> {

    public RudahhRenderer(EntityRendererProvider.Context context) {
        super(context, new RudahhModel<>(context.bakeLayer(RudahhModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RudahhEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "textures/entity/rudahh/rudahhtexture.png");
    }

    @Override
    public void render(RudahhEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.5f, 1.0f, 0.5f); // Scale down the model for baby entities
        } else {
            poseStack.scale(1.5f, 1.5f, 1.5f); // Normal scale for adult entities
        }

        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

}
