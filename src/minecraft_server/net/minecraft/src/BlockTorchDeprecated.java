package net.minecraft.src;

import java.util.Random;

public class BlockTorchDeprecated extends Block
{

    protected BlockTorchDeprecated(int i, int j)
    {
        super(i, j, Material.circuits);
        setTickOnLoad(true);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isACube()
    {
        return false;
    }


    public void onBlockPlaced(World world, int i, int j, int k, int l)
    {
    	world.setBlockWithNotify(i, j, k, 50);
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        world.setBlockWithNotify(i, j, k, 50);
    }

    public MovingObjectPosition collisionRayTrace(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
    {
        int l = world.getBlockMetadata(i, j, k) & 7;
        float f = 0.15F;
        if(l == 1)
        {
            setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        } else
        if(l == 2)
        {
            setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        } else
        if(l == 3)
        {
            setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        } else
        if(l == 4)
        {
            setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        } else
        {
            float f1 = 0.1F;
            setBlockBounds(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, 0.6F, 0.5F + f1);
        }
        return super.collisionRayTrace(world, i, j, k, vec3d, vec3d1);
    }
}
