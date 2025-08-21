package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.List;

public class V3CounterTagSystemProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		if (IsItemCounterProcedure.execute(world, itemstack)) {
			tooltip.add(Component.literal(""));
			tooltip.add(Component.literal("\u00A7d[Radiation Counter]"));
			tooltip.add(Component.literal("\u00A7eUse to display data"));
		}
	}
}
