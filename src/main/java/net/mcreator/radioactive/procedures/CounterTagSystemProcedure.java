package net.mcreator.radioactive.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CounterTagSystemProcedure {
	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		if (itemstack.is(ItemTags.create(new ResourceLocation("forge:radiation_counters")))) {
			tooltip.add(Component.literal(""));
			tooltip.add(Component.literal("\u00A76[Radiation Counter]"));
			tooltip.add(Component.literal("\u00A7eUse to display data"));
		}
	}
}
