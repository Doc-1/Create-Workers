package net._doc.createworkers.common.gui.controls.inventory;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import team.creative.creativecore.client.render.GuiRenderHelper;
import team.creative.creativecore.common.gui.GuiChildControl;
import team.creative.creativecore.common.gui.controls.inventory.GuiSlotBase;
import team.creative.creativecore.common.util.math.geo.Rect;

public class GuiSlotIcon extends GuiSlotBase {
    
    public ItemStack stack;
    
    public GuiSlotIcon(String name, ItemStack stack) {
        super(name);
        this.stack = stack;
    }
    
    public GuiSlotIcon(ItemStack stack) {
        super("");
        this.stack = stack;
    }
    
    @Override
    public boolean mouseClicked(Rect rect, double x, double y, int button) {
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
    
    @Override
    public boolean isInteractable() {
        return false;
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    protected void renderContent(GuiGraphics graphics, GuiChildControl control, Rect rect, int mouseX, int mouseY) {
        PoseStack pose = graphics.pose();
        pose.translate(1, 1, 10);
        ItemStack stack = getStackToRender();
        GuiRenderHelper.drawItemStack(pose, stack, 1F);
        GuiRenderHelper.drawItemStackDecorations(pose, stack);
        pose.translate(-1, -1, 10);
        
    }
    
}
