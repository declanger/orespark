package com.orespark.entity.model;// Made with Blockbench 5.2.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.orespark.entity.EntityAnt;
import com.orespark.util.OresparkUtil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelAnt extends ModelBase {
	private final ModelRenderer rightRearLeg;
	private final ModelRenderer rightRearLeg_r1;
	private final ModelRenderer leftRearLeg;
	private final ModelRenderer leftRearLeg_r1;
	private final ModelRenderer rightMiddleLeg;
	private final ModelRenderer rightMiddleLeg_r1;
	private final ModelRenderer leftMiddleLeg;
	private final ModelRenderer leftMiddleLeg_r1;
	private final ModelRenderer rightFrontLeg;
	private final ModelRenderer rightFrontLeg_r1;
	private final ModelRenderer leftFrontLeg;
	private final ModelRenderer leftFrontLeg_r1;
	private final ModelRenderer thorax;
	private final ModelRenderer abdomen;
	private final ModelRenderer head;
	private final ModelRenderer rightAntenna;
	private final ModelRenderer leftAntena;
	private final ModelRenderer pincerStatic;
	private final ModelRenderer leftPincer;
	private final ModelRenderer rightPincer;

	public ModelAnt() {
		textureWidth = 32;
		textureHeight = 16;

		rightRearLeg = new ModelRenderer(this);
		rightRearLeg.setRotationPoint(-0.75F, 19.35F, 0.6F);
		setRotationAngle(rightRearLeg, 0.0F, 0.6109F, 0.0F);


		rightRearLeg_r1 = new ModelRenderer(this);
		rightRearLeg_r1.setRotationPoint(-0.1651F, 2.45F, 0.0F);
		rightRearLeg.addChild(rightRearLeg_r1);
		setRotationAngle(rightRearLeg_r1, 0.0F, 0.0F, -0.5236F);
		rightRearLeg_r1.cubeList.add(new ModelBox(rightRearLeg_r1, 8, 0, -4.7321F, -0.134F, -0.5F, 5, 0, 1, 0.0F, false));

		leftRearLeg = new ModelRenderer(this);
		leftRearLeg.setRotationPoint(0.75F, 21.55F, 0.6F);
		setRotationAngle(leftRearLeg, 0.0F, -0.6109F, 0.0F);


		leftRearLeg_r1 = new ModelRenderer(this);
		leftRearLeg_r1.setRotationPoint(0.1651F, 0.25F, 0.0F);
		leftRearLeg.addChild(leftRearLeg_r1);
		setRotationAngle(leftRearLeg_r1, 0.0F, 0.0F, 0.5236F);
		leftRearLeg_r1.cubeList.add(new ModelBox(leftRearLeg_r1, 8, 0, -0.2679F, -0.134F, -0.5F, 5, 0, 1, 0.0F, true));

		rightMiddleLeg = new ModelRenderer(this);
		rightMiddleLeg.setRotationPoint(-0.75F, 21.25F, -0.5F);


		rightMiddleLeg_r1 = new ModelRenderer(this);
		rightMiddleLeg_r1.setRotationPoint(0.023F, 0.0587F, 0.0F);
		rightMiddleLeg.addChild(rightMiddleLeg_r1);
		setRotationAngle(rightMiddleLeg_r1, 0.0F, 0.0F, -0.5236F);
		rightMiddleLeg_r1.cubeList.add(new ModelBox(rightMiddleLeg_r1, 8, 0, -5.3071F, -0.0041F, -0.5F, 6, 0, 1, 0.0F, false));

		leftMiddleLeg = new ModelRenderer(this);
		leftMiddleLeg.setRotationPoint(0.75F, 21.25F, -0.5F);


		leftMiddleLeg_r1 = new ModelRenderer(this);
		leftMiddleLeg_r1.setRotationPoint(-0.023F, 0.0587F, 0.0F);
		leftMiddleLeg.addChild(leftMiddleLeg_r1);
		setRotationAngle(leftMiddleLeg_r1, 0.0F, 0.0F, 0.5236F);
		leftMiddleLeg_r1.cubeList.add(new ModelBox(leftMiddleLeg_r1, 8, 0, -0.6929F, -0.0041F, -0.5F, 6, 0, 1, 0.0F, true));

		rightFrontLeg = new ModelRenderer(this);
		rightFrontLeg.setRotationPoint(-0.75F, 19.35F, 0.6F);
		setRotationAngle(rightFrontLeg, 0.0F, -0.6109F, 0.0F);


		rightFrontLeg_r1 = new ModelRenderer(this);
		rightFrontLeg_r1.setRotationPoint(-1.427F, 2.45F, -1.8021F);
		rightFrontLeg.addChild(rightFrontLeg_r1);
		setRotationAngle(rightFrontLeg_r1, 0.0F, 0.0F, -0.5236F);
		rightFrontLeg_r1.cubeList.add(new ModelBox(rightFrontLeg_r1, 8, 0, -4.7321F, -0.134F, -0.5F, 5, 0, 1, 0.0F, false));

		leftFrontLeg = new ModelRenderer(this);
		leftFrontLeg.setRotationPoint(-0.75F, 19.35F, 0.6F);
		setRotationAngle(leftFrontLeg, 0.0F, 0.6109F, 0.0F);


		leftFrontLeg_r1 = new ModelRenderer(this);
		leftFrontLeg_r1.setRotationPoint(2.6557F, 2.45F, -0.9417F);
		leftFrontLeg.addChild(leftFrontLeg_r1);
		setRotationAngle(leftFrontLeg_r1, 0.0F, 0.0F, 0.5236F);
		leftFrontLeg_r1.cubeList.add(new ModelBox(leftFrontLeg_r1, 8, 0, -0.2679F, -0.134F, -0.5F, 5, 0, 1, 0.0F, true));

		thorax = new ModelRenderer(this);
		thorax.setRotationPoint(0.0F, 20.8507F, 1.1789F);
		setRotationAngle(thorax, 0.2182F, 0.0F, 0.0F);
		thorax.cubeList.add(new ModelBox(thorax, 14, 1, -1.5F, -1.5012F, 0.3049F, 3, 3, 4, 0.0F, false));

		abdomen = new ModelRenderer(this);
		abdomen.setRotationPoint(0.0F, 21.3748F, -0.8265F);
		setRotationAngle(abdomen, 0.0873F, 0.0F, 0.0F);
		abdomen.cubeList.add(new ModelBox(abdomen, 0, 0, -1.0F, -1.0F, -2.5F, 2, 2, 5, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 21.1F, -2.5F);
		head.cubeList.add(new ModelBox(head, 0, 7, -1.5F, -1.5F, -3.25F, 3, 3, 3, 0.0F, false));

		rightAntenna = new ModelRenderer(this);
		rightAntenna.setRotationPoint(-1.05F, -1.5F, -2.75F);
		head.addChild(rightAntenna);
		setRotationAngle(rightAntenna, 0.8727F, 0.8727F, 0.0F);
		rightAntenna.cubeList.add(new ModelBox(rightAntenna, 13, 1, -1.5F, -3.0F, 0.0F, 2, 3, 0, 0.0F, false));

		leftAntena = new ModelRenderer(this);
		leftAntena.setRotationPoint(1.05F, -1.5F, -2.75F);
		head.addChild(leftAntena);
		setRotationAngle(leftAntena, 0.8727F, -0.8727F, 0.0F);
		leftAntena.cubeList.add(new ModelBox(leftAntena, 13, 1, -0.5F, -3.0F, 0.0F, 2, 3, 0, 0.0F, true));

		pincerStatic = new ModelRenderer(this);
		pincerStatic.setRotationPoint(0.0F, 0.6F, -3.5F);
		head.addChild(pincerStatic);
		setRotationAngle(pincerStatic, 0.2618F, 0.0F, 0.0F);


		leftPincer = new ModelRenderer(this);
		leftPincer.setRotationPoint(1.25F, 0.0F, 0.0F);
		pincerStatic.addChild(leftPincer);
		leftPincer.cubeList.add(new ModelBox(leftPincer, 8, 1, -1.0F, -0.04F, -1.725F, 1, 0, 1, 0.0F, true));
		leftPincer.cubeList.add(new ModelBox(leftPincer, 8, 2, -0.5F, -0.04F, -0.725F, 1, 0, 1, 0.0F, true));

		rightPincer = new ModelRenderer(this);
		rightPincer.setRotationPoint(-1.25F, 0.0F, 0.0F);
		pincerStatic.addChild(rightPincer);
		rightPincer.cubeList.add(new ModelBox(rightPincer, 8, 2, -0.5F, -0.04F, -0.725F, 1, 0, 1, 0.0F, false));
		rightPincer.cubeList.add(new ModelBox(rightPincer, 8, 1, 0.0F, -0.04F, -1.725F, 1, 0, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		float scale = ((EntityAnt)entity).getSize();
		GlStateManager.pushMatrix();
		float heightOffset = (1.5F * (1.0F - scale));
		GlStateManager.translate(0.0F, heightOffset, 0.0F);
		GlStateManager.scale(scale,scale,scale);
		rightRearLeg.render(f5);
		leftRearLeg.render(f5);
		rightMiddleLeg.render(f5);
		leftMiddleLeg.render(f5);
		rightFrontLeg.render(f5);
		leftFrontLeg.render(f5);
		thorax.render(f5);
		abdomen.render(f5);
		head.render(f5);
		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		float sin = MathHelper.sin(limbSwing * 2f);
		float cos = MathHelper.cos(limbSwing * 2f);
		float legSwingClamped = MathHelper.clamp(limbSwingAmount,0,1.4f);
		float legCos1 = cos * 0.5f * legSwingClamped;
		float legCos2 = Math.max(0,-legCos1 - 0.05f);
		legCos1 = Math.max(0,legCos1 - 0.05f);
		float legSin = sin * 0.436332f * legSwingClamped;

		leftFrontLeg.rotateAngleY = 0.6109f + legSin;
		rightFrontLeg.rotateAngleY = -0.6109f + legSin;

		leftMiddleLeg.rotateAngleY = -legSin;
		rightMiddleLeg.rotateAngleY = -legSin;

		leftRearLeg.rotateAngleY = -0.6109f + legSin;
		rightRearLeg.rotateAngleY =  0.6109f + legSin;

		leftFrontLeg_r1.rotateAngleZ = 0.5236f - legCos1 * 1.5f;
		rightFrontLeg_r1.rotateAngleZ = -0.5236f + legCos2 * 1.5f;

		leftMiddleLeg_r1.rotateAngleZ = 0.5236f - legCos2;
		rightMiddleLeg_r1.rotateAngleZ = -0.5236f + legCos1;

		leftRearLeg_r1.rotateAngleZ = 0.5236f - legCos1 * 1.5f;
		rightRearLeg_r1.rotateAngleZ = -0.5236f + legCos2 * 1.5f;

		thorax.offsetY = sin * 0.02f * limbSwingAmount - 0.05f * legSwingClamped;
		abdomen.offsetY = sin * 0.02f * limbSwingAmount;
		head.offsetY = sin * 0.03f * limbSwingAmount;

		thorax.rotateAngleX = 0.2182F + (cos+1) * 0.17f * limbSwingAmount;
		head.rotateAngleX = -sin * 0.05f * limbSwingAmount;

		abdomen.rotateAngleX = 0.0873f + 0.35f * legSwingClamped;

		leftAntena.rotateAngleX = 0.8727F - sin * 0.35f * legSwingClamped;
		rightAntenna.rotateAngleX = 0.8727F - sin * 0.35f * legSwingClamped;

		leftAntena.rotateAngleY = -0.8727F + 1.05f * legSwingClamped;
		rightAntenna.rotateAngleY = 0.8727F - 1.05f * legSwingClamped;

		int id = entityIn.getEntityId();
		float p = (ageInTicks + id * 67) % 300;
		if (p <= 40) {
			float p2 = MathHelper.sin(p * 0.31415926535f);
			rightPincer.rotateAngleY = 0.25f + p2 * 0.5f;
			leftPincer.rotateAngleY = -0.25f - p2 * 0.5f;
		}
		else {
			rightPincer.rotateAngleY = 0.25f;
			leftPincer.rotateAngleY = -0.25f;
		}

		head.rotateAngleY = netHeadYaw * 0.017453292F;
		head.rotateAngleX = headPitch * 0.017453292F;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}