package net.jzells.voyagertcon.tools.data.sprite;

import net.jzells.voyagertcon.VoyagerTCon;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

public class VoyagerPartSpriteProvider extends AbstractPartSpriteProvider {


    public VoyagerPartSpriteProvider() {
        super(VoyagerTCon.MOD);
    }

    @Override
    public String getName() {
        return "Voyager TCon Part Sprite Data Gen";
    }

    @Override
    protected void addAllSpites() {
        buildTool("heavy_crossbow")
                .addLimb("limb_top").addLimb("limb_bottom")
                .addBreakableBowstring("bowstring").addGrip("body");
    }
}
