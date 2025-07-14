
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.radioactive.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.radioactive.block.TestBlockBlock;
import net.mcreator.radioactive.RadioactiveMod;

public class RadioactiveModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, RadioactiveMod.MODID);
	public static final RegistryObject<Block> TEST_BLOCK = REGISTRY.register("test_block", () -> new TestBlockBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
