package net._doc.createworkers.entities.controller.actions;

public interface IAction {
	public enum ActionType {
		WAIT, MOVE, ROTATE, INTEREACT;
	}

	public boolean tick();

	public void start();

	public void end();

	public boolean hasCompleted();

	public int torqueCost();

	public ActionType getActionType();
}
