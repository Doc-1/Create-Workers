package net._doc.createworkers.datagen;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.registeries.CWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class CWCreatePressingProvider extends ProcessingRecipeGen {
    GeneratedRecipe
    
    CONSTRUCTION_PAPER = createCW(() -> CWItems.CONSTRUCTION_PAPER_PULP, b -> b.output(CWItems.HOLE_PUNCHED_CARD)),
            PAPER = createCW(() -> CWItems.PAPER_PULP, b -> b.output(Items.PAPER));
    
    public CWCreatePressingProvider(PackOutput generator) {
        super(generator);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.PRESSING;
    }
    
    <T extends ProcessingRecipe<?>> GeneratedRecipe createCW(Supplier<ItemLike> singleIngredient, UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(CreateWorkers.ID, singleIngredient, transform);
    }
}
