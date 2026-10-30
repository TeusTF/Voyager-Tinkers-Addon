package net.jzells.voyagertcon.tools.data.lang;

import net.jzells.voyagertcon.VoyagerTCon;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.LanguageProvider;

import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.item.IModifiable;

public abstract class VoyagerAbstractLangValueProvider extends LanguageProvider {

    public VoyagerAbstractLangValueProvider(PackOutput output, String locale) {
        super(output, VoyagerTCon.MOD, locale);
    }

    protected void addItemObject(ItemObject<? extends ItemLike> object, String translation) {
        add(object.asItem(), translation);
    }

    protected void addToolObject(ItemObject<? extends IModifiable> object, String translation, String description) {
        addItemObject(object, translation);
        add(object.getId().toLanguageKey("item") + ".description", description);
    }

    protected void addModifier(ModifierId id, String translation, String descriptionText, String flavorText) {
        String langKey = id.toLanguageKey("modifier");
        add(langKey, translation);
        add(langKey + ".description", descriptionText);
        add(langKey + ".flavor", flavorText);
    }

    protected void addCastLang(CastItemObject castObject, String castGold, String castRedSand, String castSand) {
        add(castObject.get(), castGold);
        add(castObject.getRedSand(), castRedSand);
        add(castObject.getSand(), castSand);
    }

    @Override
    public String getName() {
        return "Voyager TCon Language: English";
    }
}
