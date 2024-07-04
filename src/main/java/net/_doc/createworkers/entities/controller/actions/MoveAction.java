package net._doc.createworkers.entities.controller.actions;

import net._doc.createworkers.entities.Worker;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveAction implements Action {

	private final double distance;
	private double distanceTraveled;

	public MoveAction(double distance) {
		this.distance = distance;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.MOVE;
	}

	@Override
	public boolean tick(Worker entity) {
		return distanceTraveled <= distance;
	}

	@Override
	public boolean hasCompleted() {
		return distanceTraveled > distance;
	}

	@Override
	public void start(Worker entity) {

		Vec3 oldPos = entity.position();

		entity.setDeltaMovement(new Vec3((double) (Mth.sin(-entity.getYRot() * ((float) Math.PI / 180F)) * 0.05), 0.0D,
				(double) (Mth.cos(entity.getYRot() * ((float) Math.PI / 180F)) * 0.05)));
		entity.move(MoverType.SELF, entity.getDeltaMovement());
		double traveled = oldPos.distanceTo(entity.position());
		distanceTraveled += traveled;
		if (traveled == 0)
			entity.playJammedAlarm(true);
		else
			entity.playJammedAlarm(false);
		entity.getTorquePower().cost(this.torqueCost() * traveled);
	}

	@Override
	public void end(Worker entity) {
		entity.playJammedAlarm(false);
	}

	@Override
	public int torqueCost() {
		return 2;
	}

}
