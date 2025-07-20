
package net.mcreator.radioactive.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.radioactive.procedures.RadiationCureOnEffectActiveTickProcedure;

public class RadiationCureMobEffect extends MobEffect {
	public RadiationCureMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3342592);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		RadiationCureOnEffectActiveTickProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
