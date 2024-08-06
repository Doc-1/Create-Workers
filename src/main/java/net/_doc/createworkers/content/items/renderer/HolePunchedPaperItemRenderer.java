package net._doc.createworkers.content.items.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class HolePunchedPaperItemRenderer extends CustomRenderedItemModelRenderer {
    
    @Override
    protected void render(ItemStack stack, CustomRenderedItemModel model, PartialItemModelRenderer renderer, ItemDisplayContext transformType, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        LocalPlayer player = Minecraft.getInstance().player;
        
        boolean leftHand = transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND;
        boolean firstPerson = leftHand || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
        
        ms.pushPose();
        
        if (firstPerson) {
            int itemInUseCount = player.getUseItemRemainingTicks();
            if (itemInUseCount > 0) {
                ms.translate(.2f, 0.3, 0.7f);
                ms.mulPose(Axis.ZP.rotationDegrees(50));
                ms.mulPose(Axis.XP.rotationDegrees(-18));
                ms.mulPose(Axis.YP.rotationDegrees(-7));
            }
        }
        
        itemRenderer.render(stack, ItemDisplayContext.NONE, false, ms, buffer, light, overlay, model.getOriginalModel());
        
        ms.popPose();
        
    }
    
}
