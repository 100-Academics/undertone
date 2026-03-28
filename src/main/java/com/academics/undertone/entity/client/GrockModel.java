package com.academics.undertone.entity.client;

import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.GrockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class

GrockModel<T extends GrockEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "grock"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart legs;
    private final ModelPart frontLegs;
    private final ModelPart backLegs;

    public GrockModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.legs = root.getChild("legs");
        this.frontLegs = this.legs.getChild("frontLegs");
        this.backLegs = this.legs.getChild("backLegs");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition legs = partdefinition.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition frontLegs = legs.addOrReplaceChild("frontLegs", CubeListBuilder.create().texOffs(8, 20).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 20).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.0F, 0.0F));

        PartDefinition backLegs = legs.addOrReplaceChild("backLegs", CubeListBuilder.create().texOffs(24, 20).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -2.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(-2, -2).addBox(-5.5F, -3.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(-2, -2).addBox(-5.5F, -7.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, 1).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -2.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 1).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -2.0F, -1.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(1, 1).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -4.0F, -1.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 1).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -4.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-1, -1).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -4.0F, 3.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-1, -1).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -4.0F, -2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 22).addBox(1.3906F, 1.1844F, -2.7572F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.5F, -6.5F, -2.5554F, -0.4198F, 2.1776F));

        PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(16, 32).addBox(4.1586F, 0.1401F, -1.2988F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, -5.5F, -0.504F, -0.3954F, 2.4706F));

        PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 28).addBox(-0.4989F, 0.4214F, -2.5091F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.5F, -5.5F, -2.299F, -0.1506F, -1.6406F));

        PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(4, 28).addBox(-2.0329F, -2.1997F, -2.6599F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.5F, -6.5F, -2.1324F, -0.4847F, 2.6976F));

        PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(20, 32).addBox(3.0762F, 0.2061F, -0.6952F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, -6.0F, 0.817F, -0.2052F, -1.65F));

        PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 28).addBox(3.1515F, -0.9384F, 2.4129F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, -5.0F, -1.9969F, -0.19F, -3.0943F));

        PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(32, 20).addBox(1.7947F, 2.2716F, -1.9482F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -9.75F, -1.5F, 1.4879F, -0.9471F, 1.0778F));

        PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(16, 30).addBox(4.4367F, 0.1105F, -2.6851F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -0.5F, -2.4536F, -0.7251F, 0.846F));

        PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(8, 28).addBox(-1.6178F, 1.1823F, -2.0978F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -10.25F, -4.25F, 3.049F, 1.3205F, 0.9562F));

        PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0177F, -0.7894F, -2.764F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -9.5F, 1.5F, 2.2867F, -0.5019F, 0.8659F));

        PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(20, 30).addBox(1.915F, -0.5051F, -1.0768F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -10.0F, -0.5F, 0.0089F, 1.2782F, 1.0899F));

        PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(12, 28).addBox(3.0447F, 0.3821F, 1.918F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -10.0F, 0.75F, 2.6996F, -0.0821F, 0.496F));

        PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(16, 30).addBox(3.2132F, 0.7826F, -2.9115F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 4.5F, 2.9016F, 0.5948F, 0.9285F));

        PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(32, 20).addBox(0.4685F, 2.363F, -1.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.5F, 3.75F, 0.9032F, 0.5404F, 1.2426F));

        PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 28).addBox(-3.1666F, -0.7491F, -1.9403F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.5F, 3.5F, 1.2306F, 0.7311F, 0.7027F));

        PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(8, 28).addBox(-0.8513F, 1.6603F, -1.0096F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.5F, 4.5F, 0.537F, 0.2038F, -1.4244F));

        PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(20, 30).addBox(2.6168F, -0.9778F, -2.21F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.5F, 4.5F, -2.6337F, 0.2586F, -1.4323F));

        PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(12, 28).addBox(2.4883F, 0.7675F, 3.1597F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 4.25F, 1.1732F, 0.4942F, 0.0933F));

        PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(24, 30).addBox(0.3121F, -0.668F, 0.4351F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -6.5F, -5.0F, -0.1777F, -0.3175F, 1.3019F));

        PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(28, 30).addBox(-0.0908F, -1.2236F, 0.1843F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -3.5F, -4.0F, -0.8133F, 0.0909F, 1.3636F));

        PartDefinition cube_r27 = body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 32).addBox(0.1566F, 0.462F, -0.2762F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -2.5F, 1.0F, 1.3423F, -0.1573F, 1.4121F));

        PartDefinition cube_r28 = body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(4, 32).addBox(0.5798F, -1.2447F, -0.1695F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -8.5F, -2.0F, -1.1532F, -0.6185F, -0.9624F));

        PartDefinition cube_r29 = body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(8, 32).addBox(0.6527F, -0.4067F, 0.2519F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -7.5F, 2.0F, 0.1235F, -0.7111F, 1.2351F));

        PartDefinition cube_r30 = body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(12, 32).addBox(0.5892F, 0.2152F, -0.8759F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -4.5F, 0.0F, 2.0547F, -0.6301F, -1.029F));

        PartDefinition cube_r31 = body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(12, 30).addBox(0.5467F, 0.3371F, -0.5175F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -2.5F, 1.5F, 1.5917F, -0.5784F, 1.0146F));

        PartDefinition cube_r32 = body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(8, 30).addBox(0.6452F, -0.3807F, 0.2547F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -6.5F, -5.5F, 0.1568F, -0.7013F, 0.8291F));

        PartDefinition cube_r33 = body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(4, 30).addBox(0.3202F, -1.0127F, 0.2966F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -2.25F, -4.25F, -0.5719F, -0.326F, 1.0291F));

        PartDefinition cube_r34 = body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 30).addBox(0.16F, 0.3182F, -1.0522F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, 3.0F, 2.1645F, -0.1606F, -1.378F));

        PartDefinition cube_r35 = body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(28, 28).addBox(0.1552F, -1.3383F, 0.0227F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -8.5F, -1.5F, -1.0132F, -0.1558F, -1.3221F));

        PartDefinition cube_r36 = body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(24, 28).addBox(0.8593F, -0.1729F, -0.1068F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.5F, 2.5F, 0.6938F, -1.0339F, 0.4823F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(GrockEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(GrockAnimations.walking, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.attackAnimationState, GrockAnimations.headbutt, ageInTicks, 1.0f);
        this.animate(entity.idleAnimationState, GrockAnimations.idlesit, ageInTicks, 1.0f);
        this.animate(entity.idleAnimationState2, GrockAnimations.animation, ageInTicks, 1.0f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        legs.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
