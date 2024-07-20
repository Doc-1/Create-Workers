package net._doc.createworkers.content.workers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.simibubi.create.AllItems;
import com.simibubi.create.CreateClient;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.RaycastHelper;

import net._doc.createworkers.items.CWItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class WorkerInteractionPointHandler {

	static List<WorkerInteractionPoint> currentSelection = new ArrayList<>();
	static ItemStack currentItem;

	static long lastBlockPos = -1;

	@SubscribeEvent
	public static void finishUsingItemSelectsThem(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() instanceof Player player) {
			if (currentItem == null)
				return;

			Level world = event.getEntity().level();
			BlockPos pos = RaycastHelper.rayTraceRange(world, player, player.getBlockReach()).getBlockPos();
			if (!world.isClientSide)
				return;
			if (player != null && player.isSpectator())
				return;

			ItemStack useItem = event.getItem();
			if (useItem.getItem().equals(CWItems.HOLE_PUNCHER.get())) {
				WorkerInteractionPoint selected = getSelected(pos);
				BlockState state = world.getBlockState(pos);
				if (selected == null) {
					WorkerInteractionPoint point = WorkerInteractionPoint.create(world, pos, state);
					if (point == null)
						return;
					selected = point;
					put(point);
				}

				if (player != null) {
					Lang.builder().translate("test", Lang.blockName(state).style(ChatFormatting.WHITE)).color(0x001111)
							.sendStatus(player);
				}
			}

		}
	}

	@SubscribeEvent
	public static void leftClickingBlocksDeselectsThem(PlayerInteractEvent.LeftClickBlock event) {
		if (currentItem == null)
			return;
		if (!event.getLevel().isClientSide)
			return;
		BlockPos pos = event.getPos();
		if (remove(pos) != null) {
			event.setCanceled(true);
			event.setCancellationResult(InteractionResult.SUCCESS);
		}
	}

	public static void flushSettings(BlockPos pos) {
	}

	public static void tick() {
		Player player = Minecraft.getInstance().player;

		if (player == null)
			return;

		ItemStack heldItemMainhand = player.getMainHandItem();

		if (!heldItemMainhand.getItem().equals(CWItems.HOLE_PUNCHER.get())) {
			currentItem = null;
		} else {
			if (heldItemMainhand != currentItem) {
				currentSelection.clear();
				currentItem = heldItemMainhand;
			}

			drawOutlines(currentSelection);
		}

		checkForWrench(heldItemMainhand);
	}

	private static void checkForWrench(ItemStack heldItem) {
		if (!AllItems.WRENCH.isIn(heldItem)) {
			return;
		}

		HitResult objectMouseOver = Minecraft.getInstance().hitResult;
		if (!(objectMouseOver instanceof BlockHitResult)) {
			return;
		}

		BlockHitResult result = (BlockHitResult) objectMouseOver;
		BlockPos pos = result.getBlockPos();

		BlockEntity be = Minecraft.getInstance().level.getBlockEntity(pos);

		if (lastBlockPos == -1 || lastBlockPos != pos.asLong()) {
			/*
			 * currentSelection.clear(); ArmBlockEntity arm = (ArmBlockEntity) be;
			 * arm.inputs.forEach(WorkerInteractionPointHandler::put);
			 * arm.outputs.forEach(WorkerInteractionPointHandler::put); lastBlockPos =
			 * pos.asLong();
			 */}

		if (lastBlockPos != -1) {
			drawOutlines(currentSelection);
		}
	}

	private static void drawOutlines(Collection<WorkerInteractionPoint> selection) {
		for (Iterator<WorkerInteractionPoint> iterator = selection.iterator(); iterator.hasNext();) {
			WorkerInteractionPoint point = iterator.next();

			if (!point.isValid()) {
				iterator.remove();
				continue;
			}

			Level level = point.getLevel();
			BlockPos pos = point.getPos();
			BlockState state = level.getBlockState(pos);
			VoxelShape shape = state.getShape(level, pos);
			if (shape.isEmpty())
				continue;

			int color = point.getMode().getColor();
			CreateClient.OUTLINER.showAABB(point, shape.bounds().move(pos)).colored(color).lineWidth(1 / 16f);
		}
	}

	private static void put(WorkerInteractionPoint point) {
		currentSelection.add(point);
	}

	private static WorkerInteractionPoint remove(BlockPos pos) {
		WorkerInteractionPoint result = getSelected(pos);
		if (result != null)
			currentSelection.remove(result);
		return result;
	}

	private static WorkerInteractionPoint getSelected(BlockPos pos) {
		for (WorkerInteractionPoint point : currentSelection)
			if (point.getPos().equals(pos))
				return point;
		return null;
	}

}