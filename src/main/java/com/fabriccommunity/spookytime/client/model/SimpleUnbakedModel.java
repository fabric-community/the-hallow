package com.fabriccommunity.spookytime.client.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;

/** Can be used for multiple blocks - will return same baked model for each */
public class SimpleUnbakedModel implements UnbakedModel {
    final Function<Function<Identifier, Sprite>, BakedModel> baker;
    final List<Identifier> sprites;
    BakedModel baked = null;

    public SimpleUnbakedModel(Function<Function<Identifier, Sprite>, BakedModel> baker, List<Identifier> sprites) {
        this.baker = baker;
        this.sprites = sprites;
    }

    @Override
    public Collection<Identifier> getModelDependencies() {
        return Collections.emptyList();
    }

    @Override
    public Collection<Identifier> getTextureDependencies(Function<Identifier, UnbakedModel> modelLoader, Set<String> errors) {
        return sprites;
    }

	@Override
	public BakedModel bake(ModelLoader modelLoader, Function<Identifier, Sprite> spriteLoader, ModelBakeSettings bakeSettings) {
		BakedModel result = baked;
		if (result == null) {
			result = baker.apply(spriteLoader);
			baked = result;
		}
		return result;
	}
}
