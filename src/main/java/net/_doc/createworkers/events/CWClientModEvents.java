package net._doc.createworkers.events;

import com.jozufozu.flywheel.backend.instancing.InstancedRenderRegistry;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.entities.CWEntities;
import net._doc.createworkers.entities.CWFlywheelInstanceTest;
import net._doc.createworkers.entity.model.CWSmallTransportRobotModel;
import net._doc.createworkers.entity.rendering.CWModelLayers;
import net._doc.createworkers.entity.rendering.CWSmallTransportRobotRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CreateWorkers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CWClientModEvents {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		EntityRenderers.register(CWEntities.CW_SMALL_TRANSPORT_ROBOT.get(), CWSmallTransportRobotRenderer::new);
		InstancedRenderRegistry.configure(CWEntities.CW_TEST.get()).factory(CWFlywheelInstanceTest::new)
				.alwaysSkipRender().apply();
	}

	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(CWModelLayers.CW_SMALL_TRANSPORT_LAYER,
				CWSmallTransportRobotModel::createBodyLayer);
	}
}
