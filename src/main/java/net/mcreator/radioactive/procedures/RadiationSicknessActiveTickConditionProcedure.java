package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class RadiationSicknessActiveTickConditionProcedure {
	public static boolean execute(LevelAccessor world, Entity entity, double amplifier) {
		if (entity == null)
			return false;
		double baseRate = 0;
		double rateWithAmplifier = 0;
		baseRate = 100;
		rateWithAmplifier = baseRate / Math.pow(RadioactiveModVariables.MapVariables.get(world).loaded__sickmult, amplifier);
		entity.getPersistentData().putDouble("radDamageCounter", (entity.getPersistentData().getDouble("radDamageCounter") + 1));
		return entity.getPersistentData().getDouble("radDamageCounter") % Math.floor(rateWithAmplifier) == 0;
	}
}
