package team.cqr.cqweaponry.item;

import net.minecraft.item.IItemTier;

public interface IDaggerItemTier extends IItemTier {
	
	public int getAttackSpeedBonus();
	public double getMovementSpeedBonus();

}
