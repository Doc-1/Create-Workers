package net._doc.createworkers.content.workers.menus;

import com.simibubi.create.foundation.gui.menu.GhostItemMenu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ScheduleWorkerMenu extends GhostItemMenu<ItemStack> {

	public ScheduleWorkerMenu(MenuType<?> type, int id, Inventory inv, ItemStack contentHolder) {
		super(type, id, inv, contentHolder);
	}

	public ScheduleWorkerMenu(MenuType<?> type, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(type, id, inv, extraData);
	}

	@Override
	protected ItemStackHandler createGhostInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean allowRepeats() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected ItemStack createOnClient(FriendlyByteBuf extraData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addSlots() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void saveData(ItemStack contentHolder) {
		// TODO Auto-generated method stub

	}

}
