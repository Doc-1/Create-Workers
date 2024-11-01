package net._doc.createworkers.capabilities.torque;

import net.minecraft.nbt.CompoundTag;

public class TorqueStorageHandler implements ITorqueStorageHandler {
    
    private double maxTorque;
    private double currentTorque;
    
    public TorqueStorageHandler() {
        this(0, 0);
    }
    
    public TorqueStorageHandler(double maxTorque) {
        this(maxTorque, maxTorque);
    }
    
    public TorqueStorageHandler(double maxTorque, double currentTorque) {
        this.maxTorque = maxTorque;
        this.currentTorque = currentTorque;
    }
    
    @Override
    public double getMaxTorque() {
        return this.maxTorque;
    }
    
    @Override
    public double getCurrentTorque() {
        return this.currentTorque;
    }
    
    @Override
    public void setTorque(double value) {
        currentTorque = Math.min(value, this.maxTorque);
    }
    
    @Override
    public boolean hasEnoughTorque(double cost) {
        return false;
    }
    
    @Override
    public boolean isEmpty() {
        return currentTorque <= 0;
    }
    
    @Override
    public boolean tryExpanse(double expense) {
        double ex;
        if ((ex = this.currentTorque - expense) >= 0) {
            this.setTorque(ex);
            return true;
        }
        return false;
    }
    
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("max_torque", maxTorque);
        tag.putDouble("current_torque", currentTorque);
        return tag;
        
    }
    
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (nbt.contains("max_torque"))
            maxTorque = nbt.getDouble("max_torque");
        if (nbt.contains("current_torque"))
            currentTorque = nbt.getDouble("current_torque");
    }
    
}
