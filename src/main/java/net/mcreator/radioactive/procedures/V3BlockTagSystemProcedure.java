package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import java.util.List;

public class V3BlockTagSystemProcedure {
	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		String id = "";
		double total_radiation = 0;
		double amount = 0;
		double range = 0;
		if (RadioactiveCFGConfiguration.V3_BLOCK_RADIATION.get()) {
			total_radiation = 0;
			for (String stringiterator : RadioactiveCFGConfiguration.V3_BLOCK_RADIATION_DEFINITION.get()) {
				id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
				amount = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) stringiterator.indexOf("=") + "=".length(), (int) stringiterator.indexOf("~")));
				if ((id).equals(ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString())) {
					range = new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(stringiterator.substring((int) (stringiterator.indexOf("~") + 1)));
					total_radiation = total_radiation + amount;
				}
			}
			if (!(total_radiation == 0)) {
				tooltip.add(Component.literal(""));
				tooltip.add(Component.literal("\u00A73[Block Radiation]"));
				tooltip.add(Component.literal(("\u00A7e-> " + new java.text.DecimalFormat("###.#").format(total_radiation * 20) + " RAD/s" + " ~ " + new java.text.DecimalFormat("###.#").format(range) + " blocks")));
			}
		}
	}
}
