package net._doc.createworkers.common.gui.controls.simple;

public class GuiScrollFloatField extends GuiScrollNumberField<Float> {
    
    public GuiScrollFloatField(String name, String text) {
        super(name, text);
        this.setFloatOnly();
        this.setMinMaxDciaml(Float.MIN_VALUE, Float.MAX_VALUE);
    }
    
    @Override
    public GuiScrollFloatField setMaxDecimalOnly(Float max) {
        this.setValidator((x) -> {
            if (x.isEmpty())
                return true;
            try {
                return Float.parseFloat(x) < max;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return this;
    }
    
    @Override
    public GuiScrollFloatField setMinDecimalOnly(Float min) {
        this.setValidator((x) -> {
            if (x.isEmpty())
                return true;
            try {
                return Float.parseFloat(x) > min;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return this;
    }
    
    @Override
    public GuiScrollFloatField setMinMaxDciaml(Float min, Float max) {
        this.min = min;
        this.max = max;
        this.setValidator((x) -> {
            if (x.isEmpty())
                return true;
            try {
                return Float.parseFloat(x) < max && Float.parseFloat(x) > min;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return this;
        
    }
    
    @Override
    public void scrollValue(int delta, boolean isShift) {
        float value = delta > 0 ? 0.1F : -0.1F;
        value = isShift ? 0.5F : value;
        value += Float.parseFloat(this.getText());
        this.setText(String.valueOf(value));
    }
}