
package net.hhdsj.changed_creatures.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.hhdsj.changed_creatures.procedures.PaleTreeUseBoneProcedure;

public class PaleTreeItem extends Item {
	public PaleTreeItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		return PaleTreeUseBoneProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getClickedFace(),
				context.getItemInHand());
	}
}
