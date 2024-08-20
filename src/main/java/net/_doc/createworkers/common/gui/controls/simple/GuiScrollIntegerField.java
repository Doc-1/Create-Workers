package net._doc.createworkers.common.gui.controls.simple;

import org.joml.Math;

public class GuiScrollIntegerField extends GuiScrollNumberField<Integer> {
    
    public GuiScrollIntegerField(String name, String text) {
        super(name, text);
        this.setNumbersOnly();
    }
    
    @Override
    public GuiScrollIntegerField setMaxDecimalOnly(Integer max) {
        this.min = Integer.MIN_VALUE;
        this.max = max;
        this.setValidator((x) -> {
            if (x.isEmpty())
                return true;
            try {
                return Integer.parseInt(x) <= max;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return this;
    }
    
    @Override
    public GuiScrollIntegerField setMinDecimalOnly(Integer min) {
        this.min = min;
        this.max = Integer.MAX_VALUE;
        this.setValidator((x) -> {
            if (x.isEmpty())
                return true;
            try {
                return Integer.parseInt(x) >= min;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return this;
    }
    
    @Override
    public GuiScrollIntegerField setMinMaxDciaml(Integer min, Integer max) {
        this.min = min;
        this.max = max;
        this.setValidator((x) -> {
            if (x.isEmpty())
                return true;
            try {
                return Integer.parseInt(x) <= max && Integer.parseInt(x) >= min;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return this;
        
    }
    
    @Override
    public void scrollValue(int delta, boolean isShift) {
        int value = delta > 0 ? 1 : -1;
        value *= isShift ? 5 : 1;
        value += Integer.parseInt(this.getText());
        value = Math.clamp(min, max, value);
        this.setText(String.valueOf(value));
    }
    
}
