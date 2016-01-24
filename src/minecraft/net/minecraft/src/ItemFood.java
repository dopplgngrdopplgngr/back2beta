// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, EntityPlayer, World

public class ItemFood extends Item
{

    public ItemFood(int i, int j, boolean flag, boolean ps, String sp)
    {
        super(i);
        healAmount = j;
        isWolfsFavoriteMeat = flag;
        maxStackSize = 1;
        soundPlayed = sp;
        playSound = ps;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	if(entityplayer.health < 20 && playSound == true) {
        itemstack.stackSize--;
        entityplayer.heal(healAmount);
        world.playSoundAtEntity(entityplayer, soundPlayed, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
    	}
    	if(entityplayer.health < 20 && playSound == false) {
        itemstack.stackSize--;
        entityplayer.heal(healAmount);
        world.playAuxSFXAtEntity(entityplayer, 3000, (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ, 0);
    	}
        return itemstack;
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    public boolean getIsWolfsFavoriteMeat()
    {
        return isWolfsFavoriteMeat;
    }
    
    private boolean playSound;
    private String soundPlayed;
    private int healAmount;
    private boolean isWolfsFavoriteMeat;
}
