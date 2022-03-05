package team.cqr.cqweaponry.item;

import net.minecraft.item.Item;

public interface IFakeWeapon<T extends Item & ISupportWeapon<?>> {

	T getOriginalItem();

}
