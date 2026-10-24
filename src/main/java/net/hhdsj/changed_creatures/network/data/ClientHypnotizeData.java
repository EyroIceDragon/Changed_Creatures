package net.hhdsj.changed_creatures.network.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientHypnotizeData {

    public static class MarkData {
        public final int level;
        public final long expireTime;

        public MarkData(int level, long expireTime) {
            this.level = level;
            this.expireTime = expireTime;
        }
    }

    private static final Map<UUID, MarkData> MARKS = new HashMap<>();
    private static final long DURATION_MS = 10000; // 10 秒有效期

    public static void setMarked(UUID attackerUUID, int level) {
        MARKS.put(attackerUUID, new MarkData(level, System.currentTimeMillis() + DURATION_MS));
    }

    public static Map<UUID, MarkData> getMarks() {
        return MARKS;
    }

    public static void tick() {
        long now = System.currentTimeMillis();
        MARKS.entrySet().removeIf(entry -> now > entry.getValue().expireTime);
    }

    public static void clear() {
        MARKS.clear();
    }

    public record TopAttackerInfo(UUID uuid, int level) {
    }

    public static TopAttackerInfo getTopAttacker() {
        UUID bestUuid = null;
        int bestLevel = -1;
        for (Map.Entry<UUID, MarkData> entry : MARKS.entrySet()) {
            if (entry.getValue().level > bestLevel) {
                bestLevel = entry.getValue().level;
                bestUuid = entry.getKey();
                System.out.println(bestUuid + " / " + bestLevel);
            }
        }
        return bestUuid != null ? new TopAttackerInfo(bestUuid, bestLevel) : null;
    }
}