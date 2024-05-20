package MISSmyIDMods.TrowelMod.Items;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.LinkedList;
import java.util.Random;

public class Trowel extends Item {

	public Trowel(String name, int id) {
		super(name, id);
	}
	private int count = 0;
	Random rand = new Random();
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if(!world.isClientSide){
			count++;
			ItemStack checkedItem;
			if(player != null){
				LinkedList<ItemStack> pool = new LinkedList<ItemStack>();
				for(int i = 0;i <= 8;i++){
					checkedItem = player.inventory.getStackInSlot(i);
					if(checkedItem != null && checkedItem.getItem() instanceof ItemBlock ){
						pool.add(checkedItem);}
				}
				if(!pool.isEmpty()){
					int x = blockX,y = blockY,z = blockZ;
					switch (side){
						case EAST:
							x++;
							break;
						case WEST:
							x--;
							break;
						case NORTH:
							z--;
							break;
						case SOUTH:
							z++;
							break;
						case TOP:
							y++;
							break;
						case BOTTOM:
							y--;
							break;
					}
					ItemStack chosenItem = pool.get(rand.nextInt(pool.size()));
					if (blockY >= 0 && blockY < world.getHeightBlocks()) {
							Block block = Block.blocksList[chosenItem.itemID];
							if(world.canBlockBePlacedAt(chosenItem.itemID, x, y, z, false, side)) {
								if (world.setBlockAndMetadataWithNotify(x, y, z, chosenItem.itemID, this.getPlacedBlockMetadata(chosenItem.getMetadata())) && chosenItem.consumeItem(player)) {
									block.onBlockPlaced(world, x, y, z, side, player, yPlaced);
									world.playBlockSoundEffect(player, ((float) x + 0.5F), ((float) y + 0.5F), ((float) z + 0.5F), block, EnumBlockSoundEffectType.PLACE);
								}
							}
							return true;
					} else return false;
				}
			}
		}
		return false;
	}
}
