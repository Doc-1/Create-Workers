package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.utility.Lang;

import net._doc.createworkers.content.logic.torque.TorquePower;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public abstract class Worker extends NonLivingEntity implements IHaveGoggleInformation {

	public float deltaRotation = 0;
	private int stuckTicks = 0;
	private final int alarmTickDelay = 20;
	private int alarmTicks = 0;

	private TorquePower torque;
	private boolean isJammmed;
	private boolean ignoreFrames = false;
	private double lerpYRot;

	protected Worker(EntityType<? extends Entity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.torque = this.setTorquePower();
	}

	@Override
	public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
		if (pHand.equals(InteractionHand.MAIN_HAND)) {
			getTorquePower().currentTorque += 100;
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public void tick() {
		super.tick();
		// this.tickLerp();
		this.controller.tick();

		if (isJammmed) {
			this.alarmTicks++;
			if (stuckTicks >= 60 && this.alarmTickDelay <= this.alarmTicks) {
				this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.NOTE_BLOCK_PLING.get(),
						this.getSoundSource(), 1.0F, 0.8F + 0.8F * this.random.nextFloat(), false);
				this.level().gameEvent(null, GameEvent.NOTE_BLOCK_PLAY, this.blockPosition());

				this.alarmTicks = 0;
			} else {
				stuckTicks++;
			}
		} else {
			stuckTicks = 0;
			alarmTicks = 0;
		}
	}

	@Override
	public void lerpTo(double pX, double pY, double pZ, float pYaw, float pPitch, int pPosRotationIncrements,
			boolean pTeleport) {
		super.lerpTo(pX, pY, pZ, pYaw, pPitch, pPosRotationIncrements,
				pTeleport);/*
							 * this.lerpX = pX; this.lerpY = pY; this.lerpZ = pZ; this.lerpYRot = (double)
							 * pYaw; this.lerpSteps = 10;
							 */
	}

	private void tickLerp() {
		if (this.isControlledByLocalInstance()) {
			this.lerpSteps = 0;
			this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
		}

		if (this.lerpSteps > 0) {
			double d0 = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
			double d3 = Mth.wrapDegrees(this.lerpYRot - (double) this.getYRot());
			this.setYRot(this.getYRot() + (float) d3 / (float) this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d0, d1, d2);
			this.setRot(this.getYRot(), 0);
		}
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
