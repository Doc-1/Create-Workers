package net._doc.createworkers.datagen;

import net._doc.createworkers.CreateWorkers;
import net._doc.createworkers.items.CWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class CWItemModelProvider extends ItemModelProvider {

	public CWItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, CreateWorkers.MOD_ID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerModels() {
		simpleItem(CWItems.MAINSPRING);
	}

	private ItemModelBuilder simpleItem(RegistryObject<Item> itemRegObj) {
		return withExistingParent(itemRegObj.getId().getPath(), new ResourceLocation("item/generated"))
				.texture("layer0", new ResourceLocation(CreateWorkers.MOD_ID, "item/" + itemRegObj.getId().getPath()));
	}

}
