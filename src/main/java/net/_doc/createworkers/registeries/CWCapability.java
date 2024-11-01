package net._doc.createworkers.registeries;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.capabilities.torque.ITorqueStorageHandler;
import net._doc.createworkers.capabilities.torque.TorqueStorageController;
import net._doc.createworkers.entities.Worker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CWCapability {
    
    public static final ResourceLocation TORQUE_CAPABILITY_KEY = CreateWorkers.asResource("capability_torque");
    public static final Capability<ITorqueStorageHandler> TORQUE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof Worker worker))
            return;
        event.addCapability(TORQUE_CAPABILITY_KEY, new TorqueStorageController(worker));
    }
}
