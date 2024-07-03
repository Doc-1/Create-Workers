package net._doc.createworkers.content.logic.torque;

public class TorquePower {
	public final double maxTorque;
	public double currentTorque = 100;
	public final double cost;

	public TorquePower(double maxTorque, double cost) {
		this.maxTorque = maxTorque;
		this.cost = cost;
	}

	public boolean hasEnoughTorque() {
		return currentTorque - cost >= 0;
	}

	public void tick(double distanceTravled) {
		if (hasEnoughTorque()) {
			currentTorque -= cost * Math.abs(distanceTravled);
		}
	}

}
