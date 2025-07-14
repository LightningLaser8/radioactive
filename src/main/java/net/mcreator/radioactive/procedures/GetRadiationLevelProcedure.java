package net.mcreator.radioactive.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class GetRadiationLevelProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double radiation = 0;
		double scale = 0;
		return new java.text.DecimalFormat("##.#").format((entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation) + " RAD";
	}
}
