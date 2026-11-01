package net.hhdsj.changed_creatures.item;

import net.hhdsj.changed_creatures.client.models.armors.changed.GoodBlockModelMaleWingedDragonArmor;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorModel;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public abstract class PainiteArmorItem extends ArmorItem {

    // ==================== 材质定义 ====================
    private static final ArmorMaterial PAINITE_MATERIAL = new ArmorMaterial() {
        @Override public int getDurabilityForType(ArmorItem.@NotNull Type type) {
            return switch (type) {
                case HELMET -> 13 * 45;
                case CHESTPLATE -> 16 * 45;
                case LEGGINGS -> 15 * 45;
                case BOOTS -> 11 * 45;
                default -> 0;
            };
        }
        @Override public int getDefenseForType(ArmorItem.@NotNull Type type) {
            return switch (type) {
                case HELMET -> 3;
                case CHESTPLATE -> 8;
                case LEGGINGS -> 6;
                case BOOTS -> 3;
                default -> 0;
            };
        }
        @Override public int getEnchantmentValue() { return 50; }
        @Override public @NotNull SoundEvent getEquipSound() {
            SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("changed_creatures:item.armor.equip_netherite"));
            return sound != null ? sound : SoundEvents.ARMOR_EQUIP_NETHERITE;
        }
        @Override public @NotNull Ingredient getRepairIngredient() { return Ingredient.of(Items.NETHERITE_INGOT); }
        @Override public @NotNull String getName() { return "painite_armor"; }
        @Override public float getToughness() { return 3f; }
        @Override public float getKnockbackResistance() { return 0.1f; }
    };

    public PainiteArmorItem(ArmorItem.Type type, Item.Properties properties) {
        super(PAINITE_MATERIAL, type, properties);
    }

    @Override public @NotNull EquipmentSlot getEquipmentSlot() { return this.type.getSlot(); }

    // ==================== 客户端安全持有者 ====================
    /**
     * 所有客户端专有对象都通过此内部类延迟加载。
     * 只有当 get() 被调用且运行在客户端时，才会初始化这些字段。
     * 在服务端，这些字段保持为 null。
     */
    private static final class ClientHolder {
        private static boolean initialized;
        private static boolean initFailed;

        @Nullable
        private static GoodBlockModelMaleWingedDragonArmor<ChangedEntity> cachedModel;

        @Nullable
        private static ModelPart emptyPart;

        private static void ensureInitialized() {
            if (initialized || initFailed) return;
            try {
                // 测试是否能加载客户端类
                Class.forName("net.minecraft.client.Minecraft");

                cachedModel = new GoodBlockModelMaleWingedDragonArmor<>(
                        Minecraft.getInstance().getEntityModels().bakeLayer(GoodBlockModelMaleWingedDragonArmor.LAYER_LOCATION),
                        ArmorModel.ARMOR_OUTER
                );
                emptyPart = new ModelPart(Collections.emptyList(), Collections.emptyMap());
                initialized = true;
            } catch (ClassNotFoundException | NoClassDefFoundError | ExceptionInInitializerError e) {
                // 服务端环境，标记为失败，不再重试
                initFailed = true;
            }
        }

        @Nullable
        static GoodBlockModelMaleWingedDragonArmor<ChangedEntity> getModel() {
            ensureInitialized();
            return cachedModel;
        }

        @Nullable
        static ModelPart getEmptyPart() {
            ensureInitialized();
            return emptyPart;
        }

        static boolean isAvailable() {
            ensureInitialized();
            return initialized;
        }

        static void animateIfPossible(LivingEntity entity) {
            if (!isAvailable()) return;
            if (!(entity instanceof ChangedEntity changed)) return;
            var model = getModel();
            if (model == null) return;

            float age = entity.tickCount + Minecraft.getInstance().getFrameTime();
            float limbSwing = entity.walkAnimation.position();
            float limbSwingAmount = Math.min(entity.walkAnimation.speed(), 1.5F);

            model.getAnimator(changed).setupAnim(changed,
                    limbSwing,
                    limbSwingAmount,
                    age,
                    entity.yHeadRot,
                    entity.getXRot()
            );
        }
    }

    // ==================== 客户端模型构建 ====================
    @Nullable
    private static HumanoidModel<?> buildModel(@Nullable LivingEntity entity,
                                               @Nullable HumanoidModel<?> original,
                                               @NotNull Map<String, ModelPart> parts) {
        if (!ClientHolder.isAvailable()) return original;
        ModelPart emptyPart = ClientHolder.getEmptyPart();
        if (emptyPart == null) return original;

        ClientHolder.animateIfPossible(entity);

        var model = new HumanoidModel<>(new ModelPart(Collections.emptyList(), parts));
        if (entity != null) {
            model.crouching = entity.isShiftKeyDown();
            model.young = entity.isBaby();
        }
        if (original != null) {
            model.riding = original.riding;
        }
        return model;
    }

    @NotNull
    private static Map<String, ModelPart> buildPartsMap() {
        if (!ClientHolder.isAvailable()) return Collections.emptyMap();
        ModelPart empty = ClientHolder.getEmptyPart();
        if (empty == null) return Collections.emptyMap();

        Map<String, ModelPart> parts = new HashMap<>();
        parts.put("head", empty);
        parts.put("hat", empty);
        parts.put("body", empty);
        parts.put("right_arm", empty);
        parts.put("left_arm", empty);
        parts.put("right_leg", empty);
        parts.put("left_leg", empty);
        return parts;
    }

    // ==================== 头盔 ====================
    public static class Helmet extends PainiteArmorItem {
        public Helmet() { super(Type.HELMET, new Properties().fireResistant().stacksTo(1)); }

        @Override
        public String getArmorTexture(ItemStack s, Entity e, EquipmentSlot slot, String t) {
            return "changed_creatures:textures/models/armor/painite_male_winged_dragon_armor_1.png";
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> c) {
            c.accept(new IClientItemExtensions() {
                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(
                        LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                    if (!ClientHolder.isAvailable()) return original;
                    var model = ClientHolder.getModel();
                    if (model == null) return original;

                    var parts = buildPartsMap();
                    parts.put("head", model.Head);
                    return Objects.requireNonNull(buildModel(entity, original, parts));
                }
            });
        }
    }

    // ==================== 胸甲 ====================
    public static class Chestplate extends PainiteArmorItem {
        public Chestplate() { super(Type.CHESTPLATE, new Properties().fireResistant().stacksTo(1)); }

        @Override
        public String getArmorTexture(ItemStack s, Entity e, EquipmentSlot slot, String t) {
            return "changed_creatures:textures/models/armor/painite_male_winged_dragon_armor_1.png";
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> c) {
            c.accept(new IClientItemExtensions() {
                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(
                        LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                    if (!ClientHolder.isAvailable()) return original;
                    var model = ClientHolder.getModel();
                    if (model == null) return original;

                    model.Tail.visible = false;
                    var parts = buildPartsMap();
                    parts.put("body", model.Torso);
                    parts.put("right_arm", model.RightArm);
                    parts.put("left_arm", model.LeftArm);
                    return Objects.requireNonNull(buildModel(entity, original, parts));
                }
            });
        }
    }

    // ==================== 护腿 ====================
    public static class Leggings extends PainiteArmorItem {
        public Leggings() { super(Type.LEGGINGS, new Properties().fireResistant().stacksTo(1)); }

        @Override
        public String getArmorTexture(ItemStack s, Entity e, EquipmentSlot slot, String t) {
            return "changed_creatures:textures/models/armor/painite_male_winged_dragon_armor_1.png";
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> c) {
            c.accept(new IClientItemExtensions() {
                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(
                        LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                    if (!ClientHolder.isAvailable()) return original;
                    var model = ClientHolder.getModel();
                    if (model == null) return original;

                    model.RightLowerLeg.visible = true;
                    model.RightFoot.visible = true;
                    model.RightPad.visible = false;
                    model.LeftLowerLeg.visible = true;
                    model.LeftFoot.visible = true;
                    model.LeftPad.visible = false;

                    var parts = buildPartsMap();
                    parts.put("right_leg", model.RightLeg);
                    parts.put("left_leg", model.LeftLeg);

                    var result = buildModel(entity, original, parts);
                    if (result != null) {
                        result.rightLeg.visible = true;
                        result.leftLeg.visible = true;
                    }
                    return result != null ? result : original;
                }
            });
        }
    }

    // ==================== 靴子 ====================
    public static class Boots extends PainiteArmorItem {
        public Boots() { super(Type.BOOTS, new Properties().fireResistant().stacksTo(1)); }

        @Override
        public String getArmorTexture(ItemStack s, Entity e, EquipmentSlot slot, String t) {
            return "changed_creatures:textures/models/armor/painite_male_winged_dragon_armor_2.png";
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> c) {
            c.accept(new IClientItemExtensions() {
                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(
                        LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                    if (!ClientHolder.isAvailable()) return original;
                    var model = ClientHolder.getModel();
                    if (model == null) return original;

                    model.RightLowerLeg.visible = true;
                    model.RightFoot.visible = true;
                    model.RightPad.visible = true;
                    model.LeftLowerLeg.visible = true;
                    model.LeftFoot.visible = true;
                    model.LeftPad.visible = true;

                    var parts = buildPartsMap();
                    parts.put("right_leg", model.RightLeg);
                    parts.put("left_leg", model.LeftLeg);

                    var result = buildModel(entity, original, parts);
                    if (result != null) {
                        result.rightLeg.visible = true;
                        result.leftLeg.visible = true;
                    }
                    return result != null ? result : original;
                }
            });
        }
    }
}