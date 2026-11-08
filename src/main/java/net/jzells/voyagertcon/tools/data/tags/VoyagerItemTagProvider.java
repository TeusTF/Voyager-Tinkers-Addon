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
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

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
        addCastTag();
        this.tag(TinkerTags.Items.TOOL_PARTS).add(VoyagerTools.bolt_core.get());
    }

    @SafeVarargs
    private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
        Item item = tool.asItem();
        for (TagKey<Item> tag : tags) {
            this.tag(tag).add(item);
        }
    }

    private void addCastTag() {
        IntrinsicTagAppender<Item> goldCasts = this.tag(TinkerTags.Items.GOLD_CASTS);
        IntrinsicTagAppender<Item> sandCasts = this.tag(TinkerTags.Items.SAND_CASTS);
        IntrinsicTagAppender<Item> redSandCasts = this.tag(TinkerTags.Items.RED_SAND_CASTS);
        IntrinsicTagAppender<Item> singleUseCasts = this.tag(TinkerTags.Items.SINGLE_USE_CASTS);
        IntrinsicTagAppender<Item> multiUseCasts = this.tag(TinkerTags.Items.MULTI_USE_CASTS);
        Consumer<CastItemObject> addCast = cast -> {
            // tag based on material
            goldCasts.add(cast.get());
            sandCasts.add(cast.getSand());
            redSandCasts.add(cast.getRedSand());
            // tag based on usage
            singleUseCasts.addTag(cast.getSingleUseTag());
            this.tag(cast.getSingleUseTag()).add(cast.getSand(), cast.getRedSand());
            multiUseCasts.addTag(cast.getMultiUseTag());
            this.tag(cast.getMultiUseTag()).add(cast.get());
        };

        addCast.accept(VoyagerTools.bolt_core_cast);
    }
}
