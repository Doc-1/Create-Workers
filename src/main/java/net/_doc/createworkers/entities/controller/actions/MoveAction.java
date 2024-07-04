package net._doc.createworkers.entities.controller.actions;

import net._doc.createworkers.entities.Worker;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveAction implements Action {

	private final double distance;
	private double distanceTraveled;
	private Vec3 startingPos = null;

	public MoveAction(double distance) {
		this.distance = distance;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.MOVE;
	}

	@Override
	public boolean tick(Worker entity) {
		if (startingPos == null)
			startingPos = entity.position();
		return entity.position().distanceTo(startingPos) <= distance;
	}

	@Override
	public boolean hasCompleted() {
		return false;
	}

	@Override
	public void start(Worker entity) {

		Vec3 oldPos = entity.position();

		entity.setDeltaMovement(new Vec3((double) (Mth.sin(-entity.getYRot() * ((float) Math.PI / 180F)) * 0.05), 0.0D,
				(double) (Mth.cos(entity.getYRot() * ((float) Math.PI / 180F)) * 0.05)));
		entity.move(MoverType.SELF, entity.getDeltaMovement());
		double traveled = oldPos.distanceTo(entity.position());
		distanceTraveled += traveled;

		entity.getTorquePower().cost(this.torqueCost() * traveled);
	}

	@Override
	public void end(Worker entity) {

	}

	@Override
	public int torqueCost() {
		return 2;
	}

}
