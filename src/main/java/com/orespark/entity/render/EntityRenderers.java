package com.orespark.entity.render;

import com.orespark.entity.*;
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

        RenderingRegistry.registerEntityRenderingHandler(EntityGrub.class, new IRenderFactory<EntityGrub>() {
            @Override
            public Render<? super EntityGrub> createRenderFor(RenderManager renderManager) {
                return new RenderGrub(renderManager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityRhinoBeetle.class, new IRenderFactory<EntityRhinoBeetle>() {
            @Override
            public Render<? super EntityRhinoBeetle> createRenderFor(RenderManager renderManager) {
                return new RenderRhinoBeetle(renderManager);
            }
        });
    }
}
