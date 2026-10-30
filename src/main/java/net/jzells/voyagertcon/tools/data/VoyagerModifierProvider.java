package net.jzells.voyagertcon.tools.data;

import net.jzells.voyagertcon.tools.modules.BoltModule;
import net.minecraft.data.PackOutput;

import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;

public class VoyagerModifierProvider extends AbstractModifierProvider {

    public VoyagerModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        buildModifier(VoyagerModifierIds.bolt_shooter)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(BoltModule.INSTANCE);
    }

    @Override
    public String getName() {
        return "Voyager TCon Modifiers";
    }
}
