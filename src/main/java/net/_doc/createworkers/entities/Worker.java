package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.utility.Lang;

import net._doc.createworkers.torque.TorquePower;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public abstract class Worker extends NonLivingEntity implements IHaveGoggleInformation {
    
    public float deltaRotation = 0;
    private int stuckTicks = 0;
    private final int alarmTickDelay = 20;
    private int alarmTicks = 0;
    
    private TorquePower torque;
    private boolean isJammmed;
    private boolean ignoreFrames = false;
    
    public Worker(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.torque = this.setTorquePower();
    }
    
    @Override
    public float getStepHeight() {
        return 0.42F;
    }
    
    public void onUpdate() {
        
    }
    
    @Override
    public void tick() {
        super.tick();
        this.controller.tick();
        this.move(MoverType.SELF, new Vec3(0, -0.4D, 0));
        if (isJammmed) {
            this.alarmTicks++;
            if (stuckTicks >= 60 && this.alarmTickDelay <= this.alarmTicks) {
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.NOTE_BLOCK_PLING.get(), this
                        .getSoundSource(), 1.0F, 0.8F + 0.8F * this.random.nextFloat(), false);
                this.level().gameEvent(null, GameEvent.NOTE_BLOCK_PLAY, this.blockPosition());
                
                this.alarmTicks = 0;
            } else {
                stuckTicks++;
            }
        } else {
            stuckTicks = 0;
            alarmTicks = 0;
        }
        onUpdate();
    }
    
    public abstract TorquePower setTorquePower();
    
    public void playJammedAlarm(boolean start) {
        this.isJammmed = start;
    }
    
    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        Lang.text("Torque remaining:" + " " + (int) Math.ceil(getTorquePower().currentTorque)).forGoggles(tooltip);
        return !isPlayerSneaking;
    }
    
    public TorquePower getTorquePower() {
        return torque;
    }
    
    public boolean isIgnoreFrames() {
        return ignoreFrames;
    }
    
    public void setIgnoreFrames(boolean ignoreFrames) {
        this.ignoreFrames = ignoreFrames;
    }
    
}
