# Radioactive
## Unified tag-based radiation for modpacks
Radioactive is a project which aims to unify radiation systems in mods, with the main goal being immersive modpacks with compatible systems.

It adds 5 main sources of radiation:
- Inventory Radiation, whereby items held in the player's inventory can irradiate them
- Proximity Radiation, which allows Inventory Radiation to affect entities near the player
- Block Radiation, which allows blocks to irradiate nearby entities
- Biome Radiation, which allows biomes to irradiate entities within
- and a flexible DamageSource-based radiation system, which allows further integration with individual mods.

3 ways to minimise radiation damage:
- Armour Protection, which makes armour decrease received radiation by a certain percentage
- DamageSource decontamination, which allows mods to directly remove radiation
- Entity immunity through either a potion effect (registry name `radioactive:radiation_immunity`) or in the V3 config.

2 ways to view radiation:
- Radiation Detectors, which just tell you if you've been irradiated or not (and in V1, where it's coming from)
- Radiation Counters, which tell you exactly how badly you've been irradiated (and in V1, where it's coming from) with a bar in the bottom-left of the screen.

The mod features a radiation sickness effect, with more debuffs as irradiation level increases, dealing unavoidable damage, even on creative mode.
All these effects are shown in tooltips on the relevant items.

Everything is configurable, and the focus is on adding more features, rather than changing old ones, so upgrading Radioactive will never break your old modpacks, just change a config option.
V3 configuration will give detailed error information for any bad syntax.


See the [curseforge page](https://www.curseforge.com/minecraft/mc-mods/radioactive-ll8) for more information.
