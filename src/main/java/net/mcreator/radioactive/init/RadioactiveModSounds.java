
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.radioactive.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.radioactive.RadioactiveMod;

public class RadioactiveModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RadioactiveMod.MODID);
	public static final RegistryObject<SoundEvent> GEIGER_CLICK = REGISTRY.register("geiger_click", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("radioactive", "geiger_click")));
}
