package net._doc.createworkers.registeries;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.entities.CWTransportWorker;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CWEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CreateWorkers.ID);
    
    public static final RegistryObject<EntityType<CWTransportWorker>> CW_TEST = ENTITY_TYPES
            .register("cw_test", () -> EntityType.Builder.of(CWTransportWorker::new, MobCategory.MISC).sized(0.95F, 0.9F).clientTrackingRange(10).build("cw_test"));
    
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
