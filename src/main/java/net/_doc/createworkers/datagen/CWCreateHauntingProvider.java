package net._doc.createworkers.datagen;

import java.util.function.Supplier;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.RegisteredObjects;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.blocks.CWBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class CWCreateHauntingProvider extends ProcessingRecipeGen {

	GeneratedRecipe BRASS_BELL = convert(() -> Ingredient.of(CWBlocks.TEMP_BLOCK.get()),
			AllBlocks.ANDESITE_CASING::get);

	public CWCreateHauntingProvider(PackOutput generator) {
		super(generator);
		// TODO Auto-generated constructor stub
	}

	public GeneratedRecipe convert(ItemLike input, ItemLike result) {
		return convert(() -> Ingredient.of(input), () -> result);
	}

	public GeneratedRecipe convert(Supplier<Ingredient> input, Supplier<ItemLike> result) {
		return create(CreateWorkers.asResource(RegisteredObjects.getKeyOrThrow(result.get().asItem()).getPath()),
				p -> p.withItemIngredients(input.get()).output(result.get()));
	}

	@Override
	protected IRecipeTypeInfo getRecipeType() {
		return AllRecipeTypes.HAUNTING;
	}

}
