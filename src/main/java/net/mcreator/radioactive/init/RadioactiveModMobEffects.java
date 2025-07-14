
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.radioactive.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.radioactive.potion.RadiationSicknessMobEffect;
import net.mcreator.radioactive.potion.RadiationImmunityMobEffect;
import net.mcreator.radioactive.RadioactiveMod;

public class RadioactiveModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, RadioactiveMod.MODID);
	public static final RegistryObject<MobEffect> RADIATION_IMMUNITY = REGISTRY.register("radiation_immunity", () -> new RadiationImmunityMobEffect());
	public static final RegistryObject<MobEffect> RADIATION_SICKNESS = REGISTRY.register("radiation_sickness", () -> new RadiationSicknessMobEffect());
}
