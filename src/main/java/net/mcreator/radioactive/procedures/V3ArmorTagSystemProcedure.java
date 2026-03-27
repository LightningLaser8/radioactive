package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.List;

public class V3ArmorTagSystemProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_protect = 0;
		total_protect = GetRadiationProtectionOfProcedure.execute(world, itemstack);
		if (!(total_protect == 0)) {
			tooltip.add(Component.literal(""));
			tooltip.add(Component.literal("\u00A7b[Radiation Resistance]"));
			tooltip.add(Component.literal(("\u00A7e-> " + new java.text.DecimalFormat("####").format(total_protect) + "% radiation reduction")));
		}
	}
}
