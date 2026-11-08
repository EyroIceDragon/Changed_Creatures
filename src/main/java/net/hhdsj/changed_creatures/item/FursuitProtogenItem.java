package net.hhdsj.changed_creatures.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class FursuitProtogenItem extends HidePlayerPartArmor {

    private static final String TEXTURES_LIGHT = "changed_creatures:textures/armor/fursuit_protogen_light.png";
    private static final String TEXTURES  = "changed_creatures:textures/armor/fursuit_protogen.png";

    public FursuitProtogenItem(ArmorItem.Type type, Item.Properties properties) {
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

    public static class Helmet extends FursuitProtogenItem {
        public Helmet() { super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant()); }
    }

    public static class Chestplate extends FursuitProtogenItem {
        public Chestplate() { super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()); }
    }

    public static class Leggings extends FursuitProtogenItem {
        public Leggings() { super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()); }
    }
}