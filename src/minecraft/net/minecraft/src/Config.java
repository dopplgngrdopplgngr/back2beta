// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

// Referenced classes of package net.minecraft.src:
//            GameSettings, IBlockAccess

public class Config
{

    private Config()
    {
    }

    private static String getVersion()
    {
        return new StringBuilder().append("Optifine_B2B").append(B2B.version).toString();
    }

    private static void checkOpenGlCaps()
    {
        log("");
        log(getVersion());
        log((new StringBuilder()).append("").append(new Date()).toString());
        log((new StringBuilder()).append("OS: ").append(System.getProperty("os.name")).append(" (").append(System.getProperty("os.arch")).append(") version ").append(System.getProperty("os.version")).toString());
        log((new StringBuilder()).append("Java: ").append(System.getProperty("java.version")).append(", ").append(System.getProperty("java.vendor")).toString());
        log((new StringBuilder()).append("VM: ").append(System.getProperty("java.vm.name")).append(" (").append(System.getProperty("java.vm.info")).append("), ").append(System.getProperty("java.vm.vendor")).toString());
        log((new StringBuilder()).append("LWJGL: ").append(Sys.getVersion()).toString());
        log((new StringBuilder()).append("OpenGL: ").append(GL11.glGetString(7937 /*GL_RENDERER*/)).append(" version ").append(GL11.glGetString(7938 /*GL_VERSION*/)).append(", ").append(GL11.glGetString(7936 /*GL_VENDOR*/)).toString());
        int i = getOpenGlVersion();
        String s = (new StringBuilder()).append("").append(i / 10).append(".").append(i % 10).toString();
        log((new StringBuilder()).append("OpenGL Version: ").append(s).toString());
        if(!GLContext.getCapabilities().OpenGL12)
        {
            log("OpenGL Mipmap levels: Not available (GL12.GL_TEXTURE_MAX_LEVEL)");
        }
        if(!GLContext.getCapabilities().GL_NV_fog_distance)
        {
            log("OpenGL Fancy fog: Not available (GL_NV_fog_distance)");
        }
        if(!GLContext.getCapabilities().GL_ARB_occlusion_query)
        {
            log("OpenGL Occlussion culling: Not available (GL_ARB_occlusion_query)");
        }
    }

    public static boolean isFancyFogAvailable()
    {
        return GLContext.getCapabilities().GL_NV_fog_distance;
    }

    public static boolean isOcclusionAvailable()
    {
        return GLContext.getCapabilities().GL_ARB_occlusion_query;
    }

    private static int getOpenGlVersion()
    {
        if(!GLContext.getCapabilities().OpenGL11)
        {
            return 10;
        }
        if(!GLContext.getCapabilities().OpenGL12)
        {
            return 11;
        }
        if(!GLContext.getCapabilities().OpenGL13)
        {
            return 12;
        }
        if(!GLContext.getCapabilities().OpenGL14)
        {
            return 13;
        }
        if(!GLContext.getCapabilities().OpenGL15)
        {
            return 14;
        }
        if(!GLContext.getCapabilities().OpenGL20)
        {
            return 15;
        }
        if(!GLContext.getCapabilities().OpenGL21)
        {
            return 20;
        }
        if(!GLContext.getCapabilities().OpenGL30)
        {
            return 21;
        }
        if(!GLContext.getCapabilities().OpenGL31)
        {
            return 30;
        }
        if(!GLContext.getCapabilities().OpenGL32)
        {
            return 31;
        }
        if(!GLContext.getCapabilities().OpenGL33)
        {
            return 32;
        }
        return GLContext.getCapabilities().OpenGL40 ? 40 : 33;
    }

    public static void setGameSettings(GameSettings gamesettings)
    {
        if(gameSettings == null)
        {
            checkOpenGlCaps();
        }
        gameSettings = gamesettings;
    }

    public static boolean isUseMipmaps()
    {
        int i = getMipmapLevel();
        return i > 0;
    }

    public static int getMipmapLevel()
    {
        if(gameSettings == null)
        {
            return DEF_MIPMAP_LEVEL.intValue();
        } else
        {
            return gameSettings.ofMipmapLevel;
        }
    }

    public static int getMipmapType()
    {
        if(gameSettings == null)
        {
            return DEF_MIPMAP_TYPE.intValue();
        }
        return !gameSettings.ofMipmapLinear ? 9984 /*GL_NEAREST_MIPMAP_NEAREST*/ : 9986 /*GL_NEAREST_MIPMAP_LINEAR*/;
    }

