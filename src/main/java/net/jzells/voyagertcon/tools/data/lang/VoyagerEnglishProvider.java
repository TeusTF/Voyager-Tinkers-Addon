package net.jzells.voyagertcon.tools.data.lang;

import net.jzells.voyagertcon.tools.VoyagerTools;
import net.jzells.voyagertcon.tools.data.VoyagerModifierIds;
import net.minecraft.data.PackOutput;

public class VoyagerEnglishProvider extends VoyagerAbstractLangValueProvider {
    public VoyagerEnglishProvider(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItemObject(VoyagerTools.heavy_crossbow,"Heavy Crossbow");
        addToolObject(VoyagerTools.bolt, "Bolt","Bolts are your standard repairable ammo type," +
                " however they can only be used correctly on weapons with the Bolt Shooter Modifier");

        addItemObject(VoyagerTools.bolt_core, "Bolt Core");
        addCastLang(VoyagerTools.bolt_core_cast,"Bolt Core Cast", "Bolt Core Red Sand Cast", "Bolt Core Sand Cast");

        addModifier(VoyagerModifierIds.bolt_shooter,"Bolt Shooter","This weapon shoots bolts","Is that a foot of rebar?");
    }
}
