package net._doc.createworkers.capabilities.torque;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface ITorqueStorageHandler extends INBTSerializable<CompoundTag> {
    
    public boolean tryExpanse(double expense);
    
    public double getMaxTorque();
    
    public double getCurrentTorque();
    
    public void setTorque(double value);
    
    public boolean hasEnoughTorque(double cost);
    
    public boolean isEmpty();
    
}
