package net._doc.createworkers.content.blocks.keypunch.gui;

import net._doc.createworkers.common.gui.controls.collection.GuiScrollComboBox;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiLayer;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.inventory.GuiPlayerInventoryGrid;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.flow.GuiFlow;
import team.creative.creativecore.common.util.math.geo.Rect;
import team.creative.creativecore.common.util.text.TextListBuilder;

public class GuiCondition extends GuiLayer {
    public GuiPlayerInventoryGrid inv;
    
    public GuiCondition() {
        super("");
        this.setFlow(GuiFlow.STACK_Y);
    }
    
    @Override
    public boolean mouseClicked(Rect rect, double x, double y, int button) {
        boolean results = super.mouseClicked(rect, x, y, button);
        GuiScrollComboBox tasksComboBox = this.get("comboBox");
        inv.setEnabled(tasksComboBox.isExtensionOpened());
        return results;
    }
    
    @Override
    public void create() {
        GuiParent top = new GuiParent("", GuiFlow.STACK_X, VAlign.CENTER);
        top.add(new GuiLabel("label").setTranslate("Condition: "));
        TextListBuilder test = new TextListBuilder();
        test.add("Scheduled Delay");
        test.add("Time of Day");
        test.add("Item Cargo Condition");
        test.add("Redstone Link");
        test.add("Cargo Inactivity");
        GuiScrollComboBox tasksComboBox = new GuiScrollComboBox("comboBox", "Continue if/after:", test);
        top.add(tasksComboBox);
        GuiParent bottom = new GuiParent("", GuiFlow.STACK_Y, Align.CENTER);
        
        bottom.add(new GuiConditionSettings("set"));
        bottom.add(inv = (GuiPlayerInventoryGrid) new GuiPlayerInventoryGrid(getPlayer()).setUnexpandableX());
        this.add(top);
        this.add(bottom);
    }
}
