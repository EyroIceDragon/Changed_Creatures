package net.hhdsj.changed_creatures.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public abstract class FursuitFoxItem extends HidePlayerPartArmor {

    private static final String TEXTURES_LIGHT = "changed_creatures:textures/armor/fursuit_fox_light.png";
    private static final String TEXTURES = "changed_creatures:textures/armor/fursuit_fox.png";

    public FursuitFoxItem(Type type, Properties properties) {
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

    public static class Helmet extends FursuitFoxItem {
        public Helmet() { super(Type.HELMET, new Properties().fireResistant()); }
    }

    public static class Chestplate extends FursuitFoxItem {
        public Chestplate() { super(Type.CHESTPLATE, new Properties().fireResistant()); }
    }

    public static class Leggings extends FursuitFoxItem {
        public Leggings() { super(Type.LEGGINGS, new Properties().fireResistant()); }
    }
}