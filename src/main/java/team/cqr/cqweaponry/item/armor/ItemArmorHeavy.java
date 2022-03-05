package team.cqr.cqweaponry.item.armor;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import team.cqr.cqrepoured.client.init.CQRArmorModels;
import team.cqr.cqrepoured.init.CQRMaterials;

public class ItemArmorHeavy extends ArmorItem {

	private AttributeModifier movementSpeed;
	private AttributeModifier knockbackResistance;

	public ItemArmorHeavy(IArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, Properties prop) {
		super(materialIn, equipmentSlotIn, prop);

		this.movementSpeed = new AttributeModifier("HeavySpeedModifier", -0.05D, Operation.MULTIPLY_TOTAL);
		this.knockbackResistance = new AttributeModifier("HeavyKnockbackModifier", 0.1D, Operation.MULTIPLY_TOTAL);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if (slot == MobEntity.getEquipmentSlotForItem(stack)) {
			multimap.put(Attributes.KNOCKBACK_RESISTANCE, this.knockbackResistance);
			multimap.put(Attributes.MOVEMENT_SPEED, this.movementSpeed);
		}

		return multimap;
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		super.onArmorTick(stack, world, player);
		player.jumpMovementFactor *= 0.95F;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	@Nullable
	public ModelBiped getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, ModelBiped _default) {
		ArmorMaterial armorMaterial = ((ArmorItem) itemStack.getItem()).getArmorMaterial();

		if (armorMaterial == CQRMaterials.ArmorMaterials.ARMOR_HEAVY_DIAMOND) {
			return armorSlot == EquipmentSlotType.LEGS ? CQRArmorModels.heavyDiamondArmorLegs : CQRArmorModels.heavyDiamondArmor;
		} else if (armorMaterial == CQRMaterials.ArmorMaterials.ARMOR_HEAVY_IRON) {
			return armorSlot == EquipmentSlotType.LEGS ? CQRArmorModels.heavyIronArmorLegs : CQRArmorModels.heavyIronArmor;
		}

		return null;

	}

}
