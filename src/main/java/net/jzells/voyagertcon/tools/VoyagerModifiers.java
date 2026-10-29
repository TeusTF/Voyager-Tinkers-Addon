package net.jzells.voyagertcon.tools;

import net.jzells.voyagertcon.VoyagerTCon;
import net.jzells.voyagertcon.tools.data.VoyagerModifierProvider;
import net.jzells.voyagertcon.tools.modules.BoltModule;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;

public class VoyagerModifiers {
    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(VoyagerTCon.MOD);

    public VoyagerModifiers(IEventBus event) {
        MODIFIERS.register(event);
    }

    @SubscribeEvent
    void registryEvent(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            ModifierModule.LOADER.register(VoyagerTCon.ofResourceLocation("bolt_shooter"), BoltModule.LOADER);
        }
    }

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput pack = generator.getPackOutput();
        boolean server = event.includeServer();
        generator.addProvider(server, new VoyagerModifierProvider(pack));
    }
}
