package net._doc.createworkers.content.blocks.keypunch.gui;

import net._doc.createworkers.registeries.CWGui;
import net.minecraft.nbt.CompoundTag;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiLayer;
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
        if (this.isClient())
            CWGui.CONDITION.open(getIntegratedParent(), new CompoundTag());
        add(textfield = new GuiTextfield("import_textfield"));
        textfield.setMaxStringLength(Integer.MAX_VALUE);
        
    }
    
}
