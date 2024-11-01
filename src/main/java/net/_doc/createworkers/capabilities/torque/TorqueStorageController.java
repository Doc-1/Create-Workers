package net._doc.createworkers.capabilities.torque;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net._doc.createworkers.entities.Worker;
import net._doc.createworkers.registeries.CWCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TorqueStorageController implements ICapabilitySerializable<CompoundTag> {
    
    private LazyOptional<TorqueStorageHandler> cap;
    private TorqueStorageHandler handler;
    
    public TorqueStorageController(Worker worker) {
        handler = new TorqueStorageHandler(500, 500);
        cap = LazyOptional.of(() -> handler);
    }
    
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CWCapability.TORQUE_CAPABILITY)
            return this.cap.cast();
        return LazyOptional.empty();
    }
    
    @Override
    public CompoundTag serializeNBT() {
        return handler.serializeNBT();
    }
    
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        handler.deserializeNBT(nbt);
    }
    
}
