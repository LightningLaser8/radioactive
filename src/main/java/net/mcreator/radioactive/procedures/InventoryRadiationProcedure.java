package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber
public class InventoryRadiationProcedure {
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
		double amount = 0;
		if (!world.isClientSide()) {
			if (!RadioactiveModVariables.MapVariables.get(world).errored) {
				if (entity instanceof LivingEntity) {
					if (RadioactiveCFGConfiguration.OLD_RADIATION.get()) {
						if (RadioactiveCFGConfiguration.INVENTORY_RADIATION.get()) {
							if (entity instanceof Player) {
								if (RadioactiveModVariables.MapVariables.get(world).rad_tick >= 20) {
									total_radiation = 0;
									current_rad_id = 0;
									{
										AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
										entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
										if (_iitemhandlerref.get() != null) {
											for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
												ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
												if (itemstackiterator.is(ItemTags.create(new ResourceLocation("forge:radioactive")))) {
													for (int index0 = 0; index0 < 1000; index0++) {
														if (itemstackiterator.is(ItemTags.create(new ResourceLocation((("forge:radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
															total_radiation = total_radiation + current_rad_id * itemstackiterator.getCount();
														}
														current_rad_id = current_rad_id + 1;
													}
												}
											}
										}
									}
									IrradiateProcedure.execute(entity, total_radiation);
									{
										double _setval = total_radiation;
										entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.receiving_inv_rad = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								}
							}
						}
					}
					if (RadioactiveCFGConfiguration.V3.get()) {
						if (RadioactiveCFGConfiguration.V3_INVENTORY_RADIATION.get()) {
							if (entity instanceof Player || !RadioactiveCFGConfiguration.ONLY_PLAYER_RADIATION.get()) {
								total_radiation = 0;
								{
									AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
									entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
									if (_iitemhandlerref.get() != null) {
										for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
											ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
											for (String stringiterator : RadioactiveCFGConfiguration.V3_INVENTORY_RADIATION_DEFINITION.get()) {
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
												if ((id).equals(ForgeRegistries.ITEMS.getKey(itemstackiterator.getItem()).toString())) {
													total_radiation = total_radiation + amount * itemstackiterator.getCount();
												}
											}
										}
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
}
