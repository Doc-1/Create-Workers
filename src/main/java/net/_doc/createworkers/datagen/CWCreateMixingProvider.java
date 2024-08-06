package net._doc.createworkers.datagen;

import java.util.function.UnaryOperator;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.registeries.CWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

public class CWCreateMixingProvider extends ProcessingRecipeGen {
    
    GeneratedRecipe CONSTRUCTION_PAPER_PULP = createCW("construction_paper_pulp", b -> b.require(Fluids.WATER, 200).require(CWItems.PAPER_PULP).require(CWItems.PAPER_PULP)
            .require(Items.WHEAT).output(CWItems.CONSTRUCTION_PAPER_PULP)),
            PAPER_PULP_FROM_PAPER = createCW("paper_pulp_from_paper", b -> b.require(Fluids.WATER, 100).require(Items.PAPER).output(CWItems.PAPER_PULP)),
            PAPER_PULP_FROM_CANE = createCW("paper_pulp_from_cane", b -> b.require(Fluids.WATER, 100).require(Items.SUGAR_CANE).output(CWItems.PAPER_PULP, 3));
    
    public CWCreateMixingProvider(PackOutput generator) {
        super(generator);
    }
    
    <T extends ProcessingRecipe<?>> GeneratedRecipe createCW(String name, UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(CreateWorkers.asResource(name), transform);
    }
    
    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.MIXING;
    }
    
}
