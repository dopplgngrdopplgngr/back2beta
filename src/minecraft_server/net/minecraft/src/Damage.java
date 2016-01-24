package net.minecraft.src;

public class Damage {
	
    public Damage(String s)
    {   	
     deathReason = s;	
    }
    
    public static DamageMob explode(Entity entity)
    {
      String explodeComplex = new StringBuilder().append(" was blown up by a ").append(EntityNames.getEntityString(entity)).toString();
      return new DamageMob(explodeComplex, entity);
    }

    public static Damage badFood()
    {	
      return new Damage(" died of food poisoning").bypassArmor();
    }

    public static DamageMob mob(Entity entity)
    {
      String mobComplex = new StringBuilder().append(" was killed by a ").append(EntityNames.getEntityString(entity)).toString();
      if(entity instanceof EntityWolf)
      {
    	if(((EntityWolf)entity).isWolfTamed() && ((EntityWolf)entity).getOwner() != null)
    	{
    	  mobComplex = new StringBuilder().append(" was killed by ").append(((EntityWolf)entity).getOwner()).append("'s Wolf").toString();
    	} else
    	mobComplex = " was killed by a wild Wolf";
      } else
      if(entity instanceof EntitySlime)
      {
    	mobComplex = " was squished by a Slime";
      } else
      if(entity instanceof EntityPigZombie)
      {
        mobComplex = " was stabbed to death by an enraged Zombie Pigman";
      } else
      if(entity instanceof EntityZombie)
      {
    	mobComplex = " was eaten alive by a hungry Zombie";
      } else
      if(entity instanceof EntitySpider)
      {
        mobComplex = " was bitten to death by a Spider";
      } else
      if(entity instanceof EntityPlayer)
      {
        mobComplex = new StringBuilder().append(" was killed by ").append(((EntityPlayer)entity).username).toString();
      }
      return new DamageMob(mobComplex, entity);
    }

    public static DamageMobIndirect mobIndirect(Entity entity, Entity used)
    {
    	 String deathReasonComplex = "";
    	 if(entity == null)
    	 {
    	  if(used instanceof EntityArrow)
    	  {
    		deathReasonComplex = " was fatally shot with an arrow from a dispenser";
    	  } else
    	  deathReasonComplex = " was fatally hit with a brick from a dispenser";
    	 } else
    	 if(entity instanceof EntityLiving && used instanceof EntityFireball)
    	 {
    	  deathReasonComplex = new StringBuilder().append(" was fireballed by a ").append((EntityNames.getEntityString(entity))).toString();
    	 } else
         if(entity instanceof EntityLiving && used instanceof EntityArrow)
         {
           if(entity instanceof EntityPlayer)
           {
        	deathReasonComplex =  new StringBuilder().append(" was fatally shot by ").append(((EntityPlayer)entity).username).toString();
           } else
           deathReasonComplex =  new StringBuilder().append(" was fatally shot by a ").append((EntityNames.getEntityString(entity))).toString();
         } else
         if(entity instanceof EntityPlayer && used instanceof EntityThrowing)
         {
          deathReasonComplex = new StringBuilder().append(" was fatally hit with a brick by ").append(((EntityPlayer)entity).username).toString();
         }
      return new DamageMobIndirect(deathReasonComplex, entity, used);
    }
    
    protected Damage bypassArmor()
    {
    	doesBypassArmor = true;
    	return this;
    }
    
 public static Damage fall = new Damage(" fell to their death");
 public static Damage fire = new Damage(" burned to death");
 public static Damage lava = new Damage(" disintegrated in lava");
 public static Damage block = new Damage(" suffocated in a block");
 public static Damage drown = new Damage(" drowned");
 public static Damage cactus = new Damage(" was pricked to death");
 public static Damage explodeGeneric = new Damage(" was blown up");
 public static Damage explodeBed = new Damage(" was blown up trying to sleep in the nether");
 public static Damage generic = new Damage(" died");
    
 public boolean doesBypassArmor;
 public Entity theEntity = null;
 public String deathReason;
}
