package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class GetRadiationStageProcedure {
	public static double execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return 0;
		return Math.floor(
				((entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation / (RadioactiveModVariables.MapVariables.get(world).loaded__effectmult * 10))
						* 90);
	}
}
