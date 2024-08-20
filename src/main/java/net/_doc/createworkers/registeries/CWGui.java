package net._doc.createworkers.registeries;

import net._doc.createworkers.content.blocks.keypunch.gui.GuiConditionEditor;
import team.creative.creativecore.common.gui.creator.GuiCreator;
import team.creative.creativecore.common.gui.creator.GuiCreator.GuiCreatorBlock;
import team.creative.creativecore.common.gui.sync.GuiSyncGlobalLayer;
import team.creative.creativecore.common.gui.sync.GuiSyncHolder;

public class CWGui {
    public static final GuiCreatorBlock KEYPUNCH = GuiCreator.register("keypunch", new GuiCreatorBlock());
    public static final GuiSyncGlobalLayer<GuiConditionEditor> CONDITION = GuiSyncHolder.GLOBAL.layer("condition", x -> new GuiConditionEditor());
    
    public static void register() {}
}
