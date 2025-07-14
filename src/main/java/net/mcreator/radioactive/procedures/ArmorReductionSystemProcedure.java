package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ArmorReductionSystemProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		double total_protect = 0;
		double amount = 0;
		String id = "";
		if (entity instanceof LivingEntity) {
			total_protect = Math.min(100, Math.max((double) RadioactiveCFGConfiguration.BASE_RESISTANCE.get(), 0));
			if (RadioactiveCFGConfiguration.V3.get()) {
				if (entity instanceof Player || !RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get()) {
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
						if ((id).equals(ForgeRegistries.ITEMS.getKey((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem()).toString())) {
							total_protect = total_protect + amount;
						}
						if ((id).equals(ForgeRegistries.ITEMS.getKey((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem()).toString())) {
							total_protect = total_protect + amount;
						}
						if ((id).equals(ForgeRegistries.ITEMS.getKey((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem()).toString())) {
							total_protect = total_protect + amount;
						}
						if ((id).equals(ForgeRegistries.ITEMS.getKey((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem()).toString())) {
							total_protect = total_protect + amount;
						}
					}
				}
				total_protect = total_protect / 100;
			} else {
				total_protect = total_protect / 100;
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:radiation_protect")))) {
					current_rad_id = 1;
					total_radiation = 0;
					for (int index0 = 0; index0 < 100; index0++) {
						if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
								.is(ItemTags.create(new ResourceLocation((("forge:radiation_protect_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
							total_radiation = total_radiation + current_rad_id;
						}
						current_rad_id = current_rad_id + 1;
					}
					if (!(total_radiation == 0)) {
						total_protect = total_protect + total_radiation / 100;
					}
				}
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:radiation_protect")))) {
					current_rad_id = 1;
					total_radiation = 0;
					for (int index1 = 0; index1 < 100; index1++) {
						if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
								.is(ItemTags.create(new ResourceLocation((("forge:radiation_protect_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
							total_radiation = total_radiation + current_rad_id;
						}
						current_rad_id = current_rad_id + 1;
					}
					if (!(total_radiation == 0)) {
						total_protect = total_protect + total_radiation / 100;
					}
				}
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:radiation_protect")))) {
					current_rad_id = 1;
					total_radiation = 0;
					for (int index2 = 0; index2 < 100; index2++) {
						if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
								.is(ItemTags.create(new ResourceLocation((("forge:radiation_protect_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
							total_radiation = total_radiation + current_rad_id;
						}
						current_rad_id = current_rad_id + 1;
					}
					if (!(total_radiation == 0)) {
						total_protect = total_protect + total_radiation / 100;
					}
				}
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:radiation_protect")))) {
					current_rad_id = 1;
					total_radiation = 0;
					for (int index3 = 0; index3 < 100; index3++) {
						if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
								.is(ItemTags.create(new ResourceLocation((("forge:radiation_protect_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
							total_radiation = total_radiation + current_rad_id;
						}
						current_rad_id = current_rad_id + 1;
					}
					if (!(total_radiation == 0)) {
						total_protect = total_protect + total_radiation / 100;
					}
				}
			}
			total_protect = Math.min(1, Math.max(Math.min((double) RadioactiveCFGConfiguration.MAX_RESISTANCE.get() / 100, Math.max(total_protect * (double) RadioactiveCFGConfiguration.RESISTANCE_MULTIPLIER.get(), 0)), 0));
			if (RadioactiveCFGConfiguration.OLD_RADIATION.get() || RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get()) {
				{
					double _setval = total_protect;
					entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.radiation_resistance = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				entity.getPersistentData().putDouble("rad_resistance", total_protect);
			}
		}
	}
}
