package net._doc.createworkers.worker_interactions.controller.actions;

import net._doc.createworkers.entities.Worker;

public class WaitAction extends Action {

	private final int ticks;

	public WaitAction(Worker entity, int ticks) {
		super(entity);
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
	public boolean shouldContinue() {
		return false;
	}

	@Override
	public boolean hasCompleted() {
		return false;
	}

	@Override
	public void start() {

	}

	@Override
	public void end() {

	}

	@Override
	public int torqueCost() {
		// TODO Auto-generated method stub
		return 0;
	}
}
