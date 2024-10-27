package net._doc.createworkers.registeries;

import com.jozufozu.flywheel.core.PartialModel;

import net._doc.createworkers.CreateWorkers;

public class CWPartialModels {
    
    public static final PartialModel
    
    TRANSPORT_WORKER_LIFT = block("workers/transport/lift/lift"), TRANSPORT_WORKER = block("workers/transport/body/body");
    
    private static PartialModel block(String path) {
        return new PartialModel(CreateWorkers.asResource("block/" + path));
    }
    
    private static PartialModel entity(String path) {
        return new PartialModel(CreateWorkers.asResource("entity/" + path));
    }
    
    public static void init() {
        System.out.println(TRANSPORT_WORKER_LIFT);
        // init static fields
    }
    
}
