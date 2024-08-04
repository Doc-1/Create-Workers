package net._doc.createworkers;

import com.tterrag.registrate.util.entry.BlockEntityEntry;

import net._doc.createworkers.content.blocks.keypunch.KeypunchBlockEntity;

public class CWBlockEntities {

	public static final BlockEntityEntry<KeypunchBlockEntity> KEYPUNCH = CreateWorkers.REGISTRATE
			.blockEntity("keypunch", KeypunchBlockEntity::new).validBlocks(CWBlocks.KEYPUNCH).register();

	public static void register() {
	}
}
