package com.academics.undertone.entity.client.Rudahh;

// Made with Blockbench 5.1.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.RudahhEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class RudahhModel<T extends RudahhEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "rudahh"), "main");
    private final ModelPart torso;
    private final ModelPart fins;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart lowerJaw;
    private final ModelPart upperJaw;
    private final ModelPart frontLeftLeg;
    private final ModelPart backRightLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart tail;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tongue;
    private final ModelPart root;

    @Override
    public ModelPart root(){
        return this.root;
    }

    public RudahhModel(ModelPart root) {
        this.root = root;
        this.torso = root.getChild("torso");
        this.fins = this.torso.getChild("fins");
        this.head = root.getChild("head");
        this.jaw = this.head.getChild("jaw");
        this.lowerJaw = this.jaw.getChild("lowerJaw");
        this.upperJaw = this.jaw.getChild("upperJaw");
        this.frontLeftLeg = root.getChild("frontLeftLeg");
        this.backRightLeg = root.getChild("backRightLeg");
        this.frontRightLeg = root.getChild("frontRightLeg");
        this.backLeftLeg = root.getChild("backLeftLeg");
        this.tail = root.getChild("tail");
        this.tail1 = this.tail.getChild("tail1");
        this.tail2 = this.tail.getChild("tail2");
        this.tail3 = this.tail.getChild("tail3");
        this.tongue = root.getChild("tongue");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, -4.0F, 12.0F, 10.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -4.0F, -12.0F, 10.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 21.0F, 0.0F));

        PartDefinition fins = torso.addOrReplaceChild("fins", CubeListBuilder.create().texOffs(42, 52).addBox(6.0F, -2.0F, -22.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(6.0F, -3.0F, -21.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(16, 57).addBox(6.0F, -4.0F, -20.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(58, 52).addBox(6.0F, -5.0F, -19.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 61).addBox(6.0F, -6.0F, -18.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(84, 19).addBox(6.0F, -7.0F, -17.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(90, 49).addBox(6.0F, -8.0F, -16.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 40).addBox(6.0F, -9.0F, -15.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(86, 49).addBox(6.0F, -10.0F, -14.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 57).addBox(6.0F, -11.0F, -13.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 0).addBox(0.0F, -3.0F, -23.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(16, 66).addBox(0.0F, -4.0F, -22.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 65).addBox(0.0F, -5.0F, -21.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(64, 61).addBox(0.0F, -6.0F, -20.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(48, 61).addBox(0.0F, -7.0F, -19.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 88).addBox(0.0F, -8.0F, -18.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(90, 54).addBox(0.0F, -9.0F, -17.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 44).addBox(0.0F, -10.0F, -16.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(28, 89).addBox(0.0F, -11.0F, -15.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 59).addBox(0.0F, -12.0F, -14.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 70).addBox(3.0F, -4.0F, -19.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(48, 70).addBox(3.0F, -5.0F, -18.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 70).addBox(3.0F, -6.0F, -17.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(68, 18).addBox(3.0F, -7.0F, -16.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(68, 9).addBox(3.0F, -8.0F, -15.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(44, 88).addBox(3.0F, -9.0F, -14.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 92).addBox(3.0F, -10.0F, -13.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 59).addBox(3.0F, -11.0F, -12.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(28, 92).addBox(3.0F, -12.0F, -11.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(94, 59).addBox(3.0F, -13.0F, -10.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 79).addBox(-1.0F, -2.0F, -15.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(16, 75).addBox(-1.0F, -3.0F, -14.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(74, 52).addBox(-1.0F, -4.0F, -13.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(-1.0F, -5.0F, -12.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(56, 88).addBox(-1.0F, -6.0F, -11.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(8, 92).addBox(-1.0F, -7.0F, -10.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 63).addBox(-1.0F, -8.0F, -9.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 97).addBox(-1.0F, -9.0F, -8.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(88, 99).addBox(-1.0F, -10.0F, -7.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 70).addBox(5.0F, -5.0F, -16.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(80, 61).addBox(5.0F, -6.0F, -15.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(80, 40).addBox(5.0F, -7.0F, -14.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(64, 79).addBox(5.0F, -8.0F, -13.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(48, 79).addBox(5.0F, -9.0F, -12.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(68, 88).addBox(5.0F, -10.0F, -11.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(92, 88).addBox(5.0F, -11.0F, -10.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 67).addBox(5.0F, -12.0F, -9.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(98, 54).addBox(5.0F, -13.0F, -8.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 99).addBox(5.0F, -14.0F, -7.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 83).addBox(7.0F, -5.0F, -8.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(82, 27).addBox(7.0F, -6.0F, -7.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(80, 79).addBox(7.0F, -7.0F, -6.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(80, 88).addBox(7.0F, -8.0F, -5.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(92, 93).addBox(7.0F, -9.0F, -4.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 71).addBox(7.0F, -10.0F, -3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(92, 98).addBox(7.0F, -11.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 100).addBox(7.0F, -12.0F, -1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(100, 0).addBox(1.0F, -7.0F, -1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(96, 98).addBox(1.0F, -6.0F, -2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(96, 75).addBox(1.0F, -5.0F, -3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(28, 95).addBox(1.0F, -4.0F, -4.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 89).addBox(1.0F, -3.0F, -5.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(84, 0).addBox(1.0F, -2.0F, -6.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 50).addBox(-3.0F, -2.0F, 1.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -16.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition lowerJaw = jaw.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(84, 9).addBox(-2.0F, 0.5F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.0F));

        PartDefinition upperJaw = jaw.addOrReplaceChild("upperJaw", CubeListBuilder.create().texOffs(84, 14).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 1.0F));

        PartDefinition frontLeftLeg = partdefinition.addOrReplaceChild("frontLeftLeg", CubeListBuilder.create().texOffs(82, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(96, 19).addBox(1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 96).addBox(2.0F, 1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(96, 23).addBox(3.0F, 2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(96, 85).addBox(3.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(84, 99).addBox(3.0F, 4.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 51).addBox(2.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(98, 48).addBox(4.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 99).addBox(3.0F, 4.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 19.0F, -10.0F));

        PartDefinition backRightLeg = partdefinition.addOrReplaceChild("backRightLeg", CubeListBuilder.create().texOffs(36, 95).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(96, 79).addBox(-4.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 95).addBox(-4.0F, 2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(68, 95).addBox(-3.0F, 1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(74, 95).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(68, 99).addBox(-4.0F, 4.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 30).addBox(-5.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(98, 27).addBox(-3.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(98, 57).addBox(-4.0F, 4.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 19.0F, 10.0F));

        PartDefinition frontRightLeg = partdefinition.addOrReplaceChild("frontRightLeg", CubeListBuilder.create().texOffs(32, 57).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 95).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 95).addBox(-3.0F, 1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 49).addBox(-4.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 95).addBox(-4.0F, 2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 97).addBox(-5.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 97).addBox(-3.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 50).addBox(-4.0F, 4.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 59).addBox(-4.0F, 4.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 19.0F, -10.0F));

        PartDefinition backLeftLeg = partdefinition.addOrReplaceChild("backLeftLeg", CubeListBuilder.create().texOffs(90, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(96, 82).addBox(3.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 95).addBox(3.0F, 2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(86, 95).addBox(2.0F, 1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 96).addBox(1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 99).addBox(3.0F, 4.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 36).addBox(2.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(98, 33).addBox(4.0F, 4.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(72, 99).addBox(3.0F, 4.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 19.0F, 10.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(46, 28).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 14.0F));

        PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(46, 40).addBox(-3.0F, -1.0F, -6.0F, 6.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, 17.0F));

        PartDefinition tail3 = tail.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(16, 84).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, 23.0F));

        PartDefinition tongue = partdefinition.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -1.0F, -24.0F, 2.0F, 1.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(36, 99).addBox(0.0F, -1.5F, -24.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 99).addBox(0.0F, -1.75F, -22.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 99).addBox(0.0F, -0.25F, -22.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 99).addBox(0.0F, -0.5F, -24.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.5F, 1.0F));

        PartDefinition cube_r1 = tongue.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(60, 99).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.5F, -21.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r2 = tongue.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 99).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.5F, -23.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r3 = tongue.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 99).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.5F, -21.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r4 = tongue.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 99).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.5F, -23.0F, 0.0F, 0.0F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(RudahhEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(RudahhAnimations.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.attack, RudahhAnimations.tongueattack, ageInTicks, 1f);
        this.animate(entity.idleAnimationState, RudahhAnimations.idle, ageInTicks, 1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        frontLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        backRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        frontRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        backLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        tongue.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}