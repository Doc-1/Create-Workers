package net._doc.createworkers.worker_interactions.controller.actions;

import net._doc.createworkers.entities.Worker;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class MoveAction extends Action {
    
    private final double distance;
    private double distanceTraveled;
    private Vec3 destination;
    
    public MoveAction(Worker entity, double distance) {
        super(entity);
        this.distance = distance;
    }
    
    @Override
    public ActionType getActionType() {
        return ActionType.MOVE;
    }
    
    @Override
    public boolean tick() {
        return distanceTraveled < distance;
    }
    
    @Override
    public boolean hasCompleted() {
        return distanceTraveled >= distance;
    }
    
    @Override
    public void start() {
        
        Vec3 oldPos = this.getEntity().position();
        
        this.getEntity().setDeltaMovement(new Vec3((double) (Mth.sin(-this.getEntity().getYRot() * ((float) Math.PI / 180F)) * -0.05), 0.0D, (double) (Mth
                .cos(this.getEntity().getYRot() * ((float) Math.PI / 180F)) * -0.05)));
        this.getEntity().move(MoverType.SELF, this.getEntity().getDeltaMovement());
        double traveled = oldPos.distanceTo(this.getEntity().position());
        distanceTraveled += traveled;
        if (traveled == 0)
            this.getEntity().playJammedAlarm(true);
        else
            this.getEntity().playJammedAlarm(false);
        this.getEntity().getTorquePower().cost(this.torqueCost() * traveled);
    }
    
    @Override
    public void end() {
        this.getEntity().playJammedAlarm(false);
        this.getEntity().setPos(new Vec3(destination.x, this.getEntity().position().y, destination.z));
    }
    
    @Override
    public int torqueCost() {
        return 2;
    }
    
}
