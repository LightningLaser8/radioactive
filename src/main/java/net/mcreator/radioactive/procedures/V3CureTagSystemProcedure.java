package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import java.util.List;

public class V3CureTagSystemProcedure {
	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		double amount = 0;
		double total_protect = 0;
		String id = "";
		boolean is_percent = false;
		boolean final_percent = false;
		if (RadioactiveCFGConfiguration.V3_CURES.get()) {
			for (String stringiterator : RadioactiveCFGConfiguration.V3_CURE_DEFINITION.get()) {
				id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
				is_percent = stringiterator.contains("%");
				amount = is_percent ? new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("%"))) : new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1)));
				if ((id).equals(ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString())) {
					final_percent = is_percent;
					total_protect = total_protect + amount;
				}
			}
			if (!(total_protect == 0)) {
				tooltip.add(Component.literal(""));
				tooltip.add(Component.literal("\u00A76[Curative Item]"));
				tooltip.add(Component.literal((final_percent ? "\u00A7e-> " + new java.text.DecimalFormat("####").format(total_protect) + "% cure" : "\u00A7e-> " + new java.text.DecimalFormat("####").format(total_protect) + " RAD cure")));
			}
		}
	}
}
