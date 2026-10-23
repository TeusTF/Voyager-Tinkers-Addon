package net.jzells.voyagertcon.tools.ranged;

import net.minecraft.world.item.ItemStack;

import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableCrossbowItem;

import java.util.function.Predicate;

public class HeavyCrossbowItem extends ModifiableCrossbowItem {

    public HeavyCrossbowItem(Properties properties, ToolDefinition toolDefinition,
                             Predicate<ItemStack> supportedHeldProjectiles, boolean storeDrawingItem) {
        super(properties, toolDefinition, supportedHeldProjectiles, storeDrawingItem);
    }

    public HeavyCrossbowItem(Properties properties, ToolDefinition toolDefinition) {
        this(properties,toolDefinition,ARROW_ONLY,false);
    }


}
