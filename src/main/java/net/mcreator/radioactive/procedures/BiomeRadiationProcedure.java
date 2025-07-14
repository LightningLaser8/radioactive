package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BiomeRadiationProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState block_chosen = Blocks.AIR.defaultBlockState();
		boolean blocked = false;
		double total_radiation = 0;
		double current_rad_id = 0;
		double current_range_id = 0;
		double total_range = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double all_radiation = 0;
		double amount = 0;
		String id = "";
		if (!world.isClientSide()) {
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (entity instanceof Player || !RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get()) {
					if (RadioactiveCFGConfiguration.V3.get()) {
						if (RadioactiveCFGConfiguration.V3_BIOME_RADIATION.get()) {
							total_radiation = 0;
							for (String stringiterator : RadioactiveCFGConfiguration.V3_BIOME_RADIATION_DEFINITION.get()) {
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
								if (world.hasChunkAt(BlockPos.containing(x, y, z)) == world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation(id))) {
									total_radiation = total_radiation + amount;
								}
							}
							IrradiateProcedure.execute(entity, total_radiation);
						}
					}
				}
			}
		}
	}
}
