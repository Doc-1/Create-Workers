package net._doc.createworkers;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net._doc.createworkers.content.workers.CWWorkerInteractionPointTypes;
import net._doc.createworkers.entities.CWEntities;
import net._doc.createworkers.items.CWCreativeModeTab;
import net._doc.createworkers.items.CWItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateWorkers.ID)
public class CreateWorkers {
	// Define mod id in a common place for everything to reference
	public static final String ID = "create_workers";
	// Directly reference a slf4j logger
	private static final Logger LOGGER = LogUtils.getLogger();

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);

	public CreateWorkers() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		REGISTRATE.registerEventListeners(modEventBus);
		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::addCreative);

		CWItems.register();
		CWCreativeModeTab.register(modEventBus);
		CWMenuTypes.register();
		CWBlocks.register();
		CWEntities.register(modEventBus);
		CWBlockEntities.register();
		MinecraftForge.EVENT_BUS.register(this);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
		CWWorkerInteractionPointTypes.register();

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CWClient.onCtorClient(modEventBus, forgeEventBus));
		// System.out.println(CreativeCore.MODID);

	}

	public static ResourceLocation asResource(String path) {
		return new ResourceLocation(ID, path);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
		}
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");

	}

}
