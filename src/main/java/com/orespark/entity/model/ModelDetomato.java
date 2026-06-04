package com.orespark.entity.model;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDetomato extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;

	public ModelDetomato() {
		textureWidth = 32;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 22.0F, 0.0F);
		root.cubeList.add(new ModelBox(root, 0, 22, -2.5F, 1.0F, -2.5F, 5, 1, 5, 0.0F, false));
		root.cubeList.add(new ModelBox(root, 0, 4, -3.5F, -4.0F, -3.5F, 7, 5, 7, 0.0F, false));
		root.cubeList.add(new ModelBox(root, 0, 16, -2.5F, -5.0F, -2.5F, 5, 1, 5, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -5.5F, 0.0F);
		root.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 3, 0, -5.0F, -3.0F, 0.0F, 10, 4, 0, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, -5.5F, 0.0F);
		root.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.7854F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 3, 0, -5.0F, -3.0F, 0.0F, 10, 4, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		root.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		root.rotateAngleX = ageInTicks / 2f;
	}
}