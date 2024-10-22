package net._doc.createworkers.events.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IItemLeftClickBlockListener {
    public boolean onItemLeftClickBlock(Level level, Player player, ItemStack mainHand, ItemStack offHand, BlockPos lookingAt);
}
