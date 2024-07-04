package net._doc.createworkers.entities.controller.actions;

import net._doc.createworkers.entities.Worker;

public class WaitAction implements Action {

	private final int ticks;

	public WaitAction(int ticks) {
		this.ticks = ticks;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.WAIT;
	}

	public int getTicks() {
		return ticks;
	}

	@Override
	public boolean tick(Worker entity) {
		return false;
	}

	@Override
	public boolean hasCompleted() {
		return false;
	}

	@Override
	public void start(Worker entity) {

	}

	@Override
	public void end(Worker entity) {

	}

	@Override
	public int torqueCost() {
		// TODO Auto-generated method stub
		return 0;
	}
}
