
package net.mcreator.radioactive.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class RadiationImmunityMobEffect extends MobEffect {
	public RadiationImmunityMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -6750055);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
