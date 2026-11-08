package net.hhdsj.changed_creatures.client.item;

import net.hhdsj.changed_creatures.client.models.armors.fursuit.HumanoidFursuitModel;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

@OnlyIn(Dist.CLIENT)
public final class FursuitArmorClientExtensions {
    private static BlockEntityWithoutLevelRenderer itemRenderer;

    private FursuitArmorClientExtensions() {
    }

    public static IClientItemExtensions create(HidePlayerPartArmor item) {
        return new IClientItemExtensions() {
            private HumanoidFursuitModel cachedModel;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (itemRenderer == null) {
                    itemRenderer = new FursuitArmorItemRenderer(
                            Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                            Minecraft.getInstance().getEntityModels());
                }
                return itemRenderer;
            }

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack,
                                                          EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                if (cachedModel == null) {
                    cachedModel = createModelInstance(item);
                }
                cachedModel.setVisibilityForSlot(slot);
                return cachedModel;
            }
        };
    }

    public static HumanoidFursuitModel createModelInstance(HidePlayerPartArmor item) {
        return FursuitArmorModelRegistry.createModel(item);
    }
}
