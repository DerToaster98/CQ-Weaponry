package team.cqr.cqweaponry.item.ranged.staff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import team.cqr.cqrepoured.entity.projectiles.ProjectileVampiricSpell;
import team.cqr.cqweaponry.item.IRangedWeapon;
import team.cqr.cqweaponry.item.ItemLore;

public class ItemStaffVampiric extends ItemLore implements IRangedWeapon {

	public ItemStaffVampiric() {
		this.setMaxDamage(2048);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		this.shoot(stack, worldIn, playerIn, handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	public void shoot(ItemStack stack, World worldIn, PlayerEntity player, Hand handIn) {
		worldIn.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERPEARL_THROW, SoundCategory.MASTER, 4.0F, (1.0F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F) * 0.7F, false);
		player.swingArm(handIn);

		if (!worldIn.isRemote) {
			ProjectileVampiricSpell spell = new ProjectileVampiricSpell(worldIn, player);
			spell.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0F);
			worldIn.spawnEntity(spell);
			stack.damageItem(1, player);
			player.getCooldownTracker().setCooldown(stack.getItem(), 20);
		}
	}

	@Override
	public void shoot(World worldIn, LivingEntity shooter, Entity target, Hand handIn) {
		shooter.swingArm(handIn);

		if (!worldIn.isRemote) {
			ProjectileVampiricSpell spell = new ProjectileVampiricSpell(worldIn, shooter);
			Vector3d v = target.position().subtract(shooter.position());
			v = v.normalize();
			v = v.scale(0.75D);
			// spell.setVelocity(v.x, v.y, v.z);
			spell.motionX = v.x;
			spell.motionY = v.y;
			spell.motionZ = v.z;
			spell.velocityChanged = true;
			worldIn.spawnEntity(spell);
		}
	}

	@Override
	public SoundEvent getShootSound() {
		return SoundEvents.ENTITY_ENDERPEARL_THROW;
	}

	@Override
	public double getRange() {
		return 32.0D;
	}

	@Override
	public int getCooldown() {
		return 60;
	}

	@Override
	public int getChargeTicks() {
		return 0;
	}

}
