package team.cqr.cqweaponry.init;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import team.cqr.cqweaponry.CQWeaponryMod;

public class CQWCreativeTabs {
	
	public static final ItemGroup CQ_WEAPONRY = new ItemGroup(CQWeaponryMod.MODID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CQWItems.WALKER_SWORD.get());
        }
    };

}
