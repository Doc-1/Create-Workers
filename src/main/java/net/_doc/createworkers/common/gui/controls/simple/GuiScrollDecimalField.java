package net._doc.createworkers.common.gui.controls.simple;

import java.util.ArrayList;
import java.util.List;

import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.foundation.gui.widget.AbstractSimiWidget;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import team.creative.creativecore.common.gui.controls.simple.GuiTextfield;
import team.creative.creativecore.common.util.math.geo.Rect;

public abstract class GuiScrollDecimalField<T> extends GuiTextfield {
    
    public T max;
    public T min;
    
    public GuiScrollDecimalField(String name, String text) {
        super(name, text);
        this.setTooltip(tooltip());
    }
    
    public abstract GuiScrollDecimalField<T> setMaxDecimalOnly(T max);
    
    public abstract GuiScrollDecimalField<T> setMinDecimalOnly(T min);
    
    public abstract GuiScrollDecimalField<T> setMinMaxDciaml(T min, T max);
    
    public abstract void scrollValue(int delta, boolean isShift);
    
    @Override
    public boolean mouseScrolled(Rect rect, double x, double y, double delta) {
        scrollValue((int) delta, Screen.hasShiftDown());
        playSound(SimpleSoundInstance.forUI(AllSoundEvents.SCROLL_VALUE.getMainEvent(), 1.5f));
        return super.mouseScrolled(rect, x, y, delta);
    }
    
    public List<Component> tooltip() {
        List<Component> customTooltip = new ArrayList<Component>();
        customTooltip.add(Component.literal(this.name).withStyle(s -> s.withColor(AbstractSimiWidget.HEADER_RGB)));
        customTooltip.add(Component.literal("Scroll or Click to Modify").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        customTooltip.add(Component.literal("Shift to Scroll Faster").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        return customTooltip;
    }
    
}
