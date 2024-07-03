package net._doc.createworkers.entities.controller;

import java.util.ArrayList;
import java.util.List;

import net._doc.createworkers.entities.NonLivingEntity;
import net.minecraft.world.phys.Vec3;

public abstract class EntityController {

	public final NonLivingEntity entity;
	private List<Vec3> steps = new ArrayList<>();

	public EntityController(NonLivingEntity entity) {
		this.entity = entity;
	}

	public abstract void tick();
}
