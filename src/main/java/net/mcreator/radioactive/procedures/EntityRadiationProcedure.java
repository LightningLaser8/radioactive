package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class EntityRadiationProcedure {
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
		if (!world.isClientSide()) {
			if (RadioactiveCFGConfiguration.OLD_RADIATION.get()) {
				if (RadioactiveCFGConfiguration.ENTITY_RADIATION.get()) {
					if (RadioactiveModVariables.MapVariables.get(world).rad_tick >= 20) {
						total_radiation = 0;
						current_rad_id = 0;
						total_range = 0;
						current_range_id = 0;
						if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge:entity_radioactive")))) {
							for (int index0 = 0; index0 < 1000; index0++) {
								if (entity.getType()
										.is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation((("forge:entity_radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
									total_radiation = total_radiation + current_rad_id;
								}
								current_rad_id = current_rad_id + 1;
							}
							for (int index1 = 0; index1 < 100; index1++) {
								if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation((("forge:entity_range_" + new java.text.DecimalFormat("####").format(current_range_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
									total_range = total_range + current_range_id;
								}
								current_range_id = current_range_id + 1;
							}
						}
						{
							final Vec3 _center = new Vec3(x, y, z);
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((total_range * 2) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
									.toList();
							for (Entity entityiterator : _entfound) {
								if (!(entityiterator == entity)) {
									{
										double _setval = (entityiterator.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation
												+ total_radiation * (1 - (entityiterator.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).radiation_resistance);
										entityiterator.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.received_radiation = _setval;
											capability.syncPlayerVariables(entityiterator);
										});
									}
								}
							}
						}
						entity.getPersistentData().putDouble("entityRadCounter", 0);
					}
				}
			}
		}
	}
}
