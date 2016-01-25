// b2b
package net.minecraft.src;

public class B2BRecipes {

public void addRecipes(CraftingManager craftingmanager) {
	/* 
	 * craftingmanager.addRecipe(new ItemStack(*class.*blockOrItem, 1, 0), new Object[] {
	 * "AAA", "AAA", "AAA", Character.valueOf('A'), *new ItemStack(*class.*itemOrBlock, 1, 0) or *class.*itemOrBlock*
	 * });
	 *  *instance of CraftingManager*.*add a recipe for a new itemstack with the amount 1 and damage 0*, *declare a new Object[] {
	 *  *crafting recipe*, *the value of the Character 'A' is an ItemStack of 1 itemOrBlock with the damage 0*
	 *  RECIPE
	 *  A A A
	 *  A A A -> blockOrItem
	 *  A A A
	 */
    craftingmanager.addRecipe(new ItemStack(Block.planks, 4, 1), new Object[] {
        	"#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 1)
    });
    craftingmanager.addRecipe(new ItemStack(Block.planks, 4, 2), new Object[] {
        	"#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 2)
    });
    craftingmanager.addRecipe(new ItemStack(Item.stick, 5), new Object[] {
            "B", Character.valueOf('B'), Block.deadBush
    });
    craftingmanager.addRecipe(new ItemStack(Item.flint, 1), new Object[] {
	        "G", Character.valueOf('G'), Block.gravel
    });		
    craftingmanager.addRecipe(new ItemStack(Block.planks), new Object[] {
            "S", "S", Character.valueOf('S'), new ItemStack(Block.stairSingle, 1, 2)
    });
    craftingmanager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 2), new Object[] {
    		new ItemStack(Block.leaves)
    });
    craftingmanager.addRecipe(new ItemStack(Block.cobblestone, 1), new Object[] {
            "C", Character.valueOf('C'), B2BBlocks.cobblestoneSmooth
    });
    craftingmanager.addRecipe(new ItemStack(Block.planks, 1, 0), new Object[] {
       	    "#", Character.valueOf('#'), new ItemStack(B2BBlocks.decPlanks, 1, 0)
    });
    craftingmanager.addRecipe(new ItemStack(Block.planks, 1, 1), new Object[] {
        	"#", Character.valueOf('#'), new ItemStack(B2BBlocks.decPlanks, 1, 1)
    });
    craftingmanager.addRecipe(new ItemStack(Block.planks, 1, 2), new Object[] {
            "#", Character.valueOf('#'), new ItemStack(B2BBlocks.decPlanks, 1, 2)
    });
}

public void addSmeltingRecipes(FurnaceRecipes furnacerecipes)
{
	/*
	 * furnacerecipes.addSmelting(Item.vanillaItem.shiftedIndex, new ItemStack(B2BItems.testItem));
	 * *instance of FurnaceRecicpes* *it adds a smelting recipe which makes the Item vanillaItem smeltable to testItem ..
	 * .. it gets the integer of the item id (shiftedIndex) and adds it to the smelting list, and then adds an ItemStack ..
	 * of testItem with no amount, or damage value specified*
	 */
    furnacerecipes.addSmelting(Item.egg.shiftedIndex, new ItemStack(B2BItems.eggBoiled));
    furnacerecipes.addSmelting(Block.stone.blockID, new ItemStack(B2BBlocks.decStone));
    furnacerecipes.addSmelting(B2BItems.porkRotten.shiftedIndex, new ItemStack(Item.porkCooked));
    furnacerecipes.addSmelting(B2BBlocks.hellGold.blockID, new ItemStack(Item.ingotGold));
    furnacerecipes.addSmelting(B2BBlocks.oreCopper.blockID, new ItemStack(B2BItems.ingotCopper));
}

}
