package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.radioactive.network.RadioactiveModVariables;

import java.util.List;

public class AutoTagSystemProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		Entity e = null;
		if (RadioactiveModVariables.local_errored) {
			tooltip.add(Component.literal("\u00A74Radioactive has errors"));
		} else {
			if (RadioactiveModVariables.loaded__show_tooltips) {
				if (RadioactiveModVariables.MapVariables.get(world).loaded__old_sys) {
					InventoryTagSystemProcedure.execute(itemstack, tooltip);
					ProximityTagSystemProcedure.execute(itemstack, tooltip);
					BlockTagSystemProcedure.execute(itemstack, tooltip);
					WeaponTagSystemProcedure.execute(itemstack, tooltip);
					ArmorTagSystemProcedure.execute(itemstack, tooltip);
					DetectorTagSystemProcedure.execute(itemstack, tooltip);
					CounterTagSystemProcedure.execute(itemstack, tooltip);
				}
				if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3) {
					V3InventoryTagSystemProcedure.execute(world, itemstack, tooltip);
					V3ProximityTagSystemProcedure.execute(world, itemstack, tooltip);
					V3BlockTagSystemProcedure.execute(world, itemstack, tooltip);
					V3ArmorTagSystemProcedure.execute(world, itemstack, tooltip);
					V3CureTagSystemProcedure.execute(world, itemstack, tooltip);
					V3DetectorTagSystemProcedure.execute(world, itemstack, tooltip);
					V3CounterTagSystemProcedure.execute(world, itemstack, tooltip);
				}
			}
		}
	}
}
