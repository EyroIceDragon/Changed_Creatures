package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.ability.data.GlobalExpSyncPacket;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilities;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilitiesCapability;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class EnrageAbility extends AbstractAbility {

    private static final UUID ENRAGE_ATTACK_UUID =
            UUID.fromString("6f2a8c1e-3b4d-4e7a-9c2f-1a2b3c4d5e6f");
    private static final UUID ENRAGE_SPEED_UUID =
            UUID.fromString("7a3b9d2f-4c5e-4f8b-8d1a-2b3c4d5e6f70");

    private final ResourceLocation texture =
            ResourceLocation.fromNamespaceAndPath(
                    ChangedCreature.MODID,
                    "textures/gui/ability/latex_ability_enrage.png"
            );

    public EnrageAbility() {
        super(Component.translatable("ability.changed_creatures.enrage.name"), 0xFFFFFF, 2);
    }

    @Override
    public ResourceLocation getAbilityTexture() {
        return texture;
    }

    @Override
    public float useExp(int level) {
        return 20 + 70 * level * 0.5F;
    }

    @Override
    public void onTick(Player player, int exp_level) {
        if (exp_level == 0) return;

        Level level = player.level();
        if (level.isClientSide) return;

        if (ProcessTransfur.getPlayerTransfurVariant(player) == null) return;
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        int have = abilities.getPlayerExp();
        if (have < exp_level) return;

        abilities.reduceExp(exp_level);

        if (player instanceof ServerPlayer sp) {
            GlobalExpSyncPacket.send(sp);
        }

        float health = player.getHealth();
        float maxHealth = player.getMaxHealth();
        float ratio = maxHealth <= 0.0F ? 1.0F : health / maxHealth;

        // 血量低于 50% 才触发狂化
        boolean enraged = ratio <= 0.5F;

        AttributeInstance attack = player.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (attack != null) {
            attack.removeModifier(ENRAGE_ATTACK_UUID);
            if (enraged) {
                double amount = 0.10D + exp_level * 0.10D; // 10% / 20% / 30%
                attack.addTransientModifier(new AttributeModifier(
                        ENRAGE_ATTACK_UUID,
                        "changed_creatures.enrage_attack",
                        amount,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                ));
            }
        }

        if (speed != null) {
            speed.removeModifier(ENRAGE_SPEED_UUID);
            if (enraged) {
                double amount = 0.05D + exp_level * 0.05D; // 5% / 10% / 15%
                speed.addTransientModifier(new AttributeModifier(
                        ENRAGE_SPEED_UUID,
                        "changed_creatures.enrage_speed",
                        amount,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                ));
            }
        }

        // 高等级狂化时附带抗性提升，体现“越残血越硬”
        if (enraged && exp_level >= 2) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_RESISTANCE,
                    40,
                    0,
                    false,
                    false,
                    true
            ));
        }
    }

    @Override
    public void onHurt(Player player, LivingEntity attacker, int exp_level) {
        if (exp_level == 0) return;
        if (player.level().isClientSide) return;
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        int have = abilities.getPlayerExp();
        if (have < exp_level) return;

        abilities.reduceExp(exp_level);

        if (player instanceof ServerPlayer sp) {
            GlobalExpSyncPacket.send(sp);
        }
        // 受击时短暂提升攻击欲望：低血量时给目标上虚弱
        float ratio = player.getMaxHealth() <= 0.0F
                ? 1.0F
                : player.getHealth() / player.getMaxHealth();

        if (ratio <= 0.5F && attacker != null && attacker.isAlive()) {
            attacker.addEffect(new MobEffectInstance(
                    MobEffects.WEAKNESS,
                    60 + exp_level * 20,
                    exp_level >= 2 ? 1 : 0
            ));
        }
    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.translatable("ability.changed_creatures.enrage.desc1"));
        list.add(Component.translatable("ability.changed_creatures.enrage.desc2"));

        if (level <= 0) {
            list.add(Component.translatable("ability.changed_creatures.enrage.level.locked"));
            list.add(Component.translatable("ability.changed_creatures.enrage.level.locked_hint"));
        } else {
            list.add(Component.translatable("ability.changed_creatures.enrage.trigger",
                    "50%"));
            list.add(Component.translatable("ability.changed_creatures.enrage.attack",
                    (10 + level * 10) + "%"));
            list.add(Component.translatable("ability.changed_creatures.enrage.speed",
                    (5 + level * 5) + "%"));

            list.add(Component.translatable("ability.changed_creatures.enrage.effect.attack"));
            list.add(Component.translatable("ability.changed_creatures.enrage.effect.speed"));

            if (level >= 2) {
                list.add(Component.translatable("ability.changed_creatures.enrage.effect.resistance"));
                list.add(Component.translatable("ability.changed_creatures.enrage.effect.weakness"));
            }
        }

        String whisperKey;
        if (level < 1) {
            whisperKey = "ability.changed_creatures.enrage.whisper.0";
        } else if (level < 2) {
            whisperKey = "ability.changed_creatures.enrage.whisper.1";
        } else {
            whisperKey = "ability.changed_creatures.enrage.whisper.2";
        }

        list.add(Component.translatable(whisperKey));
    }
}