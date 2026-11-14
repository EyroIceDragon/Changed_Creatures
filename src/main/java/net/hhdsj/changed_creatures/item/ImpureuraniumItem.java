
package net.hhdsj.changed_creatures.item;

import net.hhdsj.changed_creatures.init.ChangedCreatureModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ImpureuraniumItem extends Item {

	public ImpureuraniumItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
	}

	@Override
	public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
		return 0F;
	}

	//如果玩家把铀粉末拿在手上时,执行下面的代码(qwp)
	@Override
	public void inventoryTick(ItemStack itemstack, Level world, @NotNull Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected) {
            //获取现在的实体,给实体个效果
			if (entity instanceof LivingEntity liv_entity) {
				liv_entity.addEffect(new MobEffectInstance(ChangedCreatureModMobEffects.RADIATION.get(), 60, 1));
            }
		}
	}
}
