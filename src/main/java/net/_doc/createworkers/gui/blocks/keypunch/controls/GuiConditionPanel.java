package net._doc.createworkers.gui.blocks.keypunch.controls;

import net._doc.ccgexpansion.common.gui.controls.simple.GuiScrollIntegerField;
import net._doc.createworkers.gui.controls.parent.GuiScrollXCustom;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import team.creative.creativecore.common.gui.Align;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.VAlign;
import team.creative.creativecore.common.gui.controls.parent.GuiPanel;
import team.creative.creativecore.common.gui.controls.simple.GuiButton;
import team.creative.creativecore.common.gui.controls.simple.GuiLabel;
import team.creative.creativecore.common.gui.controls.simple.GuiStateButton;
import team.creative.creativecore.common.gui.flow.GuiFlow;

public class GuiConditionPanel extends GuiPanel {
    public GuiConditionPanel(String name, BlockPos pos) {
        super(name, GuiFlow.STACK_Y, Align.STRETCH, VAlign.TOP);
        
        GuiParent top = new GuiParent("top", GuiFlow.STACK_X, Align.LEFT, VAlign.CENTER);
        top.add(new GuiLabel("").setTitle(Component.literal("Next Stop:")));
        top.add(new GuiScrollIntegerField("x", String.valueOf(pos.getX()), true).setEnabled(false));
        top.add(new GuiScrollIntegerField("y", String.valueOf(pos.getY()), true).setEnabled(false));
        top.add(new GuiScrollIntegerField("z", String.valueOf(pos.getZ()), true).setEnabled(false));
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
        
        GuiScrollXCustom conditions = (GuiScrollXCustom) new GuiScrollXCustom().setFlow(GuiFlow.STACK_X);
        
        conditions.add(new GuiButton("add", x -> {
            GuiParent stackY = new GuiParent(GuiFlow.STACK_Y).setAlign(Align.CENTER);
            stackY.add(new GuiButton("add1", y -> {
                stackY.insertControlBefore(stackY.get("add1"), new GuiLabel("").setTitle(Component.literal("And Condition")));
                this.reflow();
            }).setTitle(Component.literal("+")).setAlign(Align.CENTER).setVAlign(VAlign.CENTER).setDim(8, 8));
            
            conditions.insertControlBefore(conditions.get("add"), stackY);
            //            p.add(new GuiButton("", t -> {
            //                CWGui.CONDITION.open(p.getIntegratedParent(), new CompoundTag());
            //            }).setTitle(Component.literal("Condition")));
            
            this.reflow();
        }).setTitle(Component.literal("+")).setAlign(Align.CENTER).setVAlign(VAlign.CENTER).setDim(8, 8));
        this.add(conditions);
        
    }
}
