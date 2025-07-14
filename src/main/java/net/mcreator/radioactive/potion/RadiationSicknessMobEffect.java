
package net.mcreator.radioactive.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.radioactive.procedures.RadiationSicknessOnEffectActiveTickProcedure;

public class RadiationSicknessMobEffect extends MobEffect {
	public RadiationSicknessMobEffect() {
		super(MobEffectCategory.HARMFUL, -16711936);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		RadiationSicknessOnEffectActiveTickProcedure.execute(entity.level(), entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
