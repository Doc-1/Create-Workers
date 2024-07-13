package net._doc.createworkers;

import com.jozufozu.flywheel.core.PartialModel;

public class CWPartialModels {

	public static final PartialModel

	TRANSPORT_WORKER_LIFT = entity("workers/transport/lift");

	private static PartialModel block(String path) {
		return new PartialModel(CreateWorkers.asResource("block/" + path));
	}

	private static PartialModel entity(String path) {
		return new PartialModel(CreateWorkers.asResource("entity/" + path));
	}

	public static void init() {
		// init static fields
	}

}
