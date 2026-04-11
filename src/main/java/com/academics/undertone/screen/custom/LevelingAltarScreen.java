package com.academics.undertone.screen.custom;

import com.academics.undertone.Undertone;
import com.academics.undertone.player.PlayerAttributeLevels;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
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
			final int buttonId = index;

			this.addRenderableWidget(Button.builder(Component.literal(formatAttributeLabel(entry.id())), button -> this.openConfirm(buttonId))
					.bounds(x, y, 78, 20)
					.build());

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
		return builder.toString();
	}

	private void changeLevel(int entry) {
		if (this.minecraft != null && this.minecraft.gameMode != null) {
			Undertone.LOGGER.info("Screen sending handleInventoryButtonClick: containerId={}, buttonId={}", this.menu.containerId, entry);
			this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, entry);
		}
	}

	private void openConfirm(int buttonId) {
		if (this.minecraft == null || this.minecraft.player == null) {
			return;
		}

		PlayerAttributeLevels.Entry entry = PlayerAttributeLevels.byIndex(buttonId);
		if (entry == null) {
			return;
		}

		int currentLevel = PlayerAttributeLevels.getLevel(this.minecraft.player, entry);
		if (currentLevel >= entry.maxLevel()) {
			return;
		}

		int cost = (int) Math.ceil(PlayerAttributeLevels.getCostForLevel(entry, currentLevel));
		boolean canAfford = this.menu.getOrbs() >= cost;
		this.minecraft.setScreen(new ConfirmSpendScreen(this, buttonId, formatAttributeLabel(entry.id()), cost, canAfford));
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

	private static class ConfirmSpendScreen extends Screen {
		private final LevelingAltarScreen parent;
		private final int buttonId;
		private final String attributeLabel;
		private final int cost;
		private final boolean canAfford;

		protected ConfirmSpendScreen(LevelingAltarScreen parent, int buttonId, String attributeLabel, int cost, boolean canAfford) {
			super(Component.literal("Confirm Upgrade"));
			this.parent = parent;
			this.buttonId = buttonId;
			this.attributeLabel = attributeLabel;
			this.cost = cost;
			this.canAfford = canAfford;
		}

		@Override
		protected void init() {
			int panelWidth = 220;
			int panelHeight = 90;
			int panelLeft = (this.width - panelWidth) / 2;
			int panelTop = (this.height - panelHeight) / 2;

			this.addRenderableWidget(Button.builder(Component.literal("Yes"), button -> {
				Undertone.LOGGER.info("Yes button clicked with buttonId: {}, canAfford: {}", this.buttonId, this.canAfford);
				this.parent.changeLevel(this.buttonId);
				this.closeToParent();
			}).bounds(panelLeft + 35, panelTop + 58, 65, 20).build()).active = this.canAfford;

			this.addRenderableWidget(Button.builder(Component.literal("No"), button -> this.closeToParent())
					.bounds(panelLeft + 120, panelTop + 58, 65, 20)
					.build());
		}

		@Override
		public boolean mouseClicked(double mouseX, double mouseY, int button) {
			if (super.mouseClicked(mouseX, mouseY, button)) {
				return true;
			}
			this.closeToParent();
			return true;
		}

		@Override
		public void onClose() {
			this.closeToParent();
		}

		private void closeToParent() {
			if (this.minecraft != null) {
				this.minecraft.setScreen(this.parent);
			}
		}

		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
			int panelWidth = 220;
			int panelHeight = 90;
			int panelLeft = (this.width - panelWidth) / 2;
			int panelTop = (this.height - panelHeight) / 2;

			guiGraphics.fill(0, 0, this.width, this.height, 0x66000000);
			guiGraphics.fill(panelLeft, panelTop, panelLeft + panelWidth, panelTop + panelHeight, 0xFFC6C6C6);
			guiGraphics.fill(panelLeft, panelTop, panelLeft + panelWidth, panelTop + 1, 0xFFFFFFFF);
			guiGraphics.fill(panelLeft, panelTop, panelLeft + 1, panelTop + panelHeight, 0xFFFFFFFF);
			guiGraphics.fill(panelLeft + panelWidth - 1, panelTop, panelLeft + panelWidth, panelTop + panelHeight, 0xFF555555);
			guiGraphics.fill(panelLeft, panelTop + panelHeight - 1, panelLeft + panelWidth, panelTop + panelHeight, 0xFF555555);

			// Render buttons through super (handles clicks), then override blur methods below.
			super.render(guiGraphics, mouseX, mouseY, partialTick);

			String line1 = "Spend " + this.cost + " orbs";
			String line2 = "on " + this.attributeLabel + "?";
			int line1X = panelLeft + (panelWidth - this.font.width(line1)) / 2;
			int line2X = panelLeft + (panelWidth - this.font.width(line2)) / 2;
			guiGraphics.drawString(this.font, line1, line1X, panelTop + 18, 0x202020, false);
			guiGraphics.drawString(this.font, line2, line2X, panelTop + 32, 0x202020, false);
		}

		@Override
		public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
			// Override to prevent vanilla background blur.
		}

		@Override
		public void renderTransparentBackground(GuiGraphics guiGraphics) {
			// Override to prevent vanilla transparent background blur.
		}

		@Override
		protected void renderBlurredBackground(float partialTick) {
			// Override to prevent blur effect.
		}
	}
}
