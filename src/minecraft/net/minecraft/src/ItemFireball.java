package net.minecraft.src;

public class ItemFireball extends ItemSnowball {

	public ItemFireball(int i) {
		super(i);
		maxStackSize = 16;
	}
	
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        world.playAuxSFXAtEntity(entityplayer, 3002, (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ, 0);
        if(!world.multiplayerWorld)
        {
            world.entityJoinedWorld(new EntityFireball(world, entityplayer, (float)entityplayer.posX, (float)entityplayer.posY, (float)entityplayer.posZ));
        }
        return itemstack;
    }

}
