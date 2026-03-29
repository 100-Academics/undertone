package com.academics.undertone.screen.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class LevelingAltarScreen extends AbstractContainerScreen<LevelingAltarMenu> {
	public LevelingAltarScreen(LevelingAltarMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageWidth = 176;
		this.imageHeight = 96;
		this.inventoryLabelY = 10000;
	}

	@Override
	protected void init() {
		super.init();

		this.addRenderableWidget(
				Button.builder(Component.literal("Level Up"), button -> {
					// Hook this up to a server packet when upgrade logic is implemented.
				}).bounds(this.leftPos + 38, this.topPos + 60, 100, 20).build()
		);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int left = this.leftPos;
		int top = this.topPos;
		int right = left + this.imageWidth;
		int bottom = top + this.imageHeight;

		// Vanilla-like panel colors: light body with darker border.
		guiGraphics.fill(left, top, right, bottom, 0xFFC6C6C6);
		guiGraphics.fill(left, top, right, top + 1, 0xFFFFFFFF);
		guiGraphics.fill(left, top, left + 1, bottom, 0xFFFFFFFF);
		guiGraphics.fill(right - 1, top, right, bottom, 0xFF555555);
		guiGraphics.fill(left, bottom - 1, right, bottom, 0xFF555555);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		int titleX = (this.imageWidth - this.font.width(this.title)) / 2;
		guiGraphics.drawString(this.font, this.title, titleX, 8, 0x404040, false);
		guiGraphics.drawString(this.font, Component.literal("Orbs: " + this.menu.getOrbs()), 8, 28, 0x404040, false);
	}
}
