package net.minecraft.src;

import java.util.Random;

public class BlockDecPlanks extends Block {
	
 protected BlockDecPlanks(int i, int j)
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
    	 return 157;
     }
     if(j == 2)
     {
    	 return 159;
     } else
     {
     return blockIndexInTexture;
     }
     
   }
 
 public int idDropped(int i, Random random)
 {
     return B2BBlocks.decPlanks.blockID;
 }
 
 protected int damageDropped(int i)
 {
     return i;
 }
 
 public static final String decPlanksTypes[] = {
	    	"", "Pine", "Birch"
	    };
}
