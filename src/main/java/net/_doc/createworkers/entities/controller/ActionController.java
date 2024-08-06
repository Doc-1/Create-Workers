package net._doc.createworkers.entities.controller;

import java.util.ArrayList;
import java.util.List;

import net._doc.createworkers.entities.controller.actions.Action;
import net.minecraft.util.RandomSource;

public class ActionController {
    
    protected final RandomSource random = RandomSource.create();
    private List<Action> steps = new ArrayList<>();
    private int selectedIndex = 0;
    
    public ActionController() {}
    
    public void add(Action action) {
        this.steps.add(action);
    }
    
    public List<Action> getSteps() {
        return steps;
    }
    
    public void setSteps(List<Action> steps) {
        this.steps = steps;
    }
    
    public boolean tick() {
        if (steps.size() > selectedIndex) {
            Action action = steps.get(selectedIndex);
            if (action.getEntity().getTorquePower().hasEnoughTorque(action.torqueCost())) {
                if (action.tick())
                    action.start();
                if (action.hasCompleted()) {
                    action.end();
                    selectedIndex++;
                }
            }
            return false;
        }
        selectedIndex = 0;
        return true;
    }
}
/*
 * if (entity.isStuck()) { entity.alarmTicks++; if (entity.stuckTicks >= 60 &&
 * entity.alarmTickDelay <= entity.alarmTicks) {
 * entity.level().playLocalSound(entity.getX(), entity.getY(), entity.getZ(),
 * SoundEvents.NOTE_BLOCK_PLING.get(), entity.getSoundSource(), 1.0F, 0.8F +
 * 0.8F * random.nextFloat(), false); entity.alarmTicks = 0; } else {
 * entity.stuckTicks++; } } else { entity.stuckTicks = 0; entity.alarmTicks = 0;
 * }
 * 
 */
