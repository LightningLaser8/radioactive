package net.mcreator.radioactive.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class RadioactiveCFGConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_MESSAGE;
	public static final ForgeConfigSpec.ConfigValue<Boolean> OLD_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<Double> RADIATION_MULTIPLIER;
	public static final ForgeConfigSpec.ConfigValue<Double> DECON_MULTIPLIER;
	public static final ForgeConfigSpec.ConfigValue<Double> RADIATION_POISONING_SCALING;
	public static final ForgeConfigSpec.ConfigValue<Double> RADIATION_SICKNESS_SCALING;
	public static final ForgeConfigSpec.ConfigValue<Boolean> INVENTORY_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> PROXIMITY_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> ENTITY_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> BLOCK_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> WEAPON_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<Double> BLOCKRAD_RANGE;
	public static final ForgeConfigSpec.ConfigValue<Boolean> IRRADIATION_DAMAGE;
	public static final ForgeConfigSpec.ConfigValue<Boolean> DECONTAMINATION_DAMAGE;
	public static final ForgeConfigSpec.ConfigValue<Boolean> RESISTS_DECON;
	public static final ForgeConfigSpec.ConfigValue<Boolean> ONLY_PLAYER_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> V3;
	public static final ForgeConfigSpec.ConfigValue<Boolean> V3_INVENTORY_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_INVENTORY_RADIATION_DEFINITION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> V3_PROXIMITY_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_PROXIMITY_RADIATION_DEFINITION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> V3_BLOCK_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_BLOCK_RADIATION_DEFINITION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> V3_BIOME_RADIATION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_BIOME_RADIATION_DEFINITION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_RADIATION_RESISTANCE_DEFINITION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_COUNTER_DEFINITION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_DETECTOR_DEFINITION;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> V3_RADIMMUNITY;
	public static final ForgeConfigSpec.ConfigValue<Double> BASE_RESISTANCE;
	public static final ForgeConfigSpec.ConfigValue<Double> MAX_RESISTANCE;
	public static final ForgeConfigSpec.ConfigValue<Double> RESISTANCE_MULTIPLIER;
	static {
		DISABLE_MESSAGE = BUILDER.comment("If true, hides that annoying text wall whenever you join a game. Does not affect error reports.").define("Disable Join Message", false);
		BUILDER.push("General Radiation Options");
		OLD_RADIATION = BUILDER.comment("Whether to use the old radiation system exclusively. This may be required for mods made for 1.x.x, but may break mods for 2.x.x. Also required for datapacks, unless the newer V3 is used..")
				.define("Use Old Radiation System", false);
		RADIATION_MULTIPLIER = BUILDER.comment("The amount of radiation received will be multiplied by this number. This will not show up in tooltips. Use 0 to disable all radiation.").define("Radiation Multiplier", (double) 1);
		DECON_MULTIPLIER = BUILDER.comment("The amount of radiation removed by DamageSource will be multiplied by this number. Use 0 to disable all radiation.").define("Decontamination Multiplier", (double) 1);
		RADIATION_POISONING_SCALING = BUILDER.comment("Smaller number here => faster radiation effect progression => harder game. Use at the risk of your sanity and friendships.").define("Radiation Effects Scaling", (double) 100);
		RADIATION_SICKNESS_SCALING = BUILDER.comment("Smaller number => slower damage increase from Radiation Sickness. Default 1.5.").define("Radiation Sickness Scaling", (double) 1.5);
		BUILDER.pop();
		BUILDER.push("Old Radiation System");
		INVENTORY_RADIATION = BUILDER.comment("Whether or not to allow radioactive items to affect a player holding them").define("Enable Inventory Radiation", true);
		PROXIMITY_RADIATION = BUILDER.comment("Whether or not to allow entities to irradiate others by holding something radioactive nearby").define("Enable Proximity Radiation", true);
		ENTITY_RADIATION = BUILDER.comment("Whether or not to allow entities to irradiate others by existing nearby").define("Enable Entity Radiation", true);
		BLOCK_RADIATION = BUILDER.comment("Whether or not to allow blocks to irradiate entities near them").define("Enable Block Radiation", true);
		WEAPON_RADIATION = BUILDER.comment("Whether or not to allow weapons to irradiate hit entities").define("Enable Weapon Hit Radiation", true);
		BLOCKRAD_RANGE = BUILDER.comment("Range (in blocks) that blocks irradiate things from").define("Block Radiation Range", (double) 16);
		BUILDER.pop();
		BUILDER.push("New Radiation System");
		IRRADIATION_DAMAGE = BUILDER.comment("If false, all 'Irradiate' damage types will become normal damage types, but without the damage cooldown (unless the previous option is ticked)").define("Enable Irradiate Damage Types", true);
		DECONTAMINATION_DAMAGE = BUILDER.comment("As with the previous option, but for the 'Decontamination' damage types.").define("Enable Decontaminate Damage Types", true);
		RESISTS_DECON = BUILDER.comment("If true, radiation resistance will be applied to decontamination damage as well.").define("Rad-Resistance Affects Decontamination", false);
		ONLY_PLAYER_RADIATION = BUILDER.comment("If true, radiation will be applied only to players, and will use a synced data tag to store radiation. If false, NBT tags will be used instead.").define("Irradiate Only Players", false);
		BUILDER.push("v3");
		V3 = BUILDER.comment("Whether or not to use version 3 radiation, a more performant version of the Old Radiation System. Does not require Old Radiation System to be enabled.").define("Use V3 Radiation", true);
		V3_INVENTORY_RADIATION = BUILDER.comment("Whether or not to allow radioactive items to affect a player holding them. This IS separate to the one in the 'Old Radiation System' area.").define("Enable Inventory Radiation", true);
		V3_INVENTORY_RADIATION_DEFINITION = BUILDER.comment("A list of item registry names to make radioactive. Should be in the form <mod>:<item_id>=<rads_per_tick>, e.g. minecraft:dirt=2 makes dirt radioactive, giving 40 RADs per second.")
				.defineList("Inventory Radiation Definition", List.of("radioactive:test=1"), entry -> true);
		V3_PROXIMITY_RADIATION = BUILDER.comment("Whether or not to allow entities to irradiate others by holding something radioactive nearby. This IS separate to the one in the 'Old Radiation System' area.").define("Enable Proximity Radiation",
				true);
		V3_PROXIMITY_RADIATION_DEFINITION = BUILDER.comment(
				"Like Inventory Radiation Definition, but with an extra part, range. Range should be specified after amount, separated by a ~, e.g. minecraft:dirt=2~4 makes dirt give 40 RADs per second to all entities (unless 'Irradiate Only Players' is enabled) within 4 blocks.")
				.defineList("Proximity Radiation Definition", List.of("radioactive:test=1~4"), entry -> true);
		V3_BLOCK_RADIATION = BUILDER.comment("Whether or not to allow blocks to irradiate entities near them. This IS separate to the one in the 'Old Radiation System' area.").define("Enable Block Radiation", true);
		V3_BLOCK_RADIATION_DEFINITION = BUILDER.comment("Like Proximity Radiation Definition, but for blocks.").defineList("Block Radiation Definition", List.of("radioactive:test_block=1~4"), entry -> true);
		V3_BIOME_RADIATION = BUILDER.comment("Whether or not to allow biomes to irradiate entities in them.").define("Enable Biome Radiation", true);
		V3_BIOME_RADIATION_DEFINITION = BUILDER.comment("A list of biome registry names to make radioactive, in the form id=amount per tick").defineList("Biome Radiation Definition", List.of("radioactive:test_biome=4"), entry -> true);
		V3_RADIATION_RESISTANCE_DEFINITION = BUILDER.comment(
				"Defines rad-resistance of armor items. Form: <mod>:<id>=<percentage>, e.g.  minecraft:iron_helmet=10 gives an iron helmet 10% rad-resistance, and bloons:bloon_lead_helmet=25 gives a Bloon Lead Helmet (from the Bloons Mod) 25% radiation resistance.")
				.defineList("Radiation Resistance Definition", List.of("radioactive:test_prot_helmet=10"), entry -> true);
		V3_COUNTER_DEFINITION = BUILDER.comment("Defines a list of radiation counter items. Each entry should be a single registry name.").defineList("Counter List", List.of("radioactive:counter"), entry -> true);
		V3_DETECTOR_DEFINITION = BUILDER.comment("Defines a list of radiation detector items.").defineList("Detector List", List.of(" "), entry -> true);
		V3_RADIMMUNITY = BUILDER.comment("A list of entities that should not receive radiation damage, or effects. Can still be irradiated, but won't feel it at all.").defineList("Immune Entities", List.of(" "), entry -> true);
		BUILDER.pop();
		BUILDER.pop();
		BUILDER.push("Radiation Resistance");
		BASE_RESISTANCE = BUILDER.comment("Percentage rad-resistance the player has without armor. Range: 0-100, default 0").define("Base Player Radiation Resistance", (double) 0);
		MAX_RESISTANCE = BUILDER.comment("Highest percentage rad-resistance a player can have. May be helpful on servers to keep radiation dangerous. Range 0-100, default 100.").define("Maximum Radiation Resistance", (double) 100);
		RESISTANCE_MULTIPLIER = BUILDER.comment("Radiation resistance will be multiplied by this number before being applied to radiation. Default 1.").define("Radiation Resistance Multiplier", (double) 1);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
