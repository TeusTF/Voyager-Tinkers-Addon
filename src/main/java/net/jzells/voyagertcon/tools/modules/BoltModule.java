package net.jzells.voyagertcon.tools.modules;

import net.jzells.voyagertcon.tools.ModifiableBoltItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.ResourceColorManager;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.DisplayNameModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;
import java.util.function.Predicate;

public enum BoltModule implements BowAmmoModifierHook, ModifierModule, DisplayNameModifierHook {

    INSTANCE;

    public static final String BOLT = "vtcon_bolt";
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider
            .<BoltModule>defaultHooks(ModifierHooks.BOW_AMMO, ModifierHooks.DISPLAY_NAME);
    public static final SingletonLoader<BoltModule> LOADER = new SingletonLoader<>(INSTANCE);

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public void shrinkAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack ammo,
                           int needed) {
        if ((ammo.getItem() instanceof ModifiableBoltItem && shooter instanceof Player player)) {
            int inv = ammo.getTag().getInt(BOLT);
            ItemStack playerBolt = player.getInventory().getItem(inv);
            playerBolt.hurtAndBreak(needed, shooter, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            return;
        }
        // If somehow a tool firing with this modifier isn't only shooting bolts...
        BowAmmoModifierHook.super.shrinkAmmo(tool, modifier, shooter, ammo, needed);
    }

    @Override
    public ItemStack findAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack standardAmmo,
                              Predicate<ItemStack> ammoPredicate) {
        if (!(shooter instanceof Player player)) {
            return standardAmmo;
        }

        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack itemstack = inventory.getItem(i);
            if (itemstack.getItem() instanceof ModifiableBoltItem bs) {
                if (ToolStack.from(itemstack).isBroken()) return ItemStack.EMPTY;
                // Yes, I'm passing the durability as the count. No, I don't know if there's a better way
                ItemStack bolt = itemstack.copyWithCount(itemstack.getMaxDamage() - itemstack.getDamageValue());
                bolt.getOrCreateTag().putInt(BOLT, i); // Needed for modifying original stack in shrinkAmmo(...)
                return bolt;
            }
        }

        return standardAmmo;
    }

    @Override
    public Component getDisplayName(IToolStackView tool, ModifierEntry entry, Component name,
                                    @Nullable RegistryAccess access) {
        for (MaterialVariant material : tool.getMaterials()) {
            String key = entry.getModifier().getTranslationKey();
            TextColor color = ResourceColorManager.getTextColor(key + "." + material.getId().getPath());
            if (color != ResourceColorManager.WHITE) {
                return name.copy().withStyle(style -> style.withColor(color));
            }
        }
        return name;
    }
}
