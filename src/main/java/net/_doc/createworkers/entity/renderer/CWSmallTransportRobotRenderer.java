package net._doc.createworkers.entity.renderer;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.entities.CWSmallTransportRobotEntity;
import net._doc.createworkers.entity.model.CWSmallTransportRobotModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CWSmallTransportRobotRenderer
		extends MobRenderer<CWSmallTransportRobotEntity, CWSmallTransportRobotModel<CWSmallTransportRobotEntity>> {

	public CWSmallTransportRobotRenderer(Context pContext) {
		super(pContext, new CWSmallTransportRobotModel<>(pContext.bakeLayer(CWModelLayers.CW_SMALL_TRANSPORT_LAYER)),
				0.7F);
	}

	@Override
	public ResourceLocation getTextureLocation(CWSmallTransportRobotEntity pEntity) {
		return CreateWorkers.asResource("textures/entity/small_transport_robot.png");
	}

}
