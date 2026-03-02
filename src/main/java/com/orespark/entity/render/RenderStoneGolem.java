package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityDirtGolem;
import com.orespark.entity.EntityStoneGolem;
import com.orespark.entity.model.ModelDirtGolem;
import com.orespark.entity.model.ModelStoneGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderStoneGolem extends RenderLiving<EntityStoneGolem> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/stonegolem.png");

    public RenderStoneGolem(RenderManager manager) {
        super(manager,new ModelStoneGolem(), 1.0f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityStoneGolem entityStoneGolem) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityStoneGolem stoneGolem, float f1, float f2, float f3) {
        super.applyRotations(stoneGolem, f1, f2, f3);
    }

}
