package net._doc.createworkers.worker_interactions.controller.actions;

import com.simibubi.create.content.logistics.vault.ItemVaultBlockEntity;

import net._doc.createworkers.entities.CWTransportWorker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TransporterTransferAction extends Action {
    private boolean input;
    
    public TransporterTransferAction(CWTransportWorker entity, boolean input) {
        super(entity);
        this.input = input;
    }
    
    @Override
    public CWTransportWorker getEntity() {
        return (CWTransportWorker) super.getEntity();
    }
    
    @Override
    public boolean shouldContinue() {
        if (!this.getEntity().isHoldingVault())
            this.pickupVault();
        
        return false;
    }
    
    private void pickupVault() {
        if (!this.getEntity().isHoldingVault()) {
            Level level = this.getEntity().level();
            BlockPos pos = this.getEntity().blockPosition().above();
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity != null && entity instanceof ItemVaultBlockEntity vault) {
                
                System.out.println(vault.getInventoryOfBlock());
                vault.setRemoved();
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }
        }
    }
    
    @Override
    public void start() {
        
    }
    
    @Override
    public void end() {
        
    }
    
    @Override
    public boolean hasCompleted() {
        return true;
    }
    
    @Override
    public int torqueCost() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public ActionType getActionType() {
        return ActionType.INTEREACT;
    }
    
}
