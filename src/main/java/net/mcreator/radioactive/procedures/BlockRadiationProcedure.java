package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BlockRadiationProcedure {
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
		boolean blocked = false;
		double total_radiation = 0;
		double current_rad_id = 0;
		double current_range_id = 0;
		double total_range = 0;
		double all_radiation = 0;
		double amount = 0;
		String id = "";
		CompoundTag entry;
		BlockState block_chosen = Blocks.AIR.defaultBlockState();
		if (!world.isClientSide()) {
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (entity instanceof Player || !RadioactiveModVariables.MapVariables.get(world).loaded__opr) {
					if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3) {
						if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_block) {
							for (String keyiterator : RadioactiveModVariables.MapVariables.get(world).v3_loaded__block.getAllKeys()) {
								entry = (RadioactiveModVariables.MapVariables.get(world).v3_loaded__block.get(keyiterator)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag();
								amount = (entry.get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
								total_range = ((entry.get("range")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D) + 1;
								int horizontalRadiusSquare = (int) total_range - 1;
								int verticalRadiusSquare = (int) total_range - 1;
								int yIterationsSquare = verticalRadiusSquare;
								for (int i = -yIterationsSquare; i <= yIterationsSquare; i++) {
									for (int xi = -horizontalRadiusSquare; xi <= horizontalRadiusSquare; xi++) {
										for (int zi = -horizontalRadiusSquare; zi <= horizontalRadiusSquare; zi++) {
											// Execute the desired statements within the square/cube
											if ((ForgeRegistries.BLOCKS.getKey((world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).getBlock()).toString()).equals(keyiterator)) {
												IrradiateProcedure.execute(world, entity, amount);
											}
										}
									}
								}
							}
						}
					}
					if (RadioactiveModVariables.MapVariables.get(world).loaded__old_sys) {
						if (RadioactiveCFGConfiguration.BLOCK_RADIATION.get()) {
							if (RadioactiveModVariables.MapVariables.get(world).rad_tick == 1) {
								entity.getPersistentData().putDouble("pitchOld", (entity.getXRot()));
								entity.getPersistentData().putDouble("yawOld", (entity.getYRot()));
								current_range_id = 16;
								all_radiation = 0;
								total_range = (double) RadioactiveCFGConfiguration.BLOCKRAD_RANGE.get();
								int horizontalRadiusSquare = (int) (2 * total_range) - 1;
								int verticalRadiusSquare = (int) (2 * total_range) - 1;
								int yIterationsSquare = verticalRadiusSquare;
								for (int i = -yIterationsSquare; i <= yIterationsSquare; i++) {
									for (int xi = -horizontalRadiusSquare; xi <= horizontalRadiusSquare; xi++) {
										for (int zi = -horizontalRadiusSquare; zi <= horizontalRadiusSquare; zi++) {
											// Execute the desired statements within the square/cube
											block_chosen = (world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi)));
											if (block_chosen.is(BlockTags.create(new ResourceLocation("forge:block_radioactive")))) {
												total_radiation = 0;
												current_rad_id = 0;
												for (int index0 = 0; index0 < 1000; index0++) {
													if (block_chosen.is(BlockTags.create(new ResourceLocation((("forge:block_radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
														total_radiation = total_radiation + current_rad_id;
													}
													current_rad_id = current_rad_id + 1;
												}
												IrradiateProcedure.execute(world, entity, total_radiation);
												all_radiation = all_radiation
														+ total_radiation * (1 - (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).radiation_resistance);
											}
										}
									}
								}
								{
									double _setval = all_radiation;
									entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.receiving_block_rad = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
							}
						}
					}
				}
			}
		}
	}
}
