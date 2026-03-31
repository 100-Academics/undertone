package com.academics.undertone.screen.custom;

import com.academics.undertone.player.PlayerAttributeLevels;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class LevelingAltarScreen extends AbstractContainerScreen<LevelingAltarMenu> {
	public LevelingAltarScreen(LevelingAltarMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageWidth = 176;
		this.imageHeight = 160;
		this.inventoryLabelY = 10000;
	}

	@Override
	protected void init() {
		super.init();

		int index = 0;
		for (PlayerAttributeLevels.Entry entry : PlayerAttributeLevels.entries().values()) {
			int column = index % 2;
			int row = index / 2;
			int x = this.leftPos + 8 + (column * 82);
			int y = this.topPos + 38 + (row * 22);

			this.addRenderableWidget(
					Button.builder(Component.literal(formatAttributeLabel(entry.id())), button -> {
						// Hook this up to a server packet when upgrade logic is implemented.
					}).bounds(x, y, 78, 20).build()
			);

			index++;
		}
	}

	private static String formatAttributeLabel(String id) {
		String[] words = id.split("_");
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			if (word.isEmpty()) {
				continue;
			}
			if (!builder.isEmpty()) {
				builder.append(' ');
			}
			builder.append(Character.toUpperCase(word.charAt(0)));
			if (word.length() > 1) {
				builder.append(word.substring(1));
			}
		}
		return builder.toString() ;
	}

    private void changeLevels(int entry){
        // TODO
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
