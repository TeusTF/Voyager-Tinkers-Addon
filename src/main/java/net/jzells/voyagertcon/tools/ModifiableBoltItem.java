package net.jzells.voyagertcon.tools;

import net.minecraft.world.item.ItemStack;

import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableArrowItem;

public class ModifiableBoltItem extends ModifiableArrowItem {

    public ModifiableBoltItem(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return super.isBarVisible(stack);
    }
}
