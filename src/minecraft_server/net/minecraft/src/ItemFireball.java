package net.minecraft.src;

public class ItemFireball extends ItemSnowball {

	public ItemFireball(int i) {
		super(i);
		maxStackSize = 16;
	}
	
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        world.playSoundAtEntity(entityplayer, "random.old_bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!world.singleplayerWorld)
        {
            world.entityJoinedWorld(new EntityFireball(world, entityplayer, (float)entityplayer.posX, (float)entityplayer.posY, (float)entityplayer.posZ));
        }
        return itemstack;
    }

}
