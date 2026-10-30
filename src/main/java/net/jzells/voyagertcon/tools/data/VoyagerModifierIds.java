package net.jzells.voyagertcon.tools.data;

import net.jzells.voyagertcon.VoyagerTCon;

import slimeknights.tconstruct.library.modifiers.ModifierId;

public class VoyagerModifierIds {

    public static final ModifierId bolt_shooter = id("bolt_shooter");

    private static ModifierId id(String name) {
        return new ModifierId(VoyagerTCon.MOD, name);
    }
}
