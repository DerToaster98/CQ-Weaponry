package team.cqr.cqweaponry.item.ranged.gun;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import team.cqr.cqrepoured.init.CQRItems;

public class ItemBullet extends Item {

	public ItemBullet(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> tooltip, ITooltipFlag pFlag) {
		if (stack.getItem() == CQRItems.BULLET_IRON) {
			tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 2.5));
		}

		if (stack.getItem() == CQRItems.BULLET_GOLD) {
			tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 3.75));
		}

		if (stack.getItem() == CQRItems.BULLET_DIAMOND) {
			tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 5));
		}

		if (stack.getItem() == CQRItems.BULLET_FIRE) {
			tooltip.add(new TranslationTextComponent(TextFormatting.RED + "description.bullet_damage.name", 5));
			tooltip.add(new TranslationTextComponent(TextFormatting.DARK_RED +"description.bullet_fire.name"));
		}
	}

}
