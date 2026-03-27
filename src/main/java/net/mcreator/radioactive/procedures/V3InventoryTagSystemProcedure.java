package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.radioactive.network.RadioactiveModVariables;

import java.util.List;

public class V3InventoryTagSystemProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_inv) {
			total_radiation = 0;
			if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__inv.contains((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) {
				total_radiation = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__inv.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
				if (!(total_radiation == 0)) {
					tooltip.add(Component.literal(""));
					tooltip.add(Component.literal("\u00A7a[Radioactive]"));
					tooltip.add(Component.literal(("\u00A7e-> " + new java.text.DecimalFormat("###.#").format(total_radiation * 20) + " RAD/s")));
					if (!(itemstack.getCount() == 1)) {
						tooltip.add(Component.literal(("\u00A7e-> Stack: " + new java.text.DecimalFormat("###.#").format(total_radiation * 20 * itemstack.getCount()) + " RAD/s")));
					}
				}
			}
		}
	}
}
