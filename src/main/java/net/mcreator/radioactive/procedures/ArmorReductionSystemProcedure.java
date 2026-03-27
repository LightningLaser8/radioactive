package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.radioactive.network.RadioactiveModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ArmorReductionSystemProcedure {
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
		String id = "";
		double total_radiation = 0;
		double current_rad_id = 0;
		double total_protect = 0;
		double amount = 0;
		if (entity instanceof LivingEntity) {
			total_protect = Math.min(100, Math.max(RadioactiveModVariables.MapVariables.get(world).loaded__base_res, 0));
			if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__is_v3) {
				if (entity instanceof Player || !RadioactiveModVariables.MapVariables.get(world).loaded__opr) {
					total_protect = total_protect + GetRadiationProtectionOfProcedure.execute(world, entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
							+ GetRadiationProtectionOfProcedure.execute(world, entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
							+ GetRadiationProtectionOfProcedure.execute(world, entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
							+ GetRadiationProtectionOfProcedure.execute(world, entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY);
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
			total_protect = Math.min(1, Math.max(Math.min(RadioactiveModVariables.MapVariables.get(world).loaded__max_res / 100, Math.max(total_protect * RadioactiveModVariables.MapVariables.get(world).loaded__res_mult, 0)), 0));
			if (RadioactiveModVariables.MapVariables.get(world).loaded__old_sys || RadioactiveModVariables.MapVariables.get(world).loaded__opr) {
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
