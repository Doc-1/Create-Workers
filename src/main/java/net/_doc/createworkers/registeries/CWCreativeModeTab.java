package net._doc.createworkers.registeries;

import net._doc.createworkers.CreateWorkers;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CWCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateWorkers.ID);
    
    public static final RegistryObject<CreativeModeTab> CREATE_WORKERS_TAB = CREATIVE_MODE_TABS.register("create_workers_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(CWItems.MAINSPRING.get())).title(Component.translatable("creativetab.create_workers_tab")).displayItems((pParameters, pOutput) -> {
                pOutput.accept(CWItems.MAINSPRING.get());
                pOutput.accept(CWBlocks.TEMP_BLOCK.get());
                pOutput.accept(CWItems.HOLE_PUNCHED_CARD.get());
                pOutput.accept(CWItems.HOLE_PUNCHER.get());
                pOutput.accept(CWItems.CONSTRUCTION_PAPER_PULP.get());
                pOutput.accept(CWItems.PAPER_PULP.get());
                pOutput.accept(CWBlocks.KEYPUNCH.get());
            })
            
            .build());
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
