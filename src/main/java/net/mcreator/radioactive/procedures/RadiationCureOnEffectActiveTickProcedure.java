package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.init.RadioactiveModMobEffects;

public class RadiationCureOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		DecontaminateProcedure.execute(world, entity,
				(entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(RadioactiveModMobEffects.RADIATION_CURE.get()) ? _livEnt.getEffect(RadioactiveModMobEffects.RADIATION_CURE.get()).getAmplifier() : 0) + 1);
	}
}
