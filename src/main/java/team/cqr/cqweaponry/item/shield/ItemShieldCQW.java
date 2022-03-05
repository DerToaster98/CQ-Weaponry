package team.cqr.cqweaponry.item.shield;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class ItemShieldCQW extends ShieldItem {

	public static final String[] SHIELD_NAMES = { "bull", "carl", "dragonslayer", "fire", "goblin", "monking", "moon", "mummy", "pigman", "pirate", "pirate2", "rainbow", "reflective", "rusted", "skeleton_friends", "specter", "spider", "sun", "tomb", "triton", "turtle", "walker", "warped", "zombie" };

	private Item repairItem;

	public ItemShieldCQW(Properties props, int durability, @Nullable Item repairItem) {
		super(props.durability(durability));
		this.repairItem = repairItem;
	}
	
	@Override
	public boolean isRepairable(ItemStack repair) {
		return repair.getItem() == this.repairItem;
	}

	@Override
	public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
		return stack.getItem() instanceof ShieldItem;
	}

}
