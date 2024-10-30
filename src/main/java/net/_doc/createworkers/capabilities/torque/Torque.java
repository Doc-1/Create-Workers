package net._doc.createworkers.capabilities.torque;

public class Torque implements ITorqueStorage {
    
    private final double maxTorque;
    private double currentTorque;
    
    public Torque(double maxTorque) {
        this.maxTorque = maxTorque;
        this.currentTorque = maxTorque;
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
    
}
