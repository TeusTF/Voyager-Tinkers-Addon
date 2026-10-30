package net.jzells.voyagertcon.tools.data.mantle;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import slimeknights.mantle.data.GenericDataProvider;

import java.util.concurrent.CompletableFuture;

// TODO LATER, Unnecessary ATM.
public class MaterialColorProvider extends GenericDataProvider {
    // private Map<MaterialId,>

    public MaterialColorProvider(PackOutput output) {
        super(output, PackOutput.Target.RESOURCE_PACK, "mantle/", GSON);
    }

    protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return "Voyager Mantle Color Gen";
    }
}
