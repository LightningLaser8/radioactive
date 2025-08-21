
package net.mcreator.radioactive.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class RadiationReductionMobEffect extends MobEffect {
	public RadiationReductionMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -65383);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
