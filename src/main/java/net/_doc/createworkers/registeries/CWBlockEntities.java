package net._doc.createworkers.registeries;

import com.tterrag.registrate.util.entry.BlockEntityEntry;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.blocks.keypunch.KeypunchBlockEntity;

public class CWBlockEntities {
    
    public static final BlockEntityEntry<KeypunchBlockEntity> KEYPUNCH = CreateWorkers.REGISTRATE.blockEntity("keypunch", KeypunchBlockEntity::new).validBlocks(CWBlocks.BASIC_WORKER_BODY)
            .register();
    
    public static void register() {}
}
