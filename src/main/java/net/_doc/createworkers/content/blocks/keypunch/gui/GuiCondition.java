package net._doc.createworkers.content.blocks.keypunch.gui;

import net.minecraft.network.chat.Component;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.parent.GuiPanel;
import team.creative.creativecore.common.gui.controls.parent.GuiScrollX;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.flow.GuiFlow;

public class GuiCondition extends GuiPanel {
    public GuiCondition(String name) {
        super(name, GuiFlow.STACK_Y, Align.STRETCH, VAlign.TOP);
        GuiParent top = new GuiParent();
        top.add(new GuiLabel("").setTitle(Component.literal("Test")));
        this.add(top);
        GuiScrollX conditions = new GuiScrollX();
        conditions.setFlow(GuiFlow.STACK_Y);
        conditions.add(new GuiLabel("").setTitle(Component.literal("Test")));
        conditions.add(new GuiLabel("").setTitle(Component.literal("Test")));
        conditions.add(new GuiLabel("").setTitle(Component.literal("Test")));
        conditions.add(new GuiLabel("").setTitle(Component.literal("Test")));
        conditions.add(new GuiLabel("").setTitle(Component.literal("Test")));
        this.add(conditions);
        
    }
}
