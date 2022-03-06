package team.cqr.cqweaponry.item.ranged.gun;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import team.cqr.cqrepoured.entity.projectiles.ProjectileBullet;
import team.cqr.cqrepoured.init.CQRPotions;
import team.cqr.cqrepoured.init.CQRSounds;
import team.cqr.cqweaponry.item.ItemLore;

public class ItemMusket extends ItemRevolver {

	public ItemMusket(Properties prop) {
		super(prop);
		this.setMaxDamage(300);
		this.setMaxStackSize(1);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 5));
		tooltip.add(new TranslationTextComponent(TextFormatting.RED + "description.fire_rate.name", -30));
		tooltip.add(new TranslationTextComponent(TextFormatting.RED + "description.accuracy.name", -50));
		ItemLore.addHoverTextLogic(this, tooltip, flagIn);
	}

	@Override
	protected float getRecoil() {
		return 0.5F * super.getRecoil();
	}

	@Override
	public void shoot(ItemStack stack, World worldIn, PlayerEntity player) {
		boolean flag = player.capabilities.isCreativeMode;
		ItemStack itemstack = this.findAmmo(player);

		if (!itemstack.isEmpty() || flag) {
			if (!worldIn.isRemote) {
				if (flag && itemstack.isEmpty()) {
					ProjectileBullet bulletE = new ProjectileBullet(worldIn, player, 1);
					bulletE.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 2F);
					player.getCooldownTracker().setCooldown(stack.getItem(), 30);
					worldIn.spawnEntity(bulletE);
				} else {
					ProjectileBullet bulletE = new ProjectileBullet(worldIn, player, this.getBulletType(itemstack));
					bulletE.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 2F);
					player.getCooldownTracker().setCooldown(stack.getItem(), 30);
					worldIn.spawnEntity(bulletE);
					stack.damageItem(1, player);
				}
			}

			worldIn.playSound(player.posX, player.posY + player.getEyeHeight(), player.posZ, this.getShootSound(), SoundCategory.MASTER, 1.0F, 0.9F + itemRand.nextFloat() * 0.2F, false);
			player.rotationPitch -= worldIn.rand.nextFloat() * 10;

			if (!flag) {
				itemstack.shrink(1);

				if (itemstack.isEmpty()) {
					player.inventory.deleteStack(itemstack);
				}
			}
		}
	}

	@Override
	public SoundEvent getShootSound() {
		return CQRSounds.MUSKET_SHOOT;
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
