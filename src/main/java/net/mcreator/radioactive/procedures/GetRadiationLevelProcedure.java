package net.mcreator.radioactive.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.radioactive.network.RadioactiveModVariables;

public class GetRadiationLevelProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double rps = 0;
		rps = (entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).rads_per_sec;
		return rps > 0
				? new java.text.DecimalFormat("##.#").format((entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation) + " RAD, "
						+ new java.text.DecimalFormat("##.#").format(rps) + " RAD/s"
				: new java.text.DecimalFormat("##.#").format((entity.getCapability(RadioactiveModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadioactiveModVariables.PlayerVariables())).received_radiation) + " RAD";
	}
}
