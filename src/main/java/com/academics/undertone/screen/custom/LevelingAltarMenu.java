package com.academics.undertone.screen.custom;

import com.academics.undertone.block.ModBlocks;
import com.academics.undertone.entity.attachments.ModAttachments;
import com.academics.undertone.player.PlayerAttributeLevels;
import com.academics.undertone.screen.ModMenuTypes;
import com.academics.undertone.Undertone;
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

    public void changeLevel(PlayerAttributeLevels.Entry entry) {
        int currentLevel = PlayerAttributeLevels.getLevel(this.player, entry);
        Undertone.LOGGER.info("changeLevel called for {}, current level: {}", entry.id(), currentLevel);
        if (currentLevel < entry.maxLevel()) {
            int orbs = this.player.getData(ModAttachments.ORBS.get());
            int cost = (int) Math.ceil(PlayerAttributeLevels.getCostForLevel(entry, currentLevel));
            Undertone.LOGGER.info("Orbs: {}, Cost: {}, Can afford: {}", orbs, cost, orbs >= cost);
            if (orbs >= cost) {
                Undertone.LOGGER.info("Spending {} orbs on {} (level {} -> {})", cost, entry.id(), currentLevel, currentLevel + 1);
                PlayerAttributeLevels.setLevel(this.player, entry, currentLevel + 1);
                int updatedOrbs = orbs - cost;
                this.player.setData(ModAttachments.ORBS.get(), updatedOrbs);
                this.data.set(0, updatedOrbs);
                this.broadcastChanges();
                Undertone.LOGGER.info("Updated player to level {} with {} orbs remaining", currentLevel + 1, updatedOrbs);
            }
        }
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        Undertone.LOGGER.info("clickMenuButton called with id: {}", id);
        PlayerAttributeLevels.Entry entry = PlayerAttributeLevels.byIndex(id);

        if (entry == null) {
            Undertone.LOGGER.warn("Entry was null for id: {}", id);
            return false;
        }

        this.changeLevel(entry);
        return true;
    }
}
