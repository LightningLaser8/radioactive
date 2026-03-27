package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.core.BlockPos;

import net.mcreator.radioactive.network.RadioactiveModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CurativeItemUseProcedure {
	@SubscribeEvent
	public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getItem());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		execute(null, world, x, y, z, entity, itemstack);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		String id = "";
		double amount = 0;
		boolean final_percent = false;
		boolean is_auto_use = false;
		if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_cure) {
			if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.contains((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) {
				amount = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("amount")) instanceof DoubleTag _doubleTag ? _doubleTag.getAsDouble() : 0.0D;
				final_percent = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("is_percent")) instanceof ByteTag _byteTag ? _byteTag.getAsByte() == 1 : false;
				is_auto_use = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()))) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag())
						.get("is_auto")) instanceof ByteTag _byteTag ? _byteTag.getAsByte() == 1 : false;
			}
			if (!(amount == 0)) {
				if (final_percent) {
					DecontaminateProcedure.execute(world, entity, (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation * (amount / 100));
				} else {
					DecontaminateProcedure.execute(world, entity, amount);
				}
				if (!RadioactiveModVariables.MapVariables.get(world).v3_loaded__shut_fuk) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.cure")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.cure")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
				}
				if (is_auto_use) {
					itemstack.shrink(1);
				}
			}
		}
	}
}
