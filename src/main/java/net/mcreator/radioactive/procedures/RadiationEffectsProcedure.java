package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.init.RadioactiveModMobEffects;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RadiationEffectsProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double scale = 0;
		double radiation = 0;
		if (entity instanceof LivingEntity) {
			if (entity instanceof Player || !RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get()) {
				if (!IsEntityRadImmuneProcedure.execute(entity)) {
					scale = (double) RadioactiveCFGConfiguration.RADIATION_POISONING_SCALING.get();
					radiation = RadioactiveCFGConfiguration.OLD_RADIATION.get() || RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get()
							? (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation
							: entity.getPersistentData().getDouble("radiation");
					if (radiation >= scale * 10) {
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("radioactive:radiation_dt")))), 10000000);
					} else if (radiation >= scale) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, (int) (Math.floor(radiation / scale) - 1)));
					}
					if (radiation >= scale * 1.5) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, (int) (Math.floor(radiation / scale) - 1)));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, (int) (Math.floor(radiation / scale) - 2)));
					}
					if (radiation >= scale * 2.5) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
					}
					if (radiation >= scale * 3) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(RadioactiveModMobEffects.RADIATION_SICKNESS.get(), 100, (int) (Math.floor(radiation / scale) - 3)));
					}
					if (radiation >= scale * 8) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
					}
				}
			}
		}
	}
}
