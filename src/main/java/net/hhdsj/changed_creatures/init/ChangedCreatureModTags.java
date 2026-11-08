package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraft.tags.TagKey;

public final class ChangedCreatureModTags {

    private static class TagsHolder {
        private static final TagKey<TransfurVariant<?>> SLOW_INFECTION =
                createTag("slow_infection_variants");
        private static final TagKey<TransfurVariant<?>> ANTI_FIRE_LATEX =
                createTag("anti_fire_latex");

        private static TagKey<TransfurVariant<?>> createTag(String name) {
            return TagKey.create(
                    ChangedRegistry.TRANSFUR_VARIANT.get().getRegistryKey(),
                    ChangedCreature.CCRL(name)
            );
        }
    }

    public static TagKey<TransfurVariant<?>> slowInfectionVariants() {
        return TagsHolder.SLOW_INFECTION;
    }

    public static TagKey<TransfurVariant<?>> antiFireLatex() {
        return TagsHolder.ANTI_FIRE_LATEX;
    }
}