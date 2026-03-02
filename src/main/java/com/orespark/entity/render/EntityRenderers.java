package com.orespark.entity.render;

import com.orespark.entity.EntityDirtGolem;
import com.orespark.entity.EntityMantis;
import com.orespark.entity.EntityStoneGolem;
import com.orespark.entity.EntityTreeGolem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRenderers {

    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMantis.class, new IRenderFactory<EntityMantis>() {
            @Override
            public Render<? super EntityMantis> createRenderFor(RenderManager renderManager) {
                return new RenderMantis(renderManager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDirtGolem.class, new IRenderFactory<EntityDirtGolem>() {
            @Override
            public Render<? super EntityDirtGolem> createRenderFor(RenderManager renderManager) {
                return new RenderDirtGolem(renderManager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityStoneGolem.class, new IRenderFactory<EntityStoneGolem>() {
            @Override
            public Render<? super EntityStoneGolem> createRenderFor(RenderManager renderManager) {
                return new RenderStoneGolem(renderManager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityTreeGolem.class, new IRenderFactory<EntityTreeGolem>() {
            @Override
            public Render<? super EntityTreeGolem> createRenderFor(RenderManager renderManager) {
                return new RenderTreeGolem(renderManager);
            }
        });
    }
}
