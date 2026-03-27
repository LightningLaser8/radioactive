package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.radioactive.network.RadioactiveModVariables;

import java.util.List;

public class V3ProximityTagSystemProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		String id = "";
		double total_radiation = 0;
		double amount = 0;
		double total_range = 0;
		if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_prox) {
			total_radiation = 0;
			if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__prox.contains((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) {
				total_radiation = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__prox.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
				total_range = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__prox.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("range")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
				if (!(total_radiation == 0 || total_range == 0)) {
					tooltip.add(Component.literal(""));
					tooltip.add(Component.literal("\u00A72[Proximity Radiation]"));
					tooltip.add(Component.literal((("\u00A7e-> " + new java.text.DecimalFormat("####").format(total_radiation * 20) + " RAD/s") + "" + (" ~ " + new java.text.DecimalFormat("####").format(total_range) + " blocks"))));
					if (!(itemstack.getCount() == 1)) {
						tooltip.add(Component.literal(
								(("\u00A7e-> Stack: " + new java.text.DecimalFormat("####").format(total_radiation * itemstack.getCount() * 20) + " RAD/s") + "" + (" ~ " + new java.text.DecimalFormat("####").format(total_range) + " blocks"))));
					}
				}
			}
		}
	}
}
