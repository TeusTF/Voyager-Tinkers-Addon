package net.jzells.voyagertcon.tools;

import net.jzells.voyagertcon.VoyagerTCon;
import net.jzells.voyagertcon.tools.data.VoyagerStationSlotLayoutProvider;
import net.jzells.voyagertcon.tools.data.VoyagerToolDefinitionDataProvider;
import net.jzells.voyagertcon.tools.data.VoyagerToolModelProvider;
import net.jzells.voyagertcon.tools.data.VoyagerToolsRecipeProvider;
import net.jzells.voyagertcon.tools.ranged.HeavyCrossbowItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;


public class VoyagerTools {
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(VoyagerTCon.MOD);
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(VoyagerTCon.MOD);

    protected static final Item.Properties ITEM_PROPS = new Item.Properties();
    protected static final Item.Properties UNSTACKABLE_PROPS = new Item.Properties().stacksTo(1);

    public static final ItemObject<HeavyCrossbowItem> heavy_crossbow = ITEMS.register("heavy_crossbow", () -> new HeavyCrossbowItem(UNSTACKABLE_PROPS, VoyagerToolDefinitions.HEAVY_CROSSBOW));
    public static final ItemObject<ModifiableBoltItem> bolt = ITEMS.register("bolt", () -> new ModifiableBoltItem(ITEM_PROPS, VoyagerToolDefinitions.BOLT));

    public static void register(IEventBus event) {
        ITEMS.register(event);
        MODIFIERS.register(event);
    }

    @SubscribeEvent
    public void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper file = event.getExistingFileHelper();
        boolean server = event.includeServer();
        boolean client = event.includeClient();
        generator.addProvider(server, new VoyagerStationSlotLayoutProvider(packOutput));
        generator.addProvider(server, new VoyagerToolsRecipeProvider(packOutput));
        generator.addProvider(server, new VoyagerToolDefinitionDataProvider(packOutput));
        generator.addProvider(client, new VoyagerToolModelProvider(packOutput,file));
    }
}
