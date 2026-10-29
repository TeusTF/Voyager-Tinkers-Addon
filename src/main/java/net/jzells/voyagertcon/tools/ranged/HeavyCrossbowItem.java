package net.jzells.voyagertcon.tools.ranged;

import net.jzells.voyagertcon.tools.ModifiableBoltItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableCrossbowItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.function.Predicate;

public class HeavyCrossbowItem extends ModifiableCrossbowItem {

    public static final Predicate<ItemStack> BOLT_ONLY = (p) -> p.getItem() instanceof ModifiableBoltItem;

    public HeavyCrossbowItem(Properties properties, ToolDefinition toolDefinition,
                             Predicate<ItemStack> supportedHeldProjectiles, boolean storeDrawingItem) {
        super(properties, toolDefinition, supportedHeldProjectiles, storeDrawingItem);
    }

    public HeavyCrossbowItem(Properties properties, ToolDefinition toolDefinition) {
        this(properties,toolDefinition,BOLT_ONLY,false);
    }

}
