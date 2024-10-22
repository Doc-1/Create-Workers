package net._doc.createworkers.registeries;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.entities.model.CWSmallTransportRobotModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class CWEntityLayerDefinition {
    public static final ModelLayerLocation CW_SMALL_TRANSPORT_LAYER = new ModelLayerLocation(new ResourceLocation(CreateWorkers.ID, "small_transport_layer"), "main");
    
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CWEntityLayerDefinition.CW_SMALL_TRANSPORT_LAYER, CWSmallTransportRobotModel::createBodyLayer);
    }
    
}
