package team.cqr.cqweaponry;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkHooks;

public interface IItemWithInventory {
	
	public default void openGUIFor(ServerPlayerEntity player, ItemStack item, int slot) {
		NetworkHooks.openGui(player, new SimpleNamedContainerProvider(
				this.createNewContainer(player, item, slot), 
				item.getDisplayName()
			), buffer -> {
				this.writeExtraData(buffer);
				buffer.writeVarInt(slot);
			}
		);
	}
	
	IContainerProvider createNewContainer(ServerPlayerEntity player, ItemStack item, int slot);
	
	void writeExtraData(PacketBuffer buffer);
	
	

}
