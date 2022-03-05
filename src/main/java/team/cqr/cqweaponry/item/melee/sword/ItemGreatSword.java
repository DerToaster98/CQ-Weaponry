package team.cqr.cqweaponry.item.melee.sword;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import team.cqr.cqrepoured.config.CQRConfig;
import team.cqr.cqrepoured.init.CQRPotions;
import team.cqr.cqweaponry.item.ItemLore;
import team.cqr.cqweaponry.item.ItemMeleeBase;
import team.cqr.cqweaponry.util.ItemUtil;

public class ItemGreatSword extends ItemMeleeBase {

	private final float specialAttackDamage;
	private final int specialAttackCooldown;

	public ItemGreatSword(IItemTier material, float damage, int cooldown) {
		super(material, CQRConfig.materials.toolMaterials.greatSwordAttackDamageBonus, CQRConfig.materials.toolMaterials.greatSwordAttackSpeedBonus);
		this.specialAttackDamage = damage;
		this.specialAttackCooldown = cooldown;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		ItemUtil.attackTarget(stack, player, entity, false, 0.0F, 1.0F, true, 2.0F, 0.0F, 1.25D, 0.25D, 0.5F);
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		ItemLore.addHoverTextLogic(tooltip, flagIn, this.getRegistryName().getPath());
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		float range = 3F;
		double mx = entityLiving.posX - range;
		double my = entityLiving.posY - range;
		double mz = entityLiving.posZ - range;
		double max = entityLiving.posX + range;
		double may = entityLiving.posY + range;
		double maz = entityLiving.posZ + range;

		AxisAlignedBB bb = new AxisAlignedBB(mx, my, mz, max, may, maz);

		List<MobEntity> entitiesInAABB = worldIn.getEntitiesWithinAABB(MobEntity.class, bb);

		for (int i = 0; i < entitiesInAABB.size(); i++) {
			MobEntity entityInAABB = entitiesInAABB.get(i);

			if (this.getMaxItemUseDuration(stack) - timeLeft <= 30) {
				entityInAABB.attackEntityFrom(DamageSource.causeExplosionDamage(entityLiving), this.specialAttackDamage);
			}

			if (this.getMaxItemUseDuration(stack) - timeLeft > 30 && this.getMaxItemUseDuration(stack) - timeLeft <= 60) {
				entityInAABB.attackEntityFrom(DamageSource.causeExplosionDamage(entityLiving), this.specialAttackDamage * 3);
			}

			if (this.getMaxItemUseDuration(stack) - timeLeft > 60) {
				entityInAABB.attackEntityFrom(DamageSource.causeExplosionDamage(entityLiving), this.specialAttackDamage * 4);
			}
		}

		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLiving;

			float x = (float) -Math.sin(Math.toRadians(player.rotationYaw));
			float z = (float) Math.cos(Math.toRadians(player.rotationYaw));
			float y = (float) -Math.sin(Math.toRadians(player.rotationPitch));
			x *= (1.0F - Math.abs(y));
			z *= (1.0F - Math.abs(y));

			if (player.onGround && this.getMaxItemUseDuration(stack) - timeLeft > 40) {
				player.posY += 0.1D;
				player.motionY += 0.35D;
			}

			player.getCooldownTracker().setCooldown(stack.getItem(), this.specialAttackCooldown);
			player.swingArm(Hand.MAIN_HAND);
			worldIn.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
			worldIn.spawnParticle(ParticleTypes.EXPLOSION_LARGE, player.posX + x, player.posY + y + 1.5D, player.posZ + z, 0D, 0D, 0D);
			stack.damageItem(1, player);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAction getItemUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!(entityIn instanceof LivingEntity)) {
			return;
		}
		if (!isSelected) {
			return;
		}
		LivingEntity entityLiving = (LivingEntity) entityIn;
		ItemStack offhand = entityLiving.getMainHandItem();
		if (!offhand.isEmpty()) {
			entityLiving.addPotionEffect(new EffectInstance(CQRPotions.TWOHANDED, 30, 1));
		}
	}

}
