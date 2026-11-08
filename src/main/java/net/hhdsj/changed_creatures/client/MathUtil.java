package net.hhdsj.changed_creatures.client;

public class MathUtil {
    private MathUtil() {}

    public static float toRadians(float degrees) {
        return degrees * (float) Math.PI / 180.0f;
    }

    public static double toRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }
}
