package net.hhdsj.changed_creatures.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.hhdsj.changed_creatures.ability.data.GlobalExpSyncPacket;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilities;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilitiesCapability;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LatexExpCommand {
    private static final IntegerArgumentType VALUE_ARG_SET =
            IntegerArgumentType.integer(0);

    private static final IntegerArgumentType VALUE_ARG = IntegerArgumentType.integer();

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("latexexp")
                        .then(Commands.literal("add")
                                .then(Commands.argument("value",VALUE_ARG)
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ServerLevel world = ctx.getSource().getLevel();
                                            Entity entity = ctx.getSource().getEntity();
                                            if (entity == null)
                                                entity = FakePlayerFactory.getMinecraft(world);

                                            if (entity instanceof Player player) {
                                                if (player.level().isClientSide()) return 0;
                                                PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
                                                abilities.addPlayerExp(value);
                                                if (player instanceof ServerPlayer sp) {
                                                    GlobalExpSyncPacket.send(sp);
                                                }
                                            }
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("set")
                                .then(Commands.argument("value", VALUE_ARG_SET)
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ServerLevel world = ctx.getSource().getLevel();
                                            Entity entity = ctx.getSource().getEntity();
                                            if (entity == null)
                                                entity = FakePlayerFactory.getMinecraft(world);

                                            if (entity instanceof Player player) {
                                                if (player.level().isClientSide()) return 0;
                                                PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
                                                abilities.setPlayerExp(value);
                                                if (player instanceof ServerPlayer sp) {
                                                    GlobalExpSyncPacket.send(sp);
                                                }
                                            }
                                            return 1;
                                        })
                                )
                        )
        );
    }
}