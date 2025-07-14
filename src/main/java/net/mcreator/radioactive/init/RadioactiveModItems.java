
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.radioactive.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.radioactive.item.TestProtItem;
import net.mcreator.radioactive.item.TestItem;
import net.mcreator.radioactive.item.CounterItem;
import net.mcreator.radioactive.RadioactiveMod;

public class RadioactiveModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, RadioactiveMod.MODID);
	public static final RegistryObject<Item> TEST = REGISTRY.register("test", () -> new TestItem());
	public static final RegistryObject<Item> COUNTER = REGISTRY.register("counter", () -> new CounterItem());
	public static final RegistryObject<Item> TEST_PROT_HELMET = REGISTRY.register("test_prot_helmet", () -> new TestProtItem.Helmet());
	public static final RegistryObject<Item> TEST_PROT_CHESTPLATE = REGISTRY.register("test_prot_chestplate", () -> new TestProtItem.Chestplate());
	public static final RegistryObject<Item> TEST_PROT_LEGGINGS = REGISTRY.register("test_prot_leggings", () -> new TestProtItem.Leggings());
	public static final RegistryObject<Item> TEST_PROT_BOOTS = REGISTRY.register("test_prot_boots", () -> new TestProtItem.Boots());
	public static final RegistryObject<Item> TEST_BLOCK = block(RadioactiveModBlocks.TEST_BLOCK);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
