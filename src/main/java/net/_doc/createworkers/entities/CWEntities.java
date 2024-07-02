package net._doc.createworkers.entities;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CWEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.ENTITY_TYPES, CreateWorkers.MOD_ID);

	public static final RegistryObject<EntityType<CWSmallTransportRobotEntity>> CW_SMALL_TRANSPORT_ROBOT = ENTITY_TYPES
			.register("small_transport_robot",
					() -> EntityType.Builder.of(CWSmallTransportRobotEntity::new, MobCategory.CREATURE)
							.sized(1.5F, 1.5F).build("small_transport_robot"));

	public static final RegistryObject<EntityType<CWFlywheelEntityTest>> CW_TEST = ENTITY_TYPES.register("cw_test",
			() -> EntityType.Builder.of(CWFlywheelEntityTest::new, MobCategory.MISC).sized(1F, 1F)
					.clientTrackingRange(10).build("cw_test"));

	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
