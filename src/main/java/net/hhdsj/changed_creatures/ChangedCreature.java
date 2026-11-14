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
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(CCRL(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;


	public ChangedCreature(FMLJavaModLoadingContext context) {
		registerPackets();
		IEventBus bus = context.getModEventBus();
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



	/**
	 * 用本模组命名空间(MODID)创建 ResourceLocation。
	 * @param path 资源路径，例如 "textures/entity/creature.png"
	 * @return 命名空间为 MODID、路径为 path 的 ResourceLocation
	 */
	public static ResourceLocation CCRL(String path){
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	/**
	 * 用指定命名空间和路径创建 ResourceLocation。
	 * @param namespace 命名空间，例如 "minecraft"
	 * @param path      资源路径
	 * @return 对应的 ResourceLocation
	 */
	public static ResourceLocation CCRL(String namespace,String path){
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
	}

	/**
	 * 解析 "namespace:path" 格式的字符串为 ResourceLocation。
	 * @param txt 完整资源字符串，例如 "minecraft:block/dirt"
	 * @return 解析后的 ResourceLocation
	 */
	public static ResourceLocation CCRLP(String txt){
		return ResourceLocation.parse(txt);
	}

	/**
	 * 语义化别名：用本模组命名空间(MODID)创建 ResourceLocation。
	 * @param path 资源路径
	 * @return 命名空间为 MODID 的 ResourceLocation
	 */
	public static ResourceLocation ChangedCreatureResourceLocation(String path){
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	/**
	 * 语义化别名：解析 "namespace:path" 字符串为 ResourceLocation。
	 * @param txt 完整资源字符串
	 * @return 解析后的 ResourceLocation
	 */
	public static ResourceLocation ChangedCreatureResourceLocationParse(String txt){
		return ResourceLocation.parse(txt);
	}

	/**
	 * 创建 Changed Creature 实体的模型层定位（ModelLayerLocation）。
	 * 命名空间固定为 MODID，模型层标识固定为 "main"。
	 * 注意：该方法仅创建定位对象，还需在
	 * {@code EntityRenderersEvent.RegisterLayerDefinitions} 事件中注册对应的 LayerDefinition。
	 *
	 * @param path 模型层路径，通常与实体注册名一致，例如 "creature"
	 * @return 一个 ModelLayerLocation，其 ResourceLocation 为 MODID:path，层标识为 "main"
	 */
	public static ModelLayerLocation ChangedCreatureModelResourceLocation(String path){
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, path),"main");
	}

	private void registerPackets() {

		// 你也可以在这里注册其他网络包...
	}
}