package net.mcreator.radioactive.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.List;

public class WeaponTagSystemProcedure {
	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("forge:weapon_radioactive")))) {
			for (int index0 = 0; index0 < 1000; index0++) {
				if (itemstack.is(ItemTags.create(new ResourceLocation((("forge:weapon_radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
					total_radiation = total_radiation + current_rad_id;
				}
				current_rad_id = current_rad_id + 1;
			}
			if (!(total_radiation == 0)) {
				tooltip.add(Component.literal(""));
				tooltip.add(Component.literal("\u00A7c[Radioactive Weapon]"));
				tooltip.add(Component.literal(("\u00A7e-> " + new java.text.DecimalFormat("####").format(total_radiation) + " RAD/hit")));
				if (!(itemstack.getCount() == 1)) {
					tooltip.add(Component.literal(("\u00A7e-> Stack: " + new java.text.DecimalFormat("####").format(total_radiation * itemstack.getCount()) + " RAD/hit")));
				}
			}
		}
	}
}
