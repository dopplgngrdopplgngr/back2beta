package net.minecraft.src;

public class ItemPlanks extends ItemBlock {
	
    public ItemPlanks(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    public int getMetadata(int i)
    {
        return i;
    }
    
    public String getItemNameIS(ItemStack itemstack)
    {
        return (new StringBuilder()).append(super.getItemName()).append(BlockPlanks.planksTypes[itemstack.getItemDamage()]).toString();
    }
    
    public int getIconFromDamage(int i, int j)
    {
        return Block.planks.getBlockTextureFromSideAndMetadata(0, j);
    }

}
