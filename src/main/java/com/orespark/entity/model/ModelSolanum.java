package com.orespark.entity.model;// Made with Blockbench 5.1.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.orespark.entity.EntitySolanum;
import com.orespark.util.OresparkUtil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSolanum extends ModelBase {
	private final ModelRenderer stem1;
	private final ModelRenderer stem2;
	private final ModelRenderer stem3;
	private final ModelRenderer stem4;
	private final ModelRenderer stem5;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer pistil;
	private final ModelRenderer east1;
	private final ModelRenderer east2;
	private final ModelRenderer east3;
	private final ModelRenderer east4;
	private final ModelRenderer east5;
	private final ModelRenderer west1;
	private final ModelRenderer west2;
	private final ModelRenderer west3;
	private final ModelRenderer west4;
	private final ModelRenderer west5;
	private final ModelRenderer south1;
	private final ModelRenderer south2;
	private final ModelRenderer south3;
	private final ModelRenderer south4;
	private final ModelRenderer south5;
	private final ModelRenderer north1;
	private final ModelRenderer north2;
	private final ModelRenderer north3;
	private final ModelRenderer north4;
	private final ModelRenderer north5;

	private final ModelRenderer[] petal1;
	private final ModelRenderer[] petal2;
	private final ModelRenderer[] petal3;
	private final ModelRenderer[] petal4;
	private final ModelRenderer[] petal5;
	private final ModelRenderer[][] petals;

	public ModelSolanum() {
		textureWidth = 128;
		textureHeight = 128;

		stem1 = new ModelRenderer(this);
		stem1.setRotationPoint(0.0F, 24.0F, 0.0F);
		stem1.cubeList.add(new ModelBox(stem1, 0, 21, -7.0F, -11.0F, -7.0F, 14, 21, 14, 0.0F, false));

		stem2 = new ModelRenderer(this);
		stem2.setRotationPoint(0.0F, -10.5F, 0.0F);
		stem1.addChild(stem2);
		stem2.cubeList.add(new ModelBox(stem2, 0, 56, -6.0F, -12.5F, -6.0F, 12, 12, 12, 0.0F, false));

		stem3 = new ModelRenderer(this);
		stem3.setRotationPoint(0.0F, -12.0F, 0.0F);
		stem2.addChild(stem3);
		stem3.cubeList.add(new ModelBox(stem3, 32, 80, -5.0F, -17.5F, -5.0F, 10, 17, 10, 0.0F, false));

		stem4 = new ModelRenderer(this);
		stem4.setRotationPoint(0.0F, -17.0F, 0.0F);
		stem3.addChild(stem4);
		stem4.cubeList.add(new ModelBox(stem4, 0, 80, -4.0F, -21.5F, -4.0F, 8, 21, 8, 0.0F, false));

		stem5 = new ModelRenderer(this);
		stem5.setRotationPoint(0.0F, -21.0F, 0.0F);
		stem4.addChild(stem5);
		stem5.cubeList.add(new ModelBox(stem5, 50, 50, -3.0F, -20.5F, -3.0F, 6, 20, 6, 0.0F, false));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, -19.5F, 0.0F);
		stem5.addChild(neck);


		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -4.0F, 0.0F);
		neck.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -9.0F, -3.0F, -9.0F, 18, 3, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 68, 0, -6.0F, 0.0F, -6.0F, 12, 3, 12, 0.0F, false));

		pistil = new ModelRenderer(this);
		pistil.setRotationPoint(0.0F, -6.5F, 0.0F);
		head.addChild(pistil);
		setRotationAngle(pistil, 0.0F, 0.7854F, 0.0F);
		pistil.cubeList.add(new ModelBox(pistil, 69, 70, -3.5F, -3.5F, -3.5F, 7, 7, 7, 0.0F, false));
		pistil.cubeList.add(new ModelBox(pistil, 93, 62, -2.5F, -10.5F, -2.5F, 5, 7, 5, 0.0F, false));
		pistil.cubeList.add(new ModelBox(pistil, 80, 52, -1.5F, -17.5F, -1.5F, 3, 7, 3, 0.0F, false));

		east1 = new ModelRenderer(this);
		east1.setRotationPoint(-9.0F, -3.0F, 0.0F);
		head.addChild(east1);
		setRotationAngle(east1, 0.0F, -1.5708F, 0.0F);
		east1.cubeList.add(new ModelBox(east1, 62, 15, -9.0F, 0.0F, 0.0F, 18, 2, 10, 0.0F, false));

		east2 = new ModelRenderer(this);
		east2.setRotationPoint(0.0F, 0.0F, 10.0F);
		east1.addChild(east2);
		east2.cubeList.add(new ModelBox(east2, 56, 38, -7.0F, 0.0F, 0.0F, 14, 2, 9, 0.0F, false));

		east3 = new ModelRenderer(this);
		east3.setRotationPoint(0.0F, 0.0F, 9.0F);
		east2.addChild(east3);
		east3.cubeList.add(new ModelBox(east3, 58, 27, -5.0F, 0.0F, 0.0F, 10, 2, 8, 0.0F, false));

		east4 = new ModelRenderer(this);
		east4.setRotationPoint(0.0F, 0.0F, 8.0F);
		east3.addChild(east4);
		east4.cubeList.add(new ModelBox(east4, 54, 0, -3.0F, 0.0F, 0.0F, 6, 2, 7, 0.0F, false));

		east5 = new ModelRenderer(this);
		east5.setRotationPoint(0.0F, 0.0F, 7.0F);
		east4.addChild(east5);
		east5.cubeList.add(new ModelBox(east5, 0, 0, -1.5F, 0.0F, 0.0F, 3, 2, 6, 0.0F, false));

		west1 = new ModelRenderer(this);
		west1.setRotationPoint(9.0F, -3.0F, 0.0F);
		head.addChild(west1);
		setRotationAngle(west1, 0.0F, 1.5708F, 0.0F);
		west1.cubeList.add(new ModelBox(west1, 62, 15, -9.0F, 0.0F, 0.0F, 18, 2, 10, 0.0F, false));

		west2 = new ModelRenderer(this);
		west2.setRotationPoint(0.0F, 0.0F, 10.0F);
		west1.addChild(west2);
		west2.cubeList.add(new ModelBox(west2, 56, 38, -7.0F, 0.0F, 0.0F, 14, 2, 9, 0.0F, false));

		west3 = new ModelRenderer(this);
		west3.setRotationPoint(0.0F, 0.0F, 9.0F);
		west2.addChild(west3);
		west3.cubeList.add(new ModelBox(west3, 58, 27, -5.0F, 0.0F, 0.0F, 10, 2, 8, 0.0F, false));

		west4 = new ModelRenderer(this);
		west4.setRotationPoint(0.0F, 0.0F, 8.0F);
		west3.addChild(west4);
		west4.cubeList.add(new ModelBox(west4, 54, 0, -3.0F, 0.0F, 0.0F, 6, 2, 7, 0.0F, false));

		west5 = new ModelRenderer(this);
		west5.setRotationPoint(0.0F, 0.0F, 7.0F);
		west4.addChild(west5);
		west5.cubeList.add(new ModelBox(west5, 0, 0, -1.5F, 0.0F, 0.0F, 3, 2, 6, 0.0F, false));

		south1 = new ModelRenderer(this);
		south1.setRotationPoint(0.0F, -3.0F, 9.0F);
		head.addChild(south1);
		south1.cubeList.add(new ModelBox(south1, 62, 15, -9.0F, 0.0F, 0.0F, 18, 2, 10, 0.0F, false));

		south2 = new ModelRenderer(this);
		south2.setRotationPoint(0.0F, 0.0F, 10.0F);
		south1.addChild(south2);
		south2.cubeList.add(new ModelBox(south2, 56, 38, -7.0F, 0.0F, 0.0F, 14, 2, 9, 0.0F, false));

		south3 = new ModelRenderer(this);
		south3.setRotationPoint(0.0F, 0.0F, 9.0F);
		south2.addChild(south3);
		south3.cubeList.add(new ModelBox(south3, 58, 27, -5.0F, 0.0F, 0.0F, 10, 2, 8, 0.0F, false));

		south4 = new ModelRenderer(this);
		south4.setRotationPoint(0.0F, 0.0F, 8.0F);
		south3.addChild(south4);
		south4.cubeList.add(new ModelBox(south4, 54, 0, -3.0F, 0.0F, 0.0F, 6, 2, 7, 0.0F, false));

		south5 = new ModelRenderer(this);
		south5.setRotationPoint(0.0F, 0.0F, 7.0F);
		south4.addChild(south5);
		south5.cubeList.add(new ModelBox(south5, 0, 0, -1.5F, 0.0F, 0.0F, 3, 2, 6, 0.0F, false));

		north1 = new ModelRenderer(this);
		north1.setRotationPoint(0.0F, -3.0F, -9.0F);
		head.addChild(north1);
		setRotationAngle(north1, 0.0F, 3.1416F, 0.0F);
		north1.cubeList.add(new ModelBox(north1, 62, 15, -9.0F, 0.0F, 0.0F, 18, 2, 10, 0.0F, false));

		north2 = new ModelRenderer(this);
		north2.setRotationPoint(0.0F, 0.0F, 10.0F);
		north1.addChild(north2);
		north2.cubeList.add(new ModelBox(north2, 56, 38, -7.0F, 0.0F, 0.0F, 14, 2, 9, 0.0F, false));

		north3 = new ModelRenderer(this);
		north3.setRotationPoint(0.0F, 0.0F, 9.0F);
		north2.addChild(north3);
		north3.cubeList.add(new ModelBox(north3, 58, 27, -5.0F, 0.0F, 0.0F, 10, 2, 8, 0.0F, false));

		north4 = new ModelRenderer(this);
		north4.setRotationPoint(0.0F, 0.0F, 8.0F);
		north3.addChild(north4);
		north4.cubeList.add(new ModelBox(north4, 54, 0, -3.0F, 0.0F, 0.0F, 6, 2, 7, 0.0F, false));

		north5 = new ModelRenderer(this);
		north5.setRotationPoint(0.0F, 0.0F, 7.0F);
		north4.addChild(north5);
		north5.cubeList.add(new ModelBox(north5, 0, 0, -1.5F, 0.0F, 0.0F, 3, 2, 6, 0.0F, false));

		petal1 = new ModelRenderer[]{north1,east1,south1,west1};
		petal2 = new ModelRenderer[]{north2,east2,south2,west2};
		petal3 = new ModelRenderer[]{north3,east3,south3,west3};
		petal4 = new ModelRenderer[]{north4,east4,south4,west4};
		petal5 = new ModelRenderer[]{north5,east5,south5,west5};
		petals = new ModelRenderer[][]{petal1,petal2,petal3,petal4,petal5};
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		stem1.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	private void setPetalAngle(int index, float x, float y, float z) {
		ModelRenderer[] petal = petals[index - 1];
		for (int i = 0; i < 4; i++) {
			petal[i].rotateAngleX = x;
			petal[i].rotateAngleY = y;
			petal[i].rotateAngleZ = z;
		}
		if (index == 1) {
			petal1[0].rotateAngleY += 3.1416f;
			petal1[1].rotateAngleY -= 1.5708f;

			petal1[3].rotateAngleY += 1.5708f;
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		EntitySolanum solanum = (EntitySolanum) entityIn;
		switch (solanum.getState()) {
			case EntitySolanum.IDLE:
				setRotationAngle(stem1,0f,0f,0f);
				setRotationAngle(stem2,0f,0f,0f);
				setRotationAngle(stem3,0f,0f,0f);
				setRotationAngle(stem4,0f,0f,0f);
				setRotationAngle(stem5,0f,0f,0f);
				setRotationAngle(neck,0f,0f,0f);
				setRotationAngle(head,0f,0f,0f);

				setPetalAngle(1,0.0436332f,0f,0f);
				setPetalAngle(2,0.0872665f,0f,0f);
				setPetalAngle(3,0.1309f,0f,0f);
				setPetalAngle(4,0.174533f,0f,0f);
				setPetalAngle(5,0.2181662f,0f,0f);

				break;
			case EntitySolanum.OPEN:
				setRotationAngle(stem1,0f,0f,0f);
				setRotationAngle(stem2,0f,0f,0f);
				setRotationAngle(stem3,0f,0f,0f);
				setRotationAngle(stem4,0f,0f,0f);
				setRotationAngle(stem5,0f,0f,0f);
				setRotationAngle(neck,0f,0f,0f);
				setRotationAngle(head,0f,0f,0f);

				setPetalAngle(1,-0.0872665f,0f,0f);
				setPetalAngle(2,-0.174533f,0f,0f);
				setPetalAngle(3,-0.261799f,0f,0f);
				setPetalAngle(4,-0.349066f,0f,0f);
				setPetalAngle(5,-0.436332f,0f,0f);
				break;
			case EntitySolanum.CLOSED:
				setRotationAngle(stem1,0f,0f,0f);
				setRotationAngle(stem2,0f,0f,0f);
				setRotationAngle(stem3,0f,0f,0f);
				setRotationAngle(stem4,0f,0f,0f);
				setRotationAngle(stem5,0f,0f,0f);
				setRotationAngle(neck,0f,0f,0f);
				setRotationAngle(head,0f,0f,0f);

				setPetalAngle(1,1.48353f,0f,0f);
				setPetalAngle(2,0.261799f,0f,0f);
				setPetalAngle(3,0.2181662f,0f,0f);
				setPetalAngle(4,0.261799f,0f,0f);
				setPetalAngle(5,-0.436332f,0f,0f);
				break;
			case EntitySolanum.SPIKED:
				setRotationAngle(stem1,0f,0f,0f);
				setRotationAngle(stem2,0f,0f,0f);
				setRotationAngle(stem3,0f,0f,0f);
				setRotationAngle(stem4,0f,0f,0f);
				setRotationAngle(stem5,0f,0f,0f);
				setRotationAngle(neck,0f,0f,0f);
				setRotationAngle(head,0f,0f,0f);

				setPetalAngle(1,1.13446f,0f,0f);
				setPetalAngle(2,0.7417649f,0f,0f);
				setPetalAngle(3,0.610865f,0f,0f);
				setPetalAngle(4,0.959931f,0f,0f);
				setPetalAngle(5,-1.91986f,0f,0f);
				break;
			case EntitySolanum.BLASTING:
				setRotationAngle(stem1,0f,0f,0f);
				setRotationAngle(stem2,0f,0f,0f);
				setRotationAngle(stem3,0f,0f,0f);
				setRotationAngle(stem4,0f,0f,0f);
				setRotationAngle(stem5,0f,0f,0f);
				setRotationAngle(neck,0f,0f,0f);
				setRotationAngle(head,0f,0f,0f);

				setPetalAngle(1,0.523599f,0f,0f);
				setPetalAngle(2,0.523599f,0f,0f);
				setPetalAngle(3,0.523599f,0f,0f);
				setPetalAngle(4,0f,0f,0f);
				setPetalAngle(5,0f,0f,0f);
				break;
			case EntitySolanum.CHARGING:
				setRotationAngle(stem1,0f,0f,0f);
				setRotationAngle(stem2,0f,0f,0f);
				setRotationAngle(stem3,0f,0f,0f);
				setRotationAngle(stem4,0f,0f,0f);
				setRotationAngle(stem5,0f,0f,0f);
				setRotationAngle(neck,0f,0f,0f);
				setRotationAngle(head,0f,0f,0f);

				setPetalAngle(1,0.698132f,0f,0f);
				setPetalAngle(2,0.610865f,0f,0f);
				setPetalAngle(3,0.610865f,0f,0f);
				setPetalAngle(4,0.610865f,0f,0f);
				setPetalAngle(5,0.610865f,0f,0f);
				break;
			case EntitySolanum.SHOOTING:
				setRotationAngle(stem1,0f,0f,0f);
				setRotationAngle(stem2,0f,0f,0f);
				setRotationAngle(stem3,0f,0f,0f);
				setRotationAngle(stem4,0f,0f,0f);
				setRotationAngle(stem5,0f,0f,0f);
				setRotationAngle(neck,0f,0f,0f);
				setRotationAngle(head,0f,0f,0f);

				float time = ageInTicks * 0.29f;

//				setPetalAngle(1, -0.0872665f + OresparkUtil.clippedSin(time) * 1.05f,0f,0f);
//				setPetalAngle(2, -0.174533f + OresparkUtil.clippedSin(time + OresparkUtil.PI / 5f) * 1.15f,0f,0f);
//				setPetalAngle(3, -0.261799f + OresparkUtil.clippedSin(time + OresparkUtil.PI * 2 / 5f) * 1.15f,0f,0f);
//				setPetalAngle(4, -0.349066f + OresparkUtil.clippedSin(time + OresparkUtil.PI * 3 / 5f) * 1.25f,0f,0f);
//				setPetalAngle(5, -0.436332f + OresparkUtil.clippedSin(time + OresparkUtil.PI * 4 / 5f) * 1.40f,0f,0f);

				setPetalAngle(1, -0.0872665f + 0.525f + MathHelper.sin(time) * 0.525f,0f,0f);
				setPetalAngle(2, -0.174533f + 0.525f + MathHelper.sin(time + OresparkUtil.PI / 5f) * 0.575f,0f,0f);
				setPetalAngle(3, -0.261799f + 0.525f + MathHelper.sin(time + OresparkUtil.PI * 2 / 5f) * 0.525f,0f,0f);
				setPetalAngle(4, -0.349066f + 0.60f + MathHelper.sin(time + OresparkUtil.PI * 3 / 5f) * 0.6f,0f,0f);
				setPetalAngle(5, -0.436332f + 0.65f + MathHelper.sin(time + OresparkUtil.PI * 4 / 5f) * 0.65f,0f,0f);

				break;
			default:
				break;
		}
	}
}