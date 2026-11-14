package net.hhdsj.changed_creatures.client.models.other;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class CrystalModel extends Model {
    public static final ResourceLocation TEXTURE = ChangedCreature.CCRL("textures/entity/crystal/crystal.png");
    private final ModelPart crystal;

    public CrystalModel(ModelPart root) {
        super(RenderType::entityTranslucentEmissive);
        this.crystal = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer(){
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(2, 0).addBox(-10.0F, -7.0F, 7.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.001F))
                    .texOffs(2, 0).addBox(-10.0F, -7.8F, 7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F))
                    .texOffs(4, 1).addBox(-9.5F, -0.2F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(2, 0).addBox(-10.0F, -0.4F, 7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F))
                    .texOffs(4, 1).addBox(-9.5F, -8.1F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

            return LayerDefinition.create(meshdefinition, 16, 16);

    }

    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer buffer, int light, int overlay, float r, float g, float b, float a) {
        crystal.render(pose, buffer, light, overlay, r, g, b, a);
    }
}