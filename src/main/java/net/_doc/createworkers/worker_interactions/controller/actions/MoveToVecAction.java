package net._doc.createworkers.worker_interactions.controller.actions;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.common.util.concurrent.AtomicDouble;

import net._doc.createworkers.entities.Worker;
import net._doc.createworkers.utils.DegreeUtils;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveToVecAction extends Action {
    
    private Double distanceGoal;
    private final Vec3 destinationGoal;
    private double travelRate;
    
    private float startingRotation;
    private float rotationTraveled;
    private float rotationTraveledGoal;
    private Float rotationGoal;
    private boolean reachedRotation;
    
    public MoveToVecAction(Worker entity, Vec3 destination) {
        super(entity);
        this.destinationGoal = destination;
    }
    
    @Override
    public boolean shouldContinue() {
        if (rotationGoal == null) {
            this.updateRotation();
            reachedRotation = false;
            rotationTraveled = 0;
        }
        if (distanceGoal == null) {
            this.setDeltaMovement(0.15);
            this.updateDistance();
            
        }
        return !hasReachedDistance() || !reachedRotation;
    }
    
    @Override
    public void start() {
        if (!reachedRotation) {
            AtomicDouble x = new AtomicDouble(this.getEntity().getYRot());
            rotationTraveled += this.getEntity().deltaRotation;
            this.getEntity().setIgnoreFrames(DegreeUtils.addToAngle(x, this.getEntity().deltaRotation));
            this.getEntity().setYRot(x.floatValue());
            reachedRotation = hasReachedRotation();
            if (reachedRotation)
                this.getEntity().setYRot(this.rotationGoal);
        } else if (!hasReachedDistance()) {
            float oldRot = this.rotationGoal;
            this.updateRotation();
            this.setDeltaMovement(0.1);
            if (oldRot != rotationGoal) {
                this.getEntity().setYRot(this.rotationGoal);
            }
            this.getEntity().move(MoverType.SELF, this.getEntity().getDeltaMovement());
            this.getEntity()
                    .playJammedAlarm(this.getEntity().getDeltaMovement().x == 0 && this.getEntity().getDeltaMovement().y == 0 && this.getEntity().getDeltaMovement().z == 0);
        } else {
            this.getEntity().setDeltaMovement(0, 0, 0);
        }
    }
    
    @Override
    public void end() {
        rotationGoal = null;
        distanceGoal = null;
        this.getEntity().setPos(new Vec3(destinationGoal.x, this.getEntity().position().y, destinationGoal.z));
        
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
        return ActionType.MOVE;
    }
    
    private void setDeltaRotation(float speed) {
        this.getEntity().deltaRotation = speed;
    }
    
    private void updateRotation() {
        Worker worker = this.getEntity();
        Point2D.Double a = new Point2D.Double(worker.position().x, worker.position().z);
        Point2D.Double b = new Point2D.Double(destinationGoal.x, destinationGoal.z);
        this.rotationGoal = -new BigDecimal(Math.toDegrees(Math.atan2(b.x - a.x, b.y - a.y))).setScale(1, RoundingMode.CEILING).floatValue();
        this.startingRotation = worker.getYRot();
        AtomicDouble x = new AtomicDouble(this.startingRotation);
        DegreeUtils.addToAngle(x, -rotationGoal);
        this.rotationTraveledGoal = new BigDecimal(-x.floatValue()).setScale(1, RoundingMode.CEILING).floatValue();
        
        int z = x.intValue() < 0 ? 1 : -1;
        this.setDeltaRotation(2F * z);
    }
    
    private void updateDistance() {
        this.travelRate = new BigDecimal(this.getEntity().getDeltaMovement().distanceTo(new Vec3(0, 0, 0))).setScale(3, RoundingMode.CEILING).doubleValue();
        Vec3 pos = new Vec3(this.getEntity().position().x, 0, this.getEntity().position().z);
        this.distanceGoal = new BigDecimal(pos.distanceTo(this.destinationGoal)).setScale(3, RoundingMode.CEILING).doubleValue();
    }
    
    private void setDeltaMovement(double speed) {
        this.getEntity().setDeltaMovement(new BigDecimal(Mth.sin(-rotationGoal * ((float) Math.PI / 180F)) * speed).setScale(3, RoundingMode.HALF_UP)
                .doubleValue(), 0, new BigDecimal(Mth.cos(rotationGoal * ((float) Math.PI / 180F)) * speed).setScale(3, RoundingMode.HALF_UP).doubleValue());
    }
    
    private boolean hasReachedRotation() {
        if (getEntity().deltaRotation > 0)
            return this.rotationTraveled > this.rotationTraveledGoal;
        else
            return this.rotationTraveled < this.rotationTraveledGoal;
        
    }
    
    private boolean hasReachedDistance() {
        double distance = new BigDecimal(this.getEntity().position().distanceTo(new Vec3(this.destinationGoal.x, this.getEntity().position().y, this.destinationGoal.z)))
                .setScale(3, RoundingMode.CEILING).doubleValue();
        return this.travelRate >= distance && distance >= distance - this.travelRate;
    }
}
