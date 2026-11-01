package net.hhdsj.changed_creatures.item;

import net.hhdsj.changed_creatures.client.models.armors.fursuit.ModelFursuitProtogen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public abstract class FursuitFoxItem extends HidePlayerPartArmor {

    private static final String TEXTURES = "changed_creatures:textures/armor/fursuit_protogen.png";

    public FursuitFoxItem(Type type, Properties properties) {
        super(type,properties);
    }

    @Override
    public String getArmorTexturePath(EquipmentSlot slot) {
        return TEXTURES;
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

    public static class Helmet extends FursuitFoxItem {
        public Helmet() { super(Type.HELMET, new Properties().fireResistant()); }

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

    public static class Chestplate extends FursuitFoxItem {
        public Chestplate() { super(Type.CHESTPLATE, new Properties().fireResistant()); }

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

    public static class Leggings extends FursuitFoxItem {
        public Leggings() { super(Type.LEGGINGS, new Properties().fireResistant()); }

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

    public static class Boots extends FursuitFoxItem {
        public Boots() { super(Type.BOOTS, new Properties().fireResistant()); }

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