package net._doc.createworkers.items;

import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.world.item.Item;

public class CWItems {

	public static final ItemEntry<Item> MAINSPRING = CreateWorkers.REGISTRATE.item("mainspring", Item::new).register();
	public static final ItemEntry<Item> HOLE_PUNCHED_CARD = CreateWorkers.REGISTRATE
			.item("hole_punched_card", Item::new).register();

	public static final ItemEntry<HolePunchItem> HOLE_PUNCH = CreateWorkers.REGISTRATE
			.item("hole_punch", HolePunchItem::new).properties(p -> p.stacksTo(1))
			.model(AssetLookup.itemModel("hole_punch")).register();

	public static void register() {
	}
}
