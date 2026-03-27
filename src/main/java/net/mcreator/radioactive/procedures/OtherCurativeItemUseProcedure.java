package net.mcreator.radioactive.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
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
public class OtherCurativeItemUseProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double amount = 0;
		String id = "";
		ItemStack item = ItemStack.EMPTY;
		boolean is_percent = false;
		boolean spd = false;
		boolean final_percent = false;
		boolean is_auto_use = false;
		if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__enabled_cure) {
			item = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
			id = ForgeRegistries.ITEMS.getKey(item.getItem()).toString();
			if (RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.contains(id)) {
				spd = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag()).get("is_speed")) instanceof ByteTag _byteTag
						? _byteTag.getAsByte() == 1
						: false;
				if (spd) {
					amount = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag()).get("amount")) instanceof DoubleTag _doubleTag
							? _doubleTag.getAsDouble()
							: 0.0D;
					final_percent = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag()).get("is_percent")) instanceof ByteTag _byteTag
							? _byteTag.getAsByte() == 1
							: false;
					is_auto_use = (((RadioactiveModVariables.MapVariables.get(world).v3_loaded__cure.get(id)) instanceof CompoundTag _compoundTag ? _compoundTag.copy() : new CompoundTag()).get("is_auto")) instanceof ByteTag _byteTag
							? _byteTag.getAsByte() == 1
							: false;
					if (is_auto_use) {
						item.shrink(1);
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
					}
				}
			}
		}
	}
}
