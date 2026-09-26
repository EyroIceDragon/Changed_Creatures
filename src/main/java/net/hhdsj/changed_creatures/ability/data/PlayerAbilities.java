package net.hhdsj.changed_creatures.ability.data;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerAbilities {

    private final Map<ResourceLocation, AbilityData> abilities = new HashMap<>();
    private int playerExp = 0;

    public int getPlayerExp() {
        return playerExp;
    }

    public void setPlayerExp(int exp) {
        this.playerExp = Math.max(0, exp);
    }

    public void addPlayerExp(int amount) {
        this.playerExp = Math.max(0, this.playerExp + amount);
    }

    public boolean enoughExp(AbstractAbility ability, int level) {
        if (ability == null) return false;
        int cost = Math.round(ability.useExp(level + 1));
        return playerExp >= cost;
    }

    public boolean consumeExp(AbstractAbility ability, int level) {
        if (ability == null) return false;
        int cost = Math.round(ability.useExp(level + 1));
        if (playerExp < cost) return false;
        playerExp -= cost;
        return true;
    }

    public void ereturnExp(AbstractAbility ability, int level) {
        if (ability == null) return;
        int cost = Math.round(ability.useExp(level));
        if (playerExp < cost) return;
        playerExp += cost;
    }

    // ---------- 能力读写 ----------
    public AbilityData get(ResourceLocation id) {
        return abilities.computeIfAbsent(id, k -> new AbilityData());
    }

    public int getLevel(ResourceLocation id) {
        AbilityData data = abilities.get(id);
        return data == null ? 0 : data.level;
    }

    public void setLevel(ResourceLocation id, int level) {
        get(id).level = Math.max(0, level);
    }

    public boolean hasAbility(ResourceLocation id) {
        AbilityData data = abilities.get(id);
        return data != null;
    }


    public Map<ResourceLocation, AbilityData> getAll() {
        return abilities;
    }

    // ---------- NBT ----------
    public Tag writeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("player_exp", playerExp);
        CompoundTag listTag = new CompoundTag();
        for (Map.Entry<ResourceLocation, AbilityData> e : abilities.entrySet()) {
            CompoundTag a = new CompoundTag();
            a.putInt("level", e.getValue().level);
            a.putLong("cooldown", e.getValue().cooldown);
            listTag.put(e.getKey().toString(), a);
        }
        nbt.put("abilities", listTag);
        return nbt;
    }

    public void readNBT(Tag tag) {
        abilities.clear();
        playerExp = 0;
        if (!(tag instanceof CompoundTag nbt)) return;
        playerExp = nbt.getInt("player_exp");
        CompoundTag listTag = nbt.getCompound("abilities");
        for (String key : listTag.getAllKeys()) {
            try {
                ResourceLocation id = new ResourceLocation(key);
                CompoundTag a = listTag.getCompound(key);
                AbilityData data = new AbilityData();
                data.level = a.getInt("level");
                data.cooldown = a.getLong("cooldown");
                abilities.put(id, data);
            } catch (Exception ex) {
                // 忽略非法 key
            }
        }
    }

    // ---------- 复制（重生/换维度用） ----------
    public void copyFrom(PlayerAbilities other) {
        abilities.clear();
        this.playerExp = other.playerExp;
        for (Map.Entry<ResourceLocation, AbilityData> e : other.abilities.entrySet()) {
            AbilityData src = e.getValue();
            AbilityData dst = new AbilityData();
            dst.level = src.level;
            dst.cooldown = src.cooldown;
            abilities.put(e.getKey(), dst);
        }
    }
}