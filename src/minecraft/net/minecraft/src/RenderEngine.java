// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

// Referenced classes of package net.minecraft.src:
//            GLAllocation, TexturePackList, TexturePackBase, Config, 
//            GameSettings, ThreadDownloadImageData, TextureFX, TextureHDFX, 
//            Block, BlockPortal, BlockFire, TexturePackDefault, 
//            TextureHDLavaFX, TextureHDWaterFX, TextureHDPortalFX, TextureHDCompassFX, 
//            TextureHDWatchFX, TextureHDWaterFlowFX, TextureHDLavaFlowFX, TextureHDFlamesFX, 
//            ImageBuffer

public class RenderEngine
{

    public RenderEngine(TexturePackList texturepacklist, GameSettings gamesettings)
    {
        terrainTextureId = -1;
        guiItemsTextureId = -1;
        hdTexturesInstalled = false;
        textureDimensionsMap = new HashMap();
        textureDataMap = new HashMap();
        tickCounter = 0;
        dynamicTexturesUpdated = false;
        textureMap = new HashMap();
        field_28151_c = new HashMap();
        textureNameToImageMap = new HashMap();
        singleIntBuffer = GLAllocation.createDirectIntBuffer(1);
        allocateImageData(256);
        textureList = new ArrayList();
        urlToImageDataMap = new HashMap();
        clampTexture = false;
        blurTexture = false;
        missingTextureImage = new BufferedImage(64, 64, 2);
        texturePack = texturepacklist;
        options = gamesettings;
        Graphics g = missingTextureImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 64, 64);
        g.setColor(Color.BLACK);
        g.drawString("missingtex", 1, 10);
        g.dispose();
    }

    public int[] func_28149_a(String s)
    {
        TexturePackBase texturepackbase = texturePack.selectedTexturePack;
        int ai[] = (int[])field_28151_c.get(s);
        if(ai != null)
        {
            return ai;
        }
        try
        {
            int ai1[] = null;
            if(s.startsWith("##"))
            {
                ai1 = func_28148_b(unwrapImageByColumns(readTextureImage(texturepackbase.getResourceAsStream(s.substring(2)))));
            } else
            if(s.startsWith("%clamp%"))
            {
                clampTexture = true;
                ai1 = func_28148_b(readTextureImage(texturepackbase.getResourceAsStream(s.substring(7))));
                clampTexture = false;
            } else
            if(s.startsWith("%blur%"))
            {
                blurTexture = true;
                ai1 = func_28148_b(readTextureImage(texturepackbase.getResourceAsStream(s.substring(6))));
                blurTexture = false;
            } else
            {
                InputStream inputstream = texturepackbase.getResourceAsStream(s);
                if(inputstream == null)
                {
                    ai1 = func_28148_b(missingTextureImage);
                } else
                {
                    ai1 = func_28148_b(readTextureImage(inputstream));
                }
            }
            field_28151_c.put(s, ai1);
            return ai1;
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        int ai2[] = func_28148_b(missingTextureImage);
        field_28151_c.put(s, ai2);
        return ai2;
    }

    private int[] func_28148_b(BufferedImage bufferedimage)
    {
        int i = bufferedimage.getWidth();
        int j = bufferedimage.getHeight();
        int ai[] = new int[i * j];
        bufferedimage.getRGB(0, 0, i, j, ai, 0, i);
        return ai;
    }

    private int[] func_28147_a(BufferedImage bufferedimage, int ai[])
    {
        int i = bufferedimage.getWidth();
        int j = bufferedimage.getHeight();
        bufferedimage.getRGB(0, 0, i, j, ai, 0, i);
        return ai;
    }

    public int getTexture(String s)
    {
        TexturePackBase texturepackbase = texturePack.selectedTexturePack;
        Integer integer = (Integer)textureMap.get(s);
        if(integer != null)
        {
            return integer.intValue();
        }
        try
        {
            singleIntBuffer.clear();
            GLAllocation.generateTextureNames(singleIntBuffer);
            int i = singleIntBuffer.get(0);
            if(s.startsWith("##"))
            {
                setupTexture(unwrapImageByColumns(readTextureImage(texturepackbase.getResourceAsStream(s.substring(2)))), i);
            } else
            if(s.startsWith("%clamp%"))
            {
                clampTexture = true;
                setupTexture(readTextureImage(texturepackbase.getResourceAsStream(s.substring(7))), i);
                clampTexture = false;
            } else
            if(s.startsWith("%blur%"))
            {
                blurTexture = true;
                setupTexture(readTextureImage(texturepackbase.getResourceAsStream(s.substring(6))), i);
                blurTexture = false;
            } else
            {
                InputStream inputstream = texturepackbase.getResourceAsStream(s);
                if(inputstream == null)
                {
                    setupTexture(missingTextureImage, i);
                } else
                {
                    if(s.equals("/terrain.png"))
                    {
                        terrainTextureId = i;
                    }
                    if(s.equals("/gui/items.png"))
                    {
                        guiItemsTextureId = i;
                    }
                    setupTexture(readTextureImage(inputstream), i);
                }
            }
            textureMap.put(s, Integer.valueOf(i));
            return i;
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        GLAllocation.generateTextureNames(singleIntBuffer);
        int j = singleIntBuffer.get(0);
        setupTexture(missingTextureImage, j);
        textureMap.put(s, Integer.valueOf(j));
        return j;
    }

    private BufferedImage unwrapImageByColumns(BufferedImage bufferedimage)
    {
        int i = bufferedimage.getWidth() / 16;
        BufferedImage bufferedimage1 = new BufferedImage(16, bufferedimage.getHeight() * i, 2);
        Graphics g = bufferedimage1.getGraphics();
        for(int j = 0; j < i; j++)
        {
            g.drawImage(bufferedimage, -j * 16, j * bufferedimage.getHeight(), null);
        }

        g.dispose();
        return bufferedimage1;
    }

    public int allocateAndSetupTexture(BufferedImage bufferedimage)
    {
        singleIntBuffer.clear();
        GLAllocation.generateTextureNames(singleIntBuffer);
        int i = singleIntBuffer.get(0);
        setupTexture(bufferedimage, i);
        textureNameToImageMap.put(Integer.valueOf(i), bufferedimage);
        return i;
    }

    public void setupTexture(BufferedImage bufferedimage, int i)
    {
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, i);
        useMipmaps = Config.isUseMipmaps();
        if(useMipmaps && i != guiItemsTextureId)
        {
            int j = Config.getMipmapType();
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10241 /*GL_TEXTURE_MIN_FILTER*/, j);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10240 /*GL_TEXTURE_MAG_FILTER*/, 9728 /*GL_NEAREST*/);
            if(GLContext.getCapabilities().OpenGL12)
            {
                GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 33084 /*GL_TEXTURE_BASE_LEVEL*/, 0);
                int l = Config.getMipmapLevel();
                if(l >= 4)
                {
                    int j1 = Math.min(bufferedimage.getWidth(), bufferedimage.getHeight());
                    l = getMaxMipmapLevel(j1) - 4;
                    if(l < 0)
                    {
                        l = 0;
                    }
                }
                GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 33085 /*GL_TEXTURE_MAX_LEVEL*/, l);
            }
        } else
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10241 /*GL_TEXTURE_MIN_FILTER*/, 9728 /*GL_NEAREST*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10240 /*GL_TEXTURE_MAG_FILTER*/, 9728 /*GL_NEAREST*/);
        }
        if(blurTexture)
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10241 /*GL_TEXTURE_MIN_FILTER*/, 9729 /*GL_LINEAR*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10240 /*GL_TEXTURE_MAG_FILTER*/, 9729 /*GL_LINEAR*/);
        }
        if(clampTexture)
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10242 /*GL_TEXTURE_WRAP_S*/, 10496 /*GL_CLAMP*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10243 /*GL_TEXTURE_WRAP_T*/, 10496 /*GL_CLAMP*/);
        } else
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10242 /*GL_TEXTURE_WRAP_S*/, 10497 /*GL_REPEAT*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10243 /*GL_TEXTURE_WRAP_T*/, 10497 /*GL_REPEAT*/);
        }
        int k = bufferedimage.getWidth();
        int i1 = bufferedimage.getHeight();
        setTextureDimension(i, new Dimension(k, i1));
        int ai[] = new int[k * i1];
        byte abyte0[] = new byte[k * i1 * 4];
        bufferedimage.getRGB(0, 0, k, i1, ai, 0, k);
        for(int k1 = 0; k1 < ai.length; k1++)
        {
            int l1 = ai[k1] >> 24 & 0xff;
            int i2 = ai[k1] >> 16 & 0xff;
            int j2 = ai[k1] >> 8 & 0xff;
            int k2 = ai[k1] & 0xff;
            if(options != null && options.anaglyph)
            {
                int l2 = (i2 * 30 + j2 * 59 + k2 * 11) / 100;
                int i3 = (i2 * 30 + j2 * 70) / 100;
                int j3 = (i2 * 30 + k2 * 70) / 100;
                i2 = l2;
                j2 = i3;
                k2 = j3;
            }
            if(l1 == 0)
            {
                i2 = 255;
                j2 = 255;
                k2 = 255;
            }
            abyte0[k1 * 4 + 0] = (byte)i2;
            abyte0[k1 * 4 + 1] = (byte)j2;
            abyte0[k1 * 4 + 2] = (byte)k2;
            abyte0[k1 * 4 + 3] = (byte)l1;
        }

        checkImageDataSize(k);
        imageData.clear();
        imageData.put(abyte0);
        imageData.position(0).limit(abyte0.length);
        GL11.glTexImage2D(3553 /*GL_TEXTURE_2D*/, 0, 6408 /*GL_RGBA*/, k, i1, 0, 6408 /*GL_RGBA*/, 5121 /*GL_UNSIGNED_BYTE*/, imageData);
        if(useMipmaps)
        {
            generateMipMaps(imageData, k, i1);
        }
    }

    private void generateMipMaps(ByteBuffer bytebuffer, int i, int j)
    {
        ByteBuffer bytebuffer1 = bytebuffer;
        int k = 1;
        do
        {
            if(k > 16)
            {
                break;
            }
            int l = i >> k - 1;
            int i1 = i >> k;
            int j1 = j >> k;
            if(i1 <= 0 || j1 <= 0)
            {
                break;
            }
            ByteBuffer bytebuffer2 = mipImageDatas[k - 1];
            for(int k1 = 0; k1 < i1; k1++)
            {
                for(int l1 = 0; l1 < j1; l1++)
                {
                    int i2 = bytebuffer1.getInt((k1 * 2 + 0 + (l1 * 2 + 0) * l) * 4);
                    int j2 = bytebuffer1.getInt((k1 * 2 + 1 + (l1 * 2 + 0) * l) * 4);
                    int k2 = bytebuffer1.getInt((k1 * 2 + 1 + (l1 * 2 + 1) * l) * 4);
                    int l2 = bytebuffer1.getInt((k1 * 2 + 0 + (l1 * 2 + 1) * l) * 4);
                    int i3 = weightedAverageColor(i2, j2, k2, l2);
                    bytebuffer2.putInt((k1 + l1 * i1) * 4, i3);
                }

            }

            GL11.glTexImage2D(3553 /*GL_TEXTURE_2D*/, k, 6408 /*GL_RGBA*/, i1, j1, 0, 6408 /*GL_RGBA*/, 5121 /*GL_UNSIGNED_BYTE*/, bytebuffer2);
            bytebuffer1 = bytebuffer2;
            k++;
        } while(true);
    }

    public void func_28150_a(int ai[], int i, int j, int k)
    {
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, k);
        if(useMipmaps)
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10241 /*GL_TEXTURE_MIN_FILTER*/, 9986 /*GL_NEAREST_MIPMAP_LINEAR*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10240 /*GL_TEXTURE_MAG_FILTER*/, 9728 /*GL_NEAREST*/);
        } else
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10241 /*GL_TEXTURE_MIN_FILTER*/, 9728 /*GL_NEAREST*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10240 /*GL_TEXTURE_MAG_FILTER*/, 9728 /*GL_NEAREST*/);
        }
        if(blurTexture)
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10241 /*GL_TEXTURE_MIN_FILTER*/, 9729 /*GL_LINEAR*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10240 /*GL_TEXTURE_MAG_FILTER*/, 9729 /*GL_LINEAR*/);
        }
        if(clampTexture)
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10242 /*GL_TEXTURE_WRAP_S*/, 10496 /*GL_CLAMP*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10243 /*GL_TEXTURE_WRAP_T*/, 10496 /*GL_CLAMP*/);
        } else
        {
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10242 /*GL_TEXTURE_WRAP_S*/, 10497 /*GL_REPEAT*/);
            GL11.glTexParameteri(3553 /*GL_TEXTURE_2D*/, 10243 /*GL_TEXTURE_WRAP_T*/, 10497 /*GL_REPEAT*/);
        }
        byte abyte0[] = new byte[i * j * 4];
        for(int l = 0; l < ai.length; l++)
        {
            int i1 = ai[l] >> 24 & 0xff;
            int j1 = ai[l] >> 16 & 0xff;
            int k1 = ai[l] >> 8 & 0xff;
            int l1 = ai[l] & 0xff;
            if(options != null && options.anaglyph)
            {
                int i2 = (j1 * 30 + k1 * 59 + l1 * 11) / 100;
                int j2 = (j1 * 30 + k1 * 70) / 100;
                int k2 = (j1 * 30 + l1 * 70) / 100;
                j1 = i2;
                k1 = j2;
                l1 = k2;
            }
            abyte0[l * 4 + 0] = (byte)j1;
            abyte0[l * 4 + 1] = (byte)k1;
            abyte0[l * 4 + 2] = (byte)l1;
            abyte0[l * 4 + 3] = (byte)i1;
        }

        imageData.clear();
        imageData.put(abyte0);
        imageData.position(0).limit(abyte0.length);
        GL11.glTexSubImage2D(3553 /*GL_TEXTURE_2D*/, 0, 0, 0, i, j, 6408 /*GL_RGBA*/, 5121 /*GL_UNSIGNED_BYTE*/, imageData);
    }

    public void deleteTexture(int i)
    {
        textureNameToImageMap.remove(Integer.valueOf(i));
        singleIntBuffer.clear();
        singleIntBuffer.put(i);
        singleIntBuffer.flip();
        GL11.glDeleteTextures(singleIntBuffer);
    }

    public int getTextureForDownloadableImage(String s, String s1)
    {
        ThreadDownloadImageData threaddownloadimagedata = (ThreadDownloadImageData)urlToImageDataMap.get(s);
        if(threaddownloadimagedata != null && threaddownloadimagedata.image != null && !threaddownloadimagedata.textureSetupComplete)
        {
            if(threaddownloadimagedata.textureName < 0)
            {
                threaddownloadimagedata.textureName = allocateAndSetupTexture(threaddownloadimagedata.image);
            } else
            {
                setupTexture(threaddownloadimagedata.image, threaddownloadimagedata.textureName);
            }
            threaddownloadimagedata.textureSetupComplete = true;
        }
        if(threaddownloadimagedata == null || threaddownloadimagedata.textureName < 0)
        {
            if(s1 == null)
            {
                return -1;
            } else
            {
                return getTexture(s1);
            }
        } else
        {
            return threaddownloadimagedata.textureName;
        }
    }

    public ThreadDownloadImageData obtainImageData(String s, ImageBuffer imagebuffer)
    {
        ThreadDownloadImageData threaddownloadimagedata = (ThreadDownloadImageData)urlToImageDataMap.get(s);
        if(threaddownloadimagedata == null)
        {
            urlToImageDataMap.put(s, new ThreadDownloadImageData(s, imagebuffer));
        } else
        {
            threaddownloadimagedata.referenceCount++;
        }
        return threaddownloadimagedata;
    }

    public void releaseImageData(String s)
    {
        ThreadDownloadImageData threaddownloadimagedata = (ThreadDownloadImageData)urlToImageDataMap.get(s);
        if(threaddownloadimagedata != null)
        {
            threaddownloadimagedata.referenceCount--;
            if(threaddownloadimagedata.referenceCount == 0)
            {
                if(threaddownloadimagedata.textureName >= 0)
                {
                    deleteTexture(threaddownloadimagedata.textureName);
                }
                urlToImageDataMap.remove(s);
            }
        }
    }

    public void registerTextureFX(TextureFX texturefx)
    {
        for(int i = 0; i < textureList.size(); i++)
        {
            TextureFX texturefx1 = (TextureFX)textureList.get(i);
            if(texturefx1.tileImage == texturefx.tileImage && texturefx1.iconIndex == texturefx.iconIndex)
            {
                textureList.remove(i);
                i--;
                Config.dbg((new StringBuilder()).append("Texture removed: ").append(texturefx1).append(", image: ").append(texturefx1.tileImage).append(", index: ").append(texturefx1.iconIndex).toString());
            }
        }

        textureList.add(texturefx);
        texturefx.onTick();
        Config.dbg((new StringBuilder()).append("Texture registered: ").append(texturefx).append(", image: ").append(texturefx.tileImage).append(", index: ").append(texturefx.iconIndex).toString());
        dynamicTexturesUpdated = false;
    }

    private void generateMipMapsSub(int i, int j, int k, int l, ByteBuffer bytebuffer, int i1, boolean flag)
    {
        ByteBuffer bytebuffer1 = bytebuffer;
        int j1 = 1;
        do
        {
            if(j1 > 16)
            {
                break;
            }
            int k1 = k >> j1 - 1;
            int l1 = k >> j1;
            int i2 = l >> j1;
            int j2 = i >> j1;
            int k2 = j >> j1;
            if(l1 <= 0 || i2 <= 0)
            {
                break;
            }
            ByteBuffer bytebuffer2 = mipImageDatas[j1 - 1];
            for(int l2 = 0; l2 < l1; l2++)
            {
                for(int j3 = 0; j3 < i2; j3++)
                {
                    int l3 = bytebuffer1.getInt((l2 * 2 + 0 + (j3 * 2 + 0) * k1) * 4);
                    int j4 = bytebuffer1.getInt((l2 * 2 + 1 + (j3 * 2 + 0) * k1) * 4);
                    int l4 = bytebuffer1.getInt((l2 * 2 + 1 + (j3 * 2 + 1) * k1) * 4);
                    int i5 = bytebuffer1.getInt((l2 * 2 + 0 + (j3 * 2 + 1) * k1) * 4);
                    int j5;
                    if(flag)
                    {
                        j5 = averageColor(averageColor(l3, j4), averageColor(l4, i5));
                    } else
                    {
                        j5 = weightedAverageColor(l3, j4, l4, i5);
                    }
                    bytebuffer2.putInt((l2 + j3 * l1) * 4, j5);
                }

            }

            for(int i3 = 0; i3 < i1; i3++)
            {
                for(int k3 = 0; k3 < i1; k3++)
                {
                    int i4 = i3 * l1;
                    int k4 = k3 * i2;
                    GL11.glTexSubImage2D(3553 /*GL_TEXTURE_2D*/, j1, j2 + i4, k2 + k4, l1, i2, 6408 /*GL_RGBA*/, 5121 /*GL_UNSIGNED_BYTE*/, bytebuffer2);
                }

            }

            bytebuffer1 = bytebuffer2;
            j1++;
        } while(true);
    }

    public void updateDynamicTextures()
    {
        checkHdTextures();
        tickCounter++;
        terrainTextureId = getTexture("/terrain.png");
        guiItemsTextureId = getTexture("/gui/items.png");
label0:
        for(int i = 0; i < textureList.size(); i++)
        {
            TextureFX texturefx = (TextureFX)textureList.get(i);
            texturefx.anaglyphEnabled = options.anaglyph;
            if(texturefx.getClass().getName().equals("ModTextureStatic") && dynamicTexturesUpdated)
            {
                continue;
            }
            int k = 0;
            if(texturefx.tileImage == 0)
            {
                k = terrainTextureId;
            } else
            {
                k = guiItemsTextureId;
            }
            Dimension dimension = getTextureDimensions(k);
            if(dimension == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown dimensions for texture id: ").append(k).toString());
            }
            int l = dimension.width / 16;
            int i1 = dimension.height / 16;
            checkImageDataSize(dimension.width);
            imageData.limit(0);
            boolean flag = updateCustomTexture(texturefx, imageData, dimension.width / 16);
            if(flag && imageData.limit() <= 0)
            {
                continue;
            }
            if(imageData.limit() <= 0)
            {
                boolean flag1 = updateDefaultTexture(texturefx, imageData, dimension.width / 16);
                if(flag1 && imageData.limit() <= 0)
                {
                    continue;
                }
            }
            if(imageData.limit() <= 0)
            {
                texturefx.onTick();
                if(texturefx.imageData == null)
                {
                    continue;
                }
                int j1 = l * i1 * 4;
                if(texturefx.imageData.length == j1)
                {
                    imageData.clear();
                    imageData.put(texturefx.imageData);
                    imageData.position(0).limit(texturefx.imageData.length);
                } else
                {
                    copyScaled(texturefx.imageData, imageData, l);
                }
            }
            texturefx.bindImage(this);
            boolean flag2 = scalesWithFastColor(texturefx);
            int k1 = 0;
            do
            {
                if(k1 >= texturefx.tileSize)
                {
                    continue label0;
                }
                for(int l1 = 0; l1 < texturefx.tileSize; l1++)
                {
                    int i2 = (texturefx.iconIndex % 16) * l + k1 * l;
                    int j2 = (texturefx.iconIndex / 16) * i1 + l1 * i1;
                    GL11.glTexSubImage2D(3553 /*GL_TEXTURE_2D*/, 0, i2, j2, l, i1, 6408 /*GL_RGBA*/, 5121 /*GL_UNSIGNED_BYTE*/, imageData);
                    if(useMipmaps && k1 == 0 && l1 == 0)
                    {
                        generateMipMapsSub(i2, j2, l, i1, imageData, texturefx.tileSize, flag2);
                    }
                }

                k1++;
            } while(true);
        }

        dynamicTexturesUpdated = true;
        for(int j = 0; j < textureList.size(); j++)
        {
            TextureFX texturefx1 = (TextureFX)textureList.get(j);
            if(texturefx1.textureId <= 0)
            {
                continue;
            }
            imageData.clear();
            imageData.put(texturefx1.imageData);
            imageData.position(0).limit(texturefx1.imageData.length);
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, texturefx1.textureId);
            GL11.glTexSubImage2D(3553 /*GL_TEXTURE_2D*/, 0, 0, 0, 16, 16, 6408 /*GL_RGBA*/, 5121 /*GL_UNSIGNED_BYTE*/, imageData);
            if(useMipmaps)
            {
                generateMipMapsSub(0, 0, 16, 16, imageData, texturefx1.tileSize, false);
            }
        }

    }

    private int averageColor(int i, int j)
    {
        int k = (i & 0xff000000) >> 24 & 0xff;
        int l = (j & 0xff000000) >> 24 & 0xff;
        return ((k + l >> 1) << 24) + ((i & 0xfefefe) + (j & 0xfefefe) >> 1);
    }

    private int weightedAverageColor(int i, int j, int k, int l)
    {
        int i1 = weightedAverageColor(i, j);
        int j1 = weightedAverageColor(k, l);
        int k1 = weightedAverageColor(i1, j1);
        return k1;
    }

    private int weightedAverageColor(int i, int j)
    {
        int k = (i & 0xff000000) >> 24 & 0xff;
        int l = (j & 0xff000000) >> 24 & 0xff;
        int i1 = (k + l) / 2;
        if(k == 0 && l == 0)
        {
            k = 1;
            l = 1;
        } else
        {
            if(k == 0)
            {
                i = j;
                i1 /= 2;
            }
            if(l == 0)
            {
                j = i;
                i1 /= 2;
            }
        }
        int j1 = (i >> 16 & 0xff) * k;
        int k1 = (i >> 8 & 0xff) * k;
        int l1 = (i & 0xff) * k;
        int i2 = (j >> 16 & 0xff) * l;
        int j2 = (j >> 8 & 0xff) * l;
        int k2 = (j & 0xff) * l;
        int l2 = (j1 + i2) / (k + l);
        int i3 = (k1 + j2) / (k + l);
        int j3 = (l1 + k2) / (k + l);
        return i1 << 24 | l2 << 16 | i3 << 8 | j3;
    }

    public void refreshTextures()
    {
        textureDataMap.clear();
        dynamicTexturesUpdated = false;
        Config.setFontRendererUpdated(false);
        TexturePackBase texturepackbase = texturePack.selectedTexturePack;
        int i;
        BufferedImage bufferedimage;
        for(Iterator iterator = textureNameToImageMap.keySet().iterator(); iterator.hasNext(); setupTexture(bufferedimage, i))
        {
            i = ((Integer)iterator.next()).intValue();
            bufferedimage = (BufferedImage)textureNameToImageMap.get(Integer.valueOf(i));
        }

        for(Iterator iterator1 = urlToImageDataMap.values().iterator(); iterator1.hasNext();)
        {
            ThreadDownloadImageData threaddownloadimagedata = (ThreadDownloadImageData)iterator1.next();
            threaddownloadimagedata.textureSetupComplete = false;
        }

        for(Iterator iterator2 = textureMap.keySet().iterator(); iterator2.hasNext();)
        {
            String s = (String)iterator2.next();
            try
            {
                BufferedImage bufferedimage1;
                if(s.startsWith("##"))
                {
                    bufferedimage1 = unwrapImageByColumns(readTextureImage(texturepackbase.getResourceAsStream(s.substring(2))));
                } else
                if(s.startsWith("%clamp%"))
                {
                    clampTexture = true;
                    bufferedimage1 = readTextureImage(texturepackbase.getResourceAsStream(s.substring(7)));
                } else
                if(s.startsWith("%blur%"))
                {
                    blurTexture = true;
                    bufferedimage1 = readTextureImage(texturepackbase.getResourceAsStream(s.substring(6)));
                } else
                {
                    bufferedimage1 = readTextureImage(texturepackbase.getResourceAsStream(s));
                }
                int j = ((Integer)textureMap.get(s)).intValue();
                setupTexture(bufferedimage1, j);
                blurTexture = false;
                clampTexture = false;
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }

        for(Iterator iterator3 = field_28151_c.keySet().iterator(); iterator3.hasNext();)
        {
            String s1 = (String)iterator3.next();
            try
            {
                BufferedImage bufferedimage2;
                if(s1.startsWith("##"))
                {
                    bufferedimage2 = unwrapImageByColumns(readTextureImage(texturepackbase.getResourceAsStream(s1.substring(2))));
                } else
                if(s1.startsWith("%clamp%"))
                {
                    clampTexture = true;
                    bufferedimage2 = readTextureImage(texturepackbase.getResourceAsStream(s1.substring(7)));
                } else
                if(s1.startsWith("%blur%"))
                {
                    blurTexture = true;
                    bufferedimage2 = readTextureImage(texturepackbase.getResourceAsStream(s1.substring(6)));
                } else
                {
                    bufferedimage2 = readTextureImage(texturepackbase.getResourceAsStream(s1));
                }
                func_28147_a(bufferedimage2, (int[])field_28151_c.get(s1));
                blurTexture = false;
                clampTexture = false;
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        }

    }

    private BufferedImage readTextureImage(InputStream inputstream)
        throws IOException
    {
        BufferedImage bufferedimage = ImageIO.read(inputstream);
        inputstream.close();
        return bufferedimage;
    }

    public void bindTexture(int i)
    {
        if(i < 0)
        {
            return;
        } else
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, i);
            return;
        }
    }

    private void setTextureDimension(int i, Dimension dimension)
    {
        textureDimensionsMap.put(new Integer(i), dimension);
        if(i == terrainTextureId)
        {
            Config.setIconWidthTerrain(dimension.width / 16);
            updateDinamicTextures(0, dimension);
        }
        if(i == guiItemsTextureId)
        {
            Config.setIconWidthItems(dimension.width / 16);
            updateDinamicTextures(1, dimension);
        }
    }

    private Dimension getTextureDimensions(int i)
    {
        return (Dimension)textureDimensionsMap.get(new Integer(i));
    }

    private void updateDinamicTextures(int i, Dimension dimension)
    {
        checkHdTextures();
        for(int j = 0; j < textureList.size(); j++)
        {
            TextureFX texturefx = (TextureFX)textureList.get(j);
            if(texturefx.tileImage == i && (texturefx instanceof TextureHDFX))
            {
                TextureHDFX texturehdfx = (TextureHDFX)texturefx;
                texturehdfx.setTexturePackBase(texturePack.selectedTexturePack);
                texturehdfx.setTileWidth(dimension.width / 16);
                texturehdfx.onTick();
            }
        }

    }

    public boolean updateCustomTexture(TextureFX texturefx, ByteBuffer bytebuffer, int i)
    {
        if(texturefx.iconIndex == Block.waterStill.blockIndexInTexture)
        {
            if(Config.isGeneratedWater())
            {
                return false;
            } else
            {
                return updateCustomTexture(texturefx, "/custom_water_still.png", bytebuffer, i, Config.isAnimatedWater(), 1);
            }
        }
        if(texturefx.iconIndex == Block.waterStill.blockIndexInTexture + 1)
        {
            if(Config.isGeneratedWater())
            {
                return false;
            } else
            {
                return updateCustomTexture(texturefx, "/custom_water_flowing.png", bytebuffer, i, Config.isAnimatedWater(), 1);
            }
        }
        if(texturefx.iconIndex == Block.lavaStill.blockIndexInTexture)
        {
            if(Config.isGeneratedLava())
            {
                return false;
            } else
            {
                return updateCustomTexture(texturefx, "/custom_lava_still.png", bytebuffer, i, Config.isAnimatedLava(), 1);
            }
        }
        if(texturefx.iconIndex == Block.lavaStill.blockIndexInTexture + 1)
        {
            if(Config.isGeneratedLava())
            {
                return false;
            } else
            {
                return updateCustomTexture(texturefx, "/custom_lava_flowing.png", bytebuffer, i, Config.isAnimatedLava(), 1);
            }
        }
        if(texturefx.iconIndex == Block.portal.blockIndexInTexture)
        {
            return updateCustomTexture(texturefx, "/custom_portal.png", bytebuffer, i, Config.isAnimatedPortal(), 1);
        }
        if(texturefx.iconIndex == Block.fire.blockIndexInTexture)
        {
            return updateCustomTexture(texturefx, "/custom_fire_n_s.png", bytebuffer, i, Config.isAnimatedFire(), 1);
        }
        if(texturefx.iconIndex == Block.fire.blockIndexInTexture + 16)
        {
            return updateCustomTexture(texturefx, "/custom_fire_e_w.png", bytebuffer, i, Config.isAnimatedFire(), 1);
        } else
        {
            return false;
        }
    }

    private boolean updateDefaultTexture(TextureFX texturefx, ByteBuffer bytebuffer, int i)
    {
        if(texturePack.selectedTexturePack instanceof TexturePackDefault)
        {
            return false;
        }
        if(texturefx.iconIndex == Block.waterStill.blockIndexInTexture)
        {
            if(Config.isGeneratedWater())
            {
                return false;
            } else
            {
                return updateDefaultTexture(texturefx, bytebuffer, i, false, 1);
            }
        }
        if(texturefx.iconIndex == Block.waterStill.blockIndexInTexture + 1)
        {
            if(Config.isGeneratedWater())
            {
                return false;
            } else
            {
                return updateDefaultTexture(texturefx, bytebuffer, i, Config.isAnimatedWater(), 1);
            }
        }
        if(texturefx.iconIndex == Block.lavaStill.blockIndexInTexture)
        {
            if(Config.isGeneratedLava())
            {
                return false;
            } else
            {
                return updateDefaultTexture(texturefx, bytebuffer, i, false, 1);
            }
        }
        if(texturefx.iconIndex == Block.lavaStill.blockIndexInTexture + 1)
        {
            if(Config.isGeneratedLava())
            {
                return false;
            } else
            {
                return updateDefaultTexture(texturefx, bytebuffer, i, Config.isAnimatedLava(), 3);
            }
        } else
        {
            return false;
        }
    }

    private boolean updateDefaultTexture(TextureFX texturefx, ByteBuffer bytebuffer, int i, boolean flag, int j)
    {
        int k = texturefx.iconIndex;
        if(!flag && dynamicTexturesUpdated)
        {
            return true;
        }
        byte abyte0[] = getTerrainIconData(k, i);
        if(abyte0 == null)
        {
            return false;
        }
        bytebuffer.clear();
        int l = abyte0.length;
        if(flag)
        {
            int i1 = i - (tickCounter / j) % i;
            int j1 = i1 * i * 4;
            bytebuffer.put(abyte0, j1, l - j1);
            bytebuffer.put(abyte0, 0, j1);
        } else
        {
            bytebuffer.put(abyte0, 0, l);
        }
        bytebuffer.position(0).limit(l);
        return true;
    }

    private boolean updateCustomTexture(TextureFX texturefx, String s, ByteBuffer bytebuffer, int i, boolean flag, int j)
    {
        byte abyte0[] = getCustomTextureData(s, i);
        if(abyte0 == null)
        {
            return false;
        }
        if(!flag && dynamicTexturesUpdated)
        {
            return true;
        }
        int k = i * i * 4;
        int l = abyte0.length / k;
        int i1 = (tickCounter / j) % l;
        int j1 = 0;
        if(flag)
        {
            j1 = k * i1;
        }
        bytebuffer.clear();
        bytebuffer.put(abyte0, j1, k);
        bytebuffer.position(0).limit(k);
        return true;
    }

    private byte[] getTerrainIconData(int i, int j)
    {
        String s = (new StringBuilder()).append("Tile-").append(i).toString();
        byte abyte0[] = getCustomTextureData(s, j);
        if(abyte0 != null)
        {
            return abyte0;
        }
        byte abyte1[] = getCustomTextureData("/terrain.png", j * 16);
        if(abyte1 == null)
        {
            return null;
        }
        abyte0 = new byte[j * j * 4];
        int k = i % 16;
        int l = i / 16;
        int i1 = k * j;
        int j1 = l * j;
        int k1 = i1 + j;
        int l1 = j1 + j;
        for(int i2 = 0; i2 < j; i2++)
        {
            int j2 = j1 + i2;
            for(int k2 = 0; k2 < j; k2++)
            {
                int l2 = i1 + k2;
                int i3 = 4 * (l2 + j2 * j * 16);
                int j3 = 4 * (k2 + i2 * j);
                abyte0[j3 + 0] = abyte1[i3 + 0];
                abyte0[j3 + 1] = abyte1[i3 + 1];
                abyte0[j3 + 2] = abyte1[i3 + 2];
                abyte0[j3 + 3] = abyte1[i3 + 3];
            }

        }

        setCustomTextureData(s, abyte0);
        return abyte0;
    }

    public byte[] getCustomTextureData(String s, int i)
    {
        byte abyte0[] = (byte[])textureDataMap.get(s);
        if(abyte0 == null)
        {
            if(textureDataMap.containsKey(s))
            {
                return null;
            }
            abyte0 = loadImage(s, i);
            textureDataMap.put(s, abyte0);
        }
        return abyte0;
    }

    private void setCustomTextureData(String s, byte abyte0[])
    {
        textureDataMap.put(s, abyte0);
    }

    private byte[] loadImage(String s, int i)
    {
        try
        {
            TexturePackBase texturepackbase = texturePack.selectedTexturePack;
            if(texturepackbase == null)
            {
                return null;
            }
            InputStream inputstream = texturepackbase.getResourceAsStream(s);
            if(inputstream == null)
            {
                return null;
            }
            BufferedImage bufferedimage = readTextureImage(inputstream);
            if(bufferedimage == null)
            {
                return null;
            }
            if(i > 0 && bufferedimage.getWidth() != i)
            {
                double d = bufferedimage.getHeight() / bufferedimage.getWidth();
                int l = (int)((double)i * d);
                bufferedimage = scaleBufferedImage(bufferedimage, i, l);
            }
            int j = bufferedimage.getWidth();
            int k = bufferedimage.getHeight();
            int ai[] = new int[j * k];
            byte abyte0[] = new byte[j * k * 4];
            bufferedimage.getRGB(0, 0, j, k, ai, 0, j);
            for(int i1 = 0; i1 < ai.length; i1++)
            {
                int j1 = ai[i1] >> 24 & 0xff;
                int k1 = ai[i1] >> 16 & 0xff;
                int l1 = ai[i1] >> 8 & 0xff;
                int i2 = ai[i1] & 0xff;
                if(options != null && options.anaglyph)
                {
                    int j2 = (k1 * 30 + l1 * 59 + i2 * 11) / 100;
                    int k2 = (k1 * 30 + l1 * 70) / 100;
                    int l2 = (k1 * 30 + i2 * 70) / 100;
                    k1 = j2;
                    l1 = k2;
                    i2 = l2;
                }
                abyte0[i1 * 4 + 0] = (byte)k1;
                abyte0[i1 * 4 + 1] = (byte)l1;
                abyte0[i1 * 4 + 2] = (byte)i2;
                abyte0[i1 * 4 + 3] = (byte)j1;
            }

            return abyte0;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static BufferedImage scaleBufferedImage(BufferedImage bufferedimage, int i, int j)
    {
        BufferedImage bufferedimage1 = new BufferedImage(i, j, 2);
        Graphics2D graphics2d = bufferedimage1.createGraphics();
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.drawImage(bufferedimage, 0, 0, i, j, null);
        return bufferedimage1;
    }

    private void checkImageDataSize(int i)
    {
        if(imageData != null)
        {
            int j = i * i * 4;
            if(imageData.capacity() >= j)
            {
                return;
            }
        }
        allocateImageData(i);
    }

    private void allocateImageData(int i)
    {
        int j = i * i * 4;
        imageData = GLAllocation.createDirectByteBuffer(j);
        ArrayList arraylist = new ArrayList();
        for(int k = i / 2; k > 0; k /= 2)
        {
            int l = k * k * 4;
            ByteBuffer bytebuffer = GLAllocation.createDirectByteBuffer(l);
            arraylist.add(bytebuffer);
        }

        mipImageDatas = (ByteBuffer[])arraylist.toArray(new ByteBuffer[arraylist.size()]);
    }

    public void checkHdTextures()
    {
        if(hdTexturesInstalled)
        {
            return;
        }
        net.minecraft.client.Minecraft minecraft = Config.getMinecraft();
        if(minecraft == null)
        {
            return;
        } else
        {
            registerTextureFX(new TextureHDLavaFX());
            registerTextureFX(new TextureHDWaterFX());
            registerTextureFX(new TextureHDPortalFX());
            registerTextureFX(new TextureHDCompassFX(minecraft));
            registerTextureFX(new TextureHDWatchFX(minecraft));
            registerTextureFX(new TextureHDWaterFlowFX());
            registerTextureFX(new TextureHDLavaFlowFX());
            registerTextureFX(new TextureHDFlamesFX(0));
            registerTextureFX(new TextureHDFlamesFX(1));
            hdTexturesInstalled = true;
            return;
        }
    }

    private int getMaxMipmapLevel(int i)
    {
        int j;
        for(j = 0; i > 0; j++)
        {
            i /= 2;
        }

        return j - 1;
    }

    private void copyScaled(byte abyte0[], ByteBuffer bytebuffer, int i)
    {
        int j = (int)Math.sqrt(abyte0.length / 4);
        int k = i / j;
        byte abyte1[] = new byte[4];
        int l = i * i;
        bytebuffer.clear();
        if(k > 1)
        {
            for(int i1 = 0; i1 < j; i1++)
            {
                int j1 = i1 * j;
                int k1 = i1 * k;
                int l1 = k1 * i;
                for(int i2 = 0; i2 < j; i2++)
                {
                    int j2 = (i2 + j1) * 4;
                    abyte1[0] = abyte0[j2];
                    abyte1[1] = abyte0[j2 + 1];
                    abyte1[2] = abyte0[j2 + 2];
                    abyte1[3] = abyte0[j2 + 3];
                    int k2 = i2 * k;
                    int l2 = k2 + l1;
                    for(int i3 = 0; i3 < k; i3++)
                    {
                        int j3 = l2 + i3 * i;
                        bytebuffer.position(j3 * 4);
                        for(int k3 = 0; k3 < k; k3++)
                        {
                            bytebuffer.put(abyte1);
                        }

                    }

                }

            }

        }
        bytebuffer.position(0).limit(i * i * 4);
    }

    private boolean scalesWithFastColor(TextureFX texturefx)
    {
        return !texturefx.getClass().getName().equals("ModTextureStatic");
    }

    public static boolean useMipmaps = false;
    private HashMap textureMap;
    private HashMap field_28151_c;
    private HashMap textureNameToImageMap;
    private IntBuffer singleIntBuffer;
    private ByteBuffer imageData;
    private java.util.List textureList;
    private Map urlToImageDataMap;
    private GameSettings options;
    private boolean clampTexture;
    private boolean blurTexture;
    private TexturePackList texturePack;
    private BufferedImage missingTextureImage;
    private int terrainTextureId;
    private int guiItemsTextureId;
    private boolean hdTexturesInstalled;
    private Map textureDimensionsMap;
    private Map textureDataMap;
    private int tickCounter;
    private ByteBuffer mipImageDatas[];
    private boolean dynamicTexturesUpdated;

}
