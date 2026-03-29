package com.academics.undertone.screen.custom;

import com.academics.undertone.block.ModBlocks;
import com.academics.undertone.entity.attachments.ModAttachments;
import com.academics.undertone.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class LevelingAltarMenu extends AbstractContainerMenu {
    private final Player player;
    private final ContainerLevelAccess access;
    private final ContainerData data;

    public LevelingAltarMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf extraData) {
        this(containerId, playerInventory, extraData.readBlockPos(), new SimpleContainerData(1));
    }

    public LevelingAltarMenu(int containerId, Inventory playerInventory, BlockPos blockPos) {
        this(containerId, playerInventory, blockPos, new SimpleContainerData(1));
    }

    private LevelingAltarMenu(int containerId, Inventory playerInventory, BlockPos blockPos, ContainerData data) {
        super(ModMenuTypes.LEVELING_ALTAR_MENU.get(), containerId);
        this.player = playerInventory.player;
        this.access = ContainerLevelAccess.create(this.player.level(), blockPos);
        this.data = data;
        this.addDataSlots(this.data);

        if (!this.player.level().isClientSide()) {
            this.data.set(0, this.player.getData(ModAttachments.ORBS.get()));
        }
    }

    @Override
    public void broadcastChanges() {
        if (!this.player.level().isClientSide()) {
            this.data.set(0, this.player.getData(ModAttachments.ORBS.get()));
        }
        super.broadcastChanges();
    }

    public int getOrbs() {
        return this.data.get(0);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.LEVELING_ALTAR.get());
    }
}
