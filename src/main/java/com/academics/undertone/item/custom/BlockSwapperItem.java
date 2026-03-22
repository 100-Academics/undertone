package com.academics.undertone.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import com.academics.undertone.Undertone;

public class BlockSwapperItem extends Item {
    // Cooldown to make sure holding right-click or the client/server double call doesn't cause the code to run multiple times in the same click.
    private static final int USE_COOLDOWN_TICKS = 5;

    private Block toSwap2 = null;
    private BlockPos pos2 = null;
    private Block toSwap = null;
    private BlockPos pos1 = null;
    private int counter = 0;

    public BlockSwapperItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        // Only run the state machine on the server.
        // The client also calls useOn for prediction/animation; running logic on both sides looks like "double triggering".
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // Debounce on the server so holding right-click (or client/server double call) won't spam.
        if (player != null) {
            if (player.getCooldowns().isOnCooldown(this)) {
                return InteractionResult.PASS;
            }
            player.getCooldowns().addCooldown(this, USE_COOLDOWN_TICKS);
        }

        if (counter == 0) {
            toSwap = level.getBlockState(context.getClickedPos()).getBlock();
            pos1 = context.getClickedPos();
            counter = 1;
            Undertone.LOGGER.info("BlockSwapper: First block selected: " + toSwap.getName().getString() + " at " + pos1.toShortString());
            return InteractionResult.SUCCESS;
        }

        // counter == 1 -> second click
        toSwap2 = level.getBlockState(context.getClickedPos()).getBlock();
        pos2 = context.getClickedPos();

        Undertone.LOGGER.info("BlockSwapper: Second block selected: " + toSwap2.getName().getString() + " at " + pos2.toShortString());

        if ((toSwap != null && toSwap2 != null) && player != null) {
            level.setBlock(pos1, toSwap2.defaultBlockState(), 3);
            level.setBlock(pos2, toSwap.defaultBlockState(), 3);

            context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), player,
                    item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            level.playSound(null, pos1, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.playSound(null, pos2, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

            Undertone.LOGGER.info("BlockSwapper: Swapped " + toSwap.getName().getString() + " at " + pos1.toShortString() + " with " + toSwap2.getName().getString() + " at " + pos2.toShortString());
        }

        counter = 0;
        return InteractionResult.SUCCESS;
    }
}
