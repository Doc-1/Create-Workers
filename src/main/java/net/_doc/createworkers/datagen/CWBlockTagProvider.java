package net._doc.createworkers.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.blocks.CWBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CWBlockTagProvider extends BlockTagsProvider {

	public CWBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, CreateWorkers.MOD_ID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addTags(Provider provider) {
		this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(CWBlocks.TEMP_BLOCK.get());
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CWBlocks.TEMP_BLOCK.get());
	}

}
