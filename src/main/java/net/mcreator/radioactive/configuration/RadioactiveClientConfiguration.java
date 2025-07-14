package net.mcreator.radioactive.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class RadioactiveClientConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_TOOLTIPS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_SCALE;
	static {
		SHOW_TOOLTIPS = BUILDER.comment("If disabled, extra data from the mod will not show up in item tooltips.").define("Show Item Tooltips", true);
		SHOW_SCALE = BUILDER.comment("If disabled, radiation counters will not show the extra overlay in the bottom-left of the screen.").define("Show Radiation Scale", true);

		SPEC = BUILDER.build();
	}

}
