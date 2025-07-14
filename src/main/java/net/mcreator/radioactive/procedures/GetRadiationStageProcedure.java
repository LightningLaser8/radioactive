package net.mcreator.radioactive.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

public class GetRadiationStageProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		double radiation = 0;
		double scale = 0;
		scale = (double) RadioactiveCFGConfiguration.RADIATION_POISONING_SCALING.get();
		radiation = (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation;
		return Math.floor((radiation / (scale * 10)) * 90);
	}
}
