package net._doc.createworkers.worker_interactions.controller.actions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net._doc.createworkers.entities.Worker;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveAction extends Action {
    
    private final double distanceGoal;
    private double distanceTraveled;
    private Vec3 destinationGoal;
    private double distanceLeft;
    
    public MoveAction(Worker entity, double distanceGoal) {
        super(entity);
        this.distanceGoal = distanceGoal;
    }
    
    @Override
    public ActionType getActionType() {
        return ActionType.MOVE;
    }
    
    @Override
    public boolean shouldContinue() {
        if (destinationGoal == null) {
            Vec3 pos = this.getEntity().position();
            Vec3 distance = new Vec3(new BigDecimal(Mth.sin(-this.getEntity().yRotO * ((float) Math.PI / 180F)) * distanceGoal).setScale(3, RoundingMode.HALF_UP)
                    .doubleValue(), -pos.y, new BigDecimal(Mth.cos(this.getEntity().yRotO * ((float) Math.PI / 180F)) * distanceGoal).setScale(3, RoundingMode.HALF_UP)
                            .doubleValue());
            this.setDeltaMovement(0.1);
            destinationGoal = pos.add(distance);
            updateDistance();
        }
        return !hasCompleted();
    }
    
    @Override
    public boolean hasCompleted() {
        return 0.1D >= distanceLeft && distanceLeft >= distanceLeft - 0.1;
    }
    
    @Override
    public void start() {
        
        Vec3 oldPos = this.getEntity().position();
        
        this.setDeltaMovement(0.1);
        this.getEntity().move(MoverType.SELF, this.getEntity().getDeltaMovement());
        this.updateDistance();
        this.getEntity().playJammedAlarm(this.getEntity().getDeltaMovement().x == 0 && this.getEntity().getDeltaMovement().y == 0 && this.getEntity().getDeltaMovement().z == 0);
        
        this.getEntity().getTorquePower().cost(this.torqueCost() * this.getEntity().position().distanceTo(oldPos));
        
    }
    
    @Override
    public void end() {
        this.getEntity().playJammedAlarm(false);
        this.getEntity().setPos(new Vec3(destinationGoal.x, this.getEntity().position().y, destinationGoal.z));
    }
    
    @Override
    public int torqueCost() {
        return 2;
    }
    
    private void updateDistance() {
        //   this.travelRate = new BigDecimal(this.getEntity().getDeltaMovement().distanceTo(new Vec3(0, 0, 0))).setScale(3, RoundingMode.CEILING).doubleValue();
        Vec3 pos = new Vec3(this.getEntity().position().x, 0, this.getEntity().position().z);
        this.distanceLeft = new BigDecimal(pos.distanceTo(this.destinationGoal)).setScale(3, RoundingMode.CEILING).doubleValue();
        
    }
    
    private void setDeltaMovement(double speed) {
        this.getEntity().setDeltaMovement(new BigDecimal(Mth.sin(-this.getEntity().yRotO * ((float) Math.PI / 180F)) * speed).setScale(3, RoundingMode.HALF_UP)
                .doubleValue(), 0, new BigDecimal(Mth.cos(this.getEntity().yRotO * ((float) Math.PI / 180F)) * speed).setScale(3, RoundingMode.HALF_UP).doubleValue());
    }
}
