package net._doc.createworkers.content.logic.torque;

public class TorquePower {
	public final int maxTorque;
	public int currentTorque = 100;
	public final int cost;

	public TorquePower(int maxTorque, int cost) {
		this.maxTorque = maxTorque;
		this.cost = cost;
	}

	public boolean hasEnoughTorque() {
		return currentTorque - cost >= 0;
	}

	public void tick() {
		if (hasEnoughTorque())
			currentTorque -= cost;
	}

}
