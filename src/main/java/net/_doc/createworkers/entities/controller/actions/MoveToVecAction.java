package net._doc.createworkers.entities.controller.actions;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.common.util.concurrent.AtomicDouble;

import net._doc.createworkers.content.logic.degree.DegreeUtils;
import net._doc.createworkers.entities.Worker;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveToVecAction extends Action {

	private double distanceTraveled;
	private Double distanceGoal;
	private final Vec3 destinationGoal;
	private double travelRate;

	private float startingRotation;
	private float rotationTraveled;
	private Float rotationGoal;
	private boolean reachedRotation;

	public MoveToVecAction(Worker entity, Vec3 destination) {
		super(entity);
		this.destinationGoal = destination;
	}

	@Override
	public boolean tick() {
		if (rotationGoal == null) {
			this.setDeltaRotation(2F);
			this.updateRotation();
			reachedRotation = false;
		}
		if (distanceGoal == null) {
			this.setDeltaMovement(0.1);
			this.updateDistance();

			System.out.println(this.getEntity().getYRot() + " " + this.getEntity().position());
		}
		return !hasReachedDistance() || !reachedRotation;
	}

	@Override
	public void start() {
		if (!reachedRotation) {
			this.getEntity().setIgnoreFrames(false);
			AtomicDouble x = new AtomicDouble(this.getEntity().getYRot());
			if (DegreeUtils.addToAngle(x, this.getEntity().deltaRotation))
				this.getEntity().setIgnoreFrames(true);
			this.getEntity().setYRot(x.floatValue());
			this.rotationTraveled += x.floatValue();
			reachedRotation = hasReachedRotation();
			if (reachedRotation)
				this.getEntity().setYRot(this.rotationGoal);
		} else if (!hasReachedDistance()) {
			this.distanceTraveled += this.travelRate;
			this.getEntity().move(MoverType.SELF, this.getEntity().getDeltaMovement());
		} else {
			this.getEntity().setDeltaMovement(0, 0, 0);
			this.getEntity().setPos(new Vec3(destinationGoal.x, this.getEntity().position().y, destinationGoal.z));
			System.out.println(this.getEntity().position());
		}
	}

	@Override
	public void end() {
		rotationGoal = null;
		distanceGoal = null;

	}

	@Override
	public boolean hasCompleted() {
		return hasReachedDistance() && reachedRotation;
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
		this.getEntity().deltaRotation = speed;
	}

	private void updateRotation() {
		Worker worker = this.getEntity();
		Point2D.Double a = new Point2D.Double(worker.position().x, worker.position().z);
		Point2D.Double b = new Point2D.Double(destinationGoal.x, destinationGoal.z);
		this.rotationGoal = -new BigDecimal(Math.toDegrees(Math.atan2(b.x - a.x, b.y - a.y)))
				.setScale(1, RoundingMode.CEILING).floatValue();
		this.startingRotation = worker.getYRot();
	}

	private void updateDistance() {
		this.travelRate = new BigDecimal(this.getEntity().getDeltaMovement().distanceTo(new Vec3(0, 0, 0)))
				.setScale(3, RoundingMode.CEILING).doubleValue();
		Vec3 pos = new Vec3(this.getEntity().position().x, 0, this.getEntity().position().z);
		this.distanceGoal = new BigDecimal(pos.distanceTo(this.destinationGoal)).setScale(3, RoundingMode.CEILING)
				.doubleValue();
	}

	private void setDeltaMovement(double speed) {
		this.getEntity().setDeltaMovement(
				new BigDecimal(Mth.sin(-rotationGoal * ((float) Math.PI / 180F)) * speed)
						.setScale(3, RoundingMode.HALF_UP).doubleValue(),
				-0.04D, new BigDecimal(Mth.cos(rotationGoal * ((float) Math.PI / 180F)) * speed)
						.setScale(3, RoundingMode.HALF_UP).doubleValue());
	}

	private double getDistancedHasTraveled() {
		return 0.0;
	}

	private boolean hasReachedRotation() {
		float yL = this.getEntity().getYRot() + this.getEntity().deltaRotation;
		float yR = this.getEntity().getYRot() - this.getEntity().deltaRotation;
		return this.rotationGoal < yL && this.rotationGoal > yR;

	}

	private boolean hasReachedDistance() {
		double distance = new BigDecimal(this.getEntity().position()
				.distanceTo(new Vec3(this.destinationGoal.x, this.getEntity().position().y, this.destinationGoal.z)))
				.setScale(3, RoundingMode.CEILING).doubleValue();
		return this.distanceGoal <= this.distanceTraveled && distance <= this.travelRate;
	}
}
