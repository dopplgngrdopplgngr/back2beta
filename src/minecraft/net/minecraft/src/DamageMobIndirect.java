package net.minecraft.src;

public class DamageMobIndirect extends DamageMob {

	public DamageMobIndirect(String s, Entity entity, Entity used)
	{
		super(s, entity);	
		deathReason = s;
		theEntity = entity;
		entityUsed = used;
	}

 public Entity entityUsed;
}
