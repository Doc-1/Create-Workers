package net._doc.createworkers.entities.controller.actions;

import java.awt.geom.Point2D;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.contraptions.bearing.WindmillBearingBlockEntity.RotationDirection;

import net._doc.createworkers.entities.Worker;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveToVecAction extends Action {

	private Double distance;
	private double distanceTraveled;
	private final Vec3 destination;
	private boolean finishedDistance = false;

	private Float rotation;
	private float startingRotation;
	private float endingRotation;
	private float rotationTraveled;
	private RotationDirection rotationDir = RotationDirection.CLOCKWISE;
	private boolean finishedRotation = false;

	public MoveToVecAction(Worker entity, Vec3 destination) {
		super(entity);
		this.destination = destination;
	}

	@Override
	public boolean tick() {
		if (rotation == null) {
			Point2D.Double a = new Point2D.Double(destination.x, destination.z);
			Point2D.Double b = new Point2D.Double(getEntity().position().x, getEntity().position().z);
			endingRotation = (float) Math.toDegrees(Math.atan2((b.x - a.x), -(b.y - a.y)));
			startingRotation = this.getEntity().getYRot();
			rotation = endingRotation - startingRotation;
			int mod = rotation < 0 ? -1 : 1;
			this.getEntity().deltaRot = 2 * mod;
			// rotationTraveled = this.getEntity().getYRot();
		}
		if (distance == null) {
			Vec3 location = new Vec3(getEntity().position().x, 0, getEntity().position().z);
			distance = location.distanceTo(destination);
			LogUtils.getLogger().info("Start " + getEntity().position() + " " + distance + " " + rotation);
		}
		return !finishedDistance || !finishedRotation;
	}

	@Override
	public void start() {
		if (hasReachedRotation()) {
			rotationTraveled += this.getEntity().deltaRot;
			this.getEntity().setYRot((rotationTraveled + startingRotation));
			this.getEntity().getTorquePower().cost(1 * (Math.abs(this.getEntity().deltaRot) * 0.0072));
		} else if (!finishedRotation) {
			this.getEntity().setYRot(endingRotation);
			finishedRotation = true;
		} else if (distanceTraveled < distance) {
			Vec3 oldPos = this.getEntity().position();
			this.getEntity()
					.setDeltaMovement(new Vec3(
							(double) (Mth.sin(-this.getEntity().getYRot() * ((float) Math.PI / 180F)) * 0.05), 0.0D,
							(double) (Mth.cos(this.getEntity().getYRot() * ((float) Math.PI / 180F)) * 0.05)));
			this.getEntity().move(MoverType.SELF, this.getEntity().getDeltaMovement());
			double traveled = oldPos.distanceTo(this.getEntity().position());
			distanceTraveled += traveled;
			if (traveled == 0)
				this.getEntity().playJammedAlarm(true);
			else
				this.getEntity().playJammedAlarm(false);
			this.getEntity().getTorquePower().cost(2 * traveled);
		} else if (!finishedDistance) {
			this.getEntity().setPos(new Vec3(destination.x, this.getEntity().getY(), destination.z));
			finishedDistance = true;
		}

	}

	@Override
	public void end() {
		LogUtils.getLogger().info("End " + this.getEntity().position());

		rotation = null;
		distance = null;
		rotationTraveled = 0;
		distanceTraveled = 0;
		finishedDistance = false;
		finishedRotation = false;

	}

	@Override
	public boolean hasCompleted() {
		return finishedRotation && finishedDistance;
	}

	@Override
	public int torqueCost() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ActionType getActionType() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean hasReachedRotation() {
		if (this.getEntity().deltaRot > 0)
			return rotationTraveled < rotation;
		else {
			return rotationTraveled > rotation;
		}
	}

}
