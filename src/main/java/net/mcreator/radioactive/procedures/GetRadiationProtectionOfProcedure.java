package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class GetRadiationProtectionOfProcedure {
	public static double execute(LevelAccessor world, ItemStack itemstack) {
		double total_protect = 0;
		String id = "";
		id = ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString();
		if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__prot.contains(id)) {
			return (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__prot.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag()).get("prot")) instanceof DoubleTag _doubleTag
					? _doubleTag.getAsDouble()
					: 0.0D;
		}
		return 0;
	}
}
