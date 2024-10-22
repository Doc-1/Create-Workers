package net._doc.createworkers.registeries;

import net._doc.createworkers.gui.blocks.keypunch.LayerConditionEditor;
import team.creative.creativecore.common.gui.creator.GuiCreator;
import team.creative.creativecore.common.gui.creator.GuiCreator.GuiCreatorBlock;
import team.creative.creativecore.common.gui.sync.GuiSyncGlobalLayer;
import team.creative.creativecore.common.gui.sync.GuiSyncHolder;

public class CWGui {
    public static final GuiCreatorBlock KEYPUNCH = GuiCreator.register("keypunch", new GuiCreatorBlock());
    public static final GuiSyncGlobalLayer<LayerConditionEditor> CONDITION = GuiSyncHolder.GLOBAL.layer("condition", x -> new LayerConditionEditor());
    
    public static void register() {}
}
