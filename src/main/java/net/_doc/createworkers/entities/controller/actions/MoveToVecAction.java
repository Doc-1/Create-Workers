package net._doc.createworkers.entities.controller.actions;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

import net._doc.createworkers.entities.Worker;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveToVecAction extends Action {

	private double distanceTraveled;
	private Double distanceGoal;
	private final Vec3 destinationVec;
	private boolean finishedDistance;

	private double travelRate;

	private float rotationTraveled;
	private Float rotationGoal;
	private boolean finishedRotation;

	public MoveToVecAction(Worker entity, Vec3 destination) {
		super(entity);
		this.destinationVec = destination;
	}

	@Override
	public boolean tick() {
		if (rotationGoal == null) {
			this.updateRotationNeeded();
			this.setDeltaRotation(3F);
			finishedRotation = false;
		}
		if (distanceGoal == null) {
			this.setDeltaMovement(0.1);
			this.updateDistanceNeeded();
			finishedDistance = false;
		}
		return !hasReachedDistance() || !hasReachedRotation();
	}

	@Override
	public void start() {

		this.getEntity().setIgnoreFrames(false);
		if (!finishedRotation && !hasReachedRotation()) {
			rotationTraveled += this.getEntity().deltaRotation;
			/*
			 * if (hasReachedRotation()) { int x = (int) Math.ceil(rotationGoal / 360);
			 * float d = rotationTraveled; if ((x != 0)) { if (rotationGoal < 0) d += 360 *
			 * (x + 1); else d -= 360 * x; this.getEntity().setIgnoreFrames(true); }
			 * rotationTraveled = d - this.getEntity().deltaRotation; finishedRotation =
			 * true; }
			 */
			if (hasReachedRotation()) {
				this.getEntity().setYRot(this.rotationGoal);
			} else {
				this.getEntity().setYRot(rotationTraveled);
			}
		} else if (!finishedRotation) {
			float yRot = this.getEntity().getYRot();
			if (yRot > 360) {
				this.getEntity().setIgnoreFrames(true);
				this.getEntity().setYRot(this.rotationGoal - 360);
			} else if (yRot < 0) {
				this.getEntity().setIgnoreFrames(true);
				this.getEntity().setYRot(this.rotationGoal + 360);
			}
			finishedRotation = true;
			System.out.println(this.getEntity().getYRot() + " " + this.rotationGoal);
		} else if (!finishedDistance && !hasReachedDistance()) {
			double dis = getDistancedHasTraveled();
			distanceTraveled += dis; // System.out.println(this.travelRate + " " + dis);
			if (hasReachedDistance()) {
				distanceTraveled = distanceGoal;
				this.getEntity().setPos(new Vec3(destinationVec.x, this.getEntity().position().y, destinationVec.z));
			} else
				this.getEntity().move(MoverType.SELF, this.getEntity().getDeltaMovement());

			System.out.println(distanceTraveled + " " + distanceGoal + " " + getDistancedHasTraveled() + " "
					+ this.getEntity().getDeltaMovement() + " " + destinationVec);
		}
		/*
		 * if (hasReachedRotation()) if (rotationTraveled >= 360) rotationTraveled -=
		 * 360; else rotationTraveled = rotationGoal;
		 */
		/*
		 * else if (!hasReachedDistance()) { double dis = getDistancedHasTraveled();
		 * distanceTraveled += dis; // System.out.println(this.travelRate + " " + dis);
		 * if (hasReachedDistance()) { distanceTraveled = distanceGoal;
		 * this.getEntity().setPos(new Vec3(destinationVec.x,
		 * this.getEntity().position().y, destinationVec.z)); } else
		 * this.getEntity().move(MoverType.SELF, this.getEntity().getDeltaMovement());
		 * 
		 * 
		 * System.out.println(distanceTraveled + " " + distanceGoal + " " +
		 * getDistancedHasTraveled() + " " + this.getEntity().getDeltaMovement() + " " +
		 * destination);
		 */

	}

	@Override
	public void end() {

		rotationGoal = null;
		distanceGoal = null;

	}

	@Override
	public boolean hasCompleted() {
		return hasReachedDistance() && hasReachedRotation();
	}

	@Override
	public int torqueCost() {
		return 1;
	}

	@Override
	public ActionType getActionType() {
		return null;
	}

	private void setDeltaRotation(float speed) {
	}

	private void updateRotationNeeded() {
		Point2D.Double a = new Point2D.Double(destinationVec.x, destinationVec.z);
		Point2D.Double b = new Point2D.Double(getEntity().position().x, getEntity().position().z);
		float r = (float) Math.toDegrees(Math.atan2((b.x - a.x), -(b.y - a.y)));
		rotationGoal = new BigDecimal(r).setScale(3, RoundingMode.HALF_UP).floatValue();
		int mod = rotationGoal < 0 ? -1 : 1;
		rotationTraveled = this.getEntity().getYRot();
		// LogUtils.getLogger().info(rotationGoal + " " + rotationTraveled + " " +
		// (rotationTraveled - rotationGoal));

		this.getEntity().deltaRotation = new BigDecimal(3 * mod).setScale(3, RoundingMode.HALF_UP).floatValue();

	}

	private void updateDistanceNeeded() {
		Vec3 location = new Vec3(getEntity().position().x, 0, getEntity().position().z);
		distanceGoal = new BigDecimal(location.distanceTo(destinationVec)).setScale(3, RoundingMode.HALF_UP)
				.doubleValue();
	}

	private void setDeltaMovement(double speed) {
		this.getEntity().setDeltaMovement(
				new BigDecimal(Mth.sin(-rotationGoal * ((float) Math.PI / 180F)) * speed)
						.setScale(3, RoundingMode.HALF_UP).doubleValue(),
				0.0D, new BigDecimal(Mth.cos(rotationGoal * ((float) Math.PI / 180F)) * speed)
						.setScale(3, RoundingMode.HALF_UP).doubleValue());
		travelRate = new BigDecimal(new Vec3(0, 0, 0).distanceTo(this.getEntity().getDeltaMovement()))
				.setScale(3, RoundingMode.HALF_UP).doubleValue();
	}

	private double getDistancedHasTraveled() {
		Vec3 location = new Vec3(getEntity().position().x, 0, getEntity().position().z);
		return new BigDecimal(location.add(this.getEntity().getDeltaMovement()).distanceTo(location))
				.setScale(3, RoundingMode.HALF_UP).doubleValue();
	}

	private boolean hasReachedRotation() {
		// System.out.println(rotationTraveled + " " + rotationGoal + " " +
		// this.getEntity().deltaRotation + " ");
		if (this.getEntity().deltaRotation > 0)
			return rotationTraveled >= rotationGoal;
		else
			return rotationTraveled <= rotationGoal;

	}

	private boolean hasReachedDistance() {

		return distanceTraveled >= distanceGoal;
	}
}
