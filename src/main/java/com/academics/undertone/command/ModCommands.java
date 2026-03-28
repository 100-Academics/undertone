package com.academics.undertone.command;

import com.academics.undertone.entity.attachments.ModAttachments;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class ModCommands {
    // Keep Brigadier argument keys in one place to avoid mismatched string literals.
    private static final String ARG_TARGET = "target";
    private static final String ARG_AMOUNT = "amount";

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("addorbs")
                        .requires(source -> source.hasPermission(2)) // Permission level 2 (OP)
                        .then(Commands.argument(ARG_TARGET, EntityArgument.player())
                                .then(Commands.argument(ARG_AMOUNT, IntegerArgumentType.integer(1))
                                        .executes(context -> {
                                            // Extract arguments
                                            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, ARG_TARGET);
                                            int amount = IntegerArgumentType.getInteger(context, ARG_AMOUNT);

                                            // Pass to your private method
                                            return addorbs(context.getSource(), targetPlayer, amount);
                                        })
                                )
                        )
        );

        dispatcher.register(
                Commands.literal("printorbs")
                        .then(Commands.argument(ARG_TARGET, EntityArgument.player())
                                .executes(context -> {
                                    // Extract arguments
                                    ServerPlayer targetPlayer = EntityArgument.getPlayer(context, ARG_TARGET);

                                    // Pass to your private method
                                    return printorbs(context.getSource(), targetPlayer);
                                })
                        )
        );
    }

    public static int addorbs(CommandSourceStack source, ServerPlayer player, int amount) {

        player.setData(ModAttachments.ORBS.get(), (player.getData(ModAttachments.ORBS.get()) + amount));
        source.sendSuccess(() -> Component.literal("Successfully added " + amount + " orbs to " + player.getScoreboardName()), true);

        return 1; // 1 = Success in Brigadier
    }

    public static int printorbs(CommandSourceStack source, ServerPlayer player) {
        int orbs = player.getData(ModAttachments.ORBS.get());
        source.sendSuccess(() -> Component.literal(player.getScoreboardName() + " has " + orbs + " orbs."), true);
        return 1; // 1 = Success in Brigadier
    }

}
