package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.radioactive.RadioactiveMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class CheckValidationProcedure {
	@SubscribeEvent
	public static void init(FMLClientSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		RadioactiveMod.LOGGER.info("Loading Radioactive V3...");
		if (SilentValidateV3Procedure.execute()) {
			RadioactiveMod.LOGGER.info("Radioactive successfully loaded!");
		} else {
			RadioactiveMod.LOGGER.info("Radioactive failed to load.");
		}
	}
}
