package net._doc.createworkers.entities.controller;

import java.util.ArrayList;
import java.util.List;

import net._doc.createworkers.entities.CWFlywheelEntityTest;
import net._doc.createworkers.entities.NonLivingEntity;
import net._doc.createworkers.entities.controller.actions.Action;
import net.minecraft.util.RandomSource;

public class EntityController {

	protected final RandomSource random = RandomSource.create();
	public final NonLivingEntity entity;
	private List<Action> steps = new ArrayList<>();
	private int selectedIndex = 0;

	public EntityController(NonLivingEntity entity) {
		this.entity = entity;
	}

	public void add(Action action) {
		this.steps.add(action);
	}

	public List<Action> getSteps() {
		return steps;
	}

	public void setSteps(List<Action> steps) {
		this.steps = steps;
	}

	public void tick() {
		if (steps.size() > selectedIndex) {
			CWFlywheelEntityTest entity = (CWFlywheelEntityTest) this.entity;
			Action action = steps.get(selectedIndex);
			if (entity.getTorquePower().hasEnoughTorque(action.torqueCost())) {
				if (action.tick(entity))
					action.start(entity);
				if (action.hasCompleted()) {
					action.end(entity);
					selectedIndex++;
				}
			}
		}

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
