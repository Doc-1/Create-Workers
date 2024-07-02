package net._doc.createworkers;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net._doc.createworkers.blocks.CWBlocks;
import net._doc.createworkers.entities.CWEntities;
import net._doc.createworkers.items.CWCreativeModeTab;
import net._doc.createworkers.items.CWItems;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateWorkers.MOD_ID)
public class CreateWorkers {
	// Define mod id in a common place for everything to reference
	public static final String MOD_ID = "create_workers";
	// Directly reference a slf4j logger
	private static final Logger LOGGER = LogUtils.getLogger();

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

	public CreateWorkers() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRATE.registerEventListeners(modEventBus);
		modEventBus.addListener(this::commonSetup);

		CWItems.register(modEventBus);

		CWCreativeModeTab.register(modEventBus);

		CWBlocks.register();

		CWEntities.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);

		modEventBus.addListener(this::addCreative);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
	}

	public static ResourceLocation asResource(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			event.accept(CWItems.MAINSPRING);
		}
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");

		DispenserBlock.registerBehavior(Items.OAK_BOAT, new BoatDispenseItemBehavior(Boat.Type.OAK));

	}

}
