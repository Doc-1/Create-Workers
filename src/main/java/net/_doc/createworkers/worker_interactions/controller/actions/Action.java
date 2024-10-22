package net._doc.createworkers.worker_interactions.controller.actions;

import net._doc.createworkers.entities.Worker;

public abstract class Action implements IAction {
	private final Worker entity;

	public Action(Worker entity) {
		this.entity = entity;
	}

	public Worker getEntity() {
		return entity;
	}
}
