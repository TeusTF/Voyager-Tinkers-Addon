package net.jzells.voyagertcon.tools.entity;

import net.jzells.voyagertcon.tools.modules.BoltModule;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.entity.ModifiableArrow;

public class ModifiableBolt extends ModifiableArrow {

    @Setter
    private ItemStack actualStack = ItemStack.EMPTY;

    public ModifiableBolt(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public ModifiableBolt(Level level, double pX, double pY, double pZ) {
        super(level, pX, pY, pZ);
    }

    public ModifiableBolt(Level level, LivingEntity shooter) {
        super(level, shooter);
    }

    @Override
    public IToolStackView onCreate(ItemStack stack, @Nullable LivingEntity shooter) {
        setActualStack(stack);
        return super.onCreate(stack, shooter);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("stack", CompoundTag.TAG_COMPOUND)) {
            setActualStack(ItemStack.of(tag.getCompound("stack")));
        }
    }

    @Override
    protected boolean tryPickup(Player player) {
        return switch (this.pickup) {
            case ALLOWED -> this.tryUpdateStack(player);
            case CREATIVE_ONLY -> player.getAbilities().instabuild;
            default -> false;
        };
    }

    private boolean tryUpdateStack(Player player) {
        CompoundTag tag = actualStack.getTag();
        if (tag == null) return false;
        ItemStack boltStack = player.getInventory().getItem(tag.getInt(BoltModule.BOLT));
        if (isSameBolt(actualStack, boltStack)) {
            boltStack.setDamageValue(boltStack.getDamageValue() > 0 ? boltStack.getDamageValue() - 1 : 0);
            return true;
        } else {
            Inventory inv = player.getInventory();
            for (int i = 0; i < inv.getContainerSize(); i++) {
                boltStack = inv.getItem(i);
                if (isSameBolt(actualStack, boltStack)) {
                    boltStack.setDamageValue(boltStack.getDamageValue() > 0 ? boltStack.getDamageValue() - 1 : 0);
                    return true;
                }
            }
            return false; //Item not returned, because duplicate stack lol
        }
    }

    private boolean isSameBolt(ItemStack bolt, ItemStack test) {
        if (!(bolt.getItem() instanceof IModifiable) || !(test.getItem() instanceof IModifiable)) return false;
        ToolStack boltStack = ToolStack.from(bolt);
        ToolStack testStack = ToolStack.from(test);
        return (boltStack.getMaterials().equals(testStack.getMaterials()));
    }
}
