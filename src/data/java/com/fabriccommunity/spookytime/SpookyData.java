package com.fabriccommunity.spookytime;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.RecipesProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public final class SpookyData
{
	public static void main(String... args) throws IOException
	{
		Path path = Paths.get(args[0]);
		DataGenerator dataGenerator = new DataGenerator(path, Collections.emptySet());
		
		dataGenerator.install(new SpookyRecipeProvider(dataGenerator));
		
		dataGenerator.run();
	}
	
	private SpookyData() {}
}
