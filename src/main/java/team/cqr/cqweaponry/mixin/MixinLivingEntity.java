package team.cqr.cqweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import team.cqr.cqweaponry.item.armor.ItemArmorBull;
import team.cqr.cqweaponry.util.ItemUtil;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

	@Inject(
			at = @At("HEAD"),
			method = "push(Lnet/minecraft/entity/Entity;)V",
			cancellable = true
	)
	private void mixinDoPush(
			Entity pEntity,
			CallbackInfo ci
	) {
		//pEntity is the pusher
		if(pEntity instanceof LivingEntity) {
			if(ItemUtil.hasFullSet((LivingEntity) pEntity, ItemArmorBull.class)) {
				if(pEntity.isSprinting()) {
					//TODO: Send "myself" flying away
				}
			}
		}
	}
	
}
