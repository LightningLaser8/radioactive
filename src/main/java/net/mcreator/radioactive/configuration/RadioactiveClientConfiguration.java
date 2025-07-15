package net.mcreator.radioactive.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class RadioactiveClientConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_TOOLTIPS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_SCALE;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHUT_UP;
	public static final ForgeConfigSpec.ConfigValue<Double> CLICK_RATE;
	public static final ForgeConfigSpec.ConfigValue<Double> CLICK_PITCH;
	static {
		SHOW_TOOLTIPS = BUILDER.comment("If disabled, extra data from the mod will not show up in item tooltips.").define("Show Item Tooltips", true);
		SHOW_SCALE = BUILDER.comment("If disabled, radiation counters will not show the extra overlay in the bottom-left of the screen.").define("Show Radiation Scale", true);
		SHUT_UP = BUILDER.comment("If enabled, radiation counters/detectors will not click when you're being irradiated.").define("Make Counters Shut Up", false);
		CLICK_RATE = BUILDER.comment("Controls how fast counters click. Higher numbers make more clicks.").define("Counter Click Rate", (double) 300);
		CLICK_PITCH = BUILDER.comment("Controls the pitch of counter clicks. Higher numbers mean higher pitch.").define("Counter Click Pitch", (double) 0.8);

		SPEC = BUILDER.build();
	}

}
