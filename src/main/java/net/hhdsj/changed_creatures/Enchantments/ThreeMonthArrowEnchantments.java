package net.hhdsj.changed_creatures.Enchantments;

import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ThreeMonthArrowEnchantments extends Enchantment {

    private final TransfurVariant formVariant = ChangedCreatureModTransfurVariants.LATEX_THREE_MONTH_WOLF.get();

    public ThreeMonthArrowEnchantments() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public void doPostAttack(@NotNull LivingEntity attacker, @NotNull Entity target, int level) {

        // 攻击逻辑 Attack Logic
        if (target instanceof LivingEntity livingTarget) {
            // 触发变体转化（80%可能）Trigger transfur transformation (80% chance)
            if (livingTarget.getRandom().nextFloat() < 0.8f) {
                ProcessTransfur.progressTransfur(livingTarget, 3f, this.formVariant, TransfurContext.hazard(TransfurCause.GRAB_REPLICATE));
            }
        }


        super.doPostAttack(attacker, target, level);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }
}
