package com.orespark.entity.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRhinoBeetle extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer hornleft_r1;
	private final ModelRenderer hornright_r1;
	private final ModelRenderer hornmid_r1;
	private final ModelRenderer hornbase_r1;
	private final ModelRenderer rightFrontHip;
	private final ModelRenderer rightFrontLeg;
	private final ModelRenderer leftMiddleHip;
	private final ModelRenderer leftMiddleLeg;
	private final ModelRenderer leftFrontLeg_r1;
	private final ModelRenderer rightMiddleHip;
	private final ModelRenderer rightMiddleLeg;
	private final ModelRenderer rightFrontLeg_r1;
	private final ModelRenderer rightBackHip;
	private final ModelRenderer rightBackLeg;
	private final ModelRenderer leftFrontHip;
	private final ModelRenderer leftFrontLeg;
	private final ModelRenderer leftBackHip;
	private final ModelRenderer leftBackLeg;
	private final ModelRenderer bb_main;

	public ModelRhinoBeetle() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 11.0F, -9.0F);
		head.cubeList.add(new ModelBox(head, 0, 52, -7.0F, -5.25F, -21.0F, 14, 12, 12, 0.0F, false));

		hornleft_r1 = new ModelRenderer(this);
		hornleft_r1.setRotationPoint(0.0F, -11.85F, -26.05F);
		head.addChild(hornleft_r1);
		setRotationAngle(hornleft_r1, -0.7854F, 0.0F, -0.2182F);
		hornleft_r1.cubeList.add(new ModelBox(hornleft_r1, 0, 92, 0.0F, -2.0F, -2.25F, 11, 4, 4, 0.0F, false));

		hornright_r1 = new ModelRenderer(this);
		hornright_r1.setRotationPoint(0.0F, -11.85F, -26.05F);
		head.addChild(hornright_r1);
		setRotationAngle(hornright_r1, -0.7854F, 0.0F, 0.2182F);
		hornright_r1.cubeList.add(new ModelBox(hornright_r1, 0, 92, -11.0F, -2.0F, -2.25F, 11, 4, 4, 0.0F, true));

		hornmid_r1 = new ModelRenderer(this);
		hornmid_r1.setRotationPoint(0.0F, -5.2F, -23.1F);
		head.addChild(hornmid_r1);
		setRotationAngle(hornmid_r1, -1.3963F, 0.0F, 0.0F);
		hornmid_r1.cubeList.add(new ModelBox(hornmid_r1, 74, 84, -2.0F, 0.0F, -7.0F, 4, 4, 9, 0.0F, false));

		hornbase_r1 = new ModelRenderer(this);
		hornbase_r1.setRotationPoint(0.5F, 0.65F, -18.95F);
		head.addChild(hornbase_r1);
		setRotationAngle(hornbase_r1, -1.0036F, 0.0F, 0.0F);
		hornbase_r1.cubeList.add(new ModelBox(hornbase_r1, 42, 84, -3.0F, 0.0F, -7.0F, 5, 5, 11, 0.0F, false));

		rightFrontHip = new ModelRenderer(this);
		rightFrontHip.setRotationPoint(-7.0F, 16.0F, -11.0F);
		setRotationAngle(rightFrontHip, 0.0F, -0.5236F, 0.0F);
		

		rightFrontLeg = new ModelRenderer(this);
		rightFrontLeg.setRotationPoint(0.0F, 0.0F, -0.5F);
		rightFrontHip.addChild(rightFrontLeg);
		setRotationAngle(rightFrontLeg, 0.0F, 0.0F, -0.5672F);
		rightFrontLeg.cubeList.add(new ModelBox(rightFrontLeg, 52, 52, -17.0F, -4.5F, -1.0F, 17, 4, 4, 0.0F, false));

		leftMiddleHip = new ModelRenderer(this);
		leftMiddleHip.setRotationPoint(7.0F, 15.5F, 0.0F);
		

		leftMiddleLeg = new ModelRenderer(this);
		leftMiddleLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		leftMiddleHip.addChild(leftMiddleLeg);
		

		leftFrontLeg_r1 = new ModelRenderer(this);
		leftFrontLeg_r1.setRotationPoint(1.0F, -3.0F, 0.0F);
		leftMiddleLeg.addChild(leftFrontLeg_r1);
		setRotationAngle(leftFrontLeg_r1, 0.0F, 0.0F, 0.5672F);
		leftFrontLeg_r1.cubeList.add(new ModelBox(leftFrontLeg_r1, 0, 84, 1.0F, -1.0F, -2.0F, 17, 4, 4, 0.0F, true));

		rightMiddleHip = new ModelRenderer(this);
		rightMiddleHip.setRotationPoint(-7.0F, 15.5F, 0.0F);
		

		rightMiddleLeg = new ModelRenderer(this);
		rightMiddleLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		rightMiddleHip.addChild(rightMiddleLeg);
		

		rightFrontLeg_r1 = new ModelRenderer(this);
		rightFrontLeg_r1.setRotationPoint(-1.0F, -3.0F, 0.0F);
		rightMiddleLeg.addChild(rightFrontLeg_r1);
		setRotationAngle(rightFrontLeg_r1, 0.0F, 0.0F, -0.5672F);
		rightFrontLeg_r1.cubeList.add(new ModelBox(rightFrontLeg_r1, 0, 84, -18.0F, -1.0F, -2.0F, 17, 4, 4, 0.0F, false));

		rightBackHip = new ModelRenderer(this);
		rightBackHip.setRotationPoint(-7.0F, 16.0F, 11.0F);
		setRotationAngle(rightBackHip, 0.0F, 0.5236F, 0.0F);
		

		rightBackLeg = new ModelRenderer(this);
		rightBackLeg.setRotationPoint(0.0F, 0.0F, 0.5F);
		rightBackHip.addChild(rightBackLeg);
		setRotationAngle(rightBackLeg, 0.0F, 0.0F, -0.5672F);
		rightBackLeg.cubeList.add(new ModelBox(rightBackLeg, 0, 76, -17.0F, -4.5F, -3.0F, 17, 4, 4, 0.0F, false));

		leftFrontHip = new ModelRenderer(this);
		leftFrontHip.setRotationPoint(7.0F, 16.0F, -11.0F);
		setRotationAngle(leftFrontHip, 0.0F, 0.5236F, 0.0F);
		

		leftFrontLeg = new ModelRenderer(this);
		leftFrontLeg.setRotationPoint(0.0F, 0.0F, -0.5F);
		leftFrontHip.addChild(leftFrontLeg);
		setRotationAngle(leftFrontLeg, 0.0F, 0.0F, 0.5672F);
		leftFrontLeg.cubeList.add(new ModelBox(leftFrontLeg, 52, 52, 0.0F, -4.5F, -1.0F, 17, 4, 4, 0.0F, true));

		leftBackHip = new ModelRenderer(this);
		leftBackHip.setRotationPoint(7.0F, 16.0F, 11.0F);
		setRotationAngle(leftBackHip, 0.0F, -0.5236F, 0.0F);
		

		leftBackLeg = new ModelRenderer(this);
		leftBackLeg.setRotationPoint(0.0F, 0.0F, 0.5F);
		leftBackHip.addChild(leftBackLeg);
		setRotationAngle(leftBackLeg, 0.0F, 0.0F, 0.5672F);
		leftBackLeg.cubeList.add(new ModelBox(leftBackLeg, 0, 76, 0.0F, -4.5F, -3.0F, 17, 4, 4, 0.0F, true));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -9.0F, -20.25F, -18.0F, 18, 14, 38, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		rightFrontHip.render(f5);
		leftMiddleHip.render(f5);
		rightMiddleHip.render(f5);
		rightBackHip.render(f5);
		leftFrontHip.render(f5);
		leftBackHip.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}