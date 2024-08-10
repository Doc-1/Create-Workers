package net._doc.createworkers.content.blocks.keypunch.gui;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import net._doc.createworkers.common.gui.controls.collection.GuiScrollComboBox;
import net._doc.createworkers.common.gui.controls.simple.GuiScrollNumberField;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.inventory.GuiSlot;
import team.creative.creativecore.common.gui.controls.inventory.IGuiInventory;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.flow.GuiFlow;
import team.creative.creativecore.common.util.text.TextListBuilder;

public class GuiConditionSettings extends GuiParent implements IGuiInventory {
    public GuiConditionSettings(String name) {
        super(name, GuiFlow.STACK_X, VAlign.CENTER);
        this.add(ConditionSettings.SCHEDULED_DALEY.getParent());
        
    }
    
    public enum ConditionSettings {
        SCHEDULED_DALEY(x -> {
            Slot slot = new Slot(new SimpleContainer(1), 0, 0, 0);
            slot.set(new ItemStack(Items.REPEATER));
            x.add(new GuiSlot(slot).setEnabled(true));
            x.add(new GuiScrollNumberField("Duration:", "0").setMinMaxDciaml(0, 120));
            TextListBuilder textList = new TextListBuilder();
            textList.add("Ticks");
            textList.add("Seconds");
            textList.add("Minutes");
            x.add(new GuiScrollComboBox("", "Time Unit:", textList));
        }),
        TIME_OF_DAY(x -> {
            x.add(new GuiLabel("").setTitle(Component.literal("Time")));
        }),
        ITEM_CARGO_CONDITION(x -> {
            
        }),
        REDSTONE_LINK(x -> {
            
        }),
        CARGO_INACTIVITY(x -> {
            
        });
        
        private Consumer<GuiParent> object;
        
        ConditionSettings(Consumer<GuiParent> object) {
            this.object = object;
        }
        
        public GuiParent getParent() {
            GuiParent parent = new GuiParent();
            parent.valign = VAlign.CENTER;
            parent.flow = GuiFlow.STACK_X;
            object.accept(parent);
            return parent;
        }
    }
    
    @Override
    public GuiSlot getSlot(int index) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public int inventorySize() {
        // TODO Auto-generated method stub
        return 1;
    }
    
    @Override
    public String name() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void setChanged() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void setChanged(int slotIndex) {
        // TODO Auto-generated method stub
        
    }
}