package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import java.util.List;

public class V3ArmorTagSystemProcedure {
	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		double amount = 0;
		double total_protect = 0;
		String id = "";
		for (String stringiterator : RadioactiveCFGConfiguration.V3_RADIATION_RESISTANCE_DEFINITION.get()) {
			id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
			amount = new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1)));
			if ((id).equals(ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString())) {
				total_protect = total_protect + amount;
			}
		}
		if (!(total_protect == 0)) {
			tooltip.add(Component.literal(""));
			tooltip.add(Component.literal("\u00A7b[Radiation Resistance]"));
			tooltip.add(Component.literal(("\u00A7e-> " + new java.text.DecimalFormat("####").format(total_protect) + "% radiation reduction")));
		}
	}
}
