package net._doc.createworkers.items.goggles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public interface IProxyHoveringEntityInformation {
    
    public BlockPos getInformationSource(Level level, Entity entity);
}
