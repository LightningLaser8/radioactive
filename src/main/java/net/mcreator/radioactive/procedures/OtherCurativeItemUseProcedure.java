package net.mcreator.radioactive.procedures;

import org.checkerframework.checker.units.qual.s;

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
import net.minecraft.core.BlockPos;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.mcreator.radioactive.configuration.RadioactiveCFGConfiguration;

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
		double total_protect = 0;
		String id = "";
		ItemStack item = ItemStack.EMPTY;
		boolean is_percent = false;
		boolean spd = false;
		boolean final_percent = false;
		if (RadioactiveCFGConfiguration.V3_CURES.get()) {
			item = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
			for (String stringiterator : RadioactiveCFGConfiguration.V3_SPEED_CURE.get()) {
				if ((stringiterator).equals(ForgeRegistries.ITEMS.getKey(item.getItem()).toString())) {
					spd = true;
					break;
				}
			}
			if (spd) {
				for (String stringiterator : RadioactiveCFGConfiguration.V3_AUTO_CURE.get()) {
					if ((stringiterator).equals(ForgeRegistries.ITEMS.getKey(item.getItem()).toString())) {
						item.shrink(1);
						break;
					}
				}
				for (String stringiterator : RadioactiveCFGConfiguration.V3_CURE_DEFINITION.get()) {
					id = stringiterator.substring(0, (int) stringiterator.indexOf("="));
					is_percent = stringiterator.contains("%");
					amount = is_percent ? new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1), (int) stringiterator.indexOf("%"))) : new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(stringiterator.substring((int) (stringiterator.indexOf("=") + 1)));
					if ((id).equals(ForgeRegistries.ITEMS.getKey(item.getItem()).toString())) {
						final_percent = is_percent;
						total_protect = total_protect + amount;
					}
				}
				if (!(total_protect == 0)) {
					if (final_percent) {
						DecontaminateProcedure.execute(entity, (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation * (total_protect / 100));
					} else {
						DecontaminateProcedure.execute(entity, total_protect);
					}
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
