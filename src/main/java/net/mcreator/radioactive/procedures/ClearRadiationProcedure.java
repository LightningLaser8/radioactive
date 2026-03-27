package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class ClearRadiationProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (RadioactiveModVariables.MapVariables.get(world).loaded__old_sys || RadioactiveModVariables.MapVariables.get(world).loaded__opr) {
			{
				double _setval = 0;
				entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.received_radiation = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			entity.getPersistentData().putDouble("radiation", 0);
		}
	}
}
