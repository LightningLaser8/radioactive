package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ByteTag;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import java.util.List;

public class V3CureTagSystemProcedure {
	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
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
			if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.contains((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) {
				total_protect = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("amount")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
				final_percent = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("is_percent")) instanceof ByteTag _byteTag ? _byteTag.getAsByte() == 1 : false;
				if (!(total_protect == 0)) {
					tooltip.add(Component.literal(""));
					tooltip.add(Component.literal("\u00A76[Curative Item]"));
					tooltip.add(Component.literal((final_percent ? "\u00A7e-> " + new java.text.DecimalFormat("####").format(total_protect) + "% cure" : "\u00A7e-> " + new java.text.DecimalFormat("####").format(total_protect) + " RAD cure")));
				}
			}
		}
	}
}
