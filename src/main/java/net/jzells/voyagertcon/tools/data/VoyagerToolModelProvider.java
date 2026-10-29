package net.jzells.voyagertcon.tools.data;

import com.google.gson.JsonObject;
import net.jzells.voyagertcon.VoyagerTCon;
import net.jzells.voyagertcon.tools.VoyagerTools;
import net.minecraft.data.PackOutput;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.data.AbstractToolItemModelProvider;

import java.io.IOException;

public class VoyagerToolModelProvider extends AbstractToolItemModelProvider {

    public VoyagerToolModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper, VoyagerTCon.MOD);
    }

    @Override
    protected void addModels() throws IOException {
        JsonObject toolBlocking = readJson(VoyagerTCon.ofResourceLocation("base/tool_blocking"));
        bow(VoyagerTools.heavy_crossbow, toolBlocking, new CrossbowAmmo(new Vec2(-1, -1), true, false), "bowstring");

    }

    @Override
    public String getName() {
        return "Voyager TCon Tool Model Provider";
    }
}
