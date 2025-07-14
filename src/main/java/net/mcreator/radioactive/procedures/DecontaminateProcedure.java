package net.mcreator.radioactive.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

public class DecontaminateProcedure {
	public static void execute(Entity entity, double amount) {
		if (entity == null)
			return;
		if (RadioactiveCFGConfiguration.OLD_RADIATION.get() || RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get()) {
			{
				double _setval = Math.min(2147483647, Math.max((entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation - amount
						* (double) RadioactiveCFGConfiguration.RADIATION_MULTIPLIER.get()
						* (RadioactiveCFGConfiguration.RESISTS_DECON.get() ? 1 - (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).radiation_resistance : 1), 0));
				entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.received_radiation = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			entity.getPersistentData()
					.putDouble("radiation",
							(Math.min(2147483647,
									Math.max(
											entity.getPersistentData().getDouble("radiation")
													- amount * (double) RadioactiveCFGConfiguration.RADIATION_MULTIPLIER.get() * (RadioactiveCFGConfiguration.RESISTS_DECON.get() ? 1 - entity.getPersistentData().getDouble("rad_resistance") : 1),
											0))));
		}
	}
}
