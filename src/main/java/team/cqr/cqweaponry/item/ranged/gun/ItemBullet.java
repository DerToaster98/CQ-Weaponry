package team.cqr.cqweaponry.item.ranged.gun;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import team.cqr.cqweaponry.init.CQWItems;

public class ItemBullet extends Item {

	public ItemBullet(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public void appendHoverText(ItemStack stack, World pLevel, List<ITextComponent> tooltip, ITooltipFlag pFlag) {
		if (stack.getItem() == CQWItems.BULLET_IRON.get()) {
			tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 2.5));
		}

		if (stack.getItem() == CQWItems.BULLET_GOLD.get()) {
			tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 3.75));
		}

		if (stack.getItem() == CQWItems.BULLET_DIAMOND.get()) {
			tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 5));
		}

		if (stack.getItem() == CQWItems.BULLET_FIRE.get()) {
			tooltip.add(new TranslationTextComponent(TextFormatting.RED + "description.bullet_damage.name", 5));
			tooltip.add(new TranslationTextComponent(TextFormatting.DARK_RED +"description.bullet_fire.name"));
		}
	}

}
