package net._doc.createworkers.capabilities.torque;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ITorqueStorage {
    
    public default void cost(double expense) {
        setTorque(getCurrentTorque() * expense);
    }
    
    public double getMaxTorque();
    
    public double getCurrentTorque();
    
    public void setTorque(double value);
}
