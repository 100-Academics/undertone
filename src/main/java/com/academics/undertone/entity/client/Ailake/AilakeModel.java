package com.academics.undertone.entity.client.Ailake;

// Made with Blockbench 5.1.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.AilakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class AilakeModel<T extends AilakeEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "ailake"), "main");
    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart shell;
    private final ModelPart bone1;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone6;
    private final ModelPart bone7;
    private final ModelPart bone8;
    private final ModelPart bone9;
    private final ModelPart bone10;
    private final ModelPart bone11;
    private final ModelPart bone12;
    private final ModelPart bone13;
    private final ModelPart bone14;
    private final ModelPart head;
    private final ModelPart headBlock;
    private final ModelPart leftEye;
    private final ModelPart upperJaw;
    private final ModelPart lowerJaw;
    private final ModelPart rightEye;

    public AilakeModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.shell = this.all.getChild("shell");
        this.bone1 = this.shell.getChild("bone1");
        this.bone2 = this.shell.getChild("bone2");
        this.bone3 = this.shell.getChild("bone3");
        this.bone4 = this.shell.getChild("bone4");
        this.bone5 = this.shell.getChild("bone5");
        this.bone6 = this.shell.getChild("bone6");
        this.bone7 = this.shell.getChild("bone7");
        this.bone8 = this.shell.getChild("bone8");
        this.bone9 = this.shell.getChild("bone9");
        this.bone10 = this.shell.getChild("bone10");
        this.bone11 = this.shell.getChild("bone11");
        this.bone12 = this.shell.getChild("bone12");
        this.bone13 = this.shell.getChild("bone13");
        this.bone14 = this.shell.getChild("bone14");
        this.head = this.all.getChild("head");
        this.headBlock = this.head.getChild("headBlock");
        this.leftEye = this.head.getChild("leftEye");
        this.upperJaw = this.head.getChild("upperJaw");
        this.lowerJaw = this.head.getChild("lowerJaw");
        this.rightEye = this.head.getChild("rightEye");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(1.0F, 3.0F, 0.0F));

        PartDefinition shell = all.addOrReplaceChild("shell", CubeListBuilder.create(), PartPose.offset(-17.0F, 12.0F, -21.0F));

        PartDefinition bone1 = shell.addOrReplaceChild("bone1", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bone1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -3.0F, -39.0F, 16.0F, 6.0F, 47.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 1.0F, 1.5708F, 0.0F, -1.5708F));

        PartDefinition bone2 = shell.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(36.0F, 1.0F, 4.0F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 53).addBox(-8.0F, -4.0F, 0.0F, 16.0F, 6.0F, 40.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition bone3 = shell.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(32.0F, 1.0F, 41.0F));

        PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 99).addBox(-8.0F, -3.0F, 0.0F, 16.0F, 6.0F, 36.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

        PartDefinition bone4 = shell.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-1.0F, 3.0F, 37.0F));

        PartDefinition cube_r4 = bone4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(104, 99).addBox(-8.0F, -3.0F, -1.0F, 16.0F, 6.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -3.1416F, 0.0F, -1.5708F));

        PartDefinition bone5 = shell.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, 7.0F));

        PartDefinition cube_r5 = bone5.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(112, 53).addBox(-8.0F, -3.0F, 0.0F, 16.0F, 6.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition bone6 = shell.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(30.0F, 1.0F, 10.0F));

        PartDefinition cube_r6 = bone6.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(126, 0).addBox(-8.0F, -2.0F, -28.0F, 16.0F, 6.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, -1.5708F));

        PartDefinition bone7 = shell.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(26.0F, 1.0F, 35.0F));

        PartDefinition cube_r7 = bone7.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(104, 139).addBox(-8.0F, -3.0F, -24.0F, 16.0F, 6.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition bone8 = shell.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(5.0F, 1.0F, 32.0F));

        PartDefinition cube_r8 = bone8.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(78, 169).addBox(-8.0F, -3.0F, 0.0F, 16.0F, 6.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, -1.5708F));

        PartDefinition bone9 = shell.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offset(8.0F, 1.0F, 13.0F));

        PartDefinition cube_r9 = bone9.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(154, 169).addBox(-8.0F, -3.0F, 0.0F, 16.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition bone10 = shell.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offset(23.0F, 1.0F, 16.0F));

        PartDefinition cube_r10 = bone10.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 170).addBox(-8.0F, -3.0F, -16.0F, 16.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, -1.5708F));

        PartDefinition bone11 = shell.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offset(20.0F, 1.0F, 29.0F));

        PartDefinition cube_r11 = bone11.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(126, 34).addBox(-8.0F, -3.0F, -12.0F, 16.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition bone12 = shell.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offset(11.0F, 1.0F, 26.0F));

        PartDefinition cube_r12 = bone12.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(182, 34).addBox(-8.0F, -3.0F, 0.0F, 16.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -1.5708F));

        PartDefinition bone13 = shell.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(14.0F, 1.0F, 19.0F));

        PartDefinition cube_r13 = bone13.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(184, 139).addBox(-8.0F, -3.0F, -6.0F, 16.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

        PartDefinition bone14 = shell.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offset(17.0F, 1.0F, 22.0F));

        PartDefinition cube_r14 = bone14.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(112, 89).addBox(-8.0F, 0.0F, -3.0F, 16.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

        PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-17.0F, 5.0F, -20.0F));

        PartDefinition headBlock = head.addOrReplaceChild("headBlock", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r15 = headBlock.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 141).addBox(-7.0F, -2.0F, 0.0F, 14.0F, 4.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition leftEye = head.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(62, 182).addBox(-3.0F, -14.0F, -1.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -25.0F, 0.0F));

        PartDefinition cube_r16 = leftEye.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(78, 154).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition upperJaw = head.addOrReplaceChild("upperJaw", CubeListBuilder.create().texOffs(156, 89).addBox(-6.0F, -4.0F, -1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 167).addBox(-5.0F, -3.0F, -2.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(182, 50).addBox(-6.0F, -2.0F, -2.0F, 12.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(184, 153).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(182, 92).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 192).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(184, 156).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 170).addBox(-2.0F, -2.0F, -9.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(184, 165).addBox(-3.0F, -1.0F, -9.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.5F, 0.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.5F, 0.0F, -4.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.5F, 0.0F, -6.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(-1, -1).addBox(-2.5F, 0.0F, -9.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(-1, -1).addBox(2.5F, 0.0F, -9.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(1.5F, 0.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(0.5F, 0.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-0.5F, 0.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-1.5F, 0.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(3.5F, 0.0F, -6.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(4.5F, 0.0F, -4.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(5.5F, 0.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, -2.0F));

        PartDefinition lowerJaw = head.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -1.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.5F, -1.0F, -4.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.5F, -1.0F, -6.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(-1, -1).addBox(-2.5F, -1.0F, -9.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-1.5F, -1.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-0.5F, -1.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(0.5F, -1.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(1.5F, -1.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, -1).addBox(2.5F, -1.0F, -9.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(3.5F, -1.0F, -6.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(4.5F, -1.0F, -4.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(5.5F, -1.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, -2.0F));

        PartDefinition cube_r17 = lowerJaw.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 192).addBox(-3.0F, -1.0F, -9.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(184, 159).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(182, 95).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(182, 89).addBox(-6.0F, -2.0F, -2.0F, 12.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 174).addBox(-2.0F, -2.0F, -9.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(34, 192).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(184, 162).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(184, 151).addBox(-5.0F, -3.0F, -2.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(156, 94).addBox(-6.0F, -4.0F, -1.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition rightEye = head.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(62, 178).addBox(-3.0F, -14.0F, -1.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, -25.0F, 0.0F));

        PartDefinition cube_r18 = rightEye.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(78, 141).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(AilakeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(AilakeAnimations.roll, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.attack1, AilakeAnimations.spit, ageInTicks, 1f);
        this.animate(entity.attack2, AilakeAnimations.bite, ageInTicks, 1f);
        this.animate(entity.idleAnimationState, AilakeAnimations.spin, ageInTicks, 1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        this.all.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}