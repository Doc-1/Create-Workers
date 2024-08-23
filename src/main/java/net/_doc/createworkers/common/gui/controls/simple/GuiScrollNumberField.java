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

public abstract class GuiScrollNumberField<T extends Number> extends GuiTextfield {
    
    public T max;
    public T min;
    
    public GuiScrollNumberField(String name, String text) {
        super(name, text);
        this.setTooltip(tooltip());
        
    }
    
    public abstract GuiScrollNumberField<T> setMaxDecimalOnly(T max);
    
    public abstract GuiScrollNumberField<T> setMinDecimalOnly(T min);
    
    public abstract GuiScrollNumberField<T> setMinMaxDciaml(T min, T max);
    
    public abstract void scrollValue(int delta, boolean isShift);
    
    @Override
    public boolean mouseScrolled(Rect rect, double x, double y, double delta) {
        String v = this.getText();
        scrollValue((int) delta, Screen.hasShiftDown());
        if (!v.equals(this.getText()))
            playSound(SimpleSoundInstance.forUI(AllSoundEvents.SCROLL_VALUE.getMainEvent(), 1.5f + 0.1f * Float.parseFloat(getText()) / ((Number) this.max).floatValue()));
        return true;
    }
    
    public List<Component> tooltip() {
        List<Component> customTooltip = new ArrayList<Component>();
        customTooltip.add(Component.literal(this.name).withStyle(s -> s.withColor(AbstractSimiWidget.HEADER_RGB)));
        customTooltip.add(Component.literal("Scroll or Click to Modify").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        customTooltip.add(Component.literal("Shift to Scroll Faster").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        return customTooltip;
    }
    
}
