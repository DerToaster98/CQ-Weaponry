package team.cqr.cqweaponry.item.melee.dagger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import team.cqr.cqweaponry.item.IExtendedItemTier;

public class ItemDaggerNinja extends ItemDagger {

	public ItemDaggerNinja(Properties props, IExtendedItemTier material, int cooldown) {
		super(props, material, cooldown);
	}
	

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);

		if (playerIn.isCrouching()) {
			playerIn.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
			playerIn.getCooldowns().addCooldown(stack.getItem(), 30);

			for (int i = 0; i < 6; i++) {
				worldIn.addParticle(ParticleTypes.PORTAL, playerIn.position().x + random.nextFloat() - 0.5D, playerIn.position().y + random.nextFloat() - 0.5D, playerIn.position().z + random.nextFloat() - 0.5D, random.nextFloat() - 0.5F, random.nextFloat() - 0.5F, random.nextFloat() - 0.5F);
			}

			double x = -Math.sin(Math.toRadians(playerIn.yRot));
			double z = Math.cos(Math.toRadians(playerIn.yRot));
			double y = -Math.sin(Math.toRadians(playerIn.xRot));
			x *= (1.0D - Math.abs(y));
			z *= (1.0D - Math.abs(y));
			int dist = 4;

			BlockPos pos = playerIn.blockPosition().offset(x * dist, y * dist + 1, z * dist);

			if (worldIn.getBlockState(pos).getBlock().getCollisionShape(worldIn.getBlockState(pos), worldIn, pos, ISelectionContext.of(playerIn)).isEmpty() && pos.getY() > 0) {
				playerIn.setPos(playerIn.position().x + x * dist, playerIn.position().y + y * dist + 1, playerIn.position().z + z * dist);
				playerIn.addEffect(new EffectInstance(Effects.INVISIBILITY, 40, 5, false, false));
			} else {
				return new ActionResult<>(ActionResultType.FAIL, stack);
			}

			stack.hurtAndBreak(1, playerIn, (p_220040_1_) -> {
                p_220040_1_.broadcastBreakEvent(handIn);
             });

			for (int i = 0; i < 6; i++) {
				worldIn.addParticle(ParticleTypes.PORTAL, playerIn.position().x + random.nextFloat() - 0.5D, playerIn.position().y + random.nextFloat() - 0.5D, playerIn.position().z + random.nextFloat() - 0.5D, random.nextFloat() - 0.5F, random.nextFloat() - 0.5F, random.nextFloat() - 0.5F);
			}
		} else {
			return super.use(worldIn, playerIn, handIn);
		}
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

}
