package net.mcreator.radioactive.procedures;

import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class JoinMessageProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double errors = 0;
		if (!RadioactiveCFGConfiguration.DISABLE_MESSAGE.get()) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7aRadioactive\u00A7e v" + new Object() {
					String getModInfo(String modid, int type) {
						String val = "";
						List<IModInfo> mods = ModList.get().getMods();
						for (IModInfo mod : mods) {
							if (mod.getModId().equals(modid.toLowerCase())) {
								if (type == 0) {
									val = mod.getVersion().toString();
								} else {
									val = mod.getDisplayName();
								}
								break;
							}
						}
						return val;
					}
				}.getModInfo("radioactive", 0) + "\u00A7f loaded!")), false);
			if (RadioactiveModVariables.MapVariables.get(world).loaded__old_sys) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7aUsing Old Radiation System"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("Inventory Radiation is " + (RadioactiveCFGConfiguration.INVENTORY_RADIATION.get() ? "On" : "Off"))), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("Proximity Radiation is " + (RadioactiveCFGConfiguration.PROXIMITY_RADIATION.get() ? "On" : "Off"))), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("Block Radiation is " + (RadioactiveCFGConfiguration.BLOCK_RADIATION.get() ? "On" : "Off"))), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("Entity Radiation is " + (RadioactiveCFGConfiguration.ENTITY_RADIATION.get() ? "On" : "Off"))), false);
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7aUsing New Radiation System"), false);
				if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7eWith V3 Modpack configurator"), false);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("Irradiation is " + (RadioactiveModVariables.MapVariables.get(world).loaded__enable_rad_damage ? "Enabled" : "Disabled"))), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("Decontamination is " + (RadioactiveModVariables.MapVariables.get(world).loaded__enable_unrad_damage ? "Enabled" : "Disabled"))), false);
				if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Inventory Radiation is " + (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_inv ? "On" : "Off"))), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Proximity Radiation is " + (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_prox ? "On" : "Off"))), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Block Radiation is " + (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_block ? "On" : "Off"))), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Biome Radiation is " + (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_biome ? "On" : "Off"))), false);
				}
			}
		}
		ValidateV3Procedure.execute(world, entity);
	}
}
