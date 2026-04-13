package com.orespark.entity.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.orespark.Orespark;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGrub extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer neck;
	private final ModelRenderer body;
	private final ModelRenderer hip;
	private final ModelRenderer leg;
	private final ModelRenderer tail;

	public ModelGrub() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0F);
		head.cubeList.add(new ModelBox(head, 22, 6, -1.5F, -3.0F, -5.0F, 3, 3, 2, 0.0F, false));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 24.0F, 0F);
		neck.cubeList.add(new ModelBox(neck, 0, 7, -2.0F, -3.5F, -3.0F, 4, 3, 2, 0.0F, false));
		neck.cubeList.add(new ModelBox(neck, 13, 0, -2.0F, -1.0F, -3.0F, 4, 1, 2, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -2.5F, -4.0F, -1.0F, 5, 4, 3, 0.0F, false));

		hip = new ModelRenderer(this);
		hip.setRotationPoint(0.0F, 24.0F, 0F);
		hip.cubeList.add(new ModelBox(hip, 20, 11, -2.0F, -3.5F, 2.0F, 4, 3, 2, 0.0F, false));
		hip.cubeList.add(new ModelBox(hip, 8, 12, -2.0F, -1.0F, 2.0F, 4, 1, 2, 0.0F, false));

		leg = new ModelRenderer(this);
		leg.setRotationPoint(0.0F, 24.0F, 0F);
		leg.cubeList.add(new ModelBox(leg, 12, 7, -1.5F, -3.0F, 4.0F, 3, 3, 2, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 24.0F, 0F);
		tail.cubeList.add(new ModelBox(tail, 24, 1, -1.0F, -2.5F, 6.0F, 2, 2, 2, 0.0F, false));
		tail.cubeList.add(new ModelBox(tail, 18, 3, -1.0F, -1.0F, 6.001F, 2, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		neck.render(f5);
		body.render(f5);
		hip.render(f5);
		leg.render(f5);
		tail.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scaleFactor,entity);
		head.offsetZ = (-MathHelper.cos(limbSwing* 1.1f)+1) * 2f / 32f;
		neck.offsetZ = (-MathHelper.cos(limbSwing* 1.1f)+1) / 32f;
		hip.offsetZ = (-MathHelper.cos(limbSwing* 1.1f)+1) / -32f;
		leg.offsetZ =  (-MathHelper.cos(limbSwing* 1.1f)+1) * 2f / -32f;
		tail.offsetZ = (-MathHelper.cos(limbSwing* 1.1f)+1) * 3f / -32f;

	}

}
