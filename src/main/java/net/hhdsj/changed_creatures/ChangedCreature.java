package net.hhdsj.changed_creatures;

import net.hhdsj.changed_creatures.capability.IAbilityData;
import net.hhdsj.changed_creatures.event.CrystalRingHandler;
import net.hhdsj.changed_creatures.init.*;
import net.hhdsj.changed_creatures.network.packets.SyncCrystalRingPacket;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

//java add

// Enchant imports


@Mod("changed_creatures")
public class ChangedCreature {
	public static final Logger LOGGER = LogManager.getLogger(ChangedCreature.class);
	public static final String MODID = "changed_creatures";
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;


	public ChangedCreature() {
		registerPackets();
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.register(ChangedCreatureRegistry.class);
		ChangedCreatureModSounds.REGISTRY.register(bus);
		ChangedCreatureModFluidTypes.REGISTRY.register(bus);
		ChangedCreaturesModNewAbiliies.register(bus);
		ChangedCreatureModFluids.REGISTRY_TYPES.register(bus);
		ChangedCreatureModFluids.REGISTRY_FLUIDS.register(bus);

		ChangedCreatureModEnchantmentInit.ENCHANTMENTS.register(bus);

		ChangedCreatureModPaintings.REGISTRY.register(bus);

		ChangedCreatureModBlocks.REGISTRY.register(bus);

		ChangedCreatureBlockEntities.BLOCK_ENTITIES.register(bus);

		ChangedCreatureModItems.ITEMS.register(bus);

		ChangedCreatureModEntities.REGISTRY.register(bus);

		ChangedCreatureModEntities.ITEMS.register(bus);

		ChangedCreatureModTabs.REGISTRY.register(bus);

		ChangedCreatureModParticleTypes.REGISTRY.register(bus);

		ChangedCreatureModBiomes.BIOMES.register(bus);

		ChangedCreatureModMobEffects.REGISTRY.register(bus);

		ChangedCreatureModAbilities.REGISTRY.register(bus);
		ChangedCreatureModMenus.REGISTRY.register(bus);
		ChangedCreatureModTransfurVariants.REGISTRY.register(bus);

		CrystalRingHandler.registerCapabilities(bus);
		SyncCrystalRingPacket.register();
		ChangedCreaturesModAbiliiesVariants.bindAll();
		MinecraftForge.EVENT_BUS.register(new CrystalRingHandler());
	}

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(IAbilityData.class);
	}

	public static ResourceLocation ChangedCreatureResourceLocation(String path){
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}
	public static ResourceLocation CCRL(String path){
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}
	public static ResourceLocation CCRL(String namespace,String path){
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
	}
	public static ResourceLocation ChangedCreatureResourceLocationParse(String txt){
		return ResourceLocation.parse(txt);
	}
	public static ModelLayerLocation ChangedCreatureModelResourceLocation(String path){
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, path),"main");
	}

	public static ResourceLocation ModResource(String Path){
		return ResourceLocation.fromNamespaceAndPath(MODID, Path);
	}

	private void registerPackets() {

		// 你也可以在这里注册其他网络包...
	}
}