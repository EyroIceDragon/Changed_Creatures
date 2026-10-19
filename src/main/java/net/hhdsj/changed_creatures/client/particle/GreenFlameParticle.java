package net.hhdsj.changed_creatures.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class GreenFlameParticle extends TextureSheetParticle {

    // 生命周期缩小参数
    private static final int SHRINK_START_AGE = 8;  // 开始缩小的年龄（寿命的一半）
    private static final float START_SCALE = 0.2f;   // 初始大小
    private static final float END_SCALE = 0f;     // 结束时的大小

    protected GreenFlameParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        this.setSize(0.2f, 0.2f);
        this.lifetime = 20;      // 寿命20刻
        this.gravity = 0f;

        // 速度为0，不移动
        this.xd = 0;
        this.yd = 0;
        this.zd = 0;

        // 设置发光颜色（绿色火焰）
        this.rCol = 0.2f;
        this.gCol = 0.8f;
        this.bCol = 0.2f;

        // 初始大小
        this.quadSize = START_SCALE;
    }

    @Override
    public void tick() {
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        if (this.age >= SHRINK_START_AGE) {
            float progress = (float)(this.age - SHRINK_START_AGE) / (this.lifetime - SHRINK_START_AGE);
            this.quadSize = START_SCALE - (START_SCALE - END_SCALE) * progress;
        }
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    public int getLightColor(float partialTick) {
        return 0xF000F0;  // 最大亮度，始终发光
    }

    public static Provider provider(SpriteSet spriteSet) {
        return new Provider(spriteSet);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            GreenFlameParticle particle = new GreenFlameParticle(level, x, y, z);
            particle.pickSprite(this.spriteSet);

            return particle;
        }
    }
}