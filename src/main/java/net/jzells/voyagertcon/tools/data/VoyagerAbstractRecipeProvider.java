package net.jzells.voyagertcon.tools.data;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.jzells.voyagertcon.VoyagerTCon;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class VoyagerAbstractRecipeProvider implements DataProvider, IConditionBuilder, IRecipeHelper {
    protected final PackOutput.PathProvider recipePathProvider;
    protected final PackOutput.PathProvider advancementPathProvider;

    public VoyagerAbstractRecipeProvider(PackOutput output) {
        this.recipePathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK,"recipes");
        this.advancementPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "advancements");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        Set<ResourceLocation> set = Sets.newHashSet();
        List<CompletableFuture<?>> list = new ArrayList<>();
        this.buildRecipes((recipe) -> {
            if (!set.add(recipe.getId())) {
                throw new IllegalStateException("Duplicate recipe " + recipe.getId());
            } else {
                list.add(DataProvider.saveStable(output, recipe.serializeRecipe(),
                        this.recipePathProvider.json(recipe.getId())));
                JsonObject jsonobject = recipe.serializeAdvancement();
                if (jsonobject != null) {
                    var saveAdvancementFuture = saveAdvancement(output, recipe, jsonobject);
                    if (saveAdvancementFuture != null)
                        list.add(saveAdvancementFuture);
                }

            }
        });
        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }

    protected abstract void buildRecipes(Consumer<FinishedRecipe> writer);


    /**
     * Called every time a recipe is saved to also save the advancement JSON if it exists.
     *
     * @return A completable future that saves the advancement to disk, or null to cancel saving the advancement.
     */
    @Nullable
    protected CompletableFuture<?> saveAdvancement(CachedOutput output, FinishedRecipe finishedRecipe,
                                                   JsonObject advancementJson) {
        return DataProvider.saveStable(output, advancementJson,
                this.advancementPathProvider.json(finishedRecipe.getAdvancementId()));
    }

    @Override
    public String getModId() {
        return VoyagerTCon.MOD;
    }
}
