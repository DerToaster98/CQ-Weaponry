package team.cqr.cqweaponry.item;

import net.minecraft.item.IItemTier;

public interface IExtendedItemTier extends IItemTier {
	
	public int getFixedAttackDamageBonus();
	public int getAttackSpeedBonus();
	public double getMovementSpeedBonus();

}
