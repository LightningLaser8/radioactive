package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ByteTag;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveClientConfiguration;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class LoadRadiationFromConfigProcedure {
	@SubscribeEvent
	public static void onWorldLoad(net.minecraftforge.event.level.LevelEvent.Load event) {
		execute(event, event.getLevel());
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		CompoundTag grabbed;
		CompoundTag entry;
		String id = "";
		double total_radiation = 0;
		double amount = 0;
		double total_range = 0;
		double total_protect = 0;
		boolean is_percent = false;
		boolean final_percent = false;
		ListTag grabbedlist;
		if (!world.isClientSide()) {// Only do this server-side, for best performance.
			// Instead of iterating everything every single frame, tick and inventory slot:
			// just put it in a hash table once, and do O(n) lookups each frame/tick/slot.
			// This is the biggest performance improvement i could think of.
			// Inventory Radiation
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_inv = RadioactiveCFGConfiguration.V3_INVENTORY_RADIATION.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_INVENTORY_RADIATION_DEFINITION.get()) {
				id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
				amount = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1)));
				entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
				entry.put("rads", DoubleTag.valueOf((amount + ((entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				grabbed.put(id, entry);
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__inv = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Proximity Radiation
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_prox = RadioactiveCFGConfiguration.V3_PROXIMITY_RADIATION.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_PROXIMITY_RADIATION_DEFINITION.get()) {
				id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
				amount = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) stringiterator.indexOf("=") + "=".length(), (int) stringiterator.indexOf("~")));
				total_range = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("~") + 1)));
				entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
				entry.put("rads", DoubleTag.valueOf((amount + ((entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				entry.put("range", DoubleTag.valueOf((total_range + ((entry.get("range")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				grabbed.put(id, entry);
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__prox = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Block Radiation
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_block = RadioactiveCFGConfiguration.V3_BLOCK_RADIATION.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_BLOCK_RADIATION_DEFINITION.get()) {
				id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
				amount = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) stringiterator.indexOf("=") + "=".length(), (int) stringiterator.indexOf("~")));
				total_range = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("~") + 1)));
				entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
				entry.put("rads", DoubleTag.valueOf((amount + ((entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				entry.put("range", DoubleTag.valueOf((total_range + ((entry.get("range")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				grabbed.put(id, entry);
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__block = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Biome Radiation
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_biome = RadioactiveCFGConfiguration.V3_BIOME_RADIATION.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_BIOME_RADIATION_DEFINITION.get()) {
				if (stringiterator.contains("|")) {
					id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
					amount = new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("|")));
					total_range = new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(stringiterator.substring((int) (stringiterator.indexOf("|") + 1)));
					entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
					entry.put("rads", DoubleTag.valueOf((amount + ((entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
					entry.put("level", DoubleTag.valueOf(total_range));
					entry.put("is_restricted", ByteTag.valueOf(true));
					grabbed.put(id, entry);
				} else {
					id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
					amount = new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1)));
					entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
					entry.put("rads", DoubleTag.valueOf((amount + ((entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
					entry.put("is_restricted", ByteTag.valueOf(false));
					grabbed.put(id, entry);
				}
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__biome = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Radiation Protection
			RadioactiveModVariables.MapVariables.get(world).loaded__base_res = (double) RadioactiveCFGConfiguration.BASE_RESISTANCE.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__max_res = (double) RadioactiveCFGConfiguration.MAX_RESISTANCE.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__res_mult = (double) RadioactiveCFGConfiguration.RESISTANCE_MULTIPLIER.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_RADIATION_RESISTANCE_DEFINITION.get()) {
				id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
				amount = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1)));
				entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
				entry.put("prot", DoubleTag.valueOf((amount + ((entry.get("prot")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				grabbed.put(id, entry);
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__prot = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Radiation Cures
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_cure = RadioactiveCFGConfiguration.V3_CURES.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__shut_fuk = RadioactiveCFGConfiguration.SHUT_UP_CURES.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_CURE_DEFINITION.get()) {
				id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
				is_percent = stringiterator.contains("%");
				amount = is_percent ? new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("%"))) : new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1)));
				entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
				entry.put("amount", DoubleTag.valueOf((amount + ((entry.get("amount")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				entry.put("is_percent", ByteTag.valueOf(is_percent));
				grabbed.put(id, entry);
			}
			for (String stringiterator : RadioactiveCFGConfiguration.V3_SPEED_CURE.get()) {
				if (grabbed.contains(stringiterator)) {
					entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
					entry.put("is_speed", ByteTag.valueOf(true));
					grabbed.put(id, entry);
				}
			}
			for (String stringiterator : RadioactiveCFGConfiguration.V3_AUTO_CURE.get()) {
				if (grabbed.contains(stringiterator)) {
					entry = (grabbed.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
					entry.put("is_auto", ByteTag.valueOf(true));
					grabbed.put(id, entry);
				}
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Radiation Counters/Detectors
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_COUNTER_DEFINITION.get()) {
				grabbed.put(stringiterator, IntTag.valueOf(0));
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__count = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_DETECTOR_DEFINITION.get()) {
				grabbed.put(stringiterator, IntTag.valueOf(0));
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__detec = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Radiation Immunity
			grabbed = new CompoundTag();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_RADIMMUNITY.get()) {
				grabbed.put(stringiterator, IntTag.valueOf(0));
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__radimmunity = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			// Other data
			RadioactiveModVariables.MapVariables.get(world).loaded__old_sys = RadioactiveCFGConfiguration.OLD_RADIATION.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__opr = RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__radmult = (double) RadioactiveCFGConfiguration.RADIATION_MULTIPLIER.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__deconmult = (double) RadioactiveCFGConfiguration.DECON_MULTIPLIER.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__effectmult = (double) RadioactiveCFGConfiguration.RADIATION_POISONING_SCALING.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__sickmult = (double) RadioactiveCFGConfiguration.RADIATION_SICKNESS_SCALING.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__enable_rad_damage = RadioactiveCFGConfiguration.IRRADIATION_DAMAGE.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__enable_unrad_damage = RadioactiveCFGConfiguration.DECONTAMINATION_DAMAGE.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).loaded__resist_decon = RadioactiveCFGConfiguration.RESISTS_DECON.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3 = RadioactiveCFGConfiguration.V3.get();
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
		} else {// Client config stuff.
			RadioactiveModVariables.loaded__bar_width = (double) RadioactiveClientConfiguration.BAR_WIDTH.get();
			RadioactiveModVariables.loaded__bar_height = (double) RadioactiveClientConfiguration.BAR_HEIGHT.get();
			RadioactiveModVariables.loaded__bar_x = (double) RadioactiveClientConfiguration.BAR_X.get();
			RadioactiveModVariables.loaded__bar_y = (double) RadioactiveClientConfiguration.BAR_Y.get();
			RadioactiveModVariables.loaded__click_rate = (double) RadioactiveClientConfiguration.CLICK_RATE.get();
			RadioactiveModVariables.loaded__click_pitch = (double) RadioactiveClientConfiguration.CLICK_PITCH.get();
			RadioactiveModVariables.loaded__shut_up_counters = RadioactiveClientConfiguration.SHUT_UP.get();
			RadioactiveModVariables.loaded__bar_enabled = RadioactiveClientConfiguration.SHOW_SCALE.get();
			RadioactiveModVariables.loaded__show_tooltips = RadioactiveClientConfiguration.SHOW_TOOLTIPS.get();
			RadioactiveModVariables.loaded__radbar_bg = RadioactiveClientConfiguration.RADBAR_BG.get();
			RadioactiveModVariables.loaded__radbar_texture = RadioactiveClientConfiguration.BAR_TEX.get();
			RadioactiveModVariables.loaded__radbar_bg_texture = RadioactiveClientConfiguration.BAR_BG.get();
		}
	}
}
