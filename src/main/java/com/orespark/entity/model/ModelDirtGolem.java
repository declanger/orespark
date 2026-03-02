package com.orespark.entity.model;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.MathHelper;

public class ModelDirtGolem extends ModelBase {
    private final ModelRenderer jaw;
    private final ModelRenderer neck;
    private final ModelRenderer flower3_r1;
    private final ModelRenderer flower4_r1;
    private final ModelRenderer flower2_r1;
    private final ModelRenderer flower1_r1;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_arm;
    private final ModelRenderer left_arm;

    public ModelDirtGolem() {
        textureWidth = 64;
        textureHeight = 64;

        jaw = new ModelRenderer(this);
        jaw.setRotationPoint(0.0F, 8.0F, 8.0F);
        jaw.cubeList.add(new ModelBox(jaw, 0, 24, -8.0F, 0.0F, -16.0F, 16, 8, 16, 0.0F, false));

        neck = new ModelRenderer(this);
        neck.setRotationPoint(0.0F, 8.0F, 8.0F);
        neck.cubeList.add(new ModelBox(neck, 0, 0, -8.0F, -8.0F, -16.0F, 16, 8, 16, 0.0F, false));

        flower3_r1 = new ModelRenderer(this);
        flower3_r1.setRotationPoint(-1.0F, -3.9F, -8.0F);
        neck.addChild(flower3_r1);
        setRotationAngle(flower3_r1, -3.0991F, 1.34F, -2.8069F);
        flower3_r1.cubeList.add(new ModelBox(flower3_r1, 48, 0, -4.0F, -16.0F, 0.0F, 8, 14, 0, 0.0F, false));

        flower4_r1 = new ModelRenderer(this);
        flower4_r1.setRotationPoint(-1.0F, -3.9F, -8.0F);
        neck.addChild(flower4_r1);
        setRotationAngle(flower4_r1, -0.01F, 0.2306F, 0.2911F);
        flower4_r1.cubeList.add(new ModelBox(flower4_r1, 48, 0, -4.0F, -16.0F, 0.0F, 8, 14, 0, 0.0F, false));

        flower2_r1 = new ModelRenderer(this);
        flower2_r1.setRotationPoint(-1.0F, -7.9F, -8.0F);
        neck.addChild(flower2_r1);
        setRotationAngle(flower2_r1, -0.1207F, 0.7703F, -0.2157F);
        flower2_r1.cubeList.add(new ModelBox(flower2_r1, 0, 0, -4.0F, -8.0F, 0.0F, 8, 8, 0, 0.0F, false));

        flower1_r1 = new ModelRenderer(this);
        flower1_r1.setRotationPoint(-1.0F, -7.9F, -8.0F);
        neck.addChild(flower1_r1);
        setRotationAngle(flower1_r1, -0.1235F, -0.793F, -0.0432F);
        flower1_r1.cubeList.add(new ModelBox(flower1_r1, 0, 0, -4.0F, -8.0F, 0.0F, 8, 8, 0, 0.0F, false));

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(4.0F, 17.0F, 0.0F);
        right_leg.cubeList.add(new ModelBox(right_leg, 0, 50, -3.0F, -1.0F, -3.0F, 6, 8, 6, 0.0F, false));

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(-4.0F, 17.0F, 0.0F);
        left_leg.cubeList.add(new ModelBox(left_leg, 0, 50, -3.0F, -1.0F, -3.0F, 6, 8, 6, 0.0F, true));

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(5.0F, 11.5F, 0.0F);
        setRotationAngle(right_arm, 0.0F, 0.0F, 0.7854F);
        right_arm.cubeList.add(new ModelBox(right_arm, 32, 51, 0.0F, -6.0F, -3.0F, 10, 6, 6, 0.0F, false));

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(-5.0F, 11.5F, 0.0F);
        setRotationAngle(left_arm, 0.0F, 0.0F, -0.7854F);
        left_arm.cubeList.add(new ModelBox(left_arm, 32, 51, -10.0F, -6.0F, -3.0F, 10, 6, 6, 0.0F, true));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        jaw.render(f5);
        neck.render(f5);
        right_leg.render(f5);
        left_leg.render(f5);
        right_arm.render(f5);
        left_arm.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scaleFactor,entity);

        this.left_leg.rotateAngleX = MathHelper.sin(limbSwing * 0.8f) * limbSwingAmount;
        this.right_leg.rotateAngleX = MathHelper.sin(limbSwing * 0.8f) * -limbSwingAmount;

        this.left_arm.rotateAngleY = MathHelper.sin(limbSwing * 0.8f) * limbSwingAmount * 0.610865238f;
        this.right_arm.rotateAngleY = MathHelper.sin(limbSwing * 0.8f) * limbSwingAmount * 0.610865238f;

        this.left_arm.rotateAngleX = MathHelper.sin(limbSwing * 0.8f) * limbSwingAmount * -0.01f;
        this.right_arm.rotateAngleX = MathHelper.sin(limbSwing * 0.8f) * limbSwingAmount * 0.01f;

        float tilt = this.jaw.rotateAngleX * 0.99f + (limbSwingAmount > 0.01f ? 0.2618f : 0.0f) * 0.01f;

        this.jaw.rotateAngleX = tilt;
        this.neck.rotateAngleX = (((EntityMob) entity).getAttackTarget() != null ? tilt : 0f) + (headPitch * 0.017453292F) - (MathHelper.sin(Math.min(swingProgress*6f,(float) Math.PI)) * 0.65f);
    }
}