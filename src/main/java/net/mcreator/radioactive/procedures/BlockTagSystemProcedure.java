package net.mcreator.radioactive.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.List;

public class BlockTagSystemProcedure {
	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double total_radiation = 0;
		double current_rad_id = 0;
		double current_range_id = 0;
		double total_range = 0;
		if ((itemstack.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()).is(BlockTags.create(new ResourceLocation("forge:block_radioactive")))) {
			total_radiation = 0;
			current_rad_id = 0;
			total_range = 0;
			current_range_id = 0;
			for (int index0 = 0; index0 < 1000; index0++) {
				if ((itemstack.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState())
						.is(BlockTags.create(new ResourceLocation((("forge:block_radioactive_" + new java.text.DecimalFormat("####").format(current_rad_id))).toLowerCase(java.util.Locale.ENGLISH))))) {
					total_radiation = total_radiation + current_rad_id;
				}
				current_rad_id = current_rad_id + 1;
			}
			if (!(total_radiation == 0)) {
				tooltip.add(Component.literal(""));
				tooltip.add(Component.literal("\u00A73[Block Radiation]"));
				tooltip.add(Component.literal((("\u00A7e-> " + new java.text.DecimalFormat("####").format(total_radiation) + " RAD/s") + " ~ 16 blocks")));
			}
		}
	}
}
