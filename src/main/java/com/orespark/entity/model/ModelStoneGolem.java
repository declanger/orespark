package com.orespark.entity.model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStoneGolem extends ModelBase {
	private final ModelRenderer neck;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer left_shoulder;
	private final ModelRenderer left_elbow;
	private final ModelRenderer left_wrist;
	private final ModelRenderer left_middle;
	private final ModelRenderer left_thumb;
	private final ModelRenderer left_thumb_r1;
	private final ModelRenderer left_pinky;
	private final ModelRenderer left_pinky_r1;
	private final ModelRenderer right_shoulder;
	private final ModelRenderer right_elbow;
	private final ModelRenderer right_wrist;
	private final ModelRenderer right_middle;
	private final ModelRenderer right_thumb;
	private final ModelRenderer right_thumb_r1;
	private final ModelRenderer right_pinky;
	private final ModelRenderer right_pinky_r1;


	public ModelStoneGolem() {
		textureWidth = 64;
		textureHeight = 64;

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 8.0F, 8.0F);
		neck.cubeList.add(new ModelBox(neck, 0, 0, -8.0F, -20.0F, -14.0F, 16, 24, 12, 0.0F, false));
		neck.cubeList.add(new ModelBox(neck, 0, 36, -8.0F, -18.0F, -16.0F, 16, 5, 2, 0.0F, false));
		neck.cubeList.add(new ModelBox(neck, 44, 0, -2.0F, -13.0F, -17.0F, 4, 8, 3, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(4.0F, 10.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 40, 42, -3.0F, 2.0F, -3.0F, 6, 12, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-4.0F, 10.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 40, 42, -3.0F, 2.0F, -3.0F, 6, 12, 6, 0.0F, true));

		left_shoulder = new ModelRenderer(this);
		left_shoulder.setRotationPoint(-8.0F, -0.5F, 0.0F);
		setRotationAngle(left_shoulder, 0.0F, -0.3491F, -0.7854F);
		left_shoulder.cubeList.add(new ModelBox(left_shoulder, 0, 43, -12.0F, -4.0F, -3.0F, 12, 6, 6, 0.0F, true));

		left_elbow = new ModelRenderer(this);
		left_elbow.setRotationPoint(-10.0F, -1.0F, 0.0F);
		left_shoulder.addChild(left_elbow);
		setRotationAngle(left_elbow, 0.2618F, -1.1345F, 0.0F);
		left_elbow.cubeList.add(new ModelBox(left_elbow, 0, 43, -14.0F, -3.0F, -3.0F, 12, 6, 6, 0.0F, true));

		left_wrist = new ModelRenderer(this);
		left_wrist.setRotationPoint(-14.0F, 0.0F, 0.0F);
		left_elbow.addChild(left_wrist);
		setRotationAngle(left_wrist, 0.4363F, -0.5236F, 0.2182F);
		left_wrist.cubeList.add(new ModelBox(left_wrist, 0, 43, -12.0F, -3.0F, -3.0F, 12, 6, 6, 0.0F, true));

		left_middle = new ModelRenderer(this);
		left_middle.setRotationPoint(-11.0F, 1.0F, 0.0F);
		left_wrist.addChild(left_middle);
		left_middle.cubeList.add(new ModelBox(left_middle, 36, 36, -6.0F, -7.0F, -1.5F, 6, 3, 3, 0.0F, true));

		left_thumb = new ModelRenderer(this);
		left_thumb.setRotationPoint(-11.0F, 5.5F, 2.5F);
		left_wrist.addChild(left_thumb);
		

		left_thumb_r1 = new ModelRenderer(this);
		left_thumb_r1.setRotationPoint(0.0F, -4.0F, 0.0F);
		left_thumb.addChild(left_thumb_r1);
		setRotationAngle(left_thumb_r1, -0.7854F, 0.0F, 0.0F);
		left_thumb_r1.cubeList.add(new ModelBox(left_thumb_r1, 36, 36, -6.0F, -1.5F, 0.0F, 6, 3, 3, 0.0F, true));

		left_pinky = new ModelRenderer(this);
		left_pinky.setRotationPoint(-11.0F, 5.5F, -2.5F);
		left_wrist.addChild(left_pinky);
		

		left_pinky_r1 = new ModelRenderer(this);
		left_pinky_r1.setRotationPoint(0.0F, -4.0F, 0.0F);
		left_pinky.addChild(left_pinky_r1);
		setRotationAngle(left_pinky_r1, 0.7854F, 0.0F, 0.0F);
		left_pinky_r1.cubeList.add(new ModelBox(left_pinky_r1, 36, 36, -6.0F, -1.5F, -3.0F, 6, 3, 3, 0.0F, true));

		right_shoulder = new ModelRenderer(this);
		right_shoulder.setRotationPoint(8.0F, -0.5F, 0.0F);
		setRotationAngle(right_shoulder, 0.0F, 0.3491F, 0.7854F);
		right_shoulder.cubeList.add(new ModelBox(right_shoulder, 0, 43, 0.0F, -4.0F, -3.0F, 12, 6, 6, 0.0F, false));

		right_elbow = new ModelRenderer(this);
		right_elbow.setRotationPoint(10.0F, -1.0F, 0.0F);
		right_shoulder.addChild(right_elbow);
		setRotationAngle(right_elbow, 0.2618F, 1.1345F, 0.0F);
		right_elbow.cubeList.add(new ModelBox(right_elbow, 0, 43, 2.0F, -3.0F, -3.0F, 12, 6, 6, 0.0F, false));

		right_wrist = new ModelRenderer(this);
		right_wrist.setRotationPoint(14.0F, 0.0F, 0.0F);
		right_elbow.addChild(right_wrist);
		setRotationAngle(right_wrist, 0.4363F, 0.5236F, -0.2182F);
		right_wrist.cubeList.add(new ModelBox(right_wrist, 0, 43, 0.0F, -3.0F, -3.0F, 12, 6, 6, 0.0F, false));

		right_middle = new ModelRenderer(this);
		right_middle.setRotationPoint(11.0F, 1.0F, 0.0F);
		right_wrist.addChild(right_middle);
		right_middle.cubeList.add(new ModelBox(right_middle, 36, 36, 0.0F, -7.0F, -1.5F, 6, 3, 3, 0.0F, false));

		right_thumb = new ModelRenderer(this);
		right_thumb.setRotationPoint(11.0F, 5.5F, 2.5F);
		right_wrist.addChild(right_thumb);
		

		right_thumb_r1 = new ModelRenderer(this);
		right_thumb_r1.setRotationPoint(0.0F, -4.0F, 0.0F);
		right_thumb.addChild(right_thumb_r1);
		setRotationAngle(right_thumb_r1, -0.7854F, 0.0F, 0.0F);
		right_thumb_r1.cubeList.add(new ModelBox(right_thumb_r1, 36, 36, 0.0F, -1.5F, 0.0F, 6, 3, 3, 0.0F, false));

		right_pinky = new ModelRenderer(this);
		right_pinky.setRotationPoint(11.0F, 5.5F, -2.5F);
		right_wrist.addChild(right_pinky);
		

		right_pinky_r1 = new ModelRenderer(this);
		right_pinky_r1.setRotationPoint(0.0F, -4.0F, 0.0F);
		right_pinky.addChild(right_pinky_r1);
		setRotationAngle(right_pinky_r1, 0.7854F, 0.0F, 0.0F);
		right_pinky_r1.cubeList.add(new ModelBox(right_pinky_r1, 36, 36, 0.0F, -1.5F, -3.0F, 6, 3, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		neck.render(f5);
		right_leg.render(f5);
		left_leg.render(f5);
		left_shoulder.render(f5);
		right_shoulder.render(f5);
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

		if (swingProgress == 0.0f) {
			this.right_shoulder.rotateAngleY = 0.3491F + (MathHelper.sin(limbSwing * 0.3f)) * 0.8f * limbSwingAmount;
			this.left_shoulder.rotateAngleY = -0.3491F + (MathHelper.sin(limbSwing * 0.3f)) * 0.8f * limbSwingAmount;

			this.right_shoulder.rotateAngleX = (MathHelper.sin(limbSwing * 0.3f)) * 0.1309f * limbSwingAmount;
			this.left_shoulder.rotateAngleX = (MathHelper.sin(limbSwing * 0.3f)) * -0.1309f * limbSwingAmount;

			this.right_elbow.rotateAngleY = (MathHelper.sin(limbSwing * 0.3f)) * 0.44f * limbSwingAmount;
			this.left_elbow.rotateAngleY = (MathHelper.sin(limbSwing * 0.3f)) * 0.44f * limbSwingAmount;

			this.right_wrist.rotateAngleY = (MathHelper.sin(limbSwing * 0.3f)) * 0.372665f * limbSwingAmount;
			this.left_wrist.rotateAngleY = (MathHelper.sin(limbSwing * 0.3f)) * 0.372665f * limbSwingAmount;
		}
		else {
			setRotationAngle(left_shoulder, 0.0F, -0.3491F - MathHelper.sin(swingProgress * (float)Math.PI) * 1.0472f, -0.7854F);
			setRotationAngle(left_elbow, 0.2618F, -1.1345F * MathHelper.cos(swingProgress * (float)Math.PI), 0.0F);
			setRotationAngle(left_wrist, 0.4363F, -0.5236F + MathHelper.sin(swingProgress * (float)Math.PI) * 0.261799f, 0.2182F);
			setRotationAngle(left_thumb_r1, -0.7854F, MathHelper.sin(swingProgress * (float)Math.PI) * -0.261799f,MathHelper.sin(swingProgress * (float)Math.PI) * -0.261799f);
			setRotationAngle(left_pinky_r1, 0.7854F, MathHelper.sin(swingProgress * (float)Math.PI) * 0.261799f, MathHelper.sin(swingProgress * (float)Math.PI) * -0.261799f);
			setRotationAngle(right_shoulder, 0.0F, 0.3491F + MathHelper.sin(swingProgress * (float)Math.PI) * 1.0472f, 0.7854F);
			setRotationAngle(right_elbow, 0.2618F, 1.1345F * MathHelper.cos(swingProgress * (float)Math.PI), 0.0F);
			setRotationAngle(right_wrist, 0.4363F, 0.5236F - MathHelper.sin(swingProgress * (float)Math.PI) * 0.261799f, -0.2182F);
			setRotationAngle(right_thumb_r1, -0.7854F, MathHelper.sin(swingProgress * (float)Math.PI) * 0.261799f, MathHelper.sin(swingProgress * (float)Math.PI) * 0.261799f);
			setRotationAngle(right_pinky_r1, 0.7854F, MathHelper.sin(swingProgress * (float)Math.PI) * -0.261799f, MathHelper.sin(swingProgress * (float)Math.PI) * 0.261799f);
		}
		
	}
}