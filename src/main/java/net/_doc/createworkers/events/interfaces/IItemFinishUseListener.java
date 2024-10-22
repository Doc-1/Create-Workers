package net._doc.createworkers.events.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/** Used on an {@link Item} to let it know it should listen for when the player finishes using the item's ItemStack. */
public interface IItemFinishUseListener {
    
    public void onItemFinishUse(Player player, Level level, ItemStack item, BlockPos pos);
}
