// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            ItemRenderer, ItemStack, Block, RenderBlocks, 
//            Config, RenderEngine, Tessellator, EntityLiving

public class ItemRendererHD extends ItemRenderer
{

    public ItemRendererHD(Minecraft minecraft1)
    {
        super(minecraft1);
        minecraft = null;
        minecraft = minecraft1;
    }

    public void renderItem(EntityLiving entityliving, ItemStack itemstack)
    {
        if(itemstack.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
        {
            super.renderItem(entityliving, itemstack);
            return;
        }
        int i = Config.getIconWidthTerrain();
        if(i <= 16)
        {
            super.renderItem(entityliving, itemstack);
            return;
        }
        GL11.glPushMatrix();
        if(itemstack.itemID < 256)
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.renderEngine.getTexture("/terrain.png"));
            i = Config.getIconWidthTerrain();
        } else
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.renderEngine.getTexture("/gui/items.png"));
            i = Config.getIconWidthItems();
        }
        Tessellator tessellator = Tessellator.instance;
        int j = entityliving.getItemIcon(itemstack);
        float f = ((float)((j % 16) * 16) + 0.0F) / 256F;
        float f1 = ((float)((j % 16) * 16) + 15.99F) / 256F;
        float f2 = ((float)((j / 16) * 16) + 0.0F) / 256F;
        float f3 = ((float)((j / 16) * 16) + 15.99F) / 256F;
        float f4 = 1.0F;
        float f5 = 0.0F;
        float f6 = 0.3F;
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glTranslatef(-f5, -f6, 0.0F);
        float f7 = 1.5F;
        GL11.glScalef(f7, f7, f7);
        GL11.glRotatef(50F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(335F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        float f8 = 0.0625F;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, f1, f3);
        tessellator.addVertexWithUV(f4, 0.0D, 0.0D, f, f3);
        tessellator.addVertexWithUV(f4, 1.0D, 0.0D, f, f2);
        tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, f1, f2);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        tessellator.addVertexWithUV(0.0D, 1.0D, 0.0F - f8, f1, f2);
        tessellator.addVertexWithUV(f4, 1.0D, 0.0F - f8, f, f2);
        tessellator.addVertexWithUV(f4, 0.0D, 0.0F - f8, f, f3);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0F - f8, f1, f3);
        tessellator.draw();
        float f9 = 1.0F / (float)(32 * i);
        float f10 = 1.0F / (float)i;
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        for(int k = 0; k < i; k++)
        {
            float f11 = (float)k / ((float)i * 1.0F);
            float f15 = (f1 + (f - f1) * f11) - f9;
            float f19 = f4 * f11;
            tessellator.addVertexWithUV(f19, 0.0D, 0.0F - f8, f15, f3);
            tessellator.addVertexWithUV(f19, 0.0D, 0.0D, f15, f3);
            tessellator.addVertexWithUV(f19, 1.0D, 0.0D, f15, f2);
            tessellator.addVertexWithUV(f19, 1.0D, 0.0F - f8, f15, f2);
        }

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        for(int l = 0; l < i; l++)
        {
            float f12 = (float)l / ((float)i * 1.0F);
            float f16 = (f1 + (f - f1) * f12) - f9;
            float f20 = f4 * f12 + f10;
            tessellator.addVertexWithUV(f20, 1.0D, 0.0F - f8, f16, f2);
            tessellator.addVertexWithUV(f20, 1.0D, 0.0D, f16, f2);
            tessellator.addVertexWithUV(f20, 0.0D, 0.0D, f16, f3);
            tessellator.addVertexWithUV(f20, 0.0D, 0.0F - f8, f16, f3);
        }

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        for(int i1 = 0; i1 < i; i1++)
        {
            float f13 = (float)i1 / ((float)i * 1.0F);
            float f17 = (f3 + (f2 - f3) * f13) - f9;
            float f21 = f4 * f13 + f10;
            tessellator.addVertexWithUV(0.0D, f21, 0.0D, f1, f17);
            tessellator.addVertexWithUV(f4, f21, 0.0D, f, f17);
            tessellator.addVertexWithUV(f4, f21, 0.0F - f8, f, f17);
            tessellator.addVertexWithUV(0.0D, f21, 0.0F - f8, f1, f17);
        }

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        for(int j1 = 0; j1 < i; j1++)
        {
            float f14 = (float)j1 / ((float)i * 1.0F);
            float f18 = (f3 + (f2 - f3) * f14) - f9;
            float f22 = f4 * f14;
            tessellator.addVertexWithUV(f4, f22, 0.0D, f, f18);
            tessellator.addVertexWithUV(0.0D, f22, 0.0D, f1, f18);
            tessellator.addVertexWithUV(0.0D, f22, 0.0F - f8, f1, f18);
            tessellator.addVertexWithUV(f4, f22, 0.0F - f8, f, f18);
        }

        tessellator.draw();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }

    private Minecraft minecraft;
}
