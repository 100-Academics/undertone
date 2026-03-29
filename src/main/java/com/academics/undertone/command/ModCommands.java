package com.academics.undertone.command;

import com.academics.undertone.entity.attachments.ModAttachments;
import com.academics.undertone.player.PlayerAttributeLevels;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.concurrent.CompletableFuture;

public class ModCommands {
    // Keep Brigadier argument keys in one place to avoid mismatched string literals.
    private static final String ARG_TARGET = "target";
    private static final String ARG_AMOUNT = "amount";
    private static final String ARG_ATTRIBUTE = "attribute";
    private static final String ARG_LEVEL = "level";

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

        dispatcher.register(
                Commands.literal("uattr")
                        .requires(source -> source.hasPermission(2)) // Permission level 2 (OP)
                        .then(Commands.literal("set")
                                .then(Commands.argument(ARG_TARGET, EntityArgument.player())
                                        .then(Commands.argument(ARG_ATTRIBUTE, StringArgumentType.word())
                                                .suggests(ModCommands::suggestAttributes)
                                                .then(Commands.argument(ARG_LEVEL, IntegerArgumentType.integer(0))
                                                        .executes(context -> {
                                                            // Extract arguments
                                                            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, ARG_TARGET);
                                                            String attribute = StringArgumentType.getString(context, ARG_ATTRIBUTE);
                                                            int level = IntegerArgumentType.getInteger(context, ARG_LEVEL);

                                                            // Pass to your private method
                                                            return setAttributeLevel(context.getSource(), targetPlayer, attribute, level);
                                                        })
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("add")
                                .then(Commands.argument(ARG_TARGET, EntityArgument.player())
                                        .then(Commands.argument(ARG_ATTRIBUTE, StringArgumentType.word())
                                                .suggests(ModCommands::suggestAttributes)
                                                .then(Commands.argument(ARG_LEVEL, IntegerArgumentType.integer(1))
                                                        .executes(context -> {
                                                            // Extract arguments
                                                            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, ARG_TARGET);
                                                            String attribute = StringArgumentType.getString(context, ARG_ATTRIBUTE);
                                                            int level = IntegerArgumentType.getInteger(context, ARG_LEVEL);

                                                            // Pass to your private method
                                                            return addAttributeLevel(context.getSource(), targetPlayer, attribute, level);
                                                        })
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("remove")
                                .then(Commands.argument(ARG_TARGET, EntityArgument.player())
                                        .then(Commands.argument(ARG_ATTRIBUTE, StringArgumentType.word())
                                                .suggests(ModCommands::suggestAttributes)
                                                .then(Commands.argument(ARG_LEVEL, IntegerArgumentType.integer(1))
                                                        .executes(context -> {
                                                            // Extract arguments
                                                            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, ARG_TARGET);
                                                            String attribute = StringArgumentType.getString(context, ARG_ATTRIBUTE);
                                                            int level = IntegerArgumentType.getInteger(context, ARG_LEVEL);

                                                            // Pass to your private method
                                                            return addAttributeLevel(context.getSource(), targetPlayer, attribute, -level);
                                                        })
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("get")
                                .then(Commands.argument(ARG_TARGET, EntityArgument.player())
                                        .then(Commands.argument(ARG_ATTRIBUTE, StringArgumentType.word())
                                                .suggests(ModCommands::suggestAttributes)
                                                .executes(context -> {
                                                    // Extract arguments
                                                    ServerPlayer targetPlayer = EntityArgument.getPlayer(context, ARG_TARGET);
                                                    String attribute = StringArgumentType.getString(context, ARG_ATTRIBUTE);

                                                    // Pass to your private method
                                                    return getAttributeLevel(context.getSource(), targetPlayer, attribute);
                                                })
                                        )
                                )
                        )
                        .then(Commands.literal("clear")
                                .then(Commands.argument(ARG_TARGET, EntityArgument.player())
                                        .executes(context -> {
                                            // Extract arguments
                                            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, ARG_TARGET);

                                            // Pass to your private method
                                            return clearAttributeLevels(context.getSource(), targetPlayer);
                                        })
                                )
                        )
        );
    }

    private static CompletableFuture<Suggestions> suggestAttributes(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(PlayerAttributeLevels.entries().keySet(), builder);
    }

    private static PlayerAttributeLevels.Entry resolveEntry(String id) throws CommandSyntaxException {
        PlayerAttributeLevels.Entry entry = PlayerAttributeLevels.byId(id);
        if (entry == null) {
            throw new SimpleCommandExceptionType(Component.literal("Unknown attribute '" + id + "'.")).create();
        }
        return entry;
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
 
    public static int setAttributeLevel(CommandSourceStack source, ServerPlayer player, String attributeId, int level) throws CommandSyntaxException {
        PlayerAttributeLevels.Entry entry = resolveEntry(attributeId);
        int applied = PlayerAttributeLevels.setLevel(player, entry, level);

        source.sendSuccess(() -> Component.literal("Set " + entry.id() + " to level " + applied + " for " + player.getScoreboardName()), true);
        return applied;
    }

    public static int addAttributeLevel(CommandSourceStack source, ServerPlayer player, String attributeId, int delta) throws CommandSyntaxException {
        PlayerAttributeLevels.Entry entry = resolveEntry(attributeId);
        int applied = PlayerAttributeLevels.addLevel(player, entry, delta);

        source.sendSuccess(() -> Component.literal("Updated " + entry.id() + " to level " + applied + " for " + player.getScoreboardName()), true);
        return applied;
    }

    public static int getAttributeLevel(CommandSourceStack source, ServerPlayer player, String attributeId) throws CommandSyntaxException {
        PlayerAttributeLevels.Entry entry = resolveEntry(attributeId);
        int level = PlayerAttributeLevels.getLevel(player, entry);

        source.sendSuccess(() -> Component.literal(player.getScoreboardName() + " has " + entry.id() + " at level " + level), false);
        return level;
    }

    public static int clearAttributeLevels(CommandSourceStack source, ServerPlayer player) {
        PlayerAttributeLevels.clearAll(player);
        source.sendSuccess(() -> Component.literal("Cleared all custom attribute levels for " + player.getScoreboardName()), true);
        return 1;
    }

}
