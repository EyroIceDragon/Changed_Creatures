package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.entity.simple.LatexthreemonthwolfEntityProjectile;
import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ArrowReplacementHandler {

    private static final float BASE_ARROW_DAMAGE = 2.0F;

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        Player player = event.getEntity();
        TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);
        ItemStack bow = event.getBow();
        int charge = event.getCharge();
        Level level = player.level();

        // 只在服务端处理
        if (level.isClientSide) return;
        if (variant == null) return;

        ResourceLocation formId = variant.getFormId();
        if (formId == null) return;

        // 检查是否是有效的形态
        boolean isValidForm = Objects.equals(formId, ChangedCreature.CCRL("form_latex_three_month_wolf")) ||
                Objects.equals(formId, ChangedCreature.CCRL("form_latex_ice_field_wolf_dragon"));
        if (!isValidForm) return;

        if (!(bow.getItem() instanceof BowItem)) return;

        ItemStack arrowStack = player.getProjectile(bow);
        if (arrowStack.isEmpty() || !(arrowStack.getItem() instanceof ArrowItem)) {
            if (player.getAbilities().instabuild) {
                arrowStack = new ItemStack(Items.ARROW);
            } else {
                return;
            }
        }

        // 计算箭矢速度
        float velocity = getArrowVelocity(charge);
        if (velocity < 0.1) return;

        // 取消原版箭矢生成
        event.setCanceled(true);

        // 创建自定义箭矢
        LatexthreemonthwolfEntityProjectile customArrow = new LatexthreemonthwolfEntityProjectile(level, player);

        // 设置变体
        if (Objects.equals(formId, ChangedCreature.CCRL("form_latex_three_month_wolf"))) {
            customArrow.setFormVariant(ChangedCreature.CCRL("form_latex_three_month_wolf"));
        }
        if (Objects.equals(formId, ChangedCreature.CCRL("form_latex_ice_field_wolf_dragon"))) {
            customArrow.setFormVariant(ChangedCreature.CCRL("form_latex_ice_field_wolf_dragon"));
        }

        // 设置箭矢属性
        customArrow.shootFromRotation(player, player.getXRot(), player.getYRot(),
                0.0F, velocity * 3.0F, 1.0F);

        float damage = BASE_ARROW_DAMAGE;

        int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bow);
        if (powerLevel > 0) {
            damage += damage * (powerLevel * 0.25F);
        }

        int punchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bow);
        if (punchLevel > 0) {
            customArrow.setKnockback(punchLevel);
        }

        boolean isFlame = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bow) > 0;

        int pierceLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, bow);
        if (pierceLevel > 0) {
            customArrow.setPierceLevel((byte) pierceLevel);
        }

        // 设置伤害
        customArrow.setProjectileDamage(damage);
        customArrow.setBaseDamage(damage);

        // 设置火矢效果
        if (isFlame) {
            customArrow.setSecondsOnFire(100);
        }

        // 可选：设置箭矢为可拾取
        customArrow.pickup = AbstractArrow.Pickup.ALLOWED;

        // 调试日志
        if (!level.isClientSide) {
            level.getServer().sendSystemMessage(
                    net.minecraft.network.chat.Component.literal(
                            String.format("Arrow damage: %.1f (Power %d, Punch %d, Flame %s, Pierce %d)",
                                    damage, powerLevel, punchLevel, isFlame, pierceLevel)
                    )
            );
        }

        // 播放射箭音效
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS,
                1.0F, 1.0F / (level.random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

        // 消耗耐久和箭矢
        if (!player.getAbilities().instabuild) {
            bow.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));

            if (!arrowStack.isEmpty()) {
                arrowStack.shrink(1);
                if (arrowStack.isEmpty()) {
                    player.getInventory().removeItem(arrowStack);
                }
            }
        }

        // 生成实体
        level.addFreshEntity(customArrow);
    }

    private static float getArrowVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        return Math.min(f, 1.0F);
    }
}