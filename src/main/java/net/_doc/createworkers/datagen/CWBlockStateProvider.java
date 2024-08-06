package net._doc.createworkers.datagen;

import com.tterrag.registrate.util.entry.BlockEntry;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.registeries.CWBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CWBlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    
    public CWBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CreateWorkers.ID, exFileHelper);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(CWBlocks.TEMP_BLOCK);
    }
    
    private void blockWithItem(BlockEntry<Block> blockRegObj) {
        Block block = blockRegObj.get();
        simpleBlockWithItem(block, cubeAll(block));
    }
    
}
