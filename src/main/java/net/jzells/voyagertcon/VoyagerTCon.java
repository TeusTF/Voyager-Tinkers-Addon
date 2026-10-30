package net.jzells.voyagertcon;

import net.jzells.voyagertcon.tools.VoyagerModifiers;
import net.jzells.voyagertcon.tools.VoyagerTools;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.library.client.model.TinkerItemProperties;

@Mod(VoyagerTCon.MOD)
public class VoyagerTCon {

    public static final String MOD = "voyagertcon";
    public static final Logger LOGGER = LogManager.getLogger();

    public VoyagerTCon(FMLJavaModLoadingContext context) {
        FMLModContainer container = context.getContainer();
        IEventBus event = context.getModEventBus();

        event.addListener(this::commonSetup);
        event.addListener(this::clientSetup);

        event.register(new VoyagerTools());
        event.register(new VoyagerModifiers(event));

        VoyagerTools.register(event);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {}

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TinkerItemProperties.registerCrossbowProperties(VoyagerTools.heavy_crossbow);
        });
    }

    public static ResourceLocation ofResourceLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD, name);
    }
}
