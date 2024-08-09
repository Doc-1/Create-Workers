package net._doc.createworkers.content.blocks.keypunch.gui;

import net._doc.createworkers.common.gui.controls.collection.GuiComboBoxExtra;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.flow.GuiFlow;
import team.creative.creativecore.common.util.text.TextListBuilder;

public class GuiActionSettings extends GuiParent {
    public GuiActionSettings() {
        super("", GuiFlow.STACK_X, VAlign.CENTER);
        add(new GuiLabel("label").setTranslate("Action"));
        TextListBuilder test = new TextListBuilder();
        test.add("Scheduled Delay");
        test.add("Time of Day");
        test.add("Item Cargo Condition");
        test.add("Redstone Link");
        test.add("Cargo Inactivity");
        GuiComboBoxExtra tasksComboBox = new GuiComboBoxExtra("comboBox", test);
        add(tasksComboBox);
    }
    
}
