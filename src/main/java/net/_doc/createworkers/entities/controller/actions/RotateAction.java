package net._doc.createworkers.entities.controller.actions;

import net._doc.createworkers.entities.Worker;

public class RotateAction implements Action {

	private final float rotation;

	public RotateAction(float rotation) {
		this.rotation = rotation;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.ROTATE;
	}

	public float getRotation() {
		return rotation;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void end(Worker entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public int torqueCost() {
		return 0;
	}

}
