package net._doc.createworkers.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CWItemTagProvider extends ItemTagsProvider {

	public CWItemTagProvider(PackOutput p_275343_, CompletableFuture<Provider> p_275729_,
			CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
		super(p_275343_, p_275729_, p_275322_, CreateWorkers.MOD_ID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addTags(Provider provider) {

	}

}
