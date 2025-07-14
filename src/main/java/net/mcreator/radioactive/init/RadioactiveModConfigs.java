package net.mcreator.radioactive.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.mcreator.radioactive.configuration.RadioactiveClientConfiguration;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;
import net.mcreator.radioactive.RadioactiveMod;

@Mod.EventBusSubscriber(modid = RadioactiveMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RadioactiveModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RadioactiveCFGConfiguration.SPEC, "radioactive.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, RadioactiveClientConfiguration.SPEC, "radioactive-client.toml");
		});
	}
}
