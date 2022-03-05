package team.cqr.cqweaponry.capability.itemhandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import team.cqr.cqweaponry.capability.SerializableCapabilityProvider;

public class CapabilityItemHandlerItemProvider extends SerializableCapabilityProvider<IItemHandler> {

	public CapabilityItemHandlerItemProvider(Capability<IItemHandler> capability, IItemHandler instance) {
		super(capability, instance);
	}

	public static CapabilityItemHandlerItemProvider createProvider(ItemStack stack, int slots) {
		return new CapabilityItemHandlerItemProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new CapabilityItemHandlerItem(stack, slots));
	}

}
