package net._doc.createworkers.gui.blocks.keypunch;

import com.simibubi.create.foundation.gui.widget.AbstractSimiWidget;

import net._doc.createworkers.gui.blocks.keypunch.controls.GuiConditionSettings;
import net._doc.createworkers.gui.controls.collection.GuiScrollComboBox;
import net._doc.createworkers.gui.controls.inventory.GuiInventoryGridCustom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiLayer;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.flow.GuiFlow;
import team.creative.creativecore.common.util.math.geo.Rect;
import team.creative.creativecore.common.util.text.TextListBuilder;

public class LayerConditionEditor extends GuiLayer {
    
    public LayerConditionEditor() {
        super("");
        this.setFlow(GuiFlow.STACK_Y);
        this.setAlign(Align.CENTER);
        
    }
    
    @Override
    protected int preferredWidth(int availableWidth) {
        return 200;
    }
    
    @Override
    public boolean mouseClicked(Rect rect, double x, double y, int button) {
        boolean results = super.mouseClicked(rect, x, y, button);
        GuiScrollComboBox tasksComboBox = this.get("comboBox");
        // inv.setEnabled(tasksComboBox.isExtensionOpened());
        return results;
    }
    
    @Override
    public void create() {
        this.add(new GuiLabel("").setTitle(Component.literal("Condition Editor").withStyle(ChatFormatting.BOLD)));
        GuiParent top = new GuiParent("", GuiFlow.STACK_Y, Align.STRETCH);
        TextListBuilder test = new TextListBuilder();
        test.add("Scheduled Delay");
        test.add("Time of Day");
        test.add("Item Cargo Condition");
        test.add("Redstone Link");
        test.add("Cargo Inactivity");
        top.add(new GuiScrollComboBox("comboBox", Component.literal("Continue if/after:").withStyle(s -> s.withColor(AbstractSimiWidget.HEADER_RGB)), test));
        top.add(new GuiConditionSettings("set"));
        
        this.add(top);
        
        GuiParent bottom = new GuiParent("", GuiFlow.STACK_Y, Align.STRETCH);
        GuiParent invLabel = new GuiParent("", GuiFlow.STACK_X, Align.LEFT);
        invLabel.add(new GuiLabel("").setTitle(Component.literal("Inventory").withStyle(ChatFormatting.BOLD)));
        bottom.add(invLabel);
        
        bottom.add(new GuiInventoryGridCustom("player", getPlayer().getInventory(), 9, 4));
        
        this.add(bottom);
    }
}
