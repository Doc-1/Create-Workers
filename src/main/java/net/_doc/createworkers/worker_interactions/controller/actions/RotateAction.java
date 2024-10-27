package net._doc.createworkers.worker_interactions.controller.actions;

import com.simibubi.create.content.contraptions.bearing.WindmillBearingBlockEntity.RotationDirection;

import net._doc.createworkers.entities.Worker;

public class RotateAction extends Action {
    
    private final float rotation;
    private final RotationDirection rotationDir;
    private float rotationTraveled;
    
    public RotateAction(Worker entity, float rotation, RotationDirection rotationDir) {
        super(entity);
        this.rotation = Math.abs(rotation);
        this.rotationDir = rotationDir;
    }
    
    @Override
    public ActionType getActionType() {
        return ActionType.ROTATE;
    }
    
    @Override
    public boolean shouldContinue() {
        return !hasCompleted();
    }
    
    @Override
    public boolean hasCompleted() {
        return rotationTraveled >= rotation;
    }
    
    @Override
    public void start() {
        this.getEntity().deltaRotation = 2;
        rotationTraveled += this.getEntity().deltaRotation;
        int mod = rotationDir.equals(RotationDirection.CLOCKWISE) ? 1 : -1;
        this.getEntity().setYRot(rotationTraveled * mod);
        this.getEntity().getTorquePower().cost(this.torqueCost() * (this.getEntity().deltaRotation * 0.0072));
    }
    
    @Override
    public void end() {
        int rotMod = rotationDir.equals(RotationDirection.COUNTER_CLOCKWISE) ? 180 : 0;
        this.getEntity().setYRot(-45);
        
    }
    
    @Override
    public int torqueCost() {
        return 1;
    }
    
}
