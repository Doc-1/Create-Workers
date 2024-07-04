package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;

import net._doc.createworkers.content.logic.torque.TorquePower;
import net._doc.createworkers.entities.controller.actions.MoveAction;
import net._doc.createworkers.entities.controller.actions.RotateAction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

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
		this.controller.add(new MoveAction(3));
		this.controller.add(new RotateAction(1000F));
		this.controller.add(new MoveAction(5));
	}

	@Override
	public TorquePower setTorquePower() {
		return new TorquePower(50, 2);
	}

}
