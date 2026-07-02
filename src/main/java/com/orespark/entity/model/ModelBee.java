package com.orespark.entity.model;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBee extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer stinger_r1;
	private final ModelRenderer leftWing;
	private final ModelRenderer rightWing;

	public ModelBee() {
		textureWidth = 16;
		textureHeight = 16;

		root = new ModelRenderer(this);
		root.setRotationPoint(1.0F, 22.0F, 0.0F);
		root.cubeList.add(new ModelBox(root, 0, 0, -2.0F, 0.0F, -1.5F, 2, 2, 3, 0.0F, false));

		stinger_r1 = new ModelRenderer(this);
		stinger_r1.setRotationPoint(-1.0F, 0.9F, 1.15F);
		root.addChild(stinger_r1);
		setRotationAngle(stinger_r1, -0.7854F, 0.6545F, 1.5708F);
		stinger_r1.cubeList.add(new ModelBox(stinger_r1, 9, 7, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		leftWing = new ModelRenderer(this);
		leftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(leftWing);
		leftWing.cubeList.add(new ModelBox(leftWing, 1, 5, 0.0F, 0.0F, -1.0F, 1, 0, 2, 0.0F, true));

		rightWing = new ModelRenderer(this);
		rightWing.setRotationPoint(-2.0F, 0.0F, 0.0F);
		root.addChild(rightWing);
		rightWing.cubeList.add(new ModelBox(rightWing, 1, 5, -1.0F, 0.0F, -1.0F, 1, 0, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		root.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		leftWing.rotateAngleZ = -0.174533f + MathHelper.sin(ageInTicks * 2f) * 0.9f;
		rightWing.rotateAngleZ = 0.174533f - MathHelper.sin(ageInTicks * 2f) * 0.9f;
	}
}