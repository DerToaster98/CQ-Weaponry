package team.cqr.cqweaponry.capability.armor;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.registries.ForgeRegistries;

public class CapabilityCooldownHandlerStorage implements IStorage<CapabilityCooldownHandler> {

	@Override
	public INBT writeNBT(Capability<CapabilityCooldownHandler> capability, CapabilityCooldownHandler instance, Direction side) {
		ListNBT nbtTagList = new ListNBT();

		instance.getItemCooldownMap().forEach((item, cd) -> {
			CompoundNBT compound = new CompoundNBT();

			compound.putString("item", item.getRegistryName().toString());
			compound.putInt("cooldown", cd);
			nbtTagList.add(compound);
		});

		return nbtTagList;
	}

	@Override
	public void readNBT(Capability<CapabilityCooldownHandler> capability, CapabilityCooldownHandler instance, Direction side, INBT nbt) {
		if (nbt instanceof ListNBT) {
			ListNBT nbtTagList = (ListNBT) nbt;

			for (int i = 0; i < nbtTagList.size(); i++) {
				CompoundNBT compound = nbtTagList.getCompound(i);
				Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(compound.getString("item")));

				if (item != null) {
					instance.setCooldown(item, compound.getInt("cooldown"));
				}
			}
		}
	}

}
