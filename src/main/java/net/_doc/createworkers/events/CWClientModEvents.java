package net._doc.createworkers.events;

import com.jozufozu.flywheel.backend.instancing.InstancedRenderRegistry;
import com.mojang.logging.LogUtils;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.entities.CWTransportWorkerInstance;
import net._doc.createworkers.items.goggles.AdditionalGoggleOverlayRenderer;
import net._doc.createworkers.registeries.CWEntities;
import net._doc.createworkers.registeries.CWEntityLayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CreateWorkers.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CWClientModEvents {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        InstancedRenderRegistry.configure(CWEntities.CW_TEST.get()).factory(CWTransportWorkerInstance::new).alwaysSkipRender().apply();
    }
    
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        CWEntityLayerDefinition.register(event);
    }
    
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        // Register overlays in reverse order
        LogUtils.getLogger().info("LOADED OVERLAY \n\n\n\n\n");
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "goggle_info_additional", AdditionalGoggleOverlayRenderer.OVERLAY);
    }
    
}
