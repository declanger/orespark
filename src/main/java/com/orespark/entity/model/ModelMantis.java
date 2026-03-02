package com.orespark.entity.model;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.orespark.Orespark;
import com.orespark.entity.EntityMantis;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;


public class ModelMantis extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer abdomen_r1;
	private final ModelRenderer neck;
	private final ModelRenderer thorax_r1;
	private final ModelRenderer head;
	private final ModelRenderer leftAntenna_r1;
	private final ModelRenderer rightAntenna_r1;
	private final ModelRenderer rightEye_r1;
	private final ModelRenderer leftEye_r1;
	private final ModelRenderer teeth_r1;
	private final ModelRenderer leftArm;
	private final ModelRenderer middleArmL;
	private final ModelRenderer lowerArmL;
	private final ModelRenderer rightArm;
	private final ModelRenderer middleArmR;
	private final ModelRenderer lowerArmR;
	private final ModelRenderer leftFrontLeg;
	private final ModelRenderer leftSpur;
	private final ModelRenderer leftTibia;
	private final ModelRenderer rightFrontLeg;
	private final ModelRenderer rightSpur;
	private final ModelRenderer rightTibia;
	private final ModelRenderer rightRearLeg;
	private final ModelRenderer rightTarus;
	private final ModelRenderer rightTarsi;
	private final ModelRenderer leftRearLeg;
	private final ModelRenderer leftTarus;
	private final ModelRenderer leftTarsi;

	public ModelMantis() {
		textureWidth = 96;
		textureHeight = 48;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.75F, -4.5F);
		

		abdomen_r1 = new ModelRenderer(this);
		abdomen_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(abdomen_r1);
		setRotationAngle(abdomen_r1, -0.4363F, 0.0F, 0.0F);
		abdomen_r1.cubeList.add(new ModelBox(abdomen_r1, 0, 0, -2.5F, -4.0F, -1.0F, 5, 5, 37, 0.0F, false));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 6.0F, -6.5F);
		

		thorax_r1 = new ModelRenderer(this);
		thorax_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		neck.addChild(thorax_r1);
		setRotationAngle(thorax_r1, 0.6109F, 0.0F, 0.0F);
		thorax_r1.cubeList.add(new ModelBox(thorax_r1, 47, 4, -1.5F, -18.0F, 1.0F, 3, 19, 3, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -15.0F, -8.0F);
		neck.addChild(head);

		leftAntenna_r1 = new ModelRenderer(this);
		leftAntenna_r1.setRotationPoint(0.25F, -2.5F, 0.0F);
		head.addChild(leftAntenna_r1);
		setRotationAngle(leftAntenna_r1, 0.0436F, -1.0908F, 0.2618F);
		leftAntenna_r1.cubeList.add(new ModelBox(leftAntenna_r1, 88, -2, 0.5F, -7.0F, -3.0F, 0, 6, 4, 0.0F, true));

		rightAntenna_r1 = new ModelRenderer(this);
		rightAntenna_r1.setRotationPoint(-0.25F, -2.5F, 0.0F);
		head.addChild(rightAntenna_r1);
		setRotationAngle(rightAntenna_r1, 0.0436F, 1.0908F, -0.2618F);
		rightAntenna_r1.cubeList.add(new ModelBox(rightAntenna_r1, 88, -2, -0.5F, -7.0F, -3.0F, 0, 6, 4, 0.0F, false));

		rightEye_r1 = new ModelRenderer(this);
		rightEye_r1.setRotationPoint(-3.0F, -6.0F, -0.5F);
		head.addChild(rightEye_r1);
		setRotationAngle(rightEye_r1, -0.0869F, -0.0076F, -0.0869F);
		rightEye_r1.cubeList.add(new ModelBox(rightEye_r1, 65, 10, -2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F, false));

		leftEye_r1 = new ModelRenderer(this);
		leftEye_r1.setRotationPoint(3.0F, -6.0F, -0.5F);
		head.addChild(leftEye_r1);
		setRotationAngle(leftEye_r1, -0.0869F, 0.0076F, 0.0869F);
		leftEye_r1.cubeList.add(new ModelBox(leftEye_r1, 65, 10, -2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F, true));

		teeth_r1 = new ModelRenderer(this);
		teeth_r1.setRotationPoint(-1.0F, 0.0F, -1.0F);
		head.addChild(teeth_r1);
		setRotationAngle(teeth_r1, -0.2618F, 0.0F, 0.0F);
		teeth_r1.cubeList.add(new ModelBox(teeth_r1, 47, 0, 0.0F, 3.0F, -2.0F, 2, 1, 1, 0.0F, false));
		teeth_r1.cubeList.add(new ModelBox(teeth_r1, 53, 0, -1.0F, 1.0F, -2.0F, 4, 2, 2, 0.0F, false));
		teeth_r1.cubeList.add(new ModelBox(teeth_r1, 62, 1, -1.5F, -5.0F, -2.0F, 5, 6, 3, 0.0F, false));

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(2.0F, -3.0F, -13.0F);
		setRotationAngle(leftArm, 0.0F, 0.0F, -0.0873F);
		leftArm.cubeList.add(new ModelBox(leftArm, 0, 12, -1.0F, -3.0F, -1.0F, 2, 10, 2, 0.0F, false));

		middleArmL = new ModelRenderer(this);
		middleArmL.setRotationPoint(0.0F, 6.0F, 0.0F);
		leftArm.addChild(middleArmL);
		middleArmL.cubeList.add(new ModelBox(middleArmL, 0, 15, -0.999F, 0.001F, -8.001F, 2, 2, 9, 0.0F, false));

		lowerArmL = new ModelRenderer(this);
		lowerArmL.setRotationPoint(0.0F, 1.5F, -8.0F);
		middleArmL.addChild(lowerArmL);
		lowerArmL.cubeList.add(new ModelBox(lowerArmL, 0, 0, -1.0F, -1.5F, -1.0F, 2, 10, 2, 0.0F, false));
		lowerArmL.cubeList.add(new ModelBox(lowerArmL, 22, 15, 0.0F, 0.5F, -1.0F, 0, 9, 5, 0.0F, false));

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-2.0F, -3.0F, -13.0F);
		setRotationAngle(rightArm, 0.0F, 0.0F, 0.0873F);
		rightArm.cubeList.add(new ModelBox(rightArm, 0, 12, -1.0F, -3.0F, -1.0F, 2, 10, 2, 0.0F, true));

		middleArmR = new ModelRenderer(this);
		middleArmR.setRotationPoint(4.0F, 6.0F, 0.0F);
		rightArm.addChild(middleArmR);
		middleArmR.cubeList.add(new ModelBox(middleArmR, 0, 15, -5.001F, 0.001F, -8.001F, 2, 2, 9, 0.0F, true));

		lowerArmR = new ModelRenderer(this);
		lowerArmR.setRotationPoint(0.0F, 1.5F, -8.0F);
		middleArmR.addChild(lowerArmR);
		lowerArmR.cubeList.add(new ModelBox(lowerArmR, 0, 0, -5.0F, -1.5F, -1.0F, 2, 10, 2, 0.0F, true));
		lowerArmR.cubeList.add(new ModelBox(lowerArmR, 22, 15, -4.0F, 0.5F, -1.0F, 0, 9, 5, 0.0F, true));

		leftFrontLeg = new ModelRenderer(this);
		leftFrontLeg.setRotationPoint(2.0F, 8.0F, -3.0F);
		setRotationAngle(leftFrontLeg, -0.1745F, -0.5236F, 0.0F);
		leftFrontLeg.cubeList.add(new ModelBox(leftFrontLeg, 13, 0, -1.0F, -1.0F, -1.0F, 2, 8, 2, 0.0F, false));

		leftSpur = new ModelRenderer(this);
		leftSpur.setRotationPoint(0.0F, 7.0F, 1.0F);
		leftFrontLeg.addChild(leftSpur);
		setRotationAngle(leftSpur, -0.2618F, 0.0F, 0.0F);
		leftSpur.cubeList.add(new ModelBox(leftSpur, 68, 21, -1.001F, 0.0F, -12.0F, 2, 2, 12, 0.0F, false));

		leftTibia = new ModelRenderer(this);
		leftTibia.setRotationPoint(0.0F, 0.0F, -12.0F);
		leftSpur.addChild(leftTibia);
		setRotationAngle(leftTibia, 0.3054F, 0.0F, 0.0F);
		leftTibia.cubeList.add(new ModelBox(leftTibia, 29, 4, -1.0F, 0.0F, -2.0F, 2, 14, 2, 0.0F, false));

		rightFrontLeg = new ModelRenderer(this);
		rightFrontLeg.setRotationPoint(-2.0F, 8.0F, -3.0F);
		setRotationAngle(rightFrontLeg, -0.1745F, 0.5236F, 0.0F);
		rightFrontLeg.cubeList.add(new ModelBox(rightFrontLeg, 13, 0, -1.0F, -1.0F, -1.0F, 2, 8, 2, 0.0F, true));

		rightSpur = new ModelRenderer(this);
		rightSpur.setRotationPoint(0.0F, 7.0F, 1.0F);
		rightFrontLeg.addChild(rightSpur);
		setRotationAngle(rightSpur, -0.2618F, 0.0F, 0.0F);
		rightSpur.cubeList.add(new ModelBox(rightSpur, 68, 21, -0.999F, 0.0F, -12.0F, 2, 2, 12, 0.0F, true));

		rightTibia = new ModelRenderer(this);
		rightTibia.setRotationPoint(0.0F, 0.0F, -12.0F);
		rightSpur.addChild(rightTibia);
		setRotationAngle(rightTibia, 0.3054F, 0.0F, 0.0F);
		rightTibia.cubeList.add(new ModelBox(rightTibia, 29, 4, -1.0F, 0.0F, -2.0F, 2, 14, 2, 0.0F, true));

		rightRearLeg = new ModelRenderer(this);
		rightRearLeg.setRotationPoint(-2.0F, 8.75F, 2.5F);
		setRotationAngle(rightRearLeg, 0.0F, -0.3054F, 0.0F);
		rightRearLeg.cubeList.add(new ModelBox(rightRearLeg, 13, 10, -1.0F, -2.0F, -1.0F, 2, 8, 2, 0.0F, false));

		rightTarus = new ModelRenderer(this);
		rightTarus.setRotationPoint(0.0F, 6.0F, -1.0F);
		rightRearLeg.addChild(rightTarus);
		setRotationAngle(rightTarus, 0.48F, 0.0F, 0.0F);
		rightTarus.cubeList.add(new ModelBox(rightTarus, 47, 18, -1.001F, 0.0F, 0.0F, 2, 2, 17, 0.0F, false));

		rightTarsi = new ModelRenderer(this);
		rightTarsi.setRotationPoint(0.0F, 0.0F, 17.0F);
		rightTarus.addChild(rightTarsi);
		setRotationAngle(rightTarsi, -0.3054F, 0.0F, 0.0F);
		rightTarsi.cubeList.add(new ModelBox(rightTarsi, 21, 0, -1.0F, 0.0F, 0.0F, 2, 18, 2, 0.0F, false));

		leftRearLeg = new ModelRenderer(this);
		leftRearLeg.setRotationPoint(2.0F, 8.75F, 2.5F);
		setRotationAngle(leftRearLeg, 0.0F, 0.3054F, 0.0F);
		leftRearLeg.cubeList.add(new ModelBox(leftRearLeg, 13, 10, -1.0F, -2.0F, -1.0F, 2, 8, 2, 0.0F, true));

		leftTarus = new ModelRenderer(this);
		leftTarus.setRotationPoint(0.0F, 6.0F, -1.0F);
		leftRearLeg.addChild(leftTarus);
		setRotationAngle(leftTarus, 0.48F, 0.0F, 0.0F);
		leftTarus.cubeList.add(new ModelBox(leftTarus, 47, 18, -0.999F, 0.0F, 0.0F, 2, 2, 17, 0.0F, true));

		leftTarsi = new ModelRenderer(this);
		leftTarsi.setRotationPoint(0.0F, 0.0F, 17.0F);
		leftTarus.addChild(leftTarsi);
		setRotationAngle(leftTarsi, -0.3054F, 0.0F, 0.0F);
		leftTarsi.cubeList.add(new ModelBox(leftTarsi, 21, 0, -1.0F, 0.0F, 0.0F, 2, 18, 2, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f0, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		neck.render(f5);
		leftArm.render(f5);
		rightArm.render(f5);
		leftFrontLeg.render(f5);
		rightFrontLeg.render(f5);
		rightRearLeg.render(f5);
		leftRearLeg.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scaleFactor,entity);

		float swingClamped = MathHelper.clamp((float)((entity.motionX * entity.motionX) + (entity.motionZ * entity.motionZ)),0.0f,0.15f);

		this.neck.rotateAngleY = netHeadYaw * 0.01745329f * 0.3f;
		this.head.rotateAngleY = netHeadYaw * 0.01745329f * 0.75f;
		this.neck.rotateAngleX = this.neck.rotateAngleX * 0.97f + (0.2618f * swingClamped / 0.026f) * 0.03f;
		this.head.rotateAngleX = headPitch * 0.01745329f + (this.neck.rotateAngleX);

		swingClamped = MathHelper.clamp(limbSwingAmount,0.0f,0.3f);

		this.leftAntenna_r1.rotateAngleX = 0.0436f + MathHelper.sin(limbSwing ) * swingClamped * 0.174533f;
		this.rightAntenna_r1.rotateAngleX = 0.0436f + MathHelper.sin(limbSwing) * swingClamped * 0.174533f;

		this.rightFrontLeg.rotateAngleY = MathHelper.sin(limbSwing * 0.6662f) * 2f * swingClamped + 0.523599f;
		this.leftFrontLeg.rotateAngleY = MathHelper.sin(limbSwing * 0.6662f) * 2f * swingClamped - 0.523599f;

		this.rightFrontLeg.rotateAngleX = Math.max(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI/2.0f * 3),0) * -2f * swingClamped - 0.1745f;
		this.leftFrontLeg.rotateAngleX = Math.max(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI/2.0f),0) * -2f * swingClamped - 0.1745f;

		this.rightRearLeg.rotateAngleY = (1.0f-MathHelper.cos(limbSwing * -0.6662f - (float)Math.PI/2.0f)) * -0.5f * swingClamped - 0.32f;
		this.leftRearLeg.rotateAngleY = (1.0f-MathHelper.cos(limbSwing * 0.6662f - (float)Math.PI/2.0f)) * 0.5f * swingClamped + 0.32f;

		this.rightRearLeg.rotateAngleX = Math.max(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI/2.0f),0) * -0.2f * swingClamped;
		this.leftRearLeg.rotateAngleX = Math.max(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI/2.0f*3.0f),0) * -0.2f * swingClamped;

		this.rightTarus.rotateAngleX = Math.max(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI/2.0f) - 0.5f,0.0f) * 2f * swingClamped + 0.4799655f;
		this.leftTarus.rotateAngleX = Math.max(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI/2.0f*3.0f) - 0.5f,0.0f) * 2f * swingClamped + 0.4799655f;

		this.rightTarsi.rotateAngleX = (1.0f-MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI/2.0f)) * -0.7f * swingClamped - 0.3054326f;
		this.leftTarsi.rotateAngleX = (1.0f-MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI/2.0f*3.0f)) * -0.7f * swingClamped - 0.3054326f;

		this.body.rotateAngleX = body.rotateAngleX * 0.99f + (limbSwingAmount > 0.01f ? 0.2618f : 0.0f) * 0.01f;


		float s = -MathHelper.sin(swingProgress * (float)Math.PI);

		this.leftArm.rotateAngleX = s;
		this.rightArm.rotateAngleX = s;

		s = -MathHelper.sin(swingProgress * (float)Math.PI*2.0f);

		this.middleArmL.rotateAngleX = s * 0.75f;
		this.middleArmR.rotateAngleX = s * 0.75f;

		this.lowerArmL.rotateAngleX = s * -1.25f;
		this.lowerArmR.rotateAngleX = s * -1.25f;
	}
}