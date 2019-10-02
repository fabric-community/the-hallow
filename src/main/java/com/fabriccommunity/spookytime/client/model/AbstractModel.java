package com.fabriccommunity.spookytime.client.model;

import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;

public abstract class AbstractModel implements BakedModel, FabricBakedModel {
    protected static final Renderer RENDERER = RendererAccess.INSTANCE.getRenderer();
    
    protected final Sprite modelSprite;
    protected final ModelTransformation transformation;
    
    protected AbstractModel(
            Sprite sprite, 
            ModelTransformation transformation) {
        this.modelSprite = sprite;
        this.transformation = transformation;
    }
    
    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean hasDepthInGui() {
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getSprite() {
        return modelSprite;
    }

    @Override
    public ModelTransformation getTransformation() {
        return transformation;
    }
}
