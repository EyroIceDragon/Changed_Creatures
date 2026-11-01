package net.hhdsj.changed_creatures.item;

import net.hhdsj.changed_creatures.client.models.armors.fursuit.HumanoidFursuitModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public abstract class HidePlayerPartArmor extends ArmorItem {

    public String getArmorTexturePath(EquipmentSlot slot) {
        return "";
    }

    public abstract HumanoidFursuitModel createModelInstance();

    public HidePlayerPartArmor(ArmorMaterial material, Type type, Item.Properties properties) {
        super(material, type, properties);
    }

    public boolean shouldHidePlayerModel(EquipmentSlot slot) {
        return this.getType().getSlot() == slot;
    }
}