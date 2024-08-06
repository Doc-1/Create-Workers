package net._doc.createworkers.content.blocks.keypunch;

import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiLayer;
import team.creative.creativecore.common.gui.controls.inventory.GuiPlayerInventoryGrid;
import team.creative.creativecore.common.gui.controls.simple.GuiTextfield;
import team.creative.creativecore.common.gui.flow.GuiFlow;

public class GuiKeypunch extends GuiLayer {
    public GuiTextfield textfield;
    
    public GuiKeypunch() {
        super("keypunch");
        this.setFlow(GuiFlow.STACK_Y);
        this.setAlign(Align.STRETCH);
    }
    
    @Override
    public void create() {
        add(textfield = new GuiTextfield("import_textfield"));
        textfield.setMaxStringLength(Integer.MAX_VALUE);
        
        add(new GuiPlayerInventoryGrid(getPlayer()).setUnexpandableX());
    }
}
