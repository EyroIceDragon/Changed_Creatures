package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ChangedCreatureModModelLayers {
    // 模型层位置（放在普通的工具类中，而不是Mixin中）
    public static final ModelLayerLocation TAIL_LAYER =
            new ModelLayerLocation(ChangedCreature.CCRL("tail"), "main");
}