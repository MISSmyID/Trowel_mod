package missmyidmods.trowelmod.items;

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
    static private Random rand = new Random();

    public Trowel(String name, int id) {
        super(name, id);
    }

    @Override
    public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        if (world.isClientSide || player == null) return false;
        // Get all blocks in the hotbar
        LinkedList<ItemStack> pool = new LinkedList<ItemStack>();
        for (int i = 0; i <= 8; i++) {
            ItemStack checkedItem = player.inventory.getStackInSlot(i + player.inventory.hotbarOffset);
            if (checkedItem != null && checkedItem.getItem() instanceof ItemBlock) {
                pool.add(checkedItem);
            }
        }
        if (pool.isEmpty()) return false;
        // Get a random item and place it
        ItemStack chosenItem = pool.get(rand.nextInt(pool.size()));
        boolean ret = chosenItem.useItem(player, world, blockX, blockY, blockZ, side, xPlaced, yPlaced);
        // Delete zero-stacked items
        for (int i = 0; i <= 8; i++) {
            ItemStack checkedItem = player.inventory.getStackInSlot(i + player.inventory.hotbarOffset);
            if (checkedItem != null && checkedItem.stackSize <= 0) {
                player.inventory.setInventorySlotContents(i + player.inventory.hotbarOffset, null);
                break;
            }
        }
        return ret;
    }
}
