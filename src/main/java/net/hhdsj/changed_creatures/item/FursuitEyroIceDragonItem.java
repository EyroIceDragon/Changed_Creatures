package net.hhdsj.changed_creatures.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public abstract class FursuitEyroIceDragonItem extends HidePlayerPartArmor {

    private static final String TEXTURES_LIGHT = "changed_creatures:textures/armor/fursuit_eyro_end_dragon_light.png";
    private static final String TEXTURES  = "changed_creatures:textures/armor/fursuit_eyro_end_dragon.png";

    public FursuitEyroIceDragonItem(Type type, Properties properties) {
        super(type,properties);
    }

    @Override
    public String getArmorTexturePath(EquipmentSlot slot) {
        return TEXTURES;
    }

    @Override
    public String getArmorTextureGlowPath() {
        return TEXTURES_LIGHT;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return getArmorTexturePath(slot);
    }

    public static class Helmet extends FursuitEyroIceDragonItem {
        public Helmet() { super(Type.HELMET, new Properties().fireResistant()); }
    }

    public static class Chestplate extends FursuitEyroIceDragonItem {
        public Chestplate() { super(Type.CHESTPLATE, new Properties().fireResistant()); }
    }

    public static class Leggings extends FursuitEyroIceDragonItem {
        public Leggings() { super(Type.LEGGINGS, new Properties().fireResistant()); }
    }
}