package net.mcreator.radioactive.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

public class RadiationSicknessActiveTickConditionProcedure {
	public static boolean execute(Entity entity, double amplifier) {
		if (entity == null)
			return false;
		double baseRate = 0;
		double rateWithAmplifier = 0;
		baseRate = 100;
		rateWithAmplifier = baseRate / Math.pow((double) RadioactiveCFGConfiguration.RADIATION_SICKNESS_SCALING.get(), amplifier);
		entity.getPersistentData().putDouble("radDamageCounter", (entity.getPersistentData().getDouble("radDamageCounter") + 1));
		return entity.getPersistentData().getDouble("radDamageCounter") % Math.floor(rateWithAmplifier) == 0;
	}
}
