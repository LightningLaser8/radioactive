package net.mcreator.radioactive.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ProximityTagSystemProcedure {
	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		double current_range_id = 0;
		double total_range = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("forge:proximity_radioactive")))) {
			total_radiation = 0;
			current_rad_id = 0;
			total_range = 0;
			current_range_id = 0;
			for (int index0 = 0; index0 < 1000; index0++) {
				if (itemstack.is(ItemTags.create(new ResourceLocation((("forge:proximity_radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
					total_radiation = total_radiation + current_rad_id;
				}
				current_rad_id = current_rad_id + 1;
			}
			for (int index1 = 0; index1 < 100; index1++) {
				if (itemstack.is(ItemTags.create(new ResourceLocation((("forge:proximity_range_" + new java.text.DecimalFormat("####").format(current_range_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
					total_range = total_range + current_range_id;
				}
				current_range_id = current_range_id + 1;
			}
			if (!(total_radiation == 0 || total_range == 0)) {
				tooltip.add(Component.literal(""));
				tooltip.add(Component.literal("\u00A72[Proximity Radiation]"));
				tooltip.add(Component.literal((("\u00A7e-> " + new java.text.DecimalFormat("####").format(total_radiation) + " RAD/s") + "" + (" ~ " + new java.text.DecimalFormat("####").format(total_range) + " blocks"))));
				if (!(itemstack.getCount() == 1)) {
					tooltip.add(Component
							.literal((("\u00A7e-> Stack: " + new java.text.DecimalFormat("####").format(total_radiation * itemstack.getCount()) + " RAD/s") + "" + (" ~ " + new java.text.DecimalFormat("####").format(total_range) + " blocks"))));
				}
			}
		}
	}
}
