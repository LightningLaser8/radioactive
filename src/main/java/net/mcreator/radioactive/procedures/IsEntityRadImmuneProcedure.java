package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.init.RadioactiveModMobEffects;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

public class IsEntityRadImmuneProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		boolean retval = false;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(RadioactiveModMobEffects.RADIATION_IMMUNITY.get())) {
			return true;
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_RADIMMUNITY.get()) {
			if ((stringiterator).equals(ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString())) {
				retval = true;
				break;
			}
		}
		return retval;
	}
}
