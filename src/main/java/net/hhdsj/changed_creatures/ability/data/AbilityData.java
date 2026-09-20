package net.hhdsj.changed_creatures.ability.data;

public class AbilityData {
    public int level = 0;
    public int exp = 0;
    public long cooldown = 0;

    public boolean isOnCooldown() {
        return System.currentTimeMillis() < cooldown;
    }

    public void setCooldown(long ms) {
        this.cooldown = System.currentTimeMillis() + ms;
    }
}