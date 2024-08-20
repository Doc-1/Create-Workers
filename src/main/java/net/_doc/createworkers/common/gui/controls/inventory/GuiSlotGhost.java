package net._doc.createworkers.common.gui.controls.inventory;

import net.minecraft.world.item.ItemStack;
import team.creative.creativecore.common.gui.controls.inventory.GuiSlotBase;
import team.creative.creativecore.common.gui.manager.GuiManagerItem;
import team.creative.creativecore.common.util.math.geo.Rect;

public class GuiSlotGhost extends GuiSlotBase {
    
    public ItemStack stack;
    
    public GuiSlotGhost(String name, ItemStack stack) {
        super(name);
        this.stack = stack;
    }
    
    public GuiSlotGhost(ItemStack stack) {
        super("");
        this.stack = stack;
    }
    
    public GuiManagerItem itemManager() {
        return getLayer().itemManager();
    }
    
    @Override
    public boolean mouseClicked(Rect rect, double x, double y, int button) {
        ItemStack hand = itemManager().getHand();
        if (button == 0) {
            if (hand.isEmpty())
                stack = ItemStack.EMPTY;
            else {
                hand = hand.copy();
                hand.setCount(1);
                stack = hand;
            }
        }
        return true;
    }
    
    @Override
    public ItemStack getStack() {
        return stack;
    }
    
    @Override
    protected ItemStack getStackToRender() {
        return stack;
    }
    
}
