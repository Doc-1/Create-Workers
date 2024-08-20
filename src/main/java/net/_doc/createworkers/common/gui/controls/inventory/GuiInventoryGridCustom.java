package net._doc.createworkers.common.gui.controls.inventory;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import team.creative.creativecore.common.gui.GuiChildControl;
import team.creative.creativecore.common.gui.GuiControl;
import team.creative.creativecore.common.gui.GuiParent;
import team.creative.creativecore.common.gui.controls.inventory.GuiSlot;
import team.creative.creativecore.common.gui.controls.inventory.GuiSlotBase;
import team.creative.creativecore.common.gui.controls.inventory.IGuiInventory;
import team.creative.creativecore.common.gui.style.ControlFormatting;

public class GuiInventoryGridCustom extends GuiParent implements IGuiInventory {
    
    public final Container container;
    protected boolean hasFixedSize;
    protected int fixedSize;
    protected boolean reverse = false;
    private int cols;
    private int rows;
    
    private int cachedCols;
    private int cachedRows;
    private boolean allChanged = false;
    private final BitSet changed = new BitSet();
    private List<Consumer<GuiSlot>> listeners;
    private final List<GuiSlot> slots = new ArrayList<>();
    
    public GuiInventoryGridCustom(String name, Container container, int cols, int rows) {
        this(name, container, cols, rows, (c, i) -> new Slot(c, i, 0, 0));
    }
    
    public GuiInventoryGridCustom(String name, Container container, int cols, int rows, BiFunction<Container, Integer, Slot> slotFactory) {
        super(name);
        this.hasFixedSize = true;
        this.cols = cols;
        this.rows = rows;
        this.container = container;
        this.fixedSize = Math.min(container.getContainerSize(), cols * rows);
        createInventoryGrid(slotFactory);
    }
    
    protected void createInventoryGrid(BiFunction<Container, Integer, Slot> slotFactory) {
        for (int i = 9; i < fixedSize; i++)
            addSlot(new GuiSlot(slotFactory.apply(container, i)));
        for (int i = 0; i < 9; i++)
            addSlot(new GuiSlot(slotFactory.apply(container, i)));
    }
    
    public GuiInventoryGridCustom disableSlot(int index) {
        getSlot(index).enabled = false;
        return this;
    }
    
    public GuiInventoryGridCustom addListener(Consumer<GuiSlot> slot) {
        if (listeners == null)
            listeners = new ArrayList<>();
        listeners.add(slot);
        return this;
    }
    
    protected GuiChildControl addSlot(GuiSlot slot) {
        while (slot.slot.getContainerSlot() >= slots.size())
            slots.add(null);
        slots.set(slot.slot.getContainerSlot(), slot);
        return super.addControl(slot);
    }
    
    @Override
    @Deprecated
    public GuiChildControl addControl(GuiControl control) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected int minWidth(int availableWidth) {
        return cols * GuiSlotBase.SLOT_SIZE;
    }
    
    @Override
    protected int minHeight(int width, int availableHeight) {
        return rows * GuiSlotBase.SLOT_SIZE + 4;
    }
    
    @Override
    public void flowX(int width, int preferred) {
        cachedCols = width / GuiSlotBase.SLOT_SIZE;
        if (hasFixedSize)
            cachedCols = Math.min(cachedCols, this.cols);
        int i = 0;
        for (GuiChildControl control : controls) {
            control.setX((i % cachedCols) * GuiSlotBase.SLOT_SIZE);
            control.setWidth(GuiSlotBase.SLOT_SIZE, width);
            control.flowX();
            i++;
        }
    }
    
    @Override
    public void flowY(int witdh, int height, int preferred) {
        cachedRows = height / GuiSlotBase.SLOT_SIZE;
        if (hasFixedSize)
            cachedRows = Math.min(cachedRows, this.rows);
        int i = reverse ? controls.size() - 1 : 0;
        for (GuiChildControl control : controls) {
            int row = i / cachedCols;
            control.setY(row == 3 ? row * GuiSlotBase.SLOT_SIZE + 4 : row * GuiSlotBase.SLOT_SIZE);
            control.setHeight(GuiSlotBase.SLOT_SIZE, height);
            control.flowY();
            control.control.visible = row <= cachedRows;
            if (reverse)
                i--;
            else
                i++;
        }
    }
    
    @Override
    protected int preferredWidth(int availableWidth) {
        return cols * GuiSlotBase.SLOT_SIZE;
    }
    
    @Override
    protected int preferredHeight(int width, int availableHeight) {
        return this.minHeight(width, availableHeight);
    }
    
    @Override
    public ControlFormatting getControlFormatting() {
        return ControlFormatting.TRANSPARENT;
    }
    
    @Override
    public GuiSlot getSlot(int index) {
        return slots.get(index);
    }
    
    @Override
    public int inventorySize() {
        if (hasFixedSize)
            return fixedSize;
        return container.getContainerSize();
    }
    
    @Override
    public String name() {
        return name;
    }
    
    @Override
    public void tick() {
        if (allChanged) {
            container.setChanged();
            syncAll();
            allChanged = false;
            changed.clear();
        } else if (!changed.isEmpty()) {
            container.setChanged();
            sync(changed);
            changed.clear();
        }
        super.tick();
    }
    
    @Override
    public void setChanged() {
        allChanged = true;
        if (listeners != null)
            for (Consumer<GuiSlot> listener : listeners)
                listener.accept(null);
    }
    
    @Override
    public void setChanged(int slotIndex) {
        if (allChanged)
            return;
        changed.set(slotIndex);
        GuiSlot slot = getSlot(slotIndex);
        if (listeners != null)
            for (Consumer<GuiSlot> listener : listeners)
                listener.accept(slot);
    }
    
}
