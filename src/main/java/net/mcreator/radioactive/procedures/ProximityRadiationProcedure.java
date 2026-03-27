package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class ProximityRadiationProcedure {
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
		double total_radiation = 0;
		double current_rad_id = 0;
		double current_range_id = 0;
		double total_range = 0;
		double amount = 0;
		String id = "";
		if (!(RadioactiveModVariables.MapVariables.get(world).errored || world.isClientSide())) {
			if (entity instanceof Player || !RadioactiveModVariables.MapVariables.get(world).loaded__opr) {
				if (RadioactiveModVariables.MapVariables.get(world).loaded__old_sys) {
					if (RadioactiveCFGConfiguration.PROXIMITY_RADIATION.get()) {
						if (RadioactiveModVariables.MapVariables.get(world).rad_tick == 1) {
							total_radiation = 0;
							current_rad_id = 0;
							total_range = 0;
							current_range_id = 0;
							{
								AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
								entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
								if (_iitemhandlerref.get() != null) {
									for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
										ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
										if (itemstackiterator.is(ItemTags.create(new ResourceLocation("forge:proximity_radioactive")))) {
											for (int index0 = 0; index0 < 1000; index0++) {
												if (itemstackiterator.is(ItemTags.create(new ResourceLocation((("forge:proximity_radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
													total_radiation = total_radiation + current_rad_id * itemstackiterator.getCount();
												}
												current_rad_id = current_rad_id + 1;
											}
											for (int index1 = 0; index1 < 100; index1++) {
												if (itemstackiterator.is(ItemTags.create(new ResourceLocation((("forge:proximity_range_" + new java.text.DecimalFormat("####").format(current_range_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
													total_range = total_range + current_range_id;
												}
												current_range_id = current_range_id + 1;
											}
										}
									}
								}
							}
							{
								final Vec3 _center = new Vec3(x, y, z);
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((total_range * 2) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (entityiterator instanceof LivingEntity) {
										if (!(entityiterator == entity)) {
											IrradiateProcedure.execute(world, entityiterator, total_radiation);
										}
									}
								}
							}
							entity.getPersistentData().putDouble("proxiRadCounter", 0);
						}
					}
				}
				if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3) {
					if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_prox) {
						{
							AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
							if (_iitemhandlerref.get() != null) {
								for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
									ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
									if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__prox.contains((ForgeRegistries.ITEMS.getKey(itemstackiterator.getItem()).toString()))) {
										total_range = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__prox.get((ForgeRegistries.ITEMS.getKey(itemstackiterator.getItem()).toString()))) instanceof CompoundTag _compoundTag
												? _compoundTag.copy()
												: new CompoundTag()).get("range")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
										total_radiation = itemstackiterator.getCount()
												* ((((RadioactiveModVariables.MapVariables.get(world).v3_loaded__prox.get((ForgeRegistries.ITEMS.getKey(itemstackiterator.getItem()).toString()))) instanceof CompoundTag _compoundTag
														? _compoundTag.copy()
														: new CompoundTag()).get("rads")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D);
										{
											final Vec3 _center = new Vec3(x, y, z);
											List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((total_range * 2) / 2d), e -> true).stream()
													.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
											for (Entity entityiterator : _entfound) {
												if (entityiterator instanceof LivingEntity) {
													if (!(entityiterator == entity)) {
														IrradiateProcedure.execute(world, entityiterator, total_radiation);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
