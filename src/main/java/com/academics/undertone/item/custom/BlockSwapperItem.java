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

public class BlockSwapperItem extends Item { // ** custom item extends the base Item class, which allows us to override its methods to define custom behavior when the item is used.
    private static final int USE_COOLDOWN_TICKS = 5; // Cooldown to make sure holding right-click or the client/server double call doesn't cause the code to run multiple times in the same click.

    //stuff for swapping
    private Block toSwap2 = null;
    private BlockPos pos2 = null;
    private Block toSwap = null;
    private BlockPos pos1 = null;
    private int counter = 0;

    public BlockSwapperItem(Properties properties) { // ** required constructor
        super(properties);
    }

    @Override // ** @Override indicates that we are overriding a method from the base class. This means we effectively inject custom code into the existing method.
    public InteractionResult useOn(UseOnContext context) { // ** this method is called when the player right-clicks on a block with this item in their hand.
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS; // return if on client side -- want to do most things on server side | This also prevents a double-trigger issue.
        }

        // Debounce on the server so holding right-click (or client/server double call) won't spam.
        if (player != null) {
            if (player.getCooldowns().isOnCooldown(this)) {
                return InteractionResult.PASS;
            }
            player.getCooldowns().addCooldown(this, USE_COOLDOWN_TICKS);
        }

        if (counter == 0) { // counter == 0 -> first click
            toSwap = level.getBlockState(context.getClickedPos()).getBlock(); // set block
            pos1 = context.getClickedPos(); // set pos
            counter = 1;
            Undertone.LOGGER.info("BlockSwapper: First block selected: " + toSwap.getName().getString() + " at " + pos1.toShortString()); // log
            return InteractionResult.SUCCESS; // return
        }


        toSwap2 = level.getBlockState(context.getClickedPos()).getBlock(); // counter == 1 -> second click
        pos2 = context.getClickedPos();

        Undertone.LOGGER.info("BlockSwapper: Second block selected: " + toSwap2.getName().getString() + " at " + pos2.toShortString()); // log

        if ((toSwap != null && toSwap2 != null) && player != null) { // if both blocks and player are valid, swap the blocks
            level.setBlock(pos1, toSwap2.defaultBlockState(), 3);
            level.setBlock(pos2, toSwap.defaultBlockState(), 3);

            context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), player, // looks nasty, does durability damage
                    item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            level.playSound(null, pos1, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F); // sfx
            level.playSound(null, pos2, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F); // sfx

            Undertone.LOGGER.info("BlockSwapper: Swapped " + toSwap.getName().getString() + " at " + pos1.toShortString() + " with " + toSwap2.getName().getString() + " at " + pos2.toShortString()); // log the swap
        }

        counter = 0;
        return InteractionResult.SUCCESS;
    }
}
