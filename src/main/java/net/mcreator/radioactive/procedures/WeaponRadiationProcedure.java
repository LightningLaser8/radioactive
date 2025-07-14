package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class WeaponRadiationProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		double current_range_id = 0;
		double total_range = 0;
		if (RadioactiveCFGConfiguration.OLD_RADIATION.get()) {
			if (RadioactiveCFGConfiguration.WEAPON_RADIATION.get()) {
				total_radiation = 0;
				current_rad_id = 0;
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:weapon_radioactive")))) {
					for (int index0 = 0; index0 < 1000; index0++) {
						if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
								.is(ItemTags.create(new ResourceLocation((("forge:weapon_radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
							total_radiation = total_radiation + current_rad_id * (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount();
						}
						current_rad_id = current_rad_id + 1;
					}
				}
				{
					double _setval = (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation
							+ total_radiation * (1 - (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).radiation_resistance);
					entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.received_radiation = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
