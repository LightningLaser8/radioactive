package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class IsItemDetectorProcedure {
	public static boolean execute(LevelAccessor world, ItemStack item) {
		boolean retval = false;
		return RadioactiveModVariables.MapVariables.get(world).v3_loaded__detec.contains((ForgeRegistries.ITEMS.getKey(item.getItem()).toString()));
	}
}
