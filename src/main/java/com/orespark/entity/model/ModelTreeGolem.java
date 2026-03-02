package com.orespark.entity.model;


import com.orespark.Orespark;
import com.orespark.entity.EntityTreeGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTreeGolem extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer bone;
	private final ModelRenderer front_right_dummy;
	private final ModelRenderer front_right_thigh;
	private final ModelRenderer front_right_dummy2;
	private final ModelRenderer front_right_knee;
	private final ModelRenderer front_right_dummy3;
	private final ModelRenderer front_right_foot;
	private final ModelRenderer back_right_dummy;
	private final ModelRenderer back_right_thigh;
	private final ModelRenderer back_right_dummy2;
	private final ModelRenderer back_right_knee;
	private final ModelRenderer back_right_dummy3;
	private final ModelRenderer back_right_foot;
	private final ModelRenderer front_left_dummy;
	private final ModelRenderer front_left_thigh;
	private final ModelRenderer front_left_dummy2;
	private final ModelRenderer front_left_knee;
	private final ModelRenderer front_left_dummy3;
	private final ModelRenderer front_left_foot;
	private final ModelRenderer back_left_dummy;
	private final ModelRenderer back_left_thigh;
	private final ModelRenderer back_left_dummy2;
	private final ModelRenderer back_left_knee;
	private final ModelRenderer back_left_dummy3;
	private final ModelRenderer back_left_foot;

	public ModelTreeGolem() {
		textureWidth = 64;
		textureHeight = 144;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, -22.0F, 0.0F);
		root.cubeList.add(new ModelBox(root, 0, 48, -8.0F, -50.0F, -8.0F, 16, 48, 16, 0.0F, false));
		root.cubeList.add(new ModelBox(root, 0, 112, -8.0F, -66.0F, -8.0F, 16, 16, 16, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -50.0F, 0.0F);
		root.addChild(bone);


		front_right_dummy = new ModelRenderer(this);
		front_right_dummy.setRotationPoint(-4.0F, -4.0F, -4.0F);
		root.addChild(front_right_dummy);
		setRotationAngle(front_right_dummy, 0.0F, -0.7854F, 0.0F);


		front_right_thigh = new ModelRenderer(this);
		front_right_thigh.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_right_dummy.addChild(front_right_thigh);
		setRotationAngle(front_right_thigh, 0.0F, 0.7854F, 0.0F);
		front_right_thigh.cubeList.add(new ModelBox(front_right_thigh, 0, 24, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		front_right_dummy2 = new ModelRenderer(this);
		front_right_dummy2.setRotationPoint(0.0F, 16.0F, 0.0F);
		front_right_thigh.addChild(front_right_dummy2);
		setRotationAngle(front_right_dummy2, 0.0F, -0.7854F, 0.0F);


		front_right_knee = new ModelRenderer(this);
		front_right_knee.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_right_dummy2.addChild(front_right_knee);
		setRotationAngle(front_right_knee, 0.0F, 0.7854F, 0.0F);
		front_right_knee.cubeList.add(new ModelBox(front_right_knee, 0, 24, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		front_right_dummy3 = new ModelRenderer(this);
		front_right_dummy3.setRotationPoint(0.0F, 16.0F, 0.0F);
		front_right_knee.addChild(front_right_dummy3);
		setRotationAngle(front_right_dummy3, 0.0F, -0.7854F, 0.0F);


		front_right_foot = new ModelRenderer(this);
		front_right_foot.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_right_dummy3.addChild(front_right_foot);
		setRotationAngle(front_right_foot, 0.0F, 0.7854F, 0.0F);
		front_right_foot.cubeList.add(new ModelBox(front_right_foot, 0, 24, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		back_right_dummy = new ModelRenderer(this);
		back_right_dummy.setRotationPoint(-4.0F, -4.0F, 4.0F);
		root.addChild(back_right_dummy);
		setRotationAngle(back_right_dummy, 0.0F, 0.7854F, 0.0F);


		back_right_thigh = new ModelRenderer(this);
		back_right_thigh.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_right_dummy.addChild(back_right_thigh);
		setRotationAngle(back_right_thigh, 0.0F, -0.7854F, 0.0F);
		back_right_thigh.cubeList.add(new ModelBox(back_right_thigh, 32, 0, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		back_right_dummy2 = new ModelRenderer(this);
		back_right_dummy2.setRotationPoint(0.0F, 16.0F, 0.0F);
		back_right_thigh.addChild(back_right_dummy2);
		setRotationAngle(back_right_dummy2, 0.0F, 0.7854F, 0.0F);


		back_right_knee = new ModelRenderer(this);
		back_right_knee.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_right_dummy2.addChild(back_right_knee);
		setRotationAngle(back_right_knee, 0.0F, -0.7854F, 0.0F);
		back_right_knee.cubeList.add(new ModelBox(back_right_knee, 32, 0, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		back_right_dummy3 = new ModelRenderer(this);
		back_right_dummy3.setRotationPoint(0.0F, 16.0F, 0.0F);
		back_right_knee.addChild(back_right_dummy3);
		setRotationAngle(back_right_dummy3, 0.0F, 0.7854F, 0.0F);


		back_right_foot = new ModelRenderer(this);
		back_right_foot.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_right_dummy3.addChild(back_right_foot);
		setRotationAngle(back_right_foot, 0.0F, -0.7854F, 0.0F);
		back_right_foot.cubeList.add(new ModelBox(back_right_foot, 32, 0, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		front_left_dummy = new ModelRenderer(this);
		front_left_dummy.setRotationPoint(4.0F, -4.0F, -4.0F);
		root.addChild(front_left_dummy);
		setRotationAngle(front_left_dummy, 0.0F, -2.3562F, 0.0F);


		front_left_thigh = new ModelRenderer(this);
		front_left_thigh.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_left_dummy.addChild(front_left_thigh);
		setRotationAngle(front_left_thigh, 0.0F, 2.3562F, 0.0F);
		front_left_thigh.cubeList.add(new ModelBox(front_left_thigh, 32, 24, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		front_left_dummy2 = new ModelRenderer(this);
		front_left_dummy2.setRotationPoint(0.0F, 16.0F, 0.0F);
		front_left_thigh.addChild(front_left_dummy2);
		setRotationAngle(front_left_dummy2, 0.0F, -2.3562F, 0.0F);


		front_left_knee = new ModelRenderer(this);
		front_left_knee.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_left_dummy2.addChild(front_left_knee);
		setRotationAngle(front_left_knee, 0.0F, 2.3562F, 0.0F);
		front_left_knee.cubeList.add(new ModelBox(front_left_knee, 32, 24, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		front_left_dummy3 = new ModelRenderer(this);
		front_left_dummy3.setRotationPoint(0.0F, 16.0F, 0.0F);
		front_left_knee.addChild(front_left_dummy3);
		setRotationAngle(front_left_dummy3, 0.0F, -2.3562F, 0.0F);


		front_left_foot = new ModelRenderer(this);
		front_left_foot.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_left_dummy3.addChild(front_left_foot);
		setRotationAngle(front_left_foot, 0.0F, 2.3562F, 0.0F);
		front_left_foot.cubeList.add(new ModelBox(front_left_foot, 32, 24, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		back_left_dummy = new ModelRenderer(this);
		back_left_dummy.setRotationPoint(4.0F, -4.0F, 4.0F);
		root.addChild(back_left_dummy);
		setRotationAngle(back_left_dummy, 0.0F, 2.3562F, 0.0F);


		back_left_thigh = new ModelRenderer(this);
		back_left_thigh.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_left_dummy.addChild(back_left_thigh);
		setRotationAngle(back_left_thigh, 0.0F, -2.3562F, 0.0F);
		back_left_thigh.cubeList.add(new ModelBox(back_left_thigh, 0, 0, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		back_left_dummy2 = new ModelRenderer(this);
		back_left_dummy2.setRotationPoint(0.0F, 16.0F, 0.0F);
		back_left_thigh.addChild(back_left_dummy2);
		setRotationAngle(back_left_dummy2, 0.0F, 2.3562F, 0.0F);


		back_left_knee = new ModelRenderer(this);
		back_left_knee.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_left_dummy2.addChild(back_left_knee);
		setRotationAngle(back_left_knee, 0.0F, -2.3562F, 0.0F);
		back_left_knee.cubeList.add(new ModelBox(back_left_knee, 0, 0, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));

		back_left_dummy3 = new ModelRenderer(this);
		back_left_dummy3.setRotationPoint(0.0F, 16.0F, 0.0F);
		back_left_knee.addChild(back_left_dummy3);
		setRotationAngle(back_left_dummy3, 0.0F, 2.3562F, 0.0F);


		back_left_foot = new ModelRenderer(this);
		back_left_foot.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_left_dummy3.addChild(back_left_foot);
		setRotationAngle(back_left_foot, 0.0F, -2.3562F, 0.0F);
		back_left_foot.cubeList.add(new ModelBox(back_left_foot, 0, 0, -4.0F, 2.0F, -4.0F, 8, 16, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		root.render(scaleFactor);
		this.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scaleFactor,entity);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		EntityTreeGolem treeGolem = (EntityTreeGolem) entity;
		boolean still = treeGolem.isStill();
		if (still != treeGolem.prevStill()) {
			treeGolem.stillFrame = ageInTicks;
		}

		if (still && treeGolem.stillProgess == 0) {
			setRotationAngle(front_right_dummy, 0.0F, -0.7854F, 0.0F);
			setRotationAngle(front_right_thigh, 0.0F, 0.7854F, 0.0F);
			setRotationAngle(front_right_knee, 0.0F, 0.7854F, 0.0F);
			setRotationAngle(front_right_foot, 0.0F, 0.7854F, 0.0F);
			setRotationAngle(back_right_dummy, 0.0F, 0.7854F, 0.0F);
			setRotationAngle(back_right_thigh, 0.0F, -0.7854F, 0.0F);
			setRotationAngle(back_right_knee, 0.0F, -0.7854F, 0.0F);
			setRotationAngle(back_right_foot, 0.0F, -0.7854F, 0.0F);
			setRotationAngle(front_left_dummy, 0.0F, -2.3562F, 0.0F);
			setRotationAngle(front_left_thigh, 0.0F, 2.3562F, 0.0F);
			setRotationAngle(front_left_knee, 0.0F, 2.3562F, 0.0F);
			setRotationAngle(front_left_foot, 0.0F, 2.3562F, 0.0F);
			setRotationAngle(back_left_dummy, 0.0F, 2.3562F, 0.0F);
			setRotationAngle(back_left_thigh, 0.0F, -2.3562F, 0.0F);
			setRotationAngle(back_left_knee, 0.0F, -2.3562F, 0.0F);
			setRotationAngle(back_left_foot, 0.0F, -2.3562F, 0.0F);
			root.offsetY = 0f;
		}
		else {
			float difference = ageInTicks - treeGolem.stillFrame;
			if (difference < 40f) {
				treeGolem.stillProgess = MathHelper.clamp(treeGolem.stillProgess + ((still ? -1/40f : 1/40f) * (ageInTicks-treeGolem.lastTick)),0,1);
			}
			else {
				treeGolem.stillProgess = still ? 0 : 1;
			}

			float thighAngle = 0.3054326f * treeGolem.stillProgess;
			float kneeAngle = 1.35263f * treeGolem.stillProgess;
			float footAngle = -1.65806f * treeGolem.stillProgess;

			float pairY = MathHelper.sin(limbSwing * 0.5f) * 0.523599f * limbSwingAmount * treeGolem.stillProgess;

			front_right_dummy.rotateAngleY = -0.7854F + pairY;
			back_right_dummy.rotateAngleY = 0.7854F - pairY;
			front_left_dummy.rotateAngleY = -2.3562F + pairY;
			back_left_dummy.rotateAngleY = 2.3562F - pairY;

			float pair1Up = Math.max(MathHelper.cos(limbSwing * 0.5f + (float) Math.PI),0f) * limbSwingAmount * treeGolem.stillProgess;
			float pair2Up = Math.max(MathHelper.cos(limbSwing * 0.5f),0f) * limbSwingAmount * treeGolem.stillProgess;

			float thighUp = 0.3926991f * treeGolem.stillProgess;
			float kneeUp = 0.3054326f * treeGolem.stillProgess;
			float footUp = -0.523599f * treeGolem.stillProgess;

			front_right_thigh.rotateAngleZ = thighAngle + pair1Up * thighUp;
			back_right_thigh.rotateAngleZ = thighAngle + pair2Up * thighUp;
			front_left_thigh.rotateAngleZ = thighAngle + pair2Up * thighUp;
			back_left_thigh.rotateAngleZ = thighAngle + pair1Up * thighUp;

			front_right_knee.rotateAngleZ = kneeAngle + pair1Up * kneeUp;
			back_right_knee.rotateAngleZ = kneeAngle + pair2Up * kneeUp;
			front_left_knee.rotateAngleZ = kneeAngle + pair2Up * kneeUp;
			back_left_knee.rotateAngleZ = kneeAngle + pair1Up * kneeUp;

			front_right_foot.rotateAngleZ = footAngle + pair1Up * footUp;
			back_right_foot.rotateAngleZ = footAngle + pair2Up * footUp;
			front_left_foot.rotateAngleZ = footAngle + pair2Up * footUp;
			back_left_foot.rotateAngleZ = footAngle + pair1Up * footUp;

			root.offsetY = 1.15f * treeGolem.stillProgess;
		}
		treeGolem.lastTick = ageInTicks;
	}
}