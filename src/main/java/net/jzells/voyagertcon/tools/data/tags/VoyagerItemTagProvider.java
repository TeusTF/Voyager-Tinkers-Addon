package net.jzells.voyagertcon.tools.data.tags;

import net.jzells.voyagertcon.VoyagerTCon;
import net.jzells.voyagertcon.tools.VoyagerTools;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static slimeknights.tconstruct.common.TinkerTags.Items.*;

public class VoyagerItemTagProvider extends ItemTagsProvider {

    public VoyagerItemTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider,
                                  CompletableFuture<TagLookup<Block>> blockTags,
                                  ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, VoyagerTCon.MOD, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        addToolTags(VoyagerTools.heavy_crossbow, DURABILITY, MULTIPART_TOOL, CROSSBOWS, INTERACTABLE_LEFT, BROAD_RANGED,
                BONUS_SLOTS, Tags.Items.TOOLS_CROSSBOWS);
        addToolTags(VoyagerTools.bolt, DURABILITY, MULTIPART_TOOL, AMMO, INTERACTABLE_LEFT, BROAD_RANGED, BONUS_SLOTS,
                ItemTags.ARROWS);
    }

    @SafeVarargs
    private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
        Item item = tool.asItem();
        for (TagKey<Item> tag : tags) {
            this.tag(tag).add(item);
        }
    }
}
