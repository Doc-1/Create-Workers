package net._doc.createworkers.datagen;

import java.util.function.UnaryOperator;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.items.CWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

public class CWCreateMixingProvider extends ProcessingRecipeGen {

	GeneratedRecipe PAPER_MUSH = createCW("paper_mush", b -> b.require(Fluids.WATER, 1000).require(Items.PAPER)
			.require(Items.PAPER).require(Items.STRING).output(CWItems.DENSE_PAPER_MUSH));

	public CWCreateMixingProvider(PackOutput generator) {
		super(generator);
	}

	<T extends ProcessingRecipe<?>> GeneratedRecipe createCW(String name,
			UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		return create(CreateWorkers.asResource(name), transform);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.MIXING;
	}

}
