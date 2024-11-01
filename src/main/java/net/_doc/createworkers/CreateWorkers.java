package net._doc.createworkers;

import org.apache.logging.log4j.LogManager;

import com.simibubi.create.foundation.data.CreateRegistrate;

import net._doc.createworkers.packets.TorqueSyncToClientPacket;
import net._doc.createworkers.registeries.CWBlockEntities;
import net._doc.createworkers.registeries.CWBlocks;
import net._doc.createworkers.registeries.CWClient;
import net._doc.createworkers.registeries.CWCreativeModeTab;
import net._doc.createworkers.registeries.CWEntities;
import net._doc.createworkers.registeries.CWGui;
import net._doc.createworkers.registeries.CWItems;
import net._doc.createworkers.registeries.CWWorkerInteractionPointTypes;
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
import team.creative.creativecore.common.network.CreativeNetwork;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateWorkers.ID)
public class CreateWorkers {
    // Define mod id in a common place for everything to reference
    public static final String ID = "create_workers";
    // Directly reference a slf4j logger
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ID);
    
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);
    public static final CreativeNetwork NETWORK = new CreativeNetwork(1, LOGGER, new ResourceLocation(ID, "main"));
    
    public CreateWorkers() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        REGISTRATE.registerEventListeners(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        CWItems.register();
        CWCreativeModeTab.register(modEventBus);
        CWGui.register();
        CWBlocks.register();
        CWEntities.register(modEventBus);
        CWBlockEntities.register();
        MinecraftForge.EVENT_BUS.register(this);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        CWWorkerInteractionPointTypes.register();
        
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CWClient.onCtorClient(modEventBus, forgeEventBus));
    }
    
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        NETWORK.registerType(TorqueSyncToClientPacket.class, TorqueSyncToClientPacket::new);
    }
    
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {}
    }
    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
        
    }
    
}
