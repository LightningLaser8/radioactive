package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GlobalRadTickProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		if (RadioactiveCFGConfiguration.OLD_RADIATION.get()) {
			if (RadioactiveModVariables.MapVariables.get(world).rad_tick < 21) {
				RadioactiveModVariables.MapVariables.get(world).rad_tick = RadioactiveModVariables.MapVariables.get(world).rad_tick + 1;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
			} else {
				RadioactiveModVariables.MapVariables.get(world).rad_tick = 0;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
			}
		}
	}
}
