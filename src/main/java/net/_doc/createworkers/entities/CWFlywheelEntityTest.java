package net._doc.createworkers.entities;

import java.util.List;

import com.simibubi.create.content.equipment.goggles.IHaveHoveringInformation;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class CWFlywheelEntityTest extends NonLivingEntity implements IHaveHoveringInformation {

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

	@Override
	public boolean addToTooltip(List<Component> tooltip, boolean isPlayerSneaking) {

		tooltip.add(Lang.text("Test").component());
		return true;
	}
}
