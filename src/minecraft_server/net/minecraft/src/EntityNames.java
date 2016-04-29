// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            World, Entity, NBTTagCompound, EntityArrow, 
//            EntitySnowball, EntityItem, EntityPainting, EntityLiving, 
//            EntityMob, EntityCreeper, EntitySkeleton, EntitySpider, 
//            EntityGiantZombie, EntityZombie, EntitySlime, EntityGhast, 
//            EntityPigZombie, EntityPig, EntitySheep, EntityCow, 
//            EntityChicken, EntitySquid, EntityWolf, EntityTNTPrimed, 
//            EntityFallingSand, EntityMinecart, EntityBoat, EntityStoneBrick

public class EntityNames
{

    public EntityNames()
    {
    	
    }

	private static void addMapping(Class<?> class1, String s)
    {
        classToStringMapping.put(class1, s);
    }

    public static String getEntityString(Entity entity)
    {
        return (String)classToStringMapping.get(entity.getClass());
    }

	private static Map<Class<?>, String> classToStringMapping = new HashMap<Class<?>, String>();


    static 
    {
        addMapping(net.minecraft.src.EntityArrow.class, "Arrow");
        addMapping(net.minecraft.src.EntitySnowball.class, "Snowball");
        addMapping(net.minecraft.src.EntityItem.class, "Item");
        addMapping(net.minecraft.src.EntityPainting.class, "Painting");
        addMapping(net.minecraft.src.EntityLiving.class, "Mob");
        addMapping(net.minecraft.src.EntityMob.class, "Monster");
        addMapping(net.minecraft.src.EntityCreeper.class, "Creeper");
        addMapping(net.minecraft.src.EntitySkeleton.class, "Skeleton");
        addMapping(net.minecraft.src.EntitySpider.class, "Spider");
        addMapping(net.minecraft.src.EntityGiantZombie.class, "Giant");
        addMapping(net.minecraft.src.EntityZombie.class, "Zombie");
        addMapping(net.minecraft.src.EntitySlime.class, "Slime");
        addMapping(net.minecraft.src.EntityGhast.class, "Ghast");
        addMapping(net.minecraft.src.EntityPigZombie.class, "Zombie Pigman");
        addMapping(net.minecraft.src.EntityPig.class, "Pig");
        addMapping(net.minecraft.src.EntitySheep.class, "Sheep");
        addMapping(net.minecraft.src.EntityCow.class, "Cow");
        addMapping(net.minecraft.src.EntityChicken.class, "Chicken");
        addMapping(net.minecraft.src.EntitySquid.class, "Squid");
        addMapping(net.minecraft.src.EntityWolf.class, "Wolf");
        addMapping(net.minecraft.src.EntityTNTPrimed.class, "Block of TNT");
        addMapping(net.minecraft.src.EntityFallingSand.class, "Falling Block");
        addMapping(net.minecraft.src.EntityMinecart.class, "Minecart");
        addMapping(net.minecraft.src.EntityBoat.class, "Boat");
        addMapping(net.minecraft.src.EntityStoneBrick.class, "Brick");
        addMapping(net.minecraft.src.EntityLightningBolt.class, "Lightning Bolt");
    }
}
