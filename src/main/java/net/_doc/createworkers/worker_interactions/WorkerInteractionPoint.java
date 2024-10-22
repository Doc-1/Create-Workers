package net._doc.createworkers.worker_interactions;

import javax.annotation.Nullable;

import com.simibubi.create.content.contraptions.StructureTransform;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmAngleTarget;
import com.simibubi.create.foundation.utility.NBTHelper;
import com.simibubi.create.foundation.utility.VecHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class WorkerInteractionPoint {
    
    protected final WorkerInteractionPointType type;
    protected Level level;
    protected final BlockPos pos;
    protected Mode mode = Mode.WAYPOINT;
    
    protected BlockState cachedState;
    protected LazyOptional<IItemHandler> cachedHandler = LazyOptional.empty();
    protected ArmAngleTarget cachedAngles;
    
    public WorkerInteractionPoint(WorkerInteractionPointType type, Level level, BlockPos pos, BlockState state) {
        this.type = type;
        this.level = level;
        this.pos = pos;
        this.cachedState = state;
    }
    
    public WorkerInteractionPointType getType() {
        return type;
    }
    
    public Level getLevel() {
        return level;
    }
    
    public void setLevel(Level level) {
        this.level = level;
    }
    
    public BlockPos getPos() {
        return pos;
    }
    
    public Mode getMode() {
        return mode;
    }
    
    protected Vec3 getInteractionPositionVector() {
        return VecHelper.getCenterOf(pos);
    }
    
    protected Direction getInteractionDirection() {
        return Direction.DOWN;
    }
    
    public ArmAngleTarget getTargetAngles(BlockPos armPos, boolean ceiling) {
        if (cachedAngles == null)
            cachedAngles = new ArmAngleTarget(armPos, getInteractionPositionVector(), getInteractionDirection(), ceiling);
        
        return cachedAngles;
    }
    
    public void updateCachedState() {
        cachedState = level.getBlockState(pos);
    }
    
    public boolean isValid() {
        updateCachedState();
        return type.canCreatePoint(level, pos, cachedState);
    }
    
    public void keepAlive() {}
    
    @Nullable
    protected IItemHandler getHandler() {
        if (!cachedHandler.isPresent()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be == null)
                return null;
            cachedHandler = be.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP);
        }
        return cachedHandler.orElse(null);
    }
    
    public ItemStack insert(ItemStack stack, boolean simulate) {
        IItemHandler handler = getHandler();
        if (handler == null)
            return stack;
        return ItemHandlerHelper.insertItem(handler, stack, simulate);
    }
    
    public ItemStack extract(int slot, int amount, boolean simulate) {
        IItemHandler handler = getHandler();
        if (handler == null)
            return ItemStack.EMPTY;
        return handler.extractItem(slot, amount, simulate);
    }
    
    public ItemStack extract(int slot, boolean simulate) {
        return extract(slot, 64, simulate);
    }
    
    public int getSlotCount() {
        IItemHandler handler = getHandler();
        if (handler == null)
            return 0;
        return handler.getSlots();
    }
    
    protected void serialize(CompoundTag nbt, BlockPos anchor) {
        NBTHelper.writeEnum(nbt, "Mode", mode);
    }
    
    protected void deserialize(CompoundTag nbt, BlockPos anchor) {
        mode = NBTHelper.readEnum(nbt, "Mode", Mode.class);
    }
    
    public final CompoundTag serialize(BlockPos anchor) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("Type", type.getId().toString());
        nbt.put("Pos", NbtUtils.writeBlockPos(pos.subtract(anchor)));
        serialize(nbt, anchor);
        return nbt;
    }
    
    @Nullable
    public static WorkerInteractionPoint deserialize(CompoundTag nbt, Level level, BlockPos anchor) {
        ResourceLocation id = ResourceLocation.tryParse(nbt.getString("Type"));
        if (id == null)
            return null;
        WorkerInteractionPointType type = WorkerInteractionPointType.get(id);
        if (type == null)
            return null;
        BlockPos pos = NbtUtils.readBlockPos(nbt.getCompound("Pos")).offset(anchor);
        BlockState state = level.getBlockState(pos);
        if (!type.canCreatePoint(level, pos, state))
            return null;
        WorkerInteractionPoint point = type.createPoint(level, pos, state);
        if (point == null)
            return null;
        point.deserialize(nbt, anchor);
        return point;
    }
    
    public static void transformPos(CompoundTag nbt, StructureTransform transform) {
        BlockPos pos = NbtUtils.readBlockPos(nbt.getCompound("Pos"));
        pos = transform.applyWithoutOffset(pos);
        nbt.put("Pos", NbtUtils.writeBlockPos(pos));
    }
    
    public static boolean isInteractable(Level level, BlockPos pos, BlockState state) {
        return WorkerInteractionPointType.getPrimaryType(level, pos, state) != null;
    }
    
    @Nullable
    public static WorkerInteractionPoint create(Level level, BlockPos pos, BlockState state) {
        WorkerInteractionPointType type = WorkerInteractionPointType.getPrimaryType(level, pos, state);
        if (type == null)
            return null;
        return type.createPoint(level, pos, state);
    }
    
    public enum Mode {
        WAYPOINT("mechanical_arm.deposit_to", 0xDD8666);
        
        private final String translationKey;
        private final int color;
        
        Mode(String translationKey, int color) {
            this.translationKey = translationKey;
            this.color = color;
        }
        
        public String getTranslationKey() {
            return translationKey;
        }
        
        public int getColor() {
            return color;
        }
    }
    
}
