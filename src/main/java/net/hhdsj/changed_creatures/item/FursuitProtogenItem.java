package net.hhdsj.changed_creatures.item;

import net.hhdsj.changed_creatures.client.models.armors.fursuit.ModelFursuitProtogen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
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

import java.util.function.Consumer;

public abstract class FursuitProtogenItem extends HidePlayerPartArmor {

    private static final String TEX_BODY  = "changed_creatures:textures/armor/fursuit_protogen_body.png";
    private static final String TEX_BOOTS = "changed_creatures:textures/armor/fursuit_protogen_boots.png";

    public FursuitProtogenItem(ArmorItem.Type type, Item.Properties properties) {
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
            public String getName() { return "changed_creatures:fursuit_protogen"; }

            @Override
            public float getToughness() { return 0f; }

            @Override
            public float getKnockbackResistance() { return 0f; }
        }, type, properties);
    }

    @Override
    public String getArmorTexturePath(EquipmentSlot slot) {
        return slot == EquipmentSlot.FEET ? TEX_BOOTS : TEX_BODY;
    }

    @Override
    public ModelFursuitProtogen createModelInstance() {
        return new ModelFursuitProtogen(
                Minecraft.getInstance().getEntityModels().bakeLayer(ModelFursuitProtogen.LAYER_LOCATION));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return getArmorTexturePath(slot);
    }

    public static class Helmet extends FursuitProtogenItem {
        public Helmet() { super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant()); }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private ModelFursuitProtogen cached;

                @Override
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack,
                                                              EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                    if (cached == null) cached = createModelInstance();
                    cached.setVisibilityForSlot(slot);
                    return cached;
                }
            });
        }
    }

    public static class Chestplate extends FursuitProtogenItem {
        public Chestplate() { super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()); }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private ModelFursuitProtogen cached;

                @Override
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack,
                                                              EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                    if (cached == null) cached = createModelInstance();
                    cached.setVisibilityForSlot(slot);
                    return cached;
                }
            });
        }
    }

    public static class Leggings extends FursuitProtogenItem {
        public Leggings() { super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()); }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private ModelFursuitProtogen cached;

                @Override
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack,
                                                              EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                    if (cached == null) cached = createModelInstance();
                    cached.setVisibilityForSlot(slot);
                    return cached;
                }
            });
        }
    }

    public static class Boots extends FursuitProtogenItem {
        public Boots() { super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()); }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private ModelFursuitProtogen cached;

                @Override
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack,
                                                              EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                    if (cached == null) cached = createModelInstance();
                    cached.setVisibilityForSlot(slot);
                    return cached;
                }
            });
        }
    }
}