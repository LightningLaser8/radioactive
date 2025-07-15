package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SyncRadiationsProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (!(RadioactiveCFGConfiguration.OLD_RADIATION.get() || RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get())) {
				{
					double _setval = entity.getPersistentData().getDouble("radiation");
					entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.received_radiation = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (RadioactiveModVariables.MapVariables.get(world).rad_tick == 20) {
				{
					double _setval = (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation
							- (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).last_rad;
					entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.rads_per_sec = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation;
					entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.last_rad = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
