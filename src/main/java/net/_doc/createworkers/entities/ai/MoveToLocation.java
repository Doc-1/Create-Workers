package net._doc.createworkers.entities.ai;

import net.minecraft.world.entity.ai.goal.Goal;

public class MoveToLocation extends Goal {

	@Override
	public boolean canUse() {
		System.out.println("use");
		return true;
	}

	@Override
	public void start() {
		System.out.println("start");
	}

	@Override
	public void tick() {
		System.out.println("tick");
	}

}
