package net.jzells.voyagertcon.tools.data;

import net.jzells.voyagertcon.tools.VoyagerTools;
import net.minecraft.data.PackOutput;

import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class VoyagerStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {

    public VoyagerStationSlotLayoutProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(VoyagerTools.heavy_crossbow)
                .sortIndex(SORT_RANGED)
                .addInputItem(TinkerToolParts.bowLimb, 20, 50)
                .addInputItem(TinkerToolParts.bowLimb, 40, 30)
                .addInputItem(TinkerToolParts.bowGrip, 40, 50)
                .addInputItem(TinkerToolParts.bowstring, 20, 30)
                .build();

        defineModifiable(VoyagerTools.bolt)
                .sortIndex(SORT_AMMO)
                .addInputItem(VoyagerTools.bolt_core, 40, 30)
                .addInputItem(TinkerToolParts.toughHandle, 40, 50)
                .build();
    }

    @Override
    public String getName() {
        return "Voyager TCON Patterns";
    }
}
