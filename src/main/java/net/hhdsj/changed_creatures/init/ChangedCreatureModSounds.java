package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChangedCreatureModSounds {

	public static final DeferredRegister<SoundEvent> REGISTRY =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ChangedCreature.MODID);

	// ==================== 唱片音乐 ====================
	public static final RegistryObject<SoundEvent> INK_SANS = register("music.ink_sans");
	public static final RegistryObject<SoundEvent> THE_FLUFFY_HEARTBEAT_IN_THE_CUBICLE = register("music.the_fluffy_heartbeat_in_the_cubicle");
	public static final RegistryObject<SoundEvent> ANIT_FURRY_MUSIC = register("music.anit_furry_music");
	public static final RegistryObject<SoundEvent> FLUTEMUSICA_1RECORDS = register("music.flutemusica_1records");
	public static final RegistryObject<SoundEvent> SRENING_OGG1 = register("music.srening_ogg1");


	private static RegistryObject<SoundEvent> register(String id) {
		return REGISTRY.register(id, () -> SoundEvent.createVariableRangeEvent(
				ChangedCreature.CCRL(id)
		));
	}
}