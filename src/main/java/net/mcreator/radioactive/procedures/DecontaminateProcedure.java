package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class DecontaminateProcedure {
	public static void execute(LevelAccessor world, Entity entity, double amount) {
		if (entity == null)
			return;
		if (RadioactiveModVariables.MapVariables.get(world).loaded__opr || RadioactiveModVariables.MapVariables.get(world).loaded__old_sys) {
			{
				double _setval = Math.min(2147483647,
						Math.max((entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation
								- amount * RadioactiveModVariables.MapVariables.get(world).loaded__deconmult
										* (RadioactiveModVariables.MapVariables.get(world).loaded__resist_decon
												? 1 - (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).radiation_resistance
												: 1),
								0));
				entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.received_radiation = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			entity.getPersistentData().putDouble("radiation", (Math.min(2147483647, Math.max(entity.getPersistentData().getDouble("radiation")
					- amount * RadioactiveModVariables.MapVariables.get(world).loaded__deconmult * (RadioactiveModVariables.MapVariables.get(world).loaded__resist_decon ? 1 - entity.getPersistentData().getDouble("rad_resistance") : 1), 0))));
		}
	}
}
