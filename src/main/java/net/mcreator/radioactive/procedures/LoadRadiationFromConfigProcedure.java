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
		// Only do this server-side, for best performance.
		if (!world.isClientSide()) {// Instead of iterating everything every single frame, tick and inventory slot:
			// just put it in a hash table once, and do O(n) lookups each frame/tick/slot.
			// This is the biggest performance improvement i could think of.
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
				entry.put("prot", DoubleTag.valueOf((amount + ((entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D))));
				grabbed.put(id, entry);
			}
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__prot = grabbed;
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
			RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure = grabbed;
			RadioactiveModVariables.MapVariables.get(world).syncData(world);
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
		}
	}
}
