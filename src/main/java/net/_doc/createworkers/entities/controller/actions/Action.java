package net._doc.createworkers.entities.controller.actions;

import net._doc.createworkers.entities.Worker;

public interface Action {
	public enum ActionType {
		WAIT, MOVE, ROTATE, INTEREACT;
	}

	public boolean tick(Worker entity);

	public void start(Worker entity);

	public void end(Worker entity);

	public boolean hasCompleted();

	public int torqueCost();

	public ActionType getActionType();
}
