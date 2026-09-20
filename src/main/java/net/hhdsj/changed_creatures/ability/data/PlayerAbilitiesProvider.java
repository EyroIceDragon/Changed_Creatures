package net.hhdsj.changed_creatures.ability.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerAbilitiesProvider implements ICapabilitySerializable<Tag> {

    private final PlayerAbilities abilities = new PlayerAbilities();
    private final LazyOptional<PlayerAbilities> instance = LazyOptional.of(() -> abilities);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap,
                                                      @Nullable Direction side) {
        if (cap == PlayerAbilitiesCapability.PLAYER_ABILITIES) {
            return instance.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public Tag serializeNBT() {
        return abilities.writeNBT();
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        abilities.readNBT(nbt);
    }
}