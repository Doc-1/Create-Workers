package net._doc.createworkers.registeries;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.content.blocks.keypunch.KeypunchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;

public class CWBlocks {
    
    public static final BlockEntry<Block> TEMP_BLOCK = CreateWorkers.REGISTRATE.block("temp_block", Block::new).defaultLoot().initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL)).transform(TagGen.pickaxeOnly()).transform(b -> b.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)).item().build()
            .register();
    
    public static final BlockEntry<KeypunchBlock> KEYPUNCH = CreateWorkers.REGISTRATE.block("keypunch", KeypunchBlock::new).initialProperties(SharedProperties::softMetal)
            .properties(p -> p.noOcclusion().mapColor(MapColor.COLOR_YELLOW)).blockstate(BlockStateGen.horizontalBlockProvider(true)).transform(axeOrPickaxe()).item()
            .transform(customItemModel()).register();
    
    public static void register() {}
}
