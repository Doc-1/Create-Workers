package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;

import net._doc.createworkers.content.logic.torque.TorquePower;
import net._doc.createworkers.entities.controller.actions.MoveToVecAction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CWFlywheelEntityTest extends Worker implements IHaveGoggleInformation {

	private float chasingVelocity = 0;
	private float independentAngle = 0;

	protected CWFlywheelEntityTest(EntityType<? extends Entity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public void tick() {
		chasingVelocity += ((20 * 10 / 3f) - chasingVelocity) * .25f;
		independentAngle += chasingVelocity;
		super.tick();
	}

	public float getIndependentAngle(float partialTicks) {
		return (independentAngle + partialTicks * chasingVelocity) / 360;
	}

	@Override
	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
		return super.addToGoggleTooltip(tooltip, isPlayerSneaking);
	}

	@Override
	public void setControllerActions() {
		// this.controller.add(new MoveToVecAction(new Vec3(242, 0, -24)));
		this.controller.add(new MoveToVecAction(this, new Vec3(236.5, 0, -16.5)));
		this.controller.add(new MoveToVecAction(this, new Vec3(236.5, 0, -14.5)));

	}

	@Override
	public TorquePower setTorquePower() {
		return new TorquePower(50, 2);
	}

}
