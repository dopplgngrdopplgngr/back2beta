package net.minecraft.src;

public class DamageMob extends Damage {

	public DamageMob(String s,Entity entity)
	{
		super(s);	
		deathReason = s;
	    theEntity = entity; 
	}
}
