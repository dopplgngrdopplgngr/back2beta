// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ItemBlock

public class ItemSapling extends ItemBlock
{

    public ItemSapling(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    public String getItemNameIS(ItemStack itemstack)
    {
        return (new StringBuilder()).append(super.getItemName()).append(BlockSapling.saplingTypes[itemstack.getItemDamage()]).toString();
    }

    public int getMetadata(int i)
    {
        return i;
    }
}
