package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.fluid.BloodFluid;
import com.fabriccommunity.spookytime.fluid.WitchWaterFluid;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

public class SpookyFluids {
	public static final BaseFluid WITCH_WATER = register("witch_water", new WitchWaterFluid.Still());
	public static final BaseFluid FLOWING_WITCH_WATER = register("witch_water_flowing", new WitchWaterFluid.Flowing());

	public static final BaseFluid BLOOD = register("blood", new BloodFluid.Still());
	public static final BaseFluid FLOWING_BLOOD = register("blood_flowing", new BloodFluid.Flowing());
	
	private SpookyFluids() {
		// NO-OP
	}
	
	public static void init() {
	
	}
	
	static <T extends Fluid> T register(String name, T fluid) {
		T b = Registry.register(Registry.FLUID, SpookyTime.id(name), fluid);
		return b;
	}
}
