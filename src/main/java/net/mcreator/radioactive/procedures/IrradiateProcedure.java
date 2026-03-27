package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.init.RadioactiveModMobEffects;

public class IrradiateProcedure {
	public static boolean execute(LevelAccessor world, Entity entity, double amount) {
		if (entity == null)
			return false;
		double rad = 0;
		if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(RadioactiveModMobEffects.RADIATION_PROTECTION.get()))) {
			rad = amount * RadioactiveModVariables.MapVariables.get(world).loaded__radmult
					* (1 - (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).radiation_resistance);
			if (entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(RadioactiveModMobEffects.RADIATION_REDUCTION.get())) {
				rad = rad - ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(RadioactiveModMobEffects.RADIATION_REDUCTION.get()) ? _livEnt.getEffect(RadioactiveModMobEffects.RADIATION_REDUCTION.get()).getAmplifier() : 0) + 1) * 0.25;
			}
			if (rad <= 0) {
				return false;
			}
			if (RadioactiveModVariables.MapVariables.get(world).loaded__old_sys || RadioactiveModVariables.MapVariables.get(world).loaded__opr) {
				{
					double _setval = Math.min(2147483647, Math.max((entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation + rad, 0));
					entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.received_radiation = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				entity.getPersistentData().putDouble("radiation", (Math.min(2147483647, Math.max(entity.getPersistentData().getDouble("radiation") + rad, 0))));
			}
			return true;
		}
		return false;
	}
}
