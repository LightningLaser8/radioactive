package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;
import net.mcreator.radioactive.RadioactiveMod;

public class SilentValidateV3Procedure {
	public static boolean execute() {
		double errors = 0;
		RadioactiveModVariables.local_errored = false;
		for (String stringiterator : RadioactiveCFGConfiguration.V3_RADIATION_RESISTANCE_DEFINITION.get()) {
			if (!stringiterator.contains("=")) {
				RadioactiveMod.LOGGER.error(("[v3_radiation_resistance_definition] " + "Syntax Error: Missing '=' in \"" + stringiterator + "\""));
				RadioactiveModVariables.local_errored = true;
			}
			if (!RadioactiveModVariables.local_errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1))) == 0) {
					RadioactiveMod.LOGGER.error(("[v3_radiation_resistance_definition] " + "Syntax Error: Radiation is not a number in \"" + stringiterator + "\""));
					RadioactiveModVariables.local_errored = true;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_INVENTORY_RADIATION_DEFINITION.get()) {
			if (!stringiterator.contains("=")) {
				RadioactiveMod.LOGGER.error(("[v3_inventory_radiation_definition] " + "Syntax Error: Missing '=' in \"" + stringiterator + "\""));
				RadioactiveModVariables.local_errored = true;
			}
			if (!RadioactiveModVariables.local_errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1))) == 0) {
					RadioactiveMod.LOGGER.error(("[v3_inventory_radiation_definition] " + "Syntax Error: Radiation is not a number in \"" + stringiterator + "\""));
					RadioactiveModVariables.local_errored = true;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_BLOCK_RADIATION_DEFINITION.get()) {
			if (!stringiterator.contains("=")) {
				RadioactiveMod.LOGGER.error(("[v3_block_radiation_definition] " + "Syntax Error: Missing '=' in \"" + stringiterator + "\""));
				RadioactiveModVariables.local_errored = true;
			}
			if (!stringiterator.contains("~")) {
				RadioactiveMod.LOGGER.error(("[v3_block_radiation_definition] " + "Syntax Error: Missing '~' in \"" + stringiterator + "\""));
				RadioactiveModVariables.local_errored = true;
			}
			if (!RadioactiveModVariables.local_errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("~"))) == 0) {
					RadioactiveMod.LOGGER.error(("[v3_block_radiation_definition] " + "Syntax Error: Radiation is not a number in \"" + stringiterator + "\""));
					RadioactiveModVariables.local_errored = true;
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
					RadioactiveMod.LOGGER.error(("[v3_block_radiation_definition] " + "Syntax Error: Range is not a number in \"" + stringiterator + "\""));
					RadioactiveModVariables.local_errored = true;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_PROXIMITY_RADIATION_DEFINITION.get()) {
			if (!stringiterator.contains("=")) {
				RadioactiveMod.LOGGER.error(("[v3_proximity_radiation_definition] " + "Syntax Error: Missing '=' in \"" + stringiterator + "\""));
				RadioactiveModVariables.local_errored = true;
			}
			if (!stringiterator.contains("~")) {
				RadioactiveMod.LOGGER.error(("[v3_proximity_radiation_definition] " + "Syntax Error: Missing '~' in \"" + stringiterator + "\""));
				RadioactiveModVariables.local_errored = true;
			}
			if (!RadioactiveModVariables.local_errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("~"))) == 0) {
					RadioactiveMod.LOGGER.error(("[v3_proximity_radiation_definition] " + "Syntax Error: Radiation is not a number in \"" + stringiterator + "\""));
					RadioactiveModVariables.local_errored = true;
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
					RadioactiveMod.LOGGER.error(("[v3_proximity_radiation_definition] " + "Syntax Error: Range is not a number in \"" + stringiterator + "\""));
					RadioactiveModVariables.local_errored = true;
				}
			}
		}
		for (String stringiterator : RadioactiveCFGConfiguration.V3_BIOME_RADIATION_DEFINITION.get()) {
			if (!stringiterator.contains("=")) {
				RadioactiveMod.LOGGER.error(("[v3_biome_radiation_definition] " + "Syntax Error: Missing '=' in \"" + stringiterator + "\""));
				RadioactiveModVariables.local_errored = true;
			}
			if (!RadioactiveModVariables.local_errored) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1))) == 0) {
					RadioactiveMod.LOGGER.error(("[v3_biome_radiation_definition] " + "Syntax Error: Radiation is not a number in \"" + stringiterator + "\""));
					RadioactiveModVariables.local_errored = true;
				}
			}
		}
		return !RadioactiveModVariables.local_errored;
	}
}
