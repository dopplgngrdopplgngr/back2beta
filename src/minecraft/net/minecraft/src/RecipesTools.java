// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Block, Item, ItemStack, CraftingManager

public class RecipesTools
{

    public RecipesTools()
    {
        recipeItems = (new Object[][] {
            new Object[] {
                Block.planks, Block.cobblestone, Item.ingotIron, Item.diamond, Item.ingotGold, Block.obsidian, B2BItems.onyxStone, B2BItems.ingotCopper
            }, new Object[] {
                Item.pickaxeWood, Item.pickaxeStone, Item.pickaxeIron, Item.pickaxeDiamond, Item.pickaxeGold, B2BItems.pickaxeObsidian, B2BItems.pickaxeOnyx, B2BItems.pickaxeCopper
            }, new Object[] {
                Item.shovelWood, Item.shovelStone, Item.shovelIron, Item.shovelDiamond, Item.shovelGold, B2BItems.shovelObsidian, B2BItems.shovelOnyx, B2BItems.shovelCopper
            }, new Object[] {
                Item.axeWood, Item.axeStone, Item.axeIron, Item.axeDiamond, Item.axeGold, B2BItems.axeObsidian, B2BItems.axeOnyx, B2BItems.axeCopper
            }, new Object[] {
                Item.hoeWood, Item.hoeStone, Item.hoeSteel, Item.hoeDiamond, Item.hoeGold, B2BItems.hoeObsidian, B2BItems.hoeOnyx, B2BItems.hoeCopper
            }
        });
    }

    public void addRecipes(CraftingManager craftingmanager)
    {
        for(int i = 0; i < recipeItems[0].length; i++)
        {
            Object obj = recipeItems[0][i];
            for(int j = 0; j < recipeItems.length - 1; j++)
            {
                Item item = (Item)recipeItems[j + 1][i];
                craftingmanager.addRecipe(new ItemStack(item), new Object[] {
                    recipePatterns[j], Character.valueOf('#'), Item.stick, Character.valueOf('X'), obj
                });
            }

        }

        craftingmanager.addRecipe(new ItemStack(Item.shears), new Object[] {
            " #", "# ", Character.valueOf('#'), Item.ingotIron
        });
    }

    private String recipePatterns[][] = {
        {
            "XXX", " # ", " # "
        }, {
            "X", "#", "#"
        }, {
            "XX", "X#", " #"
        }, {
            "XX", " #", " #"
        }
    };
    private Object recipeItems[][];
}
