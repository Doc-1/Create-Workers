package net._doc.createworkers;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CWClient {
	public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
		modEventBus.addListener(CWClient::clientInit);
	}

	public static void clientInit(final FMLClientSetupEvent event) {
		CWPartialModels.init();

	}
}
