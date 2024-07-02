package net._doc.createworkers.datagen;

import java.util.concurrent.CompletableFuture;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateWorkers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CWDataGenerator {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput out = gen.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

		// gen.addProvider(event.includeServer(), new CWRecipeProvider(out));
		// gen.addProvider(event.includeServer(), new CWBlockStateProvider(out,
		// helper));
		gen.addProvider(event.includeServer(), new CWItemModelProvider(out, helper));

		// CWBlockTagProvider tagProvider = gen.addProvider(event.includeServer(),
		// new CWBlockTagProvider(out, provider, helper));
		// gen.addProvider(event.includeServer(),
		// new CWItemTagProvider(out, provider, tagProvider.contentsGetter(), helper));

		gen.addProvider(true, new CWCreateHauntingProvider(out));
	}
}
