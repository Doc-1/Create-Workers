package net._doc.createworkers.items;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CWItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			CreateWorkers.MOD_ID);

	public static final RegistryObject<Item> MAINSPRING = ITEMS.register("mainspring",
			() -> new Item(new Item.Properties()));

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
