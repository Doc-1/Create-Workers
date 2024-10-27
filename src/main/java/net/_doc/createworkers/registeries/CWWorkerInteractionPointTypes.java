package net._doc.createworkers.registeries;

import java.util.function.Function;

import com.simibubi.create.Create;

import net._doc.createworkers.worker_interactions.WorkerInteractionPoint;
import net._doc.createworkers.worker_interactions.WorkerInteractionPointType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CWWorkerInteractionPointTypes {
    public static final WalkType WALK = register("walk", WalkType::new);
    
    private static <T extends WorkerInteractionPointType> T register(String id, Function<ResourceLocation, T> factory) {
        T type = factory.apply(Create.asResource(id));
        WorkerInteractionPointType.register(type);
        return type;
    }
    
    public static void register() {}
    
    public static class WalkType extends WorkerInteractionPointType {
        public WalkType(ResourceLocation id) {
            super(id);
        }
        
        @Override
        public boolean canCreatePoint(Level level, BlockPos pos, BlockState state) {
            return !state.isAir();
        }
        
        @Override
        public WorkerInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
            return new WorkerInteractionPoint(this, level, pos, state);
        }
    }
}
