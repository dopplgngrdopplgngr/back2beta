// b2b
package net.minecraft.src;

public class B2BBlocks {
/* all B2B blocks and block crafting recipes are contained in this class, and then passed to the craftingmanager 
 * using addRecipes "(new RecipesClass()).addRecipes(this);"
 *
 */ 
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
       craftingmanager.addRecipe(new ItemStack(stoneBricks, 1), new Object[] {
            "BB ", "BB ", Character.valueOf('B'), B2BItems.stoneBrick
       });
       craftingmanager.addRecipe(new ItemStack(cobblestoneSmooth, 1), new Object[] {
            "C", Character.valueOf('C'), Block.cobblestone
       });
       craftingmanager.addRecipe(new ItemStack(decPlanks, 1, 0), new Object[] {
       		"#", Character.valueOf('#'), new ItemStack(Block.planks, 1, 0)
       });
       craftingmanager.addRecipe(new ItemStack(decPlanks, 1, 1), new Object[] {
       		"#", Character.valueOf('#'), new ItemStack(Block.planks, 1, 1)
       });
       craftingmanager.addRecipe(new ItemStack(decPlanks, 1, 2), new Object[] {
       		"#", Character.valueOf('#'), new ItemStack(Block.planks, 1, 2)
       });
       craftingmanager.addRecipe(new ItemStack(blockOnyx), new Object[] {
    		"OO", "OO", Character.valueOf('O'), new ItemStack(B2BItems.onyxStone)
       });
       craftingmanager.addShapelessRecipe(new ItemStack(B2BItems.onyxStone, 4), new Object[] {
    		blockOnyx,
       });
	}
    /*
     * public static final Block blockName = new BlockBlockName(0, 0).setHardness(1.0F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("stringtobetranslated");
     * 
     * *a new accessible, non changing, and unchangeable Block called blockName" = "a new BlockBlockName with the block id 0 (WARNING DON'T USE 0) 16x texture index 0 (counts from 0+) on terrain.png, ..
     * .. a hardness and resistance float of 1.0 and 5, a stone sound for when stepped on and the string key for translation to the block name is "stringtobetranslated" .. 
     * stringtobetranslated is turned into "tile.name.stringtobetranslated, it takes the name from the en_us.lang file which should ..
     * contain: tile.name.stringtobetranslated=Block Name
     */
    public static final Block stoneBricks = new BlockStoneBrick(100, 136).setHardness(1.5F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("stonebricks");
    public static final Block cobblestoneSmooth = new Block(101, 137, Material.rock).setHardness(2.0F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setBlockName("cobblestoneSmooth");
    public static final Block torchOff = new BlockTorchDeprecated(102, 138).setHardness(0.0F).setStepSound(Block.soundWoodFootstep).setBlockName("torchoff");
    public static final Block torchStone = new BlockTorchDeprecated(103, 139).setHardness(0.0F).setLightValue(0.9375F).setStepSound(Block.soundStoneFootstep).setBlockName("torchstone");
    public static final Block torchStoneOff = new BlockTorchDeprecated(104, 141).setHardness(0.0F).setStepSound(Block.soundStoneFootstep).setBlockName("torchstoneoff");
    public static final Block torchIron = new BlockTorchDeprecated(105, 143).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundStoneFootstep).setBlockName("torchiron");
    public static final Block decStone = new Block(106, 142, Material.rock).setHardness(2.0F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setBlockName("decStone");
    public static final Block decPlanks = new BlockDecPlanks(107, 158).setHardness(2.0F).setResistance(5F).setStepSound(Block.soundWoodFootstep).setBlockName("decWood");
    public static final Block piePumpkin = new BlockCake(108, 153).setHardness(0.5F).setStepSound(Block.soundClothFootstep).setBlockName("piePumpkin").disableStats();
    public static final Block oreOnyx = new BlockOre(109, 126).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("oreOnyx");
    public static final Block blockOnyx = new BlockOreStorage(110, 125).setHardness(3F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setBlockName("blockOnyx");
    public static final Block hellGold = (new BlockHellOre(111, 141)).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("oreGold");
    public static final Block oreCopper = new BlockOre(112, 143).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("oreCopper");
    public static final Block blockCopper = new BlockOreStorage(113, 173).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setBlockName("blockCopper");
    public static final Block oreOsmium = new BlockOre(114, 174).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("oreOsmium");
    public static final Block blockOsmium = new BlockOreStorage(115, 175).setHardness(5F).setResistance(5F).setStepSound(Block.soundMetalFootstep).setBlockName("blockOsmium");
    public static final Block oreUranium = new BlockOre(116, 171).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("oreUranium");
    public static final Block blockUranium = new BlockOreStorage(117, 170).setHardness(3.5F).setResistance(4.5F).setStepSound(Block.soundMetalFootstep).setBlockName("blockUranium");
    public static final Block oreArdum = new BlockOre(118, 167).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("oreArdum");
    public static final Block blockArdum = new BlockOreStorage(119, 166).setHardness(3.5F).setResistance(4.5F).setStepSound(Block.soundMetalFootstep).setBlockName("blockArdum");    
    public static final Block orePyrite = new BlockOre(120, 169).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("orePyrite");
    public static final Block blockUnrefinedPyrite = new BlockOreStorage(121, 168).setHardness(3.5F).setResistance(4.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockUnrefinedPyrite");
}
