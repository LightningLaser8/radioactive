package net.mcreator.radioactive.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
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
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		if (RadioactiveModVariables.local_errored) {
			tooltip.add(Component.literal("\u00A74Radioactive has errors"));
		} else {
			if (RadioactiveClientConfiguration.SHOW_TOOLTIPS.get()) {
				if (RadioactiveCFGConfiguration.OLD_RADIATION.get()) {
					InventoryTagSystemProcedure.execute(itemstack, tooltip);
					ProximityTagSystemProcedure.execute(itemstack, tooltip);
					BlockTagSystemProcedure.execute(itemstack, tooltip);
					WeaponTagSystemProcedure.execute(itemstack, tooltip);
					ArmorTagSystemProcedure.execute(itemstack, tooltip);
				}
				if (RadioactiveCFGConfiguration.V3.get()) {
					V3InventoryTagSystemProcedure.execute(itemstack, tooltip);
					V3ProximityTagSystemProcedure.execute(itemstack, tooltip);
					V3BlockTagSystemProcedure.execute(itemstack, tooltip);
					V3ArmorTagSystemProcedure.execute(itemstack, tooltip);
					V3CureTagSystemProcedure.execute(itemstack, tooltip);
				}
				DetectorTagSystemProcedure.execute(itemstack, tooltip);
				CounterTagSystemProcedure.execute(itemstack, tooltip);
			}
		}
	}
}
