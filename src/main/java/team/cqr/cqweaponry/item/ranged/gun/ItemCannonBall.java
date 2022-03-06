package team.cqr.cqweaponry.item.ranged.gun;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemCannonBall extends Item {

	
	public ItemCannonBall(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> tooltip, ITooltipFlag pFlag) {
		super.appendHoverText(pStack, pLevel, tooltip, pFlag);
		tooltip.add(new TranslationTextComponent(TextFormatting.BLUE + "description.bullet_damage.name", 5));
	}

}
