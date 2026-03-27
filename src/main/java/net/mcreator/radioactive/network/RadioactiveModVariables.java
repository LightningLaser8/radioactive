package net.mcreator.radioactive.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.radioactive.RadioactiveMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RadioactiveModVariables {
	public static boolean local_errored = false;
	public static double client_radiation = 0;
	public static double loaded__bar_width = 0;
	public static double loaded__bar_height = 0;
	public static double loaded__bar_x = 0;
	public static double loaded__bar_y = 0;
	public static boolean loaded__bar_enabled = false;
	public static boolean loaded__shut_up_counters = false;
	public static double loaded__click_rate = 0;
	public static double loaded__click_pitch = 0;
	public static boolean loaded__show_tooltips = false;
	public static boolean loaded__radbar_bg = false;
	public static String loaded__radbar_texture = "\"\"";
	public static String loaded__radbar_bg_texture = "\"\"";
	public static boolean client_show_bar = false;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		RadioactiveMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
		RadioactiveMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			if (!event.isWasDeath()) {
				clone.received_radiation = original.received_radiation;
				clone.receiving_inv_rad = original.receiving_inv_rad;
				clone.receiving_block_rad = original.receiving_block_rad;
				clone.radiation_resistance = original.radiation_resistance;
				clone.last_rad = original.last_rad;
				clone.rads_per_sec = original.rads_per_sec;
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					RadioactiveMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					RadioactiveMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					RadioactiveMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "radioactive_worldvars";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				RadioactiveMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "radioactive_mapvars";
		public double rad_tick = 0;
		public boolean errored = false;
		public CompoundTag v3_loaded__inv = new CompoundTag();
		public CompoundTag v3_loaded__prox = new CompoundTag();
		public CompoundTag v3_loaded__block = new CompoundTag();
		public CompoundTag v3_loaded__entity = new CompoundTag();
		public CompoundTag v3_loaded__biome = new CompoundTag();
		public CompoundTag v3_loaded__prot = new CompoundTag();
		public CompoundTag v3_loaded__cure = new CompoundTag();
		public CompoundTag v3_loaded__count = new CompoundTag();
		public CompoundTag v3_loaded__detec = new CompoundTag();
		public boolean loaded__old_sys = false;
		public boolean loaded__opr = false;
		public double loaded__radmult = 0;
		public double loaded__deconmult = 0;
		public double loaded__effectmult = 0;
		public double loaded__sickmult = 0;
		public boolean v3_loaded__enabled_inv = false;
		public boolean v3_loaded__enabled_prox = false;
		public boolean v3_loaded__enabled_block = false;
		public boolean v3_loaded__enabled_biome = false;
		public boolean v3_loaded__enabled_cure = false;
		public boolean v3_loaded__shut_fuk = false;
		public CompoundTag v3_loaded__radimmunity = new CompoundTag();
		public double loaded__base_res = 0;
		public double loaded__max_res = 0;
		public double loaded__res_mult = 0;
		public boolean loaded__resist_decon = false;
		public boolean loaded__enable_rad_damage = false;
		public boolean loaded__enable_unrad_damage = false;
		public boolean v3_loaded__is_v3 = false;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			rad_tick = nbt.getDouble("rad_tick");
			errored = nbt.getBoolean("errored");
			this.v3_loaded__inv = nbt.get("v3_loaded__inv") instanceof CompoundTag v3_loaded__inv ? v3_loaded__inv : new CompoundTag();
			this.v3_loaded__prox = nbt.get("v3_loaded__prox") instanceof CompoundTag v3_loaded__prox ? v3_loaded__prox : new CompoundTag();
			this.v3_loaded__block = nbt.get("v3_loaded__block") instanceof CompoundTag v3_loaded__block ? v3_loaded__block : new CompoundTag();
			this.v3_loaded__entity = nbt.get("v3_loaded__entity") instanceof CompoundTag v3_loaded__entity ? v3_loaded__entity : new CompoundTag();
			this.v3_loaded__biome = nbt.get("v3_loaded__biome") instanceof CompoundTag v3_loaded__biome ? v3_loaded__biome : new CompoundTag();
			this.v3_loaded__prot = nbt.get("v3_loaded__prot") instanceof CompoundTag v3_loaded__prot ? v3_loaded__prot : new CompoundTag();
			this.v3_loaded__cure = nbt.get("v3_loaded__cure") instanceof CompoundTag v3_loaded__cure ? v3_loaded__cure : new CompoundTag();
			this.v3_loaded__count = nbt.get("v3_loaded__count") instanceof CompoundTag v3_loaded__count ? v3_loaded__count : new CompoundTag();
			this.v3_loaded__detec = nbt.get("v3_loaded__detec") instanceof CompoundTag v3_loaded__detec ? v3_loaded__detec : new CompoundTag();
			loaded__old_sys = nbt.getBoolean("loaded__old_sys");
			loaded__opr = nbt.getBoolean("loaded__opr");
			loaded__radmult = nbt.getDouble("loaded__radmult");
			loaded__deconmult = nbt.getDouble("loaded__deconmult");
			loaded__effectmult = nbt.getDouble("loaded__effectmult");
			loaded__sickmult = nbt.getDouble("loaded__sickmult");
			v3_loaded__enabled_inv = nbt.getBoolean("v3_loaded__enabled_inv");
			v3_loaded__enabled_prox = nbt.getBoolean("v3_loaded__enabled_prox");
			v3_loaded__enabled_block = nbt.getBoolean("v3_loaded__enabled_block");
			v3_loaded__enabled_biome = nbt.getBoolean("v3_loaded__enabled_biome");
			v3_loaded__enabled_cure = nbt.getBoolean("v3_loaded__enabled_cure");
			v3_loaded__shut_fuk = nbt.getBoolean("v3_loaded__shut_fuk");
			this.v3_loaded__radimmunity = nbt.get("v3_loaded__radimmunity") instanceof CompoundTag v3_loaded__radimmunity ? v3_loaded__radimmunity : new CompoundTag();
			loaded__base_res = nbt.getDouble("loaded__base_res");
			loaded__max_res = nbt.getDouble("loaded__max_res");
			loaded__res_mult = nbt.getDouble("loaded__res_mult");
			loaded__resist_decon = nbt.getBoolean("loaded__resist_decon");
			loaded__enable_rad_damage = nbt.getBoolean("loaded__enable_rad_damage");
			loaded__enable_unrad_damage = nbt.getBoolean("loaded__enable_unrad_damage");
			v3_loaded__is_v3 = nbt.getBoolean("v3_loaded__is_v3");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("rad_tick", rad_tick);
			nbt.putBoolean("errored", errored);
			nbt.put("v3_loaded__inv", this.v3_loaded__inv);
			nbt.put("v3_loaded__prox", this.v3_loaded__prox);
			nbt.put("v3_loaded__block", this.v3_loaded__block);
			nbt.put("v3_loaded__entity", this.v3_loaded__entity);
			nbt.put("v3_loaded__biome", this.v3_loaded__biome);
			nbt.put("v3_loaded__prot", this.v3_loaded__prot);
			nbt.put("v3_loaded__cure", this.v3_loaded__cure);
			nbt.put("v3_loaded__count", this.v3_loaded__count);
			nbt.put("v3_loaded__detec", this.v3_loaded__detec);
			nbt.putBoolean("loaded__old_sys", loaded__old_sys);
			nbt.putBoolean("loaded__opr", loaded__opr);
			nbt.putDouble("loaded__radmult", loaded__radmult);
			nbt.putDouble("loaded__deconmult", loaded__deconmult);
			nbt.putDouble("loaded__effectmult", loaded__effectmult);
			nbt.putDouble("loaded__sickmult", loaded__sickmult);
			nbt.putBoolean("v3_loaded__enabled_inv", v3_loaded__enabled_inv);
			nbt.putBoolean("v3_loaded__enabled_prox", v3_loaded__enabled_prox);
			nbt.putBoolean("v3_loaded__enabled_block", v3_loaded__enabled_block);
			nbt.putBoolean("v3_loaded__enabled_biome", v3_loaded__enabled_biome);
			nbt.putBoolean("v3_loaded__enabled_cure", v3_loaded__enabled_cure);
			nbt.putBoolean("v3_loaded__shut_fuk", v3_loaded__shut_fuk);
			nbt.put("v3_loaded__radimmunity", this.v3_loaded__radimmunity);
			nbt.putDouble("loaded__base_res", loaded__base_res);
			nbt.putDouble("loaded__max_res", loaded__max_res);
			nbt.putDouble("loaded__res_mult", loaded__res_mult);
			nbt.putBoolean("loaded__resist_decon", loaded__resist_decon);
			nbt.putBoolean("loaded__enable_rad_damage", loaded__enable_rad_damage);
			nbt.putBoolean("loaded__enable_unrad_damage", loaded__enable_unrad_damage);
			nbt.putBoolean("v3_loaded__is_v3", v3_loaded__is_v3);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				RadioactiveMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("radioactive", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double received_radiation = 0;
		public double receiving_inv_rad = 0;
		public double receiving_block_rad = 0;
		public double radiation_resistance = 0;
		public double last_rad = 0;
		public double rads_per_sec = 0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				RadioactiveMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("received_radiation", received_radiation);
			nbt.putDouble("receiving_inv_rad", receiving_inv_rad);
			nbt.putDouble("receiving_block_rad", receiving_block_rad);
			nbt.putDouble("radiation_resistance", radiation_resistance);
			nbt.putDouble("last_rad", last_rad);
			nbt.putDouble("rads_per_sec", rads_per_sec);
			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			received_radiation = nbt.getDouble("received_radiation");
			receiving_inv_rad = nbt.getDouble("receiving_inv_rad");
			receiving_block_rad = nbt.getDouble("receiving_block_rad");
			radiation_resistance = nbt.getDouble("radiation_resistance");
			last_rad = nbt.getDouble("last_rad");
			rads_per_sec = nbt.getDouble("rads_per_sec");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.received_radiation = message.data.received_radiation;
					variables.receiving_inv_rad = message.data.receiving_inv_rad;
					variables.receiving_block_rad = message.data.receiving_block_rad;
					variables.radiation_resistance = message.data.radiation_resistance;
					variables.last_rad = message.data.last_rad;
					variables.rads_per_sec = message.data.rads_per_sec;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
