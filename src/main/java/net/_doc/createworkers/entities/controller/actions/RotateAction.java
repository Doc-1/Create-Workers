package net._doc.createworkers.entities.controller.actions;

import net._doc.createworkers.entities.Worker;

public class RotateAction implements Action {

	private final float rotation;
	private float rotationTraveled;

	public RotateAction(float rotation) {
		this.rotation = rotation;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.ROTATE;
	}

	@Override
	public boolean tick(Worker entity) {
		return rotationTraveled <= rotation;
	}

	@Override
	public boolean hasCompleted() {
		return rotationTraveled >= rotation;
	}

	@Override
	public void start(Worker entity) {
		float oldRot = entity.getYRot();
		entity.deltaRot = 2;
		rotationTraveled += entity.deltaRot;
		entity.setYRot(rotationTraveled);
		entity.getTorquePower().cost(this.torqueCost() * (entity.deltaRot * 0.0036));
	}

	@Override
	public void end(Worker entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public int torqueCost() {
		return 1;
	}

}
