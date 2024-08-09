package net._doc.createworkers.content.blocks.keypunch.gui;

import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiLayer;
import team.creative.creativecore.common.gui.controls.inventory.GuiPlayerInventoryGrid;
import team.creative.creativecore.common.gui.controls.simple.GuiTextfield;
import team.creative.creativecore.common.gui.flow.GuiFlow;

public class GuiKeypunch extends GuiLayer {
    public GuiTextfield textfield;
    public GuiPlayerInventoryGrid inv;
    
    public GuiKeypunch() {
        super("keypunch");
        this.setFlow(GuiFlow.STACK_Y);
        this.setAlign(Align.STRETCH);
    }
    
    @Override
    public void create() {
        add(new GuiActionSettings());
        add(textfield = new GuiTextfield("import_textfield"));
        textfield.setMaxStringLength(Integer.MAX_VALUE);
        
        add(inv = (GuiPlayerInventoryGrid) new GuiPlayerInventoryGrid(getPlayer()).setUnexpandableX());
        registerEventClick(x -> {
            System.out.println(x + " da");
        });
    }
}
