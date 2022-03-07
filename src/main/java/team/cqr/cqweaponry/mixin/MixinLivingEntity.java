package team.cqr.cqweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import team.cqr.cqweaponry.item.armor.ItemArmorBull;
import team.cqr.cqweaponry.util.ItemUtil;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

	@Inject(at = @At("HEAD"), method = "push(Lnet/minecraft/entity/Entity;)V", cancellable = true)
	private void mixinDoPush(Entity pEntity, CallbackInfo ci) {
		// pEntity is the pusher
		if (pEntity instanceof LivingEntity) {
			if (ItemUtil.hasFullSet((LivingEntity) pEntity, ItemArmorBull.class)) {
				if (pEntity.isSprinting()) {
					double myVolume = this.getBbWidth() * this.getBbHeight() * this.getBbWidth();
					double theirVolume = pEntity.getBbWidth() * pEntity.getBbHeight() * pEntity.getBbWidth();

					if((myVolume / 2) <= theirVolume) {
						
						Vector3d velocity = this.position().subtract(pEntity.position());
						velocity = velocity.add(0, 0.1, 0);
						velocity = velocity.multiply(1.5, 1.5, 1.5);
						velocity = velocity.add(this.getDeltaMovement());
						this.setDeltaMovement(velocity);
					}
				}
			}
		}
	}

	@Shadow
	public void setDeltaMovement(Vector3d pMotion) {
		throw new IllegalStateException("Mixin failed to shadow setDeltaMovement(Vector3d v)");
	}

	@Shadow
	public Vector3d getDeltaMovement() {
		throw new IllegalStateException("Mixin failed to shadow getDeltaMovement()");
	}

	@Shadow
	public Vector3d position() {
		throw new IllegalStateException("Mixin failed to shadow position()");
	}

	@Shadow
	public final float getBbWidth() {
		throw new IllegalStateException("Mixin failed to shadow getBbWidth()");
	}

	@Shadow
	public final float getBbHeight() {
		throw new IllegalStateException("Mixin failed to shadow getBbHeight()");
	}

}
