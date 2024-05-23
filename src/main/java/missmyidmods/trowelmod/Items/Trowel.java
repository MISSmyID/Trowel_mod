package missmyidmods.trowelmod.Items;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLadder;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.LinkedList;
import java.util.Random;

public class Trowel extends Item {

	public Trowel(String name, int id) {
		super(name, id);
	}

	Random rand = new Random();

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (!world.isClientSide) {
			ItemStack checkedItem = null;
			if (player != null) {
				LinkedList<ItemStack> pool = new LinkedList<ItemStack>();
				for (int i = 0; i <= 8; i++) {
					checkedItem = player.inventory.getStackInSlot(i);
					if (checkedItem != null && checkedItem.getItem() instanceof ItemBlock && checkedItem.itemID != Block.ladderOak.id) {
						pool.add(checkedItem);
					}
				}
				if (!pool.isEmpty()) {
					ItemStack chosenItem = pool.get(rand.nextInt(pool.size()));
					if (blockY >= 0 && blockY < world.getHeightBlocks()) {
						if (chosenItem.stackSize != 0) {
							chosenItem.getItem().onItemUse(chosenItem, player, world, blockX, blockY, blockZ, side, xPlaced, yPlaced);
							return true;
						} else {
								pool.remove(chosenItem);
							}
							return false;
						}
					}
				}
			}
		return false;
	}
}

