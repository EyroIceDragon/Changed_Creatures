package net.hhdsj.changed_creatures.ability.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class PlayerAbilities {

    /** 能力表：ID → 数据 */
    private final Map<ResourceLocation, AbilityData> abilities = new HashMap<>();

    // ---------- 读写 ----------
    public AbilityData get(ResourceLocation id) {
        return abilities.computeIfAbsent(id, k -> new AbilityData());
    }

    public int getLevel(ResourceLocation id) {
        return get(id).level;
    }

    public void setLevel(ResourceLocation id, int level) {
        get(id).level = Math.max(0, level);
    }

    public void addExp(ResourceLocation id, int amount) {
        AbilityData data = get(id);
        data.exp += amount;
        // 每 100 经验升 1 级，最多 10 级
        while (data.exp >= 100 && data.level < 10) {
            data.exp -= 100;
            data.level++;
        }
    }

    public boolean hasAbility(ResourceLocation id) {
        return abilities.containsKey(id);
    }

    public Map<ResourceLocation, AbilityData> getAll() {
        return abilities;
    }

    // ---------- NBT ----------
    public Tag writeNBT() {
        CompoundTag nbt = new CompoundTag();
        CompoundTag listTag = new CompoundTag();
        for (Map.Entry<ResourceLocation, AbilityData> e : abilities.entrySet()) {
            CompoundTag a = new CompoundTag();
            a.putInt("level", e.getValue().level);
            a.putInt("exp", e.getValue().exp);
            a.putLong("cooldown", e.getValue().cooldown);
            listTag.put(e.getKey().toString(), a);
        }
        nbt.put("abilities", listTag);
        return nbt;
    }

    public void readNBT(Tag tag) {
        abilities.clear();
        if (!(tag instanceof CompoundTag nbt)) return;
        CompoundTag listTag = nbt.getCompound("abilities");
        for (String key : listTag.getAllKeys()) {
            try {
                ResourceLocation id = new ResourceLocation(key);
                CompoundTag a = listTag.getCompound(key);
                AbilityData data = new AbilityData();
                data.level = a.getInt("level");
                data.exp = a.getInt("exp");
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
        for (Map.Entry<ResourceLocation, AbilityData> e : other.abilities.entrySet()) {
            AbilityData src = e.getValue();
            AbilityData dst = new AbilityData();
            dst.level = src.level;
            dst.exp = src.exp;
            dst.cooldown = src.cooldown;
            abilities.put(e.getKey(), dst);
        }
    }
}