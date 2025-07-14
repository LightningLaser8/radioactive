package net.mcreator.radioactive.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class ForceIrradiateEnemiesProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "targets")) {
				IrradiateProcedure.execute(entityiterator, DoubleArgumentType.getDouble(arguments, "amount"));
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}
