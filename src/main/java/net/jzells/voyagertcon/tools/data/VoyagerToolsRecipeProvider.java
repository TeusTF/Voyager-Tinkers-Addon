package net.jzells.voyagertcon.tools.data;

import net.jzells.voyagertcon.tools.VoyagerTools;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;

import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;

public class VoyagerToolsRecipeProvider extends VoyagerAbstractRecipeProvider
                                        implements IMaterialRecipeHelper, IToolRecipeHelper {

    public VoyagerToolsRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        this.addToolBuildingRecipes(writer);
        this.addPartRecipes(writer);
    }

    private void addToolBuildingRecipes(Consumer<FinishedRecipe> writer) {
        String folder = "tools/building/";
        toolBuilding(writer, VoyagerTools.heavy_crossbow, folder);
        toolBuilding(writer, VoyagerTools.bolt, folder);
    }

     private void addPartRecipes(Consumer<FinishedRecipe> writer) {
        String parts = "tools/parts";
        String cast = "smeltery/casts";
        partRecipes(writer,VoyagerTools.bolt_core,VoyagerTools.bolt_core_cast,2,parts,cast);
     }

    @Override
    public String getName() {
        return "Voyager TCon Tool Recipes";
    }
}
