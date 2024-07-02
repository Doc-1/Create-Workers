package net._doc.createworkers.blocks;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;

public class CWBlocks {

	public static final BlockEntry<Block> TEMP_BLOCK = CreateWorkers.REGISTRATE.block("temp_block", Block::new)
			.defaultLoot().initialProperties(SharedProperties::stone)
			.properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL)).transform(TagGen.pickaxeOnly())
			.transform(b -> b.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)).item().transform(customItemModel()).register();

	public static void register() {
	}
}
