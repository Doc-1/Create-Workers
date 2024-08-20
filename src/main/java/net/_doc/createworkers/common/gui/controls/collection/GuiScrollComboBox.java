package net._doc.createworkers.common.gui.controls.collection;

import java.util.ArrayList;
import java.util.List;

import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.foundation.gui.widget.AbstractSimiWidget;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import team.creative.creativecore.client.render.text.CompiledText;
import team.creative.creativecore.common.gui.controls.collection.GuiComboBox;
import team.creative.creativecore.common.gui.controls.collection.GuiComboBoxExtension;
import team.creative.creativecore.common.util.math.geo.Rect;
import team.creative.creativecore.common.util.text.ITextCollection;

public class GuiScrollComboBox extends GuiComboBox {
    public String title;
    
    public GuiScrollComboBox(String name, String title, ITextCollection builder) {
        super(name, builder);
        this.title = title;
        
        this.setTooltip(this.tooltip());
        
    }
    
    @Override
    public int preferredHeight(int width, int availableHeight) {
        int height = 10;
        for (CompiledText text : lines)
            height = Math.max(height, text.getTotalHeight());
        return height;
    }
    
    public List<Component> tooltip() {
        List<Component> customTooltip = new ArrayList<Component>();
        customTooltip.add(Component.literal(title).withStyle(s -> s.withColor(AbstractSimiWidget.HEADER_RGB)));
        int x = 0;
        for (CompiledText d : lines) {
            for (Component a : d.untrimmedContent())
                customTooltip.add(Component.literal((x == this.getIndex() ? "-> " : "> ") + a.getString()));
            x++;
        }
        customTooltip.add(Component.literal("Scroll or Click to Select").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        return customTooltip;
    }
    
    @Override
    public boolean select(int index) {
        boolean result = super.select(index);
        if (result)
            this.setTooltip(this.tooltip());
        return result;
    }
    
    @Override
    public boolean mouseScrolled(Rect rect, double x, double y, double delta) {
        int scroll = 0;
        if (delta > 0)
            scroll = -1;
        else
            scroll = 1;
        if (this.select(this.getIndex() + scroll))
            playSound(SimpleSoundInstance.forUI(AllSoundEvents.SCROLL_VALUE.getMainEvent(), 1.5f + 0.1f * this.getIndex() / this.lines.length));
        return super.mouseScrolled(rect, x, y, delta);
    }
    
    public boolean isExtensionOpened() {
        return !this.getLayer().has(comboBoxExtensionName());
    }
    
    protected GuiComboBoxExtension createBox() {
        return new GuiComboBoxExtension(comboBoxExtensionName(), this);
    }
    
    private String comboBoxExtensionName() {
        return name + "extension";
    }
}
