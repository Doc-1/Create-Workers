package net._doc.createworkers.content.blocks.keypunch.gui;

import net._doc.createworkers.common.gui.controls.parent.GuiScrollXCustom;
import net._doc.createworkers.common.gui.controls.simple.GuiScrollIntegerField;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.parent.GuiPanel;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.controls.simple.GuiStateButton;
import team.creative.creativecore.common.gui.flow.GuiFlow;

public class GuiCondition extends GuiPanel {
    public GuiCondition(String name, BlockPos pos) {
        super(name, GuiFlow.STACK_Y, Align.STRETCH, VAlign.TOP);
        
        GuiParent top = new GuiParent("top", GuiFlow.STACK_X, Align.LEFT, VAlign.CENTER);
        top.add(new GuiLabel("").setTitle(Component.literal("Next Stop:")));
        top.add(new GuiScrollIntegerField("x", String.valueOf(pos.getX())).setEnabled(false));
        top.add(new GuiScrollIntegerField("y", String.valueOf(pos.getY())).setEnabled(false));
        top.add(new GuiScrollIntegerField("z", String.valueOf(pos.getZ())).setEnabled(false));
        top.add(new GuiStateButton("lock", 0, "X", "O"));
        top.registerEventClick((event) -> {
            if (event.control.is("lock") && event.control instanceof GuiStateButton button) {
                GuiParent parent = this.get("top", GuiParent.class);
                boolean flag = button.getState() == 1;
                parent.get("x").setEnabled(flag);
                parent.get("y").setEnabled(flag);
                parent.get("z").setEnabled(flag);
            }
        });
        this.add(top);
        
        GuiScrollXCustom conditions = new GuiScrollXCustom();
        conditions.setFlow(GuiFlow.STACK_Y);
        GuiParent p = new GuiParent();
        p.add(new GuiLabel("").setTitle(Component.literal("Testasdfasdf")));
        conditions.add(p);
        
        this.add(conditions);
        
    }
}
