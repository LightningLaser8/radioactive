package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveClientConfiguration;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class AutoTagSystemProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		Entity e = null;
		if (RadioactiveModVariables.local_errored) {
			tooltip.add(Component.literal("\u00A74Radioactive has errors"));
		} else {
			e = entity;
			if (RadioactiveClientConfiguration.SHOW_TOOLTIPS.get()) {
				if (RadioactiveCFGConfiguration.OLD_RADIATION.get()) {
					InventoryTagSystemProcedure.execute(itemstack, tooltip);
					ProximityTagSystemProcedure.execute(itemstack, tooltip);
					BlockTagSystemProcedure.execute(itemstack, tooltip);
					WeaponTagSystemProcedure.execute(itemstack, tooltip);
					ArmorTagSystemProcedure.execute(itemstack, tooltip);
					DetectorTagSystemProcedure.execute(itemstack, tooltip);
					CounterTagSystemProcedure.execute(itemstack, tooltip);
				}
				if (RadioactiveCFGConfiguration.V3.get()) {
					V3InventoryTagSystemProcedure.execute(e.level(), itemstack, tooltip);
					V3ProximityTagSystemProcedure.execute(e.level(), itemstack, tooltip);
					V3BlockTagSystemProcedure.execute(e.level(), itemstack, tooltip);
					V3ArmorTagSystemProcedure.execute(e.level(), itemstack, tooltip);
					V3CureTagSystemProcedure.execute(e.level(), itemstack, tooltip);
					V3DetectorTagSystemProcedure.execute(e.level(), itemstack, tooltip);
					V3CounterTagSystemProcedure.execute(e.level(), itemstack, tooltip);
				}
			}
		}
	}
}
