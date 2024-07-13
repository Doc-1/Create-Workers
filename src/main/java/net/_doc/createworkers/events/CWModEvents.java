package net._doc.createworkers.events;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.entities.CWEntities;
import net._doc.createworkers.entities.CWSmallTransportRobotEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateWorkers.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CWModEvents {

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(CWEntities.CW_SMALL_TRANSPORT_ROBOT.get(), CWSmallTransportRobotEntity.createAttributes().build());
	}
}
