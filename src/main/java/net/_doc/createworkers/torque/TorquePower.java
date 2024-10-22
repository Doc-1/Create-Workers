package net._doc.createworkers.torque;

public class TorquePower {
	public final double maxTorque;
	public double currentTorque;
	public final double costMulti;

	public TorquePower(double maxTorque, double costMulti) {
		this.maxTorque = maxTorque;
		this.currentTorque = maxTorque;
		this.costMulti = costMulti;
	}

	public boolean hasEnoughTorque(double cost) {
		return currentTorque - cost * costMulti >= 0;
	}

	public void cost(double cost) {
		currentTorque -= cost * costMulti;
	}

	public boolean isHasTorque() {
		return currentTorque > 0;
	}

}
