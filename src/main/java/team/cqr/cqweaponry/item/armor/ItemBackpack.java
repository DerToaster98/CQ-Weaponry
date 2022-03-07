package team.cqr.cqweaponry.item.armor;

import java.util.List;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import team.cqr.cqrepoured.util.GuiHandler;
import team.cqr.cqweaponry.CQWeaponryMod;
import team.cqr.cqweaponry.IItemWithInventory;
import team.cqr.cqweaponry.capability.itemhandler.CapabilityItemHandlerItemProvider;
import team.cqr.cqweaponry.client.init.CQWArmorModels;
import team.cqr.cqweaponry.item.ItemLore;

public class ItemBackpack extends ArmorItem implements IItemWithInventory {

	public ItemBackpack(IArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, Properties prop) {
		super(materialIn, equipmentSlotIn, prop);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (!worldIn.isClientSide) {
			playerIn.openGui(CQWeaponryMod.INSTANCE, GuiHandler.BACKPACK_GUI_ID, worldIn, handIn.ordinal(), 0, 0);
			return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
		}
		return new ActionResult<>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		ItemLore.addHoverTextLogic(tooltip, flagIn, this.getRegistryName().getPath());
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		return CapabilityItemHandlerItemProvider.createProvider(stack, 36);
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		return (A) CQWArmorModels.BACKPACK;
	}

	@Override
	public IContainerProvider createNewContainer(ServerPlayerEntity player, ItemStack item, int slot) {
		return null;
	}

	@Override
	public void writeExtraData(PacketBuffer buffer) {
		
	}

}
