// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.Random;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            Config, TexturePackList, TexturePackBase, RenderChicken, 
//            RenderEngine, GameSettings, PositionTextureVertex, ChatAllowedCharacters

public class FontRenderer
{

    public FontRenderer(GameSettings gamesettings, String s, RenderEngine renderengine)
    {
        charTexIds = new int[256];
        imgWidth = 128;
        imgHeight = 128;
        charWidth = 8;
        charHeight = 8;
        renderViewEntity = new int[32];
        charTexWidths = new byte[256];
        charPixelWidths = new byte[256];
        unicodeWidth = new byte[0x10000];
        random = new Random();
        tex = renderengine;
        textureName = s;
        gameSettings = gamesettings;
        init();
    }

    private void init()
    {
        BufferedImage bufferedimage;
        try
        {
            if(Config.getMinecraft() != null)
            {
                bufferedimage = ImageIO.read(Config.getMinecraft().texturePackList.selectedTexturePack.getResourceAsStream(textureName));
                InputStream inputstream = Config.getMinecraft().texturePackList.selectedTexturePack.getResourceAsStream("/font/glyph_sizes.bin");
                if(inputstream != null)
                {
                    inputstream.read(unicodeWidth);
                }
            } else
            {
                bufferedimage = ImageIO.read((net.minecraft.src.RenderChicken.class).getResourceAsStream(textureName));
                InputStream inputstream1 = (net.minecraft.src.RenderChicken.class).getResourceAsStream("/font/glyph_sizes.bin");
                if(inputstream1 != null)
                {
                    inputstream1.read(unicodeWidth);
                }
            }
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
        imgWidth = bufferedimage.getWidth();
        imgHeight = bufferedimage.getHeight();
        charWidth = imgWidth / 16;
        charHeight = imgHeight / 16;
        int ai[] = new int[imgWidth * imgHeight];
        bufferedimage.getRGB(0, 0, imgWidth, imgHeight, ai, 0, imgWidth);
        for(int i = 0; i < 256; i++)
        {
            int k = i % 16;
            int i1 = i / 16;
            int k1 = charWidth - 1;
            do
            {
                if(k1 < 0)
                {
                    break;
                }
                int i2 = k * charWidth + k1;
                boolean flag = true;
                for(int l2 = 0; l2 < charHeight && flag; l2++)
                {
                    int j3 = ai[i2 + (i1 * charHeight + l2) * imgWidth] & 0xff;
                    if(j3 > 0)
                    {
                        flag = false;
                    }
                }

                if(!flag)
                {
                    break;
                }
                k1--;
            } while(true);
            if(i == 32)
            {
                k1 = 2;
            }
            charTexWidths[i] = (byte)(k1 + 2);
            charPixelWidths[i] = (byte)(((k1 + 2) * 128) / imgWidth);
        }

        basicTexID = tex.allocateAndSetupTexture(bufferedimage);
        for(int j = 0; j < 32; j++)
        {
            int l = (j >> 3 & 1) * 85;
            int j1 = (j >> 2 & 1) * 170 + l;
            int l1 = (j >> 1 & 1) * 170 + l;
            int j2 = (j >> 0 & 1) * 170 + l;
            if(j == 6)
            {
                j1 += 85;
            }
            if(gameSettings.anaglyph)
            {
                int k2 = (j1 * 30 + l1 * 59 + j2 * 11) / 100;
                int i3 = (j1 * 30 + l1 * 70) / 100;
                int k3 = (j1 * 30 + j2 * 70) / 100;
                j1 = k2;
                l1 = i3;
                j2 = k3;
            }
            if(j >= 16)
            {
                j1 /= 4;
                l1 /= 4;
                j2 /= 4;
            }
            renderViewEntity[j] = (j1 & 0xff) << 16 | (l1 & 0xff) << 8 | j2 & 0xff;
        }

    }

    private void convertMapFormat(int i)
    {
        float f = (i % 16) * charWidth;
        float f1 = (i / 16) * charHeight;
        if(lastBoundTexID != basicTexID)
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, basicTexID);
            lastBoundTexID = basicTexID;
        }
        float f2 = (float)charTexWidths[i] - 0.01F;
        float f3 = charPixelWidths[i];
        GL11.glBegin(5);
        GL11.glTexCoord2f(f / (float)imgWidth, f1 / (float)imgHeight);
        GL11.glVertex3f(xPos, yPos, 0.0F);
        GL11.glTexCoord2f(f / (float)imgWidth, (f1 + (float)charHeight) / (float)imgHeight);
        GL11.glVertex3f(xPos, yPos + 8F, 0.0F);
        GL11.glTexCoord2f((f + f2) / (float)imgWidth, f1 / (float)imgHeight);
        GL11.glVertex3f(xPos + f3, yPos, 0.0F);
        GL11.glTexCoord2f((f + f2) / (float)imgWidth, (f1 + (float)charHeight) / (float)imgHeight);
        GL11.glVertex3f(xPos + f3, yPos + 8F, 0.0F);
        GL11.glEnd();
        xPos += f3;
    }

    private void lineIsCommand(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        (new Formatter(stringbuilder)).format("/font/glyph_%02X.png", new Object[] {
            Integer.valueOf(i)
        });
        BufferedImage bufferedimage;
        try
        {
            if(Config.getMinecraft() != null)
            {
                bufferedimage = ImageIO.read(Config.getMinecraft().texturePackList.selectedTexturePack.getResourceAsStream(stringbuilder.toString()));
            } else
            {
                bufferedimage = ImageIO.read((net.minecraft.src.PositionTextureVertex.class).getResourceAsStream(stringbuilder.toString()));
            }
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
        charTexIds[i] = tex.allocateAndSetupTexture(bufferedimage);
        lastBoundTexID = charTexIds[i];
    }

    private String isFancyGraphicsEnabled(int i)
    {
        char ac[] = new char[i];
        while(i-- != 0) 
        {
            int j = random.nextInt() & 0xf;
            if(j == 0)
            {
                ac[i] = '\247';
            } else
            {
                ac[i] = (char)(48 + j);
            }
        }
        return new String(ac);
    }

    private void getSaveLoader(char c)
    {
        if(unicodeWidth[c] == 0)
        {
            return;
        }
        int i = c / 256;
        if(charTexIds[i] == 0)
        {
            lineIsCommand(i);
        }
        if(lastBoundTexID != charTexIds[i])
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, charTexIds[i]);
            lastBoundTexID = charTexIds[i];
        }
        int j = unicodeWidth[c] >> 4;
        int k = unicodeWidth[c] & 0xf;
        float f;
        float f1;
        if(k > 7)
        {
            f1 = 16F;
            f = 0.0F;
        } else
        {
            f1 = k + 1;
            f = j;
        }
        float f2 = (float)((c % 16) * 16) + f;
        float f3 = ((c & 0xff) / 16) * 16;
        float f4 = f1 - f - 0.02F;
        GL11.glBegin(5);
        GL11.glTexCoord2f(f2 / 256F, f3 / 256F);
        GL11.glVertex3f(xPos, yPos, 0.0F);
        GL11.glTexCoord2f(f2 / 256F, (f3 + 15.98F) / 256F);
        GL11.glVertex3f(xPos, yPos + 7.99F, 0.0F);
        GL11.glTexCoord2f((f2 + f4) / 256F, f3 / 256F);
        GL11.glVertex3f(xPos + f4 / 2.0F, yPos, 0.0F);
        GL11.glTexCoord2f((f2 + f4) / 256F, (f3 + 15.98F) / 256F);
        GL11.glVertex3f(xPos + f4 / 2.0F, yPos + 7.99F, 0.0F);
        GL11.glEnd();
        xPos += (f1 - f) / 2.0F + 1.0F;
    }

    private void renderStringImpl(String s, boolean flag)
    {
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c == '\247' && i + 1 < s.length())
            {
                int j = "0123456789abcdef".indexOf(s.toLowerCase().charAt(i + 1));
                if(j < 0 || j > 15)
                {
                    j = 15;
                }
                if(flag)
                {
                    j += 16;
                }
                int l = renderViewEntity[j];
                GL11.glColor3f((float)(l >> 16) / 255F, (float)(l >> 8 & 0xff) / 255F, (float)(l & 0xff) / 255F);
                i++;
                continue;
            }
            int k = ChatAllowedCharacters.allowedCharacters.indexOf(c);
            if(c == ' ')
            {
                xPos += 4F;
                continue;
            }
            if(k > 0)
            {
                convertMapFormat(k + 32);
            } else
            {
                getSaveLoader(c);
            }
        }

    }

    private void renderString(String s, int i, int j, int k, boolean flag)
    {
        checkUpdated();
        if(s != null)
        {
            lastBoundTexID = 0;
            if((k & 0xff000000) == 0)
            {
                k |= 0xff000000;
            }
            if(flag)
            {
                k = (k & 0xfcfcfc) >> 2 | k & 0xff000000;
            }
            GL11.glColor4f((float)(k >> 16 & 0xff) / 255F, (float)(k >> 8 & 0xff) / 255F, (float)(k & 0xff) / 255F, (float)(k >> 24 & 0xff) / 255F);
            xPos = i;
            yPos = j;
            renderStringImpl(s, flag);
        }
    }

    public void drawStringWithShadow(String s, int i, int j, int k)
    {
        renderString(s, i + 1, j + 1, k, true);
        renderString(s, i, j, k, false);
    }

    public void drawString(String s, int i, int j, int k)
    {
        renderString(s, i, j, k, false);
    }

    public int getStringWidth(String s)
    {
        checkUpdated();
        if(s == null)
        {
            return 0;
        }
        int i = 0;
        for(int j = 0; j < s.length(); j++)
        {
            char c = s.charAt(j);
            if(c == '\247')
            {
                j++;
                continue;
            }
            int k = ChatAllowedCharacters.allowedCharacters.indexOf(c);
            if(k >= 0)
            {
                i += charPixelWidths[k + 32];
                continue;
            }
            if(unicodeWidth[c] == 0)
            {
                continue;
            }
            int l = unicodeWidth[c] >> 4;
            int i1 = unicodeWidth[c] & 0xf;
            if(i1 > 7)
            {
                i1 = 15;
                l = 0;
            }
            i1++;
            i += (i1 - l) / 2 + 1;
        }

        return i;
    }

    private void checkUpdated()
    {
        if(Config.isFontRendererUpdated())
        {
            return;
        } else
        {
            init();
            Config.setFontRendererUpdated(true);
            return;
        }
    }

    public void func_27278_a(String s, int i, int j, int k, int l)
    {
        checkUpdated();
        String as[] = s.split("\n");
        if(as.length > 1)
        {
            for(int i1 = 0; i1 < as.length; i1++)
            {
                func_27278_a(as[i1], i, j, k, l);
                j += func_27277_a(as[i1], k);
            }

            return;
        }
        String as1[] = s.split(" ");
        int j1 = 0;
        do
        {
            if(j1 >= as1.length)
            {
                break;
            }
            String s1;
            for(s1 = (new StringBuilder()).append(as1[j1++]).append(" ").toString(); j1 < as1.length && getStringWidth((new StringBuilder()).append(s1).append(as1[j1]).toString()) < k; s1 = (new StringBuilder()).append(s1).append(as1[j1++]).append(" ").toString()) { }
            int k1;
            for(; getStringWidth(s1) > k; s1 = s1.substring(k1))
            {
                for(k1 = 0; getStringWidth(s1.substring(0, k1 + 1)) <= k; k1++) { }
                if(s1.substring(0, k1).trim().length() > 0)
                {
                    drawString(s1.substring(0, k1), i, j, l);
                    j += charHeight;
                }
            }

            if(s1.trim().length() > 0)
            {
                drawString(s1, i, j, l);
                j += charHeight;
            }
        } while(true);
    }

    public int func_27277_a(String s, int i)
    {
        checkUpdated();
        String as[] = s.split("\n");
        if(as.length > 1)
        {
            int j = 0;
            for(int k = 0; k < as.length; k++)
            {
                j += func_27277_a(as[k], i);
            }

            return j;
        }
        String as1[] = s.split(" ");
        int l = 0;
        int i1 = 0;
        do
        {
            if(l >= as1.length)
            {
                break;
            }
            String s1;
            for(s1 = (new StringBuilder()).append(as1[l++]).append(" ").toString(); l < as1.length && getStringWidth((new StringBuilder()).append(s1).append(as1[l]).toString()) < i; s1 = (new StringBuilder()).append(s1).append(as1[l++]).append(" ").toString()) { }
            int j1;
            for(; getStringWidth(s1) > i; s1 = s1.substring(j1))
            {
                for(j1 = 0; getStringWidth(s1.substring(0, j1 + 1)) <= i; j1++) { }
                if(s1.substring(0, j1).trim().length() > 0)
                {
                    i1 += charHeight;
                }
            }

            if(s1.trim().length() > 0)
            {
                i1 += charHeight;
            }
        } while(true);
        if(i1 < 8)
        {
            i1 += charHeight;
        }
        return i1;
    }

    private int renderViewEntity[];
    private byte charTexWidths[];
    private byte charPixelWidths[];
    private byte unicodeWidth[];
    private int charTexIds[];
    private int basicTexID;
    private int lastBoundTexID;
    private RenderEngine tex;
    private float xPos;
    private float yPos;
    private Random random;
    private int imgWidth;
    private int imgHeight;
    private int charWidth;
    private int charHeight;
    private String textureName;
    private GameSettings gameSettings;
}
