package net._doc.createworkers.content.blocks.keypunch.gui;

import net.minecraft.network.chat.Component;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiLayer;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.parent.GuiScrollY;
import team.creative.creativecore.common.gui.controls.simple.GuiButton;
import team.creative.creativecore.common.gui.flow.GuiFlow;

public class GuiKeypunch extends GuiLayer {
    
    public GuiKeypunch() {
        super("keypunch");
        this.setFlow(GuiFlow.STACK_Y);
    }
    
    @Override
    public void create() {
        GuiScrollY list = new GuiScrollY("list").setDim(220, 200);
        list.setAlign(Align.STRETCH);
        list.add(new GuiParent("add").add((GuiButton) new GuiButton("", x -> {
            list.insertControlBefore(list.get("add"), new GuiCondition(""));
            list.reflow();
        }).setTitle(Component.literal("+")).setAlign(Align.CENTER).setVAlign(VAlign.CENTER).setDim(8, 8)));
        this.add(list);
        
    }
    
    /*
        if (this.isClient())
            CWGui.CONDITION.open(getIntegratedParent(), new CompoundTag());
     */
}
