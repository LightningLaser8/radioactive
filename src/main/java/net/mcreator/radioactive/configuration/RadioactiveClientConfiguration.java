package net.mcreator.radioactive.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class RadioactiveClientConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_TOOLTIPS;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHUT_UP;
	public static final ForgeConfigSpec.ConfigValue<Double> CLICK_RATE;
	public static final ForgeConfigSpec.ConfigValue<Double> CLICK_PITCH;
	public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_SCALE;
	public static final ForgeConfigSpec.ConfigValue<Double> BAR_X;
	public static final ForgeConfigSpec.ConfigValue<Double> BAR_Y;
	public static final ForgeConfigSpec.ConfigValue<Double> BAR_WIDTH;
	public static final ForgeConfigSpec.ConfigValue<Double> BAR_HEIGHT;
	public static final ForgeConfigSpec.ConfigValue<String> BAR_TEX;
	public static final ForgeConfigSpec.ConfigValue<Boolean> RADBAR_BG;
	public static final ForgeConfigSpec.ConfigValue<String> BAR_BG;
	static {
		SHOW_TOOLTIPS = BUILDER.comment("If disabled, extra data from the mod will not show up in item tooltips.").define("Show Item Tooltips", true);
		SHUT_UP = BUILDER.comment("If enabled, radiation counters/detectors will not click when you're being irradiated.").define("Make Counters Shut Up", false);
		CLICK_RATE = BUILDER.comment("Controls how fast counters click. Higher numbers make more clicks.").define("Counter Click Rate", (double) 300);
		CLICK_PITCH = BUILDER.comment("Controls the pitch of counter clicks. Higher numbers mean higher pitch.").define("Counter Click Pitch", (double) 0.8);
		BUILDER.push("Radiation Bar");
		SHOW_SCALE = BUILDER.comment("If disabled, radiation counters will not show the extra overlay in the bottom-left of the screen.").define("Show Radiation Scale", true);
		BAR_X = BUILDER.comment("Controls the horizontal position of the radiation bar, from the left side of the screen.").define("Radiation Bar X Position", (double) 100);
		BAR_Y = BUILDER.comment("Controls the vertical position of the radiation bar, from the top of the screen.").define("Radiation Bar Y Position", (double) 0);
		BAR_WIDTH = BUILDER.comment("Controls the visual horizontal scale of the radiation bar. The actual width will be the texture width multiplied by this number.").define("Radiation Bar Width", (double) 1);
		BAR_HEIGHT = BUILDER.comment("Controls the visual height of the radiation bar. The actual height will be the texture height multiplied by this number.").define("Radiation Bar Height", (double) 1);
		BAR_TEX = BUILDER.comment("Sets the texture of the radiation bar.").define("Radiation Bar Texture", "radioactive:textures/radbar.png");
		RADBAR_BG = BUILDER.comment("If disabled, the radiation bar's background will not show.").define("Show Background", true);
		BAR_BG = BUILDER.comment("Sets the texture of the radiation bar's background. Should be the same size as the radiation bar itself.").define("Radiation Bar Background Texture", "radioactive:textures/radbar_bg.png");
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
