package net._doc.createworkers.registeries;

import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.content.items.HolePuncherItem;
import net._doc.createworkers.content.items.HolePunchedCardItem;
import net.minecraft.world.item.Item;

public class CWItems {
    
    public static final ItemEntry<Item> MAINSPRING = CreateWorkers.REGISTRATE.item("mainspring", Item::new).register();
    public static final ItemEntry<HolePunchedCardItem> HOLE_PUNCHED_CARD = CreateWorkers.REGISTRATE.item("hole_punched_card", HolePunchedCardItem::new).register();
    public static final ItemEntry<Item> CONSTRUCTION_PAPER_PULP = CreateWorkers.REGISTRATE.item("construction_paper_pulp", Item::new).register();
    public static final ItemEntry<Item> PAPER_PULP = CreateWorkers.REGISTRATE.item("paper_pulp", Item::new).register();
    public static final ItemEntry<HolePuncherItem> HOLE_PUNCHER = CreateWorkers.REGISTRATE.item("hole_puncher", HolePuncherItem::new).properties(p -> p.stacksTo(1))
            .model(AssetLookup.itemModel("hole_puncher")).register();
    
    public static void register() {}
}
