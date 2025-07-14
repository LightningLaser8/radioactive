package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.ItemStack;

import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

public class IsItemCounterProcedure {
	public static boolean execute(ItemStack item) {
		boolean retval = false;
		for (String stringiterator : RadioactiveCFGConfiguration.V3_COUNTER_DEFINITION.get()) {
			if ((stringiterator).equals(ForgeRegistries.ITEMS.getKey(item.getItem()).toString())) {
				retval = true;
				break;
			}
		}
		return retval;
	}
}
