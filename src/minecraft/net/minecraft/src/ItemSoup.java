// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EntityPlayer, World, ItemStack

public class ItemSoup extends Item
{

    public ItemSoup(int i, int j, Item is, int c)
    {
        super(i);
        healAmount = j;
        maxStackSize = 1;
        returnItem = is;
        sound = c;
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	if(entityplayer.health < 20)
    	{
        entityplayer.heal(healAmount);
        world.playAuxSFXAtEntity(entityplayer, sound, (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ, 0);
        return new ItemStack(returnItem);
    	} else
    	{
    	  return itemstack;
    	}
    }
    
    private int sound;
    private int healAmount;
    private Item returnItem;
}
