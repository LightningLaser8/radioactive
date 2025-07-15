package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveClientConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CounterNoiseProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world.isClientSide()) {
			if (!RadioactiveClientConfiguration.SHUT_UP.get() && (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).rads_per_sec > 0
					&& world.getLevelData().getGameTime() % Math.round(75000
							/ ((double) RadioactiveClientConfiguration.CLICK_RATE.get() * (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).rads_per_sec)) == 0) {
				if (world instanceof Level _level) {
					if (_level.isClientSide()) {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("radioactive:geiger_click")), SoundSource.NEUTRAL, 1, (float) (double) RadioactiveClientConfiguration.CLICK_PITCH.get(), false);
					}
				}
			}
		}
	}
}
