package net.minecraft.src;

import java.util.Random;


public class ItemStoneBrick extends Item
{

    public ItemStoneBrick(int i)
    {
        super(i);
        maxStackSize = 8;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        world.playAuxSFXAtEntity(entityplayer, 3002, (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ, 0);
        if(!world.multiplayerWorld)
        {
            world.entityJoinedWorld(new EntityStoneBrick(world, entityplayer));
        }
        return itemstack;
    }
}
