// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, EntityPlayer, World

public class ItemFoodBad extends ItemFood
{

    public ItemFoodBad(int i, int j, boolean flag, boolean ps, String sp)
    {
        super(i, j, flag, ps, sp);
        damageAmount = j;
        playSound = ps;
        soundPlayed = sp;
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	if(playSound == true) {
            itemstack.stackSize--;
            entityplayer.attackEntityFrom(Damage.badFood(), damageAmount);
            world.playSoundAtEntity(entityplayer, soundPlayed, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
        	}
        	if(playSound == false) {
            itemstack.stackSize--;
            entityplayer.attackEntityFrom(Damage.badFood(), damageAmount);
            world.playAuxSFXAtEntity(entityplayer, 3000, (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ, 0);
        }
        	return itemstack;
    }

    public int getDamageAmount()
    {
        return damageAmount;
    }

    private int damageAmount;
    private boolean playSound;
    private String soundPlayed;
}
