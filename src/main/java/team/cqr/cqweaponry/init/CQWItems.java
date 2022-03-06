package team.cqr.cqweaponry.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import team.cqr.cqweaponry.CQWeaponryMod;
import team.cqr.cqweaponry.item.melee.sword.ItemSwordTurtle;
import team.cqr.cqweaponry.item.ranged.gun.ItemBullet;

public class CQWItems {
	
	public static void registerToEventBus(IEventBus eventbus) {
		ITEMS.register(eventbus);
	}

	static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CQWeaponryMod.MODID);
	
	static Properties createStandardCQWStackPropertiesStackable() {
		return (new Properties().tab(CQWCreativeTabs.CQ_WEAPONRY).stacksTo(64));
	}
	
	static Properties createStandardCQWStackProperties() {
		return (new Properties().tab(CQWCreativeTabs.CQ_WEAPONRY).stacksTo(1));
	}
	
	//Bullets
	public static final RegistryObject<Item> ITEM_BULLET_IRON = ITEMS.register("bullet_iron", () -> new ItemBullet(createStandardCQWStackPropertiesStackable()));
	public static final RegistryObject<Item> ITEM_BULLET_GOLD = ITEMS.register("bullet_gold", () -> new ItemBullet(createStandardCQWStackPropertiesStackable()));
	public static final RegistryObject<Item> ITEM_BULLET_DIAMOND = ITEMS.register("bullet_diamond", () -> new ItemBullet(createStandardCQWStackPropertiesStackable()));
	public static final RegistryObject<Item> ITEM_BULLET_FIRE = ITEMS.register("bullet_fire", () -> new ItemBullet(createStandardCQWStackPropertiesStackable().fireResistant()));
	
	//Swords
	
	//Daggers
	
	//Spears
	
	//Guns
	
	//Hookhots
	
	//Staffs
	
	//Armor

}
