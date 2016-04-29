package net.minecraft.src;

public class ItemDecPlanks extends ItemBlock {
	
    public ItemDecPlanks(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    public int getPlacedBlockMetadata(int i)
    {
        return i;
    }
    
    public String getItemNameIS(ItemStack itemstack)
    {
        return (new StringBuilder()).append(super.getItemName()).append(BlockDecPlanks.decPlanksTypes[itemstack.getItemDamage()]).toString();
    }
    
    public int getIconFromDamage(int i, int j)
    {
        return B2BBlocks.decPlanks.getBlockTextureFromSideAndMetadata(0, j);
    }

}
