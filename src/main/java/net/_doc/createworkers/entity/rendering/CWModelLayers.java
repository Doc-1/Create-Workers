package net._doc.createworkers.entity.rendering;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class CWModelLayers {
	public static final ModelLayerLocation RHINO_LAYER = new ModelLayerLocation(
			new ResourceLocation(CreateWorkers.MOD_ID, "rhino_layer"), "main");

	public static final ModelLayerLocation CW_SMALL_TRANSPORT_LAYER = new ModelLayerLocation(
			new ResourceLocation(CreateWorkers.MOD_ID, "small_transport_layer"), "main");
}
