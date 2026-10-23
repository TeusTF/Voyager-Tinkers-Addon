package net.jzells.voyagertcon.tools.data;

import net.jzells.voyagertcon.tools.VoyagerTools;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;

public class VoyagerToolsRecipeProvider extends VoyagerAbstractRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

    public VoyagerToolsRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        this.addToolBuildingRecipes(writer);
    }

    private void addToolBuildingRecipes(Consumer<FinishedRecipe> writer) {
        String folder = "tools/building/";
        toolBuilding(writer, VoyagerTools.heavy_crossbow, folder);
        toolBuilding(writer, VoyagerTools.bolt,folder);

    }

    @Override
    public String getName() {
        return "Voyager TCon Tool Recipes";
    }
}
