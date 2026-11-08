package net.hhdsj.changed_creatures.item;

import net.hhdsj.changed_creatures.client.item.FursuitArmorClientExtensions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Consumer;

public abstract class HidePlayerPartArmor extends ArmorItem {

    public String getArmorTexturePath(EquipmentSlot slot) {
        return "";
    }

    public String getArmorTextureGlowPath() {
        return null;
    }

    public HidePlayerPartArmor(Type type, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 15;
            }

            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                return new int[]{1, 1, 2, 1}[type.getSlot().getIndex()];
            }

            @Override
            public int getEnchantmentValue() { return 9; }

            @Override
            public SoundEvent getEquipSound() { return SoundEvents.EMPTY; }

            @Override
            public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }

            @Override
            public String getName() { return "changed_creatures:fursuit"; }

            @Override
            public float getToughness() { return 0f; }

            @Override
            public float getKnockbackResistance() { return 0f; }
        }, type, properties.durability(Integer.MAX_VALUE).setNoRepair());;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> consumer.accept(FursuitArmorClientExtensions.create(this)));
    }

    public boolean shouldHidePlayerModel(EquipmentSlot slot) {
        return this.getType().getSlot() == slot;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return 0;
    }
}