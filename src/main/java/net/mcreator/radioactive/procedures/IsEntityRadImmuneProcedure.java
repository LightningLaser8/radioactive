package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.init.RadioactiveModMobEffects;

public class IsEntityRadImmuneProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		boolean retval = false;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(RadioactiveModMobEffects.RADIATION_IMMUNITY.get())) {
			return true;
		}
		return RadioactiveModVariables.MapVariables.get(world).v3_loaded__radimmunity.contains((ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString()));
	}
}
