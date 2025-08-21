package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import java.util.List;

public class V3BlockTagSystemProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		String id = "";
		double total_radiation = 0;
		double amount = 0;
		double range = 0;
		if (RadioactiveCFGConfiguration.V3_BLOCK_RADIATION.get()) {
			if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__block.contains((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) {
				total_radiation = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__block.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
				range = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__block.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("range")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
				if (!(total_radiation == 0)) {
					tooltip.add(Component.literal(""));
					tooltip.add(Component.literal("\u00A73[Block Radiation]"));
					tooltip.add(Component.literal(("\u00A7e-> " + new java.text.DecimalFormat("###.#").format(total_radiation * 20) + " RAD/s" + " ~ " + new java.text.DecimalFormat("###.#").format(range) + " blocks")));
				}
			}
		}
	}
}
