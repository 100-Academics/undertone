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
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("addorbs")
                        .requires(source -> source.hasPermission(2)) // Permission level 2 (OP)
                        .then(Commands.argument("target", EntityArgument.player())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                                        .executes(context -> {
                                            // Extract arguments
                                            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, "target");
                                            int amount = IntegerArgumentType.getInteger(context, "amount");

                                            // Pass to your private method
                                            return addorbs(context.getSource(), targetPlayer, amount);
                                        })
                                )
                        )
        );

        dispatcher.register(
                Commands.literal("printorbs")
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(context -> {
                                    // Extract arguments
                                    ServerPlayer targetPlayer = EntityArgument.getPlayer(context, "target");

                                    // Pass to your private method
                                    return printorbs(context.getSource(), targetPlayer);
                                })
                        )
        );
    }

    private static int addorbs(CommandSourceStack source, ServerPlayer player, int amount) {

        player.setData(ModAttachments.ORBS.get(), (player.getData(ModAttachments.ORBS.get()) + amount));
        source.sendSuccess(() -> Component.literal("Successfully added " + amount + " orbs to " + player.getScoreboardName()), true);

        return 1; // 1 = Success in Brigadier
    }

    private static int printorbs(CommandSourceStack source, ServerPlayer player) {
        int orbs = player.getData(ModAttachments.ORBS.get());
        source.sendSuccess(() -> Component.literal(player.getScoreboardName() + " has " + orbs + " orbs."), true);
        return 1; // 1 = Success in Brigadier
    }

}
