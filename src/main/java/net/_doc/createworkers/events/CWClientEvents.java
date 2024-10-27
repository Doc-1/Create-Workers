package net._doc.createworkers.events;

import com.simibubi.create.foundation.utility.RaycastHelper;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.events.interfaces.IItemFinishUseListener;
import net._doc.createworkers.events.interfaces.IItemLeftClickBlockListener;
import net._doc.createworkers.worker_interactions.WorkerInteractionPointHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateWorkers.ID, value = Dist.CLIENT)
public class CWClientEvents {
    
    @SubscribeEvent
    public static void onTick(ClientTickEvent event) {
        if (!isGameActive())
            return;
        WorkerInteractionPointHandler.tick();
    }
    
    @SubscribeEvent
    public static void leftClickingBlocksDeselectsThem(PlayerInteractEvent.LeftClickBlock event) {
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offHand = player.getItemInHand(InteractionHand.OFF_HAND);
        if (mainHand.getItem() instanceof IItemLeftClickBlockListener item)
            if (item.onItemLeftClickBlock(event.getLevel(), player, mainHand, offHand, pos)) {
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
    }
    
    @SubscribeEvent
    public static void finishUsingItemSelectsThem(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            
            Level level = event.getEntity().level();
            BlockPos lookingAt = RaycastHelper.rayTraceRange(level, player, player.getBlockReach()).getBlockPos();
            if (!level.isClientSide)
                return;
            if (player != null && player.isSpectator())
                return;
            
            ItemStack useItem = event.getItem();
            if (useItem.getItem() instanceof IItemFinishUseListener item)
                item.onItemFinishUse(player, level, useItem, lookingAt);
        }
    }
    
    @SuppressWarnings("resource")
    protected static boolean isGameActive() {
        return !(Minecraft.getInstance().level == null || Minecraft.getInstance().player == null);
    }
}
