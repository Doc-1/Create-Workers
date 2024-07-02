package net._doc.createworkers.content.equipment.goggles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.contraptions.piston.MechanicalPistonBlock;
import com.simibubi.create.content.contraptions.piston.PistonExtensionPoleBlock;
import com.simibubi.create.content.equipment.goggles.GogglesItem;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.equipment.goggles.IHaveHoveringInformation;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBox;
import com.simibubi.create.foundation.gui.RemovedGuiUtils;
import com.simibubi.create.foundation.gui.Theme;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import com.simibubi.create.foundation.outliner.Outline;
import com.simibubi.create.foundation.outliner.Outliner.OutlineEntry;
import com.simibubi.create.foundation.utility.Color;
import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.simibubi.create.infrastructure.config.CClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class AdditionalGoggleOverlayRenderer {

	public static final IGuiOverlay OVERLAY = AdditionalGoggleOverlayRenderer::renderOverlay;

	private static final Map<Object, OutlineEntry> outlines = CreateClient.OUTLINER.getOutlines();

	public static int hoverTicks = 0;
	public static BlockPos lastHovered = null;

	public static void renderOverlay(ForgeGui gui, GuiGraphics graphics, float partialTicks, int width, int height) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.options.hideGui || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
			return;

		HitResult objectMouseOver = mc.hitResult;
		if (!(objectMouseOver instanceof EntityHitResult)) {
			lastHovered = null;
			hoverTicks = 0;
			return;
		}

		for (OutlineEntry entry : outlines.values()) {
			if (!entry.isAlive())
				continue;
			Outline outline = entry.getOutline();
			if (outline instanceof ValueBox && !((ValueBox) outline).isPassive)
				return;
		}

		EntityHitResult result = (EntityHitResult) objectMouseOver;
		ClientLevel world = mc.level;
		Entity entity = result.getEntity();
		BlockPos pos = entity.getOnPos();

		int prevHoverTicks = hoverTicks;
		hoverTicks++;
		lastHovered = pos;

		pos = proxiedOverlayPosition(world, entity);

		boolean wearingGoggles = GogglesItem.isWearingGoggles(mc.player);

		boolean hasGoggleInformation = entity instanceof IHaveGoggleInformation;
		boolean hasHoveringInformation = entity instanceof IHaveHoveringInformation;

		boolean goggleAddedInformation = false;
		boolean hoverAddedInformation = false;

		List<Component> tooltip = new ArrayList<>();

		if (hasGoggleInformation && wearingGoggles) {
			IHaveGoggleInformation gte = (IHaveGoggleInformation) entity;
			goggleAddedInformation = gte.addToGoggleTooltip(tooltip, mc.player.isShiftKeyDown());
		}

		if (hasHoveringInformation) {
			if (!tooltip.isEmpty())
				tooltip.add(Components.immutableEmpty());
			IHaveHoveringInformation hte = (IHaveHoveringInformation) entity;
			hoverAddedInformation = hte.addToTooltip(tooltip, mc.player.isShiftKeyDown());

			if (goggleAddedInformation && !hoverAddedInformation)
				tooltip.remove(tooltip.size() - 1);
		}

		// break early if goggle or hover returned false when present
		if ((hasGoggleInformation && !goggleAddedInformation) && (hasHoveringInformation && !hoverAddedInformation)) {
			hoverTicks = 0;
			return;
		}

		// check for piston poles if goggles are worn
		BlockState state = world.getBlockState(pos);
		if (wearingGoggles && AllBlocks.PISTON_EXTENSION_POLE.has(state)) {
			Direction[] directions = Iterate
					.directionsInAxis(state.getValue(PistonExtensionPoleBlock.FACING).getAxis());
			int poles = 1;
			boolean pistonFound = false;
			for (Direction dir : directions) {
				int attachedPoles = PistonExtensionPoleBlock.PlacementHelper.get().attachedPoles(world, pos, dir);
				poles += attachedPoles;
				pistonFound |= world.getBlockState(pos.relative(dir, attachedPoles + 1))
						.getBlock() instanceof MechanicalPistonBlock;
			}

			if (!pistonFound) {
				hoverTicks = 0;
				return;
			}
			if (!tooltip.isEmpty())
				tooltip.add(Components.immutableEmpty());

			tooltip.add(IHaveGoggleInformation.componentSpacing.plainCopy()
					.append(Lang.translateDirect("gui.goggles.pole_length")).append(Components.literal(" " + poles)));
		}

		if (tooltip.isEmpty()) {
			hoverTicks = 0;
			return;
		}

		PoseStack poseStack = graphics.pose();
		poseStack.pushPose();

		int tooltipTextWidth = 0;
		for (FormattedText textLine : tooltip) {
			int textLineWidth = mc.font.width(textLine);
			if (textLineWidth > tooltipTextWidth)
				tooltipTextWidth = textLineWidth;
		}

		int tooltipHeight = 8;
		if (tooltip.size() > 1) {
			tooltipHeight += 2; // gap between title lines and next lines
			tooltipHeight += (tooltip.size() - 1) * 10;
		}

		CClient cfg = AllConfigs.client();
		int posX = width / 2 + cfg.overlayOffsetX.get();
		int posY = height / 2 + cfg.overlayOffsetY.get();

		posX = Math.min(posX, width - tooltipTextWidth - 20);
		posY = Math.min(posY, height - tooltipHeight - 20);

		float fade = Mth.clamp((hoverTicks + partialTicks) / 24f, 0, 1);
		Boolean useCustom = cfg.overlayCustomColor.get();
		Color colorBackground = useCustom ? new Color(cfg.overlayBackgroundColor.get())
				: Theme.c(Theme.Key.VANILLA_TOOLTIP_BACKGROUND).scaleAlpha(.75f);
		Color colorBorderTop = useCustom ? new Color(cfg.overlayBorderColorTop.get())
				: Theme.c(Theme.Key.VANILLA_TOOLTIP_BORDER, true).copy();
		Color colorBorderBot = useCustom ? new Color(cfg.overlayBorderColorBot.get())
				: Theme.c(Theme.Key.VANILLA_TOOLTIP_BORDER, false).copy();

		if (fade < 1) {
			poseStack.translate(Math.pow(1 - fade, 3) * Math.signum(cfg.overlayOffsetX.get() + .5f) * 8, 0, 0);
			colorBackground.scaleAlpha(fade);
			colorBorderTop.scaleAlpha(fade);
			colorBorderBot.scaleAlpha(fade);
		}

		RemovedGuiUtils.drawHoveringText(graphics, tooltip, posX, posY, width, height, -1, colorBackground.getRGB(),
				colorBorderTop.getRGB(), colorBorderBot.getRGB(), mc.font);

		ItemStack item = AllItems.GOGGLES.asStack();
		GuiGameElement.of(item).at(posX + 10, posY - 16, 450).render(graphics);
		poseStack.popPose();
	}

	public static BlockPos proxiedOverlayPosition(Level level, Entity entity) {
		if (entity instanceof IProxyHoveringEntityInformation proxy)
			return proxy.getInformationSource(level, entity);
		return entity.blockPosition();
	}

}
