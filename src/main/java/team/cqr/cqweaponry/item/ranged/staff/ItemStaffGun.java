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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import team.cqr.cqrepoured.entity.projectiles.ProjectileCannonBall;
import team.cqr.cqrepoured.init.CQRSounds;
import team.cqr.cqweaponry.item.IRangedWeapon;
import team.cqr.cqweaponry.item.ItemLore;

public class ItemStaffGun extends ItemLore implements IRangedWeapon {

	public ItemStaffGun() {
		this.setMaxDamage(2048);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		this.shootStaff(worldIn, playerIn, stack, handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	public void shootStaff(World worldIn, PlayerEntity player, ItemStack stack, Hand handIn) {
		worldIn.playSound(player.posX, player.posY, player.posZ, CQRSounds.GUN_SHOOT, SoundCategory.MASTER, 4.0F, (1.0F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F) * 0.7F, false);

		if (!worldIn.isRemote) {
			ProjectileCannonBall ball = new ProjectileCannonBall(worldIn, player, false);
			ball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 0F);
			worldIn.spawnEntity(ball);
			stack.damageItem(1, player);
			player.getCooldownTracker().setCooldown(stack.getItem(), 20);
		}
	}

	@Override
	public void shoot(World worldIn, LivingEntity shooter, Entity target, Hand handIn) {
		if (!worldIn.isRemote) {
			ProjectileCannonBall ball = new ProjectileCannonBall(worldIn, shooter, false);
			Vector3d v = target.position().subtract(shooter.position());
			v = v.normalize();
			v = v.scale(3.5D);
			// ball.setVelocity(v.x, v.y, v.z);
			ball.motionX = v.x;
			ball.motionY = v.y;
			ball.motionZ = v.z;
			ball.velocityChanged = true;
			worldIn.spawnEntity(ball);
		}
	}

	@Override
	public SoundEvent getShootSound() {
		return CQRSounds.GUN_SHOOT;
	}

	@Override
	public double getRange() {
		return 32.0D;
	}

	@Override
	public int getCooldown() {
		return 50;
	}

	@Override
	public int getChargeTicks() {
		return 0;
	}

}
