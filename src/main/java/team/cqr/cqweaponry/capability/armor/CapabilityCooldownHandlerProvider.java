package team.cqr.cqweaponry.capability.armor;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import team.cqr.cqweaponry.CQWeaponryMod;
import team.cqr.cqweaponry.capability.SerializableCapabilityProvider;

public class CapabilityCooldownHandlerProvider extends SerializableCapabilityProvider<CapabilityCooldownHandler> {

	public static final ResourceLocation REGISTRY_NAME = new ResourceLocation(CQWeaponryMod.MODID, "item_cooldown_handler");

	@CapabilityInject(CapabilityCooldownHandler.class)
	public static final Capability<CapabilityCooldownHandler> CAPABILITY_ITEM_COOLDOWN_CQR = null;

	public CapabilityCooldownHandlerProvider(Capability<CapabilityCooldownHandler> capability, CapabilityCooldownHandler instance) {
		super(capability, instance);
	}

	public static void register() {
		CapabilityManager.INSTANCE.register(CapabilityCooldownHandler.class, new CapabilityCooldownHandlerStorage(), CapabilityCooldownHandler::new);
	}

	public static CapabilityCooldownHandlerProvider createProvider() {
		return new CapabilityCooldownHandlerProvider(CAPABILITY_ITEM_COOLDOWN_CQR, new CapabilityCooldownHandler());
	}

}
