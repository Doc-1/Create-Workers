package net._doc.createworkers.gui.blocks.keypunch.controls;

import com.simibubi.create.foundation.gui.widget.AbstractSimiWidget;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import net._doc.ccgexpansion.common.gui.controls.inventory.GuiSlotIcon;
import net._doc.ccgexpansion.common.gui.controls.simple.GuiScrollIntegerField;
import net._doc.createworkers.gui.controls.collection.GuiScrollComboBox;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.flow.GuiFlow;
import team.creative.creativecore.common.util.text.TextListBuilder;

public class GuiConditionSettings extends GuiParent {
    
    protected SimpleContainer container = new SimpleContainer(1);
    
    protected GuiSlotIcon slot;
    
    public GuiConditionSettings(String name) {
        super(name, GuiFlow.STACK_X, Align.CENTER, VAlign.CENTER);
        this.setSpacing(5);
        slot = new GuiSlotIcon(new ItemStack(Items.REPEATER));
        this.add(slot);
        ConditionSettings.SCHEDULED_DALEY.addTo(this);
        
    }
    
    public enum ConditionSettings {
        SCHEDULED_DALEY(x -> {
            x.add(new GuiScrollIntegerField("Duration:", "0", true).setMinMaxNumber(0, 120));
            TextListBuilder textList = new TextListBuilder();
            textList.add("Ticks");
            textList.add("Seconds");
            textList.add("Minutes");
            x.add(new GuiScrollComboBox("", Component.literal("Time Unit:").withStyle(s -> s.withColor(AbstractSimiWidget.HEADER_RGB)), textList).setDim(70, 10));
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
        
        public GuiParent addTo(GuiParent parent) {
            object.accept(parent);
            return parent;
        }
    }
    
}