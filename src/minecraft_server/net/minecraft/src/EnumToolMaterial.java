// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


public enum EnumToolMaterial
{
    WOOD("WOOD", 0, 0, 59, 2.0F, 0),
    STONE("STONE", 1, 1, 131, 4F, 1),
    IRON("IRON", 2, 2, 250, 6F, 2),
    EMERALD("EMERALD", 3, 3, 1561, 8F, 3),
    GOLD("GOLD", 4, 0, 32, 2F, 0),
	BUTTERKNIFE("BUTTERKNIFE", 5, 3, 9000, 9000F, 9000),
	OBSIDIAN("OBSIDIAN", 6, 3, 59, 11F, 6),
	ONYX("ONYX", 7, 3, 550, 7F, 2),
	COPPER("COPPER", 8, 2, 230, 5F, 2),
	OSMIUM("OSMIUM", 9, 3, 2633, 17F, 7),
	MEME("MEME", 10, 3, 65536, 90F, 9000);
/*
    public static EnumToolMaterial[] values()
    {
        return (EnumToolMaterial[])field_21182_j.clone();
    }

    public static EnumToolMaterial valueOf(String s)
    {
        return (EnumToolMaterial)Enum.valueOf(net.minecraft.src.EnumToolMaterial.class, s);
    }
*/
    private EnumToolMaterial(String s, int i, int j, int k, float f, int l)
    {
//        super(s, i);
        harvestLevel = j;
        maxUses = k;
        efficiencyOnProperMaterial = f;
        damageVsEntity = l;
    }

    public int getMaxUses()
    {
        return maxUses;
    }

    public float getEfficiencyOnProperMaterial()
    {
        return efficiencyOnProperMaterial;
    }

    public int getDamageVsEntity()
    {
        return damageVsEntity;
    }

    public int getHarvestLevel()
    {
        return harvestLevel;
    }
/*
    public static final EnumToolMaterial WOOD;
    public static final EnumToolMaterial STONE;
    public static final EnumToolMaterial IRON;
    public static final EnumToolMaterial EMERALD;
    public static final EnumToolMaterial GOLD;
*/
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final int damageVsEntity;
    private static final EnumToolMaterial field_21182_j[]; /* synthetic field */

    static 
    {
/*
        WOOD = new EnumToolMaterial("WOOD", 0, 0, 59, 2.0F, 0);
        STONE = new EnumToolMaterial("STONE", 1, 1, 131, 4F, 1);
        IRON = new EnumToolMaterial("IRON", 2, 2, 250, 6F, 2);
        EMERALD = new EnumToolMaterial("EMERALD", 3, 3, 1561, 8F, 3);
        GOLD = new EnumToolMaterial("GOLD", 4, 0, 32, 12F, 0);
*/
        field_21182_j = (new EnumToolMaterial[] {
            WOOD, STONE, IRON, EMERALD, GOLD, BUTTERKNIFE, OBSIDIAN, ONYX
        });
    }
}
