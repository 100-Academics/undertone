package com.academics.undertone.block.entity;

import com.academics.undertone.screen.custom.LevelingAltarMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LevelingAltarEntity extends BlockEntity implements MenuProvider {

    public LevelingAltarEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LEVELING_ALTAR_BE.get(), pos, blockState);
    }


    @Override
    public Component getDisplayName() {
        return Component.literal("Leveling Altar");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new LevelingAltarMenu(containerId, playerInventory, this.getBlockPos());
    }
}
