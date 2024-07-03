package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.utility.Lang;

import net._doc.createworkers.content.logic.torque.TorquePower;
import net._doc.createworkers.entities.controller.EntityController;
import net.minecraft.network.chat.Component;
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

	protected TorquePower torque = new TorquePower(100, 2);

	protected CWFlywheelEntityTest(EntityType<? extends Entity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
		if (pHand.equals(InteractionHand.MAIN_HAND)) {
			torque.currentTorque += 10;
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public void tick() {
		super.tick();
		torque.tick();
		if (!this.entityCollision && torque.hasEnoughTorque()) {
			chasingVelocity += ((20 * 10 / 3f) - chasingVelocity) * .25f;
			independentAngle += chasingVelocity;

			this.setYRot(180);
			this.setDeltaMovement(new Vec3((double) (Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * 0.04), 0.0D,
					(double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * 0.04)));

			this.move(MoverType.SELF, this.getDeltaMovement());
		}
	}

	public float getIndependentAngle(float partialTicks) {
		return (independentAngle + partialTicks * chasingVelocity) / 360;
	}

	@Override
	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
		Lang.text("Torque remaining:" + " " + torque.currentTorque).forGoggles(tooltip);
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
