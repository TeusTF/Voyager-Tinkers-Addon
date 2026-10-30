package net.jzells.voyagertcon.tools.data;

import net.jzells.voyagertcon.VoyagerTCon;
import net.jzells.voyagertcon.tools.VoyagerToolDefinitions;
import net.minecraft.data.PackOutput;

import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.materials.RandomMaterial;
import slimeknights.tconstruct.library.tools.definition.module.build.MultiplyStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.SetStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolTraitsModule;
import slimeknights.tconstruct.library.tools.definition.module.display.FixedMaterialToolName;
import slimeknights.tconstruct.library.tools.definition.module.material.DefaultMaterialsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.PartStatsModule;
import slimeknights.tconstruct.library.tools.nbt.MultiplierNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static net.jzells.voyagertcon.tools.VoyagerTools.*;
import static slimeknights.tconstruct.tools.TinkerToolParts.*;

public class VoyagerToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {

    public VoyagerToolDefinitionDataProvider(PackOutput packOutput) {
        super(packOutput, VoyagerTCon.MOD);
    }

    @Override
    protected void addToolDefinitions() {
        RandomMaterial tier1material = RandomMaterial.random().tier(1).build();
        DefaultMaterialsModule defaultFourParts = DefaultMaterialsModule.builder()
                .material(tier1material, tier1material, tier1material, tier1material).build();
        DefaultMaterialsModule defaultTwoParts = DefaultMaterialsModule.builder().material(tier1material, tier1material)
                .build();

        define(VoyagerToolDefinitions.HEAVY_CROSSBOW)
                // parts
                .module(PartStatsModule.parts()
                        .part(bowLimb)
                        .part(bowLimb)
                        .part(bowGrip)
                        .part(bowstring).build())
                .module(defaultFourParts)
                // stats
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, 0f)
                        .set(ToolStats.ATTACK_SPEED, 1.0f).build()))
                .module(new MultiplyStatsModule(MultiplierNBT.builder()
                        .set(ToolStats.DURABILITY, 2f).build()))
                .module(ToolTraitsModule.builder().trait(VoyagerModifierIds.bolt_shooter).build())
                .largeToolStartingSlots()
                // faster tool name logic
                .module(FixedMaterialToolName.FIRST);

        define(VoyagerToolDefinitions.BOLT)
                .module(PartStatsModule.parts()
                        .part(bolt_core)
                        .part(toughHandle)
                        .build())
                .module(defaultTwoParts)
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, 0f)
                        .set(ToolStats.VELOCITY, 1f)
                        .build()))
                .module(new MultiplyStatsModule(MultiplierNBT.builder()
                        .set(ToolStats.DURABILITY, 2f)
                        .build()))
                .smallToolStartingSlots()
                .module(FixedMaterialToolName.FIRST);
    }

    @Override
    public String getName() {
        return "Voyaget TCon Tool Definition Data Generator";
    }
}
