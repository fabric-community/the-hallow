package com.fabriccommunity.thehallow.registry;

import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.fluid.BloodFluid;
import com.fabriccommunity.thehallow.fluid.WitchWaterFluid;

public class HallowedFluids {
	public static final BaseFluid WITCH_WATER = register("witch_water", new WitchWaterFluid.Still());
	public static final BaseFluid FLOWING_WITCH_WATER = register("witch_water_flowing", new WitchWaterFluid.Flowing());
	
	public static final BaseFluid BLOOD = register("blood", new BloodFluid.Still());
	public static final BaseFluid FLOWING_BLOOD = register("blood_flowing", new BloodFluid.Flowing());
	
	private HallowedFluids() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	static <T extends Fluid> T register(String name, T fluid) {
		T b = Registry.register(Registry.FLUID, TheHallow.id(name), fluid);
		return b;
	}
}
