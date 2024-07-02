package net._doc.createworkers.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class CWFlywheelEntityTest extends NonLivingEntity {

	private float chasingVelocity = 0;
	private float independentAngle = 0;

	protected CWFlywheelEntityTest(EntityType<? extends Entity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.blocksBuilding = true;

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

}