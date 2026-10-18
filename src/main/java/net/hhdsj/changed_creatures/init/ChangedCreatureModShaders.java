package net.hhdsj.changed_creatures.init;

import com.mojang.blaze3d.shaders.Uniform;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class ChangedCreatureModShaders {

    public static final ResourceLocation MENTAL_CONFUSION_ID =
            ChangedCreature.CCRL("mental_confusion");

    @Nullable
    private static ShaderInstance mentalConfusionShader;

    public static ShaderInstance getMentalConfusionShader() {
        if (mentalConfusionShader == null) {
            throw new IllegalStateException("mental_confusion shader 尚未加载完成");
        }
        return mentalConfusionShader;
    }

    public static void setMentalConfusionShader(@Nullable ShaderInstance shader) {
        mentalConfusionShader = shader;
    }

    public static Optional<Uniform> getUniform(ShaderInstance shader, String name) {
        return Optional.ofNullable(shader.getUniform(name));
    }
}