package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.utility.Lang;

import net._doc.createworkers.content.logic.torque.TorquePower;
import net._doc.createworkers.entities.controller.EntityController;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CWFlywheelEntityTest extends NonLivingEntity implements IHaveGoggleInformation {

	private float chasingVelocity = 0;
	private float independentAngle = 0;

	private float deltaRot = 0;
	private int stuckTicks = 0;
	private final int alarmTickDelay = 20;
	private int alarmTicks = 0;

	protected double distance = 0;
	protected TorquePower torque = new TorquePower(50, 2);

	protected CWFlywheelEntityTest(EntityType<? extends Entity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
		if (pHand.equals(InteractionHand.MAIN_HAND)) {
			torque.currentTorque += 100;
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public void tick() {
		super.tick();
		Vec3 oldPos = this.position();

		if (torque.hasEnoughTorque()) {
			chasingVelocity += ((20 * 10 / 3f) - chasingVelocity) * .25f;
			independentAngle += chasingVelocity;

			// this.setYRot(deltaRot++);
			this.setDeltaMovement(new Vec3((double) (Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * 0.05), 0.0D,
					(double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * 0.05)));
			this.move(MoverType.SELF, this.getDeltaMovement());

			distance = oldPos.distanceTo(this.position());
			if (distance <= 0) {
				this.alarmTicks++;
				if (stuckTicks >= 60 && this.alarmTickDelay <= this.alarmTicks) {
					this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
							SoundEvents.NOTE_BLOCK_PLING.get(), this.getSoundSource(), 1.0F,
							0.8F + 0.8F * this.random.nextFloat(), false);
					this.alarmTicks = 0;
				} else {
					stuckTicks++;
				}
			} else {
				stuckTicks = 0;
				alarmTicks = 0;
			}
			torque.tick(distance);
		}
	}

	public boolean isStuck() {
		return distance <= 0;
	}

	public float getIndependentAngle(float partialTicks) {
		return (independentAngle + partialTicks * chasingVelocity) / 360;
	}

	@Override
	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
		Lang.text("Torque remaining:" + " " + (int) Math.ceil(torque.currentTorque)).forGoggles(tooltip);
		return !isPlayerSneaking;
	}

	@Override
	public EntityController setController() {
		return new EntityController(this) {
			@Override
			public void tick() {

			}
		};
	}
}
