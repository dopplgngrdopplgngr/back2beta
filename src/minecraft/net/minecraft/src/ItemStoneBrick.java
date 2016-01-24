// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, World, EntityStoneBrick, 
//            EntityPlayer

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
