package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.radioactive.network.RadioactiveModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DamageTypeRadiationProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource(), event.getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity, double amount) {
		execute(null, world, damagesource, entity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity, double amount) {
		if (damagesource == null || entity == null)
			return;
		double current_rad_id = 0;
		if (RadioactiveModVariables.MapVariables.get(world).loaded__enable_rad_damage) {
			if (damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("forge:radiation_damage")))) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				} else if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
				IrradiateProcedure.execute(world, entity, amount);
			}
		}
		if (RadioactiveModVariables.MapVariables.get(world).loaded__enable_unrad_damage) {
			if (damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("forge:radiation_remove_damage")))) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				} else if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
				DecontaminateProcedure.execute(world, entity, amount);
			}
		}
	}
}
