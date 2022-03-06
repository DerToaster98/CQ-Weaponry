package team.cqr.cqweaponry.item.melee;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import team.cqr.cqrepoured.entity.projectiles.ProjectileEarthQuake;
import team.cqr.cqweaponry.item.ItemLore;

public class ItemBullBattleAxe extends SwordItem {

	public ItemBullBattleAxe(IItemTier material, Properties prop) {
		super(material, prop);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (!playerIn.isSwingInProgress && playerIn.onGround) {
			playerIn.posY += 0.1D;
			playerIn.motionY += 0.35D;

			if (!worldIn.isRemote) {
				ProjectileEarthQuake quake = new ProjectileEarthQuake(worldIn, playerIn);
				quake.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.25F, 1.0F);
				worldIn.spawnEntity(quake);

				playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 20);
				return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
			}
			playerIn.swingArm(handIn);
		}
		return new ActionResult<>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			this.replaceModifier(modifiers, Attributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, 0.6);
		}

		return modifiers;
	}

	protected void replaceModifier(Multimap<String, AttributeModifier> modifierMultimap, Attribute attribute, UUID id, double multiplier) {
		Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());
		Optional<AttributeModifier> modifierOptional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (modifierOptional.isPresent()) {
			final AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier);
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() - multiplier, modifier.getOperation()));
		}
	}

	
	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		ItemLore.addHoverTextLogic(this, pTooltip, pFlag);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return (state.getMaterial() == Material.WOOD || state.getMaterial() == Material.PLANTS || state.getMaterial() == Material.VINE) ? 7.0F : super.getDestroySpeed(stack, state);
	}

}
