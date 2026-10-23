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
                .addInputItem(TinkerToolParts.bowLimb,44,29)
                .addInputItem(TinkerToolParts.bowLimb,42,31)
                .addInputItem(TinkerToolParts.bowstring, 40,35)
                .addInputItem(TinkerToolParts.bowGrip,35,20)
                .build();

        defineModifiable(VoyagerTools.bolt)
                .sortIndex(SORT_AMMO)
                .addInputItem(TinkerToolParts.arrowHead, 40,30)
                .addInputItem(TinkerToolParts.toughHandle,40, 25)
                .build();
    }

    @Override
    public String getName() {
        return "Voyager TCON Patterns";
    }
}
