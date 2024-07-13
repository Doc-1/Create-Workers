package net._doc.createworkers.events;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.content.workers.WorkerInteractionPointHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateWorkers.ID, value = Dist.CLIENT)
public class CWClientEvents {

	@SubscribeEvent
	public static void onTick(ClientTickEvent event) {
		if (!isGameActive())
			return;
		WorkerInteractionPointHandler.tick();
	}

	@SuppressWarnings("resource")
	protected static boolean isGameActive() {
		return !(Minecraft.getInstance().level == null || Minecraft.getInstance().player == null);
	}
}
