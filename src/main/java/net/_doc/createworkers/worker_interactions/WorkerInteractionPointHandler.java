package net._doc.createworkers.worker_interactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.simibubi.create.AllItems;
import com.simibubi.create.CreateClient;

import net._doc.createworkers.registeries.CWItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class WorkerInteractionPointHandler {
    
    static List<WorkerInteractionPoint> currentSelection = new ArrayList<>();
    static ItemStack currentItem;
    
    static long lastBlockPos = -1;
    
    public static void flushSettings(BlockPos pos) {}
    
    @SuppressWarnings("resource")
    public static void tick() {
        Player player = Minecraft.getInstance().player;
        
        if (player == null)
            return;
        
        ItemStack heldItemMainhand = player.getMainHandItem();
        
        if (!heldItemMainhand.getItem().equals(CWItems.HOLE_PUNCHER.get())) {
            currentItem = null;
        } else {
            if (heldItemMainhand != currentItem) {
                currentSelection.clear();
                currentItem = heldItemMainhand;
            }
            
            drawOutlines(currentSelection);
        }
        
        checkForWrench(heldItemMainhand);
    }
    
    @SuppressWarnings("resource")
    private static void checkForWrench(ItemStack heldItem) {
        if (!AllItems.WRENCH.isIn(heldItem)) {
            return;
        }
        
        HitResult objectMouseOver = Minecraft.getInstance().hitResult;
        if (!(objectMouseOver instanceof BlockHitResult)) {
            return;
        }
        
        BlockHitResult result = (BlockHitResult) objectMouseOver;
        BlockPos pos = result.getBlockPos();
        
        BlockEntity be = Minecraft.getInstance().level.getBlockEntity(pos);
        
        if (lastBlockPos == -1 || lastBlockPos != pos.asLong()) {
            /*
             * currentSelection.clear(); ArmBlockEntity arm = (ArmBlockEntity) be;
             * arm.inputs.forEach(WorkerInteractionPointHandler::put);
             * arm.outputs.forEach(WorkerInteractionPointHandler::put); lastBlockPos =
             * pos.asLong();
             */}
            
        if (lastBlockPos != -1) {
            drawOutlines(currentSelection);
        }
    }
    
    private static void drawOutlines(Collection<WorkerInteractionPoint> selection) {
        for (Iterator<WorkerInteractionPoint> iterator = selection.iterator(); iterator.hasNext();) {
            WorkerInteractionPoint point = iterator.next();
            
            if (!point.isValid()) {
                iterator.remove();
                continue;
            }
            
            Level level = point.getLevel();
            BlockPos pos = point.getPos();
            BlockState state = level.getBlockState(pos);
            VoxelShape shape = state.getShape(level, pos);
            if (shape.isEmpty())
                continue;
            
            int color = point.getMode().getColor();
            CreateClient.OUTLINER.showAABB(point, shape.bounds().move(pos)).colored(color).lineWidth(1 / 16f);
        }
    }
    
    public static void put(WorkerInteractionPoint point) {
        currentSelection.add(point);
    }
    
    public static WorkerInteractionPoint remove(BlockPos pos) {
        WorkerInteractionPoint result = getSelected(pos);
        if (result != null)
            currentSelection.remove(result);
        return result;
    }
    
    public static WorkerInteractionPoint getSelected(BlockPos pos) {
        for (WorkerInteractionPoint point : currentSelection)
            if (point.getPos().equals(pos))
                return point;
        return null;
    }
    
}