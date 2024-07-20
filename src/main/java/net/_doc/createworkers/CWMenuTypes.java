package net._doc.createworkers;

import com.tterrag.registrate.builders.MenuBuilder.ForgeMenuFactory;
import com.tterrag.registrate.builders.MenuBuilder.ScreenFactory;
import com.tterrag.registrate.util.entry.MenuEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net._doc.createworkers.content.workers.menus.ScheduleWorkerMenu;
import net._doc.createworkers.content.workers.screens.ScheduleWorkerScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class CWMenuTypes {

	public static final MenuEntry<ScheduleWorkerMenu> SCHEDULE_WORKER = register("schedule_worker",
			ScheduleWorkerMenu::new, () -> ScheduleWorkerScreen::new);

	private static <C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> MenuEntry<C> register(
			String name, ForgeMenuFactory<C> factory, NonNullSupplier<ScreenFactory<C, S>> screenFactory) {
		return CreateWorkers.REGISTRATE.menu(name, factory, screenFactory).register();
	}

	public static void register() {
	}
}
