package net._doc.createworkers.gui.controls.parent;

import net.minecraft.client.gui.screens.Screen;
import team.creative.creativecore.common.gui.controls.parent.GuiScrollX;
import team.creative.creativecore.common.util.math.geo.Rect;

public class GuiScrollXCustom extends GuiScrollX {
    @Override
    protected int preferredHeight(int width, int availableHeight) {
        return super.preferredHeight(width, availableHeight) + scrollbarHeight;
    }
    
    @Override
    public boolean mouseScrolled(Rect rect, double x, double y, double scrolled) {
        if (Screen.hasShiftDown() && !Screen.hasControlDown()) {
            scroll(scrolled);
            return true;
        } else if (Screen.hasControlDown())
            return super.mouseScrolled(rect, x, y, scrolled);
        
        return false;
    }
}
