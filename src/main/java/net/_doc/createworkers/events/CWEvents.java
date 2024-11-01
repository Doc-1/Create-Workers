package net._doc.createworkers.events;

import net._doc.createworkers.registeries.CWCapability;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CWEvents {
    
    @SubscribeEvent
    public static void reg(AttachCapabilitiesEvent<Entity> event) {
        CWCapability.attachCapability(event);
    }
}
