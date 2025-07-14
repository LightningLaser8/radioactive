package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

public class ValidateV3Procedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		double errors = 0;
		for (String stringiterator : RadioactiveCFGConfiguration.V3_RADIATION_RESISTANCE_DEFINITION.get()) {
			RadioactiveModVariables.MapVariables.get(world).errored = false;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			if (!stringiterator.contains("=")) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Error in Radiation Resistance Definitions"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: No '=' found"), false);
				RadioactiveModVariables.MapVariables.get(world).errored = true;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
				errors = errors + 1;
			}
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1))) == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A74Error in Radiation Resistance Definitions"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: Resistance value is not a number, or is zero"), false);
					RadioactiveModVariables.local_errored = true;
					errors = errors + 1;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_INVENTORY_RADIATION_DEFINITION.get()) {
			RadioactiveModVariables.MapVariables.get(world).errored = false;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			if (!stringiterator.contains("=")) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Error in Inventory Radiation Definitions"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: No '=' found"), false);
				RadioactiveModVariables.MapVariables.get(world).errored = true;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
				errors = errors + 1;
			}
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1))) == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A74Error in Inventory Radiation Definitions"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: Radiation is not a number, or is zero"), false);
					RadioactiveModVariables.local_errored = true;
					errors = errors + 1;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_BLOCK_RADIATION_DEFINITION.get()) {
			RadioactiveModVariables.MapVariables.get(world).errored = false;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			if (!stringiterator.contains("=")) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Error in Block Radiation Definitions"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: No '=' found"), false);
				RadioactiveModVariables.MapVariables.get(world).errored = true;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
				errors = errors + 1;
			}
			if (!stringiterator.contains("~")) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Error in Block Radiation Definitions"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: No '~' found"), false);
				RadioactiveModVariables.MapVariables.get(world).errored = true;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
				errors = errors + 1;
			}
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("~"))) == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A74Error in Block Radiation Definitions"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: Radiation is not a number, or is zero"), false);
					RadioactiveModVariables.local_errored = true;
					errors = errors + 1;
				}
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("~") + 1))) == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A74Error in Block Radiation Definitions"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: Range is not a number, or is zero"), false);
					RadioactiveModVariables.local_errored = true;
					errors = errors + 1;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_PROXIMITY_RADIATION_DEFINITION.get()) {
			RadioactiveModVariables.MapVariables.get(world).errored = false;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			if (!stringiterator.contains("=")) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Error in Proximity Radiation Definitions"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: No '=' found"), false);
				RadioactiveModVariables.MapVariables.get(world).errored = true;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
				errors = errors + 1;
			}
			if (!stringiterator.contains("~")) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Error in Proximity Radiation Definitions"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: No '~' found"), false);
				RadioactiveModVariables.MapVariables.get(world).errored = true;
				RadioactiveModVariables.MapVariables.get(world).syncData(world);
				errors = errors + 1;
			}
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("~"))) == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A74Error in Proximity Radiation Definitions"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: Radiation is not a number, or is zero"), false);
					RadioactiveModVariables.local_errored = true;
					errors = errors + 1;
				}
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("~") + 1))) == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A74Error in Proximity Radiation Definitions"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: Range is not a number, or is zero"), false);
					RadioactiveModVariables.local_errored = true;
					errors = errors + 1;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_BIOME_RADIATION_DEFINITION.get()) {
			RadioactiveModVariables.MapVariables.get(world).errored = false;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			if (!stringiterator.contains("=")) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74Error in Biome Radiation Definitions"), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: No '=' present"), false);
				RadioactiveModVariables.local_errored = true;
				errors = errors + 1;
			}
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1))) == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A74Error in Biome Radiation Definitions"), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7cIn entry \"" + "" + stringiterator + "\"")), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cSyntax Error: Radiation is not a number, or is zero"), false);
					RadioactiveModVariables.local_errored = true;
					errors = errors + 1;
				}
			}
		}
		RadioactiveModVariables.MapVariables.get(world).errored = errors > 0;
		RadioactiveModVariables.MapVariables.get(world).syncData(world);
		RadioactiveModVariables.local_errored = RadioactiveModVariables.MapVariables.get(world).errored;
		if (RadioactiveModVariables.MapVariables.get(world).errored) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A74Found " + new java.text.DecimalFormat("##").format(errors) + " errors")), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A74Fix errors before playing with Radioactive"), false);
		}
		return !RadioactiveModVariables.MapVariables.get(world).errored;
	}
}
