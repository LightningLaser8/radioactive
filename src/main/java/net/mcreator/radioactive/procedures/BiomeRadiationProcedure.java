package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.core.registries.Registries;

import net.mcreator.radioactive.network.RadioactiveModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BiomeRadiationProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getY(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double y, Entity entity) {
		execute(null, world, y, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double y, Entity entity) {
		if (entity == null)
			return;
		String biome = "";
		CompoundTag entry;
		if (!world.isClientSide()) {
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (entity instanceof Player || !RadioactiveModVariables.MapVariables.get(world).loaded__opr) {
					if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3) {
						if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_biome) {
							biome = entity.level().registryAccess().registryOrThrow(Registries.BIOME).getKey(entity.level().getBiome(entity.blockPosition()).value()).toString();
							if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__biome.contains(biome)) {
								entry = (RadioactiveModVariables.MapVariables.get(world).v3_loaded__biome.get(biome)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
								if (y > (((entry.get("is_restricted")) instanceof ByteTag _byteTag ? _byteTag.getAsByte() == 1 : false) ? ((entry.get("level")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D) : -64)) {
									IrradiateProcedure.execute(world, entity, (entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D);
								}
							}
						}
					}
				}
			}
		}
	}
}
