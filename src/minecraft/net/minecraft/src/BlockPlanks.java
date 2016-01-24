package net.minecraft.src;

import java.util.Random;

public class BlockPlanks extends Block {
	
 protected BlockPlanks(int i, int j)
 {
	 super(i, j, Material.wood);
 }
 
 public int getBlockTextureFromSideAndMetadata(int i, int j)
 {
     if(j == 0)
     {
         return blockIndexInTexture;
     }
     if(j == 1)
     {
    	 return 127;
     }
     if(j == 2)
     {
    	 return 111;
     } else
     {
     return blockIndexInTexture;
     }
     
   }
 
 public int idDropped(int i, Random random)
 {
     return Block.planks.blockID;
 }
 
 protected int damageDropped(int i)
 {
     return i;
 }
 
 public static final String planksTypes[] = {
	    	"", "Pine", "Birch"
	    };
}