    public static boolean isUseAlphaFunc()
    {
        float f = getAlphaFuncLevel();
        return f > DEF_ALPHA_FUNC_LEVEL.floatValue() + 1E-005F;
    }

    public static float getAlphaFuncLevel()
    {
        return DEF_ALPHA_FUNC_LEVEL.floatValue();
    }

    public static boolean isFogFancy()
    {
        if(!GLContext.getCapabilities().GL_NV_fog_distance)
        {
            return false;
        }
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofFogFancy;
        }
    }

    public static float getFogStart()
    {
        if(gameSettings == null)
        {
            return DEF_FOG_START.floatValue();
        } else
        {
            return gameSettings.ofFogStart;
        }
    }

    public static boolean isOcclusionEnabled()
    {
        if(gameSettings == null)
        {
            return DEF_OCCLUSION_ENABLED.booleanValue();
        } else
        {
            return gameSettings.advancedOpengl;
        }
    }

    public static boolean isOcclusionFancy()
    {
        if(!isOcclusionEnabled())
        {
            return false;
        }
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofOcclusionFancy;
        }
    }

    public static boolean isLoadChunksFar()
    {
        if(gameSettings == null)
        {
            return DEF_LOAD_CHUNKS_FAR.booleanValue();
        } else
        {
            return gameSettings.ofLoadFar;
        }
    }

    public static int getPreloadedChunks()
    {
        if(gameSettings == null)
        {
            return DEF_PRELOADED_CHUNKS.intValue();
        } else
        {
            return gameSettings.ofPreloadedChunks;
        }
    }

    public static void dbg(String s)
    {
        System.out.println(s);
    }

    public static void log(String s)
    {
        dbg(s);
        try
        {
            if(logFile == null)
            {
                logFile = new File(Minecraft.getMinecraftDir(), "optifog.log");
                logFile.delete();
                logFile.createNewFile();
            }
            FileOutputStream fileoutputstream = new FileOutputStream(logFile, true);
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(fileoutputstream, "ASCII");
            try
            {
                outputstreamwriter.write(s);
                outputstreamwriter.write("\n");
                outputstreamwriter.flush();
            }
            finally
            {
                outputstreamwriter.close();
            }
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public static int getUpdatesPerFrame()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofChunkUpdates;
        } else
        {
            return 1;
        }
    }

    public static boolean isDynamicUpdates()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofChunkUpdatesDynamic;
        } else
        {
            return true;
        }
    }

    public static boolean isRainFancy()
    {
        if(gameSettings.ofRain == 0)
        {
            return gameSettings.fancyGraphics;
        } else
        {
            return gameSettings.ofRain == 2;
        }
    }

    public static boolean isWaterFancy()
    {
        if(gameSettings.ofWater == 0)
        {
            return gameSettings.fancyGraphics;
        } else
        {
            return gameSettings.ofWater == 2;
        }
    }

    public static boolean isRainOff()
    {
        return gameSettings.ofRain == 3;
    }

    public static boolean isCloudsFancy()
    {
        if(gameSettings.ofClouds == 0)
        {
            return gameSettings.fancyGraphics;
        } else
        {
            return gameSettings.ofClouds == 2;
        }
    }

    public static boolean isCloudsOff()
    {
        return gameSettings.ofClouds == 3;
    }

    public static boolean isTreesFancy()
    {
        if(gameSettings.ofTrees == 0)
        {
            return gameSettings.fancyGraphics;
        } else
        {
            return gameSettings.ofTrees == 2;
        }
    }

    public static boolean isGrassFancy()
    {
        if(gameSettings.ofGrass == 0)
        {
            return gameSettings.fancyGraphics;
        } else
        {
            return gameSettings.ofGrass == 2;
        }
    }

    public static int limit(int i, int j, int k)
    {
        if(i < j)
        {
            return j;
        }
        if(i > k)
        {
            return k;
        } else
        {
            return i;
        }
    }

    public static float limit(float f, float f1, float f2)
    {
        if(f < f1)
        {
            return f1;
        }
        if(f > f2)
        {
            return f2;
        } else
        {
            return f;
        }
    }

    public static boolean isAnimatedWater()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedWater != 2;
        } else
        {
            return true;
        }
    }

    public static boolean isGeneratedWater()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedWater == 1;
        } else
        {
            return true;
        }
    }

    public static boolean isAnimatedPortal()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedPortal;
        } else
        {
            return true;
        }
    }

    public static boolean isAnimatedLava()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedLava != 2;
        } else
        {
            return true;
        }
    }

    public static boolean isGeneratedLava()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedLava == 1;
        } else
        {
            return true;
        }
    }

    public static boolean isAnimatedFire()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedFire;
        } else
        {
            return true;
        }
    }

    public static boolean isAnimatedRedstone()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedRedstone;
        } else
        {
            return true;
        }
    }

    public static boolean isAnimatedExplosion()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedExplosion;
        } else
        {
            return true;
        }
    }

    public static boolean isAnimatedFlame()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedFlame;
        } else
        {
            return true;
        }
    }

    public static boolean isAnimatedSmoke()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAnimatedSmoke;
        } else
        {
            return true;
        }
    }

    public static float getAmbientOcclusionLevel()
    {
        if(gameSettings != null)
        {
            return gameSettings.ofAoLevel;
        } else
        {
            return 0.0F;
        }
    }

    public static float fixAoLight(float f, float f1)
    {
        if(lightLevels == null)
        {
            return f;
        }
        float f2 = lightLevels[0];
        float f3 = lightLevels[1];
        if(f > f2)
        {
            return f;
        }
        if(f1 <= f3)
        {
            return f;
        } else
        {
            float f4 = 1.0F - getAmbientOcclusionLevel();
            return f + (f1 - f) * f4;
        }
    }

    public static void setLightLevels(float af[])
    {
        lightLevels = af;
    }

    public static boolean callBoolean(String s, String s1, Object aobj[])
    {
        try
        {
            Class class1 = getClass(s);
            if(class1 == null)
            {
                return false;
            }
            Method method = getMethod(class1, s1, aobj);
            if(method == null)
            {
                return false;
            } else
            {
                Boolean boolean1 = (Boolean)method.invoke(null, aobj);
                return boolean1.booleanValue();
            }
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace();
        }
        return false;
    }

    public static void callVoid(String s, String s1, Object aobj[])
    {
        try
        {
            Class class1 = getClass(s);
            if(class1 == null)
            {
                return;
            }
            Method method = getMethod(class1, s1, aobj);
            if(method == null)
            {
                return;
            }
            method.invoke(null, aobj);
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }

    public static void callVoid(Object obj, String s, Object aobj[])
    {
        try
        {
            if(obj == null)
            {
                return;
            }
            Class class1 = obj.getClass();
            if(class1 == null)
            {
                return;
            }
            Method method = getMethod(class1, s, aobj);
            if(method == null)
            {
                return;
            }
            method.invoke(obj, aobj);
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }

    public static Object getFieldValue(String s, String s1)
    {
        try
        {
            Class class1 = getClass(s);
            if(class1 == null)
            {
                return null;
            }
            Field field = class1.getDeclaredField(s1);
            if(field == null)
            {
                return null;
            } else
            {
                Object obj = field.get(null);
                return obj;
            }
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace();
        }
        return null;
    }

    public static Object getFieldValue(Object obj, String s)
    {
        try
        {
            if(obj == null)
            {
                return null;
            }
            Class class1 = obj.getClass();
            if(class1 == null)
            {
                return null;
            }
            Field field = class1.getField(s);
            if(field == null)
            {
                return null;
            } else
            {
                Object obj1 = field.get(obj);
                return obj1;
            }
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace();
        }
        return null;
    }

    private static Method getMethod(Class class1, String s, Object aobj[])
    {
        Method amethod[] = class1.getMethods();
        for(int i = 0; i < amethod.length; i++)
        {
            Method method = amethod[i];
            if(method.getName().equals(s) && method.getParameterTypes().length == aobj.length)
            {
                return method;
            }
        }

        dbg((new StringBuilder()).append("No method found for: ").append(class1.getName()).append(".").append(s).append("(").append(arrayToString(aobj)).append(")").toString());
        return null;
    }

    public static String arrayToString(Object aobj[])
    {
        StringBuffer stringbuffer = new StringBuffer(aobj.length * 5);
        for(int i = 0; i < aobj.length; i++)
        {
            Object obj = aobj[i];
            if(i > 0)
            {
                stringbuffer.append(", ");
            }
            stringbuffer.append(String.valueOf(obj));
        }

        return stringbuffer.toString();
    }

    public static boolean hasModLoader()
    {
        Class class1 = getClass("ModLoader");
        return class1 != null;
    }

    private static Class getClass(String s)
    {
        Class class1 = (Class)foundClassesMap.get(s);
        if(class1 != null)
        {
            return class1;
        }
        if(foundClassesMap.containsKey(s))
        {
            return null;
        }
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            log((new StringBuilder()).append("Class not found: ").append(s).toString());
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace();
        }
        foundClassesMap.put(s, class1);
        return class1;
    }

    public static void setMinecraft(Minecraft minecraft1)
    {
        minecraft = minecraft1;
    }

    public static Minecraft getMinecraft()
    {
        return minecraft;
    }

    public static int getIconWidthTerrain()
    {
        return iconWidthTerrain;
    }

    public static int getIconWidthItems()
    {
        return iconWidthItems;
    }

    public static void setIconWidthItems(int i)
    {
        iconWidthItems = i;
    }

    public static void setIconWidthTerrain(int i)
    {
        iconWidthTerrain = i;
    }

    public static int getMaxDynamicTileWidth()
    {
        return 64;
    }

    public static int getSideGrassTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(!isBetterGrass())
        {
            return 3;
        }
        if(isBetterGrassFancy())
        {
            j--;
            switch(l)
            {
            case 2: // '\002'
                k--;
                break;

            case 3: // '\003'
                k++;
                break;

            case 4: // '\004'
                i--;
                break;

            case 5: // '\005'
                i++;
                break;
            }
            int i1 = iblockaccess.getBlockId(i, j, k);
            if(i1 != 2)
            {
                return 3;
            }
        }
        return 0;
    }

    public static int getSideSnowGrassTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(!isBetterGrass())
        {
            return 68;
        }
        if(isBetterGrassFancy())
        {
            switch(l)
            {
            case 2: // '\002'
                k--;
                break;

            case 3: // '\003'
                k++;
                break;

            case 4: // '\004'
                i--;
                break;

            case 5: // '\005'
                i++;
                break;
            }
            int i1 = iblockaccess.getBlockId(i, j, k);
            if(i1 != 78 && i1 != 80)
            {
                return 68;
            }
        }
        return 66;
    }

    public static boolean isBetterGrass()
    {
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofBetterGrass != 3;
        }
    }

    public static boolean isBetterGrassFancy()
    {
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofBetterGrass == 2;
        }
    }

    public static boolean isFontRendererUpdated()
    {
        return fontRendererUpdated;
    }

    public static void setFontRendererUpdated(boolean flag)
    {
        fontRendererUpdated = flag;
    }

    public static boolean isWeatherEnabled()
    {
        if(gameSettings == null)
        {
            return true;
        } else
        {
            return gameSettings.ofWeather;
        }
    }

    public static boolean isSkyEnabled()
    {
        if(gameSettings == null)
        {
            return true;
        } else
        {
            return gameSettings.ofSky;
        }
    }

    public static boolean isStarsEnabled()
    {
        if(gameSettings == null)
        {
            return true;
        } else
        {
            return gameSettings.ofStars;
        }
    }

    public static boolean isFarView()
    {
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofFarView;
        }
    }

    public static void sleep(long l)
    {
        try
        {
            Thread.currentThread();
            Thread.sleep(l);
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
    }

    public static boolean isTimeDayOnly()
    {
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofTime == 1;
        }
    }

    public static boolean isTimeNightOnly()
    {
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofTime == 2;
        }
    }

    public static boolean isClearWater()
    {
        if(gameSettings == null)
        {
            return false;
        } else
        {
            return gameSettings.ofClearWater;
        }
    }

    private static GameSettings gameSettings = null;
    private static Minecraft minecraft = null;
    private static float lightLevels[] = null;
    private static int iconWidthTerrain = 16;
    private static int iconWidthItems = 16;
    private static Map foundClassesMap = new HashMap();
    private static boolean fontRendererUpdated = false;
    private static File logFile = null;
    public static final Boolean DEF_FOG_FANCY = Boolean.valueOf(true);
    public static final Float DEF_FOG_START = Float.valueOf(0.2F);
    public static final Boolean DEF_OPTIMIZE_RENDER_DISTANCE = Boolean.valueOf(false);
    public static final Boolean DEF_OCCLUSION_ENABLED = Boolean.valueOf(false);
    public static final Integer DEF_MIPMAP_LEVEL = Integer.valueOf(0);
    public static final Integer DEF_MIPMAP_TYPE = Integer.valueOf(9984 /*GL_NEAREST_MIPMAP_NEAREST*/);
    public static final Float DEF_ALPHA_FUNC_LEVEL = Float.valueOf(0.1F);
    public static final Boolean DEF_LOAD_CHUNKS_FAR = Boolean.valueOf(false);
    public static final Integer DEF_PRELOADED_CHUNKS = Integer.valueOf(0);
    public static final Integer DEF_CHUNKS_LIMIT = Integer.valueOf(25);
    public static final Integer DEF_UPDATES_PER_FRAME = Integer.valueOf(3);
    public static final Boolean DEF_DYNAMIC_UPDATES = Boolean.valueOf(false);

}
