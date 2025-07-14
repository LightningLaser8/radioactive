package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.configuration.RadioactiveClientConfiguration;

public class RadiationBarDisplayOverlayIngameProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		return RadioactiveClientConfiguration.SHOW_SCALE.get() && HasEntityRadiationCounterProcedure.execute(world, entity);
	}
}
