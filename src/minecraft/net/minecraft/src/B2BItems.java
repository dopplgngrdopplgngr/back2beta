// b2b
package net.minecraft.src;

public class B2BItems {
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
        craftingmanager.addRecipe(new ItemStack(stoneBrick, 4), new Object[] {
                "S", Character.valueOf('S'), Block.stone
        });
        craftingmanager.addRecipe(new ItemStack(cakeSlice, 6), new Object[] {
				"C", Character.valueOf('C'), Item.cake
		});
        craftingmanager.addShapelessRecipe(new ItemStack(piePumpkin), new Object[] {
        		new ItemStack(Block.pumpkin), new ItemStack(Item.wheat), new ItemStack(Item.sugar)
        });
	}
	/*
	 * public static Item itemTest = (new Item(0)).setIconCoord(0, 0).setItemName("itemName");
	 * *a accessable, non changing Item named itemTest* = * a new Item with the ID 0*
	 * *the 16x scale coordinates on items.png are (0, 0)* *the string key to translate to the item name is "itemName"*
	 * *the string is translated with item.itemName.name*
	 * *it's taken from the file en_US.lang which should contain: item.itemName.name=Test Item*
	 */
    public static Item porkRotten = (new ItemFoodBad(200, 8, false, false, null)).setIconCoord(7, 7).setItemName("porkchopBad");
    public static Item friedFryes = (new ItemFood(201, 20, false, false, null)).setIconCoord(8, 7).setItemName("friedFryes");
    public static Item leafSuper = (new Item(203)).setIconCoord(15, 15).setItemName("leafBig");
    public static Item stoneBrick = (new ItemStoneBrick(204)).setIconCoord(13, 15).setItemName("stoneBrick");
    public static Item cakeSlice = (new ItemCookie(209, 3, false, false, null, 8)).setIconCoord(12, 15).setItemName("cakeSlice");
    public static Item eggShell = (new Item(207)).setIconCoord(11, 15).setItemName("eggShell");
    public static Item eggBoiled = (new ItemSoup(208, 6, eggShell, 3000)).setIconCoord(10, 15).setItemName("eggBoiled");
    public static Item butterKnife = (new ItemSword(206, EnumToolMaterial.BUTTERKNIFE)).setIconCoord(14, 15).setItemName("BLOODYBUTTERKNIFE");
    public static Item piePumpkin = (new ItemReed(210, B2BBlocks.piePumpkin)).setMaxStackSize(1).setIconCoord(9, 15).setItemName("piePumpkin");
    public static Item swordObsidian = (new ItemSword(211, EnumToolMaterial.OBSIDIAN)).setIconCoord(0, 14).setItemName("swordObsidian");
    public static Item shovelObsidian = (new ItemSpade(212, EnumToolMaterial.OBSIDIAN)).setIconCoord(1, 14).setItemName("shovelObsidian");
    public static Item pickaxeObsidian = (new ItemPickaxe(213, EnumToolMaterial.OBSIDIAN)).setIconCoord(2, 14).setItemName("pickaxeObsidian");
    public static Item axeObsidian = (new ItemAxe(214, EnumToolMaterial.OBSIDIAN)).setIconCoord(3, 14).setItemName("hatchetObsidian");
    public static Item hoeObsidian = (new ItemHoe(215, EnumToolMaterial.OBSIDIAN)).setIconCoord(4, 14).setItemName("hoeObsidian");
    public static Item onyxStone = (new Item(216)).setIconCoord(8, 15).setItemName("onyx");
    public static Item chicken = (new ItemFood(217, 6, false, false, null)).setIconCoord(7 , 15).setItemName("chicken");
    public static Item fireball = (new ItemFireball(218).setIconCoord(14, 0).setItemName("fireball"));
    public static Item swordOnyx = (new ItemSword(219, EnumToolMaterial.ONYX)).setIconCoord(0, 13).setItemName("swordOnyx"); //ID=475
    public static Item shovelOnyx = (new ItemSpade(220, EnumToolMaterial.ONYX)).setIconCoord(1, 13).setItemName("shovelOnyx"); //ID=476
    public static Item pickaxeOnyx = (new ItemPickaxe(221, EnumToolMaterial.ONYX)).setIconCoord(2, 13).setItemName("pickaxeOnyx"); //ID=477
    public static Item axeOnyx = (new ItemAxe(222, EnumToolMaterial.ONYX)).setIconCoord(3, 13).setItemName("hatchetOnyx"); //ID=478
    public static Item hoeOnyx = (new ItemHoe(223, EnumToolMaterial.ONYX)).setIconCoord(4, 13).setItemName("hoeOnyx"); //ID=479
    public static Item ingotCopper = (new Item(224)).setIconCoord(6, 15).setItemName("ingotCopper"); //ID=480
    public static Item swordCopper = (new ItemSword(225, EnumToolMaterial.COPPER)).setIconCoord(0, 12).setItemName("swordCopper"); //ID=481
    public static Item shovelCopper = (new ItemSpade(226, EnumToolMaterial.COPPER)).setIconCoord(1, 12).setItemName("shovelCopper"); //ID=482
    public static Item pickaxeCopper = (new ItemPickaxe(227, EnumToolMaterial.COPPER)).setIconCoord(2, 12).setItemName("pickaxeCopper"); //ID=483
    public static Item axeCopper = (new ItemAxe(228, EnumToolMaterial.COPPER)).setIconCoord(3, 12).setItemName("axeCopper"); //ID=484
    public static Item hoeCopper = (new ItemHoe(229, EnumToolMaterial.COPPER)).setIconCoord(4, 12).setItemName("hoeCopper"); //ID=485
    public static Item helmetCopper = (new ItemArmor(230, 8, 8, 0)).setIconCoord(8, 12).setItemName("helmetCopper");
    public static Item plateCopper = (new ItemArmor(231, 8, 8, 1)).setIconCoord(7, 12).setItemName("chestplateCopper");
    public static Item legsCopper = (new ItemArmor(232, 8, 8, 2)).setIconCoord(6, 12).setItemName("leggingsCopper");
    public static Item bootsCopper = (new ItemArmor(233, 8, 8, 3)).setIconCoord(5, 12).setItemName("bootsCopper");
    public static Item ingotOsmium = (new Item(234)).setIconCoord(5, 15).setItemName("ingotOsmium");
}

