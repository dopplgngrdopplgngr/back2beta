// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

// Referenced classes of package net.minecraft.src:
//            KeyBinding, Config, StringTranslate, EnumOptions, 
//            SoundManager, RenderGlobal, EntityRenderer, Block, 
//            World, IChunkProvider, Chunk, NibbleArray, 
//            RenderEngine, RenderBlocks, EnumOptionsMappingHelper, StatCollector

public class GameSettings
{

    public GameSettings(Minecraft minecraft, File file)
    {
        ofFogFancy = false;
        ofFogStart = 0.8F;
        ofMipmapLevel = 0;
        ofMipmapLinear = false;
        ofLoadFar = false;
        ofPreloadedChunks = 0;
        ofOcclusionFancy = false;
        ofSmoothFps = false;
        ofSmoothInput = false;
        ofBrightness = 0.0F;
        ofAoLevel = 1.0F;
        ofClouds = 0;
        ofCloudsHeight = 1.0F;
        ofTrees = 0;
        ofGrass = 0;
        ofRain = 0;
        ofWater = 0;
        ofBetterGrass = 3;
        ofAutoSaveTicks = 4000;
        ofFastDebugInfo = false;
        ofWeather = true;
        ofSky = true;
        ofStars = true;
        ofChunkUpdates = 1;
        ofChunkUpdatesDynamic = true;
        ofFarView = false;
        ofTime = 0;
        ofClearWater = false;
        ofAnimatedWater = 1;
        ofAnimatedLava = 1;
        ofAnimatedFire = true;
        ofAnimatedPortal = true;
        ofAnimatedRedstone = true;
        ofAnimatedExplosion = true;
        ofAnimatedFlame = true;
        ofAnimatedSmoke = true;
        musicVolume = 1.0F;
        soundVolume = 1.0F;
        mouseSensitivity = 0.5F;
        invertMouse = false;
        renderDistance = 1;
        viewBobbing = true;
        anaglyph = false;
        advancedOpengl = false;
        limitFramerate = 0;
        fancyGraphics = true;
        ambientOcclusion = true;
        skin = "Default";
        keyBindForward = new KeyBinding("key.forward", 17);
        keyBindLeft = new KeyBinding("key.left", 30);
        keyBindBack = new KeyBinding("key.back", 31);
        keyBindRight = new KeyBinding("key.right", 32);
        keyBindJump = new KeyBinding("key.jump", 57);
        keyBindInventory = new KeyBinding("key.inventory", 18);
        keyBindDrop = new KeyBinding("key.drop", 16);
        keyBindChat = new KeyBinding("key.chat", 20);
        keyBindToggleFog = new KeyBinding("key.fog", 33);
        keyBindSneak = new KeyBinding("key.sneak", 42);
        ofKeyBindZoom = new KeyBinding("Zoom", 46);
        keyBindings = (new KeyBinding[] {
            keyBindForward, keyBindLeft, keyBindBack, keyBindRight, keyBindJump, keyBindSneak, keyBindDrop, keyBindInventory, keyBindChat, keyBindToggleFog, 
            ofKeyBindZoom
        });
        difficulty = 2;
        hideGUI = false;
        thirdPersonView = false;
        showDebugInfo = false;
        lastServer = "";
        field_22275_C = false;
        smoothCamera = false;
        field_22273_E = false;
        field_22272_F = 1.0F;
        field_22271_G = 1.0F;
        guiScale = 0;
        mc = minecraft;
        optionsFile = new File(file, "options.txt");
        loadOptions();
        Config.setGameSettings(this);
    }

    public GameSettings()
    {
        ofFogFancy = false;
        ofFogStart = 0.8F;
        ofMipmapLevel = 0;
        ofMipmapLinear = false;
        ofLoadFar = false;
        ofPreloadedChunks = 0;
        ofOcclusionFancy = false;
        ofSmoothFps = false;
        ofSmoothInput = false;
        ofBrightness = 0.0F;
        ofAoLevel = 0.0F;
        ofClouds = 0;
        ofCloudsHeight = 0.0F;
        ofTrees = 0;
        ofGrass = 0;
        ofRain = 0;
        ofWater = 0;
        ofBetterGrass = 3;
        ofAutoSaveTicks = 4000;
        ofFastDebugInfo = false;
        ofWeather = true;
        ofSky = true;
        ofStars = true;
        ofChunkUpdates = 1;
        ofChunkUpdatesDynamic = true;
        ofFarView = false;
        ofTime = 0;
        ofClearWater = false;
        ofAnimatedWater = 0;
        ofAnimatedLava = 0;
        ofAnimatedFire = true;
        ofAnimatedPortal = true;
        ofAnimatedRedstone = true;
        ofAnimatedExplosion = true;
        ofAnimatedFlame = true;
        ofAnimatedSmoke = true;
        musicVolume = 1.0F;
        soundVolume = 1.0F;
        mouseSensitivity = 0.5F;
        invertMouse = false;
        renderDistance = 0;
        viewBobbing = true;
        anaglyph = false;
        advancedOpengl = false;
        limitFramerate = 1;
        fancyGraphics = true;
        ambientOcclusion = true;
        skin = "Default";
        keyBindForward = new KeyBinding("key.forward", 17);
        keyBindLeft = new KeyBinding("key.left", 30);
        keyBindBack = new KeyBinding("key.back", 31);
        keyBindRight = new KeyBinding("key.right", 32);
        keyBindJump = new KeyBinding("key.jump", 57);
        keyBindInventory = new KeyBinding("key.inventory", 18);
        keyBindDrop = new KeyBinding("key.drop", 16);
        keyBindChat = new KeyBinding("key.chat", 20);
        keyBindToggleFog = new KeyBinding("key.fog", 33);
        keyBindSneak = new KeyBinding("key.sneak", 42);
        keyBindings = (new KeyBinding[] {
            keyBindForward, keyBindLeft, keyBindBack, keyBindRight, keyBindJump, keyBindSneak, keyBindDrop, keyBindInventory, keyBindChat, keyBindToggleFog
        });
        difficulty = 2;
        hideGUI = false;
        thirdPersonView = false;
        showDebugInfo = false;
        lastServer = "";
        field_22275_C = false;
        smoothCamera = false;
        field_22273_E = false;
        field_22272_F = 1.0F;
        field_22271_G = 1.0F;
        guiScale = 0;
    }

    public String getKeyBindingDescription(int i)
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        return stringtranslate.translateKey(keyBindings[i].keyDescription);
    }

    public String getOptionDisplayString(int i)
    {
        return Keyboard.getKeyName(keyBindings[i].keyCode);
    }

    public void setKeyBinding(int i, int j)
    {
        keyBindings[i].keyCode = j;
        saveOptions();
    }

    public void setOptionFloatValue(EnumOptions enumoptions, float f)
    {
        if(enumoptions == EnumOptions.MUSIC)
        {
            musicVolume = f;
            mc.sndManager.onSoundOptionsChanged();
        }
        if(enumoptions == EnumOptions.SOUND)
        {
            soundVolume = f;
            mc.sndManager.onSoundOptionsChanged();
        }
        if(enumoptions == EnumOptions.SENSITIVITY)
        {
            mouseSensitivity = f;
        }
        if(enumoptions == EnumOptions.BRIGHTNESS)
        {
            ofBrightness = f;
            updateWorldLightLevels();
        }
        if(enumoptions == EnumOptions.CLOUD_HEIGHT)
        {
            ofCloudsHeight = f;
        }
        if(enumoptions == EnumOptions.AO_LEVEL)
        {
            ofAoLevel = f;
            ambientOcclusion = ofAoLevel > 0.0F;
            mc.renderGlobal.loadRenderers();
        }
    }

    private void updateWorldLightLevels()
    {
        if(mc.entityRenderer != null)
        {
            mc.entityRenderer.updateWorldLightLevels();
        }
        if(mc.renderGlobal != null)
        {
            mc.renderGlobal.loadRenderers();
        }
    }

    private void updateWaterOpacity()
    {
        byte byte0 = 3;
        if(ofClearWater)
        {
            byte0 = 1;
        }
        Block.waterStill.setLightOpacity(byte0);
        Block.waterMoving.setLightOpacity(byte0);
        if(mc.theWorld == null)
        {
            return;
        }
        IChunkProvider ichunkprovider = mc.theWorld.chunkProvider;
        if(ichunkprovider == null)
        {
            return;
        }
        for(int i = -512; i < 512; i++)
        {
            for(int j = -512; j < 512; j++)
            {
                if(!ichunkprovider.chunkExists(i, j))
                {
                    continue;
                }
                Chunk chunk = ichunkprovider.provideChunk(i, j);
                if(chunk == null)
                {
                    continue;
                }
                byte abyte0[] = chunk.skylightMap.data;
                for(int k = 0; k < abyte0.length; k++)
                {
                    abyte0[k] = 0;
                }

                chunk.func_1024_c();
            }

        }

        mc.renderGlobal.loadRenderers();
    }

    public void setOptionValue(EnumOptions enumoptions, int i)
    {
        if(enumoptions == EnumOptions.INVERT_MOUSE)
        {
            invertMouse = !invertMouse;
        }
        if(enumoptions == EnumOptions.RENDER_DISTANCE)
        {
            renderDistance = renderDistance + i & 3;
        }
        if(enumoptions == EnumOptions.GUI_SCALE)
        {
            guiScale = guiScale + i & 3;
        }
        if(enumoptions == EnumOptions.VIEW_BOBBING)
        {
            viewBobbing = !viewBobbing;
        }
        if(enumoptions == EnumOptions.ADVANCED_OPENGL)
        {
            if(!Config.isOcclusionAvailable())
            {
                ofOcclusionFancy = false;
                advancedOpengl = false;
            } else
            if(!advancedOpengl)
            {
                advancedOpengl = true;
                ofOcclusionFancy = false;
            } else
            if(!ofOcclusionFancy)
            {
                ofOcclusionFancy = true;
            } else
            {
                ofOcclusionFancy = false;
                advancedOpengl = false;
            }
            mc.renderGlobal.setAllRenderesVisible();
        }
        if(enumoptions == EnumOptions.ANAGLYPH)
        {
            anaglyph = !anaglyph;
            mc.renderEngine.refreshTextures();
        }
        if(enumoptions == EnumOptions.FRAMERATE_LIMIT)
        {
            limitFramerate = (limitFramerate + i) % 4;
            Display.setVSyncEnabled(limitFramerate == 3);
        }
        if(enumoptions == EnumOptions.DIFFICULTY)
        {
            difficulty = difficulty + i & 3;
        }
        if(enumoptions == EnumOptions.GRAPHICS)
        {
            fancyGraphics = !fancyGraphics;
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.AMBIENT_OCCLUSION)
        {
            ambientOcclusion = !ambientOcclusion;
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.FOG_FANCY)
        {
            if(!Config.isFancyFogAvailable())
            {
                ofFogFancy = false;
            } else
            {
                ofFogFancy = !ofFogFancy;
            }
        }
        if(enumoptions == EnumOptions.FOG_START)
        {
            ofFogStart += 0.2F;
            if(ofFogStart > 0.81F)
            {
                ofFogStart = 0.2F;
            }
        }
        if(enumoptions == EnumOptions.MIPMAP_LEVEL)
        {
            ofMipmapLevel++;
            if(ofMipmapLevel > 4)
            {
                ofMipmapLevel = 0;
            }
            mc.renderEngine.refreshTextures();
        }
        if(enumoptions == EnumOptions.MIPMAP_TYPE)
        {
            ofMipmapLinear = !ofMipmapLinear;
            mc.renderEngine.refreshTextures();
        }
        if(enumoptions == EnumOptions.LOAD_FAR)
        {
            ofLoadFar = !ofLoadFar;
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.PRELOADED_CHUNKS)
        {
            ofPreloadedChunks += 2;
            if(ofPreloadedChunks > 8)
            {
                ofPreloadedChunks = 0;
            }
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.SMOOTH_FPS)
        {
            ofSmoothFps = !ofSmoothFps;
        }
        if(enumoptions == EnumOptions.SMOOTH_INPUT)
        {
            ofSmoothInput = !ofSmoothInput;
        }
        if(enumoptions == EnumOptions.CLOUDS)
        {
            ofClouds++;
            if(ofClouds > 3)
            {
                ofClouds = 0;
            }
        }
        if(enumoptions == EnumOptions.TREES)
        {
            ofTrees++;
            if(ofTrees > 2)
            {
                ofTrees = 0;
            }
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.GRASS)
        {
            ofGrass++;
            if(ofGrass > 2)
            {
                ofGrass = 0;
            }
            RenderBlocks.fancyGrass = Config.isGrassFancy();
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.RAIN)
        {
            ofRain++;
            if(ofRain > 3)
            {
                ofRain = 0;
            }
        }
        if(enumoptions == EnumOptions.WATER)
        {
            ofWater++;
            if(ofWater > 2)
            {
                ofWater = 0;
            }
        }
        if(enumoptions == EnumOptions.ANIMATED_WATER)
        {
            ofAnimatedWater++;
            if(ofAnimatedWater > 2)
            {
                ofAnimatedWater = 0;
            }
            mc.renderEngine.refreshTextures();
        }
        if(enumoptions == EnumOptions.ANIMATED_LAVA)
        {
            ofAnimatedLava++;
            if(ofAnimatedLava > 2)
            {
                ofAnimatedLava = 0;
            }
            mc.renderEngine.refreshTextures();
        }
        if(enumoptions == EnumOptions.ANIMATED_FIRE)
        {
            ofAnimatedFire = !ofAnimatedFire;
            mc.renderEngine.refreshTextures();
        }
        if(enumoptions == EnumOptions.ANIMATED_PORTAL)
        {
            ofAnimatedPortal = !ofAnimatedPortal;
            mc.renderEngine.refreshTextures();
        }
        if(enumoptions == EnumOptions.ANIMATED_REDSTONE)
        {
            ofAnimatedRedstone = !ofAnimatedRedstone;
        }
        if(enumoptions == EnumOptions.ANIMATED_EXPLOSION)
        {
            ofAnimatedExplosion = !ofAnimatedExplosion;
        }
        if(enumoptions == EnumOptions.ANIMATED_FLAME)
        {
            ofAnimatedFlame = !ofAnimatedFlame;
        }
        if(enumoptions == EnumOptions.ANIMATED_SMOKE)
        {
            ofAnimatedSmoke = !ofAnimatedSmoke;
        }
        if(enumoptions == EnumOptions.FAST_DEBUG_INFO)
        {
            ofFastDebugInfo = !ofFastDebugInfo;
        }
        if(enumoptions == EnumOptions.AUTOSAVE_TICKS)
        {
            ofAutoSaveTicks *= 10;
            if(ofAutoSaveTicks > 40000)
            {
                ofAutoSaveTicks = 40;
            }
        }
        if(enumoptions == EnumOptions.BETTER_GRASS)
        {
            ofBetterGrass++;
            if(ofBetterGrass > 3)
            {
                ofBetterGrass = 1;
            }
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.WEATHER)
        {
            ofWeather = !ofWeather;
        }
        if(enumoptions == EnumOptions.SKY)
        {
            ofSky = !ofSky;
        }
        if(enumoptions == EnumOptions.STARS)
        {
            ofStars = !ofStars;
        }
        if(enumoptions == EnumOptions.CHUNK_UPDATES)
        {
            ofChunkUpdates++;
            if(ofChunkUpdates > 5)
            {
                ofChunkUpdates = 1;
            }
        }
        if(enumoptions == EnumOptions.CHUNK_UPDATES_DYNAMIC)
        {
            ofChunkUpdatesDynamic = !ofChunkUpdatesDynamic;
        }
        if(enumoptions == EnumOptions.FAR_VIEW)
        {
            ofFarView = !ofFarView;
            mc.renderGlobal.loadRenderers();
        }
        if(enumoptions == EnumOptions.TIME)
        {
            ofTime++;
            if(ofTime > 2)
            {
                ofTime = 0;
            }
        }
        if(enumoptions == EnumOptions.CLEAR_WATER)
        {
            ofClearWater = !ofClearWater;
            updateWaterOpacity();
        }
        saveOptions();
    }

    public float getOptionFloatValue(EnumOptions enumoptions)
    {
        if(enumoptions == EnumOptions.MUSIC)
        {
            return musicVolume;
        }
        if(enumoptions == EnumOptions.SOUND)
        {
            return soundVolume;
        }
        if(enumoptions == EnumOptions.SENSITIVITY)
        {
            return mouseSensitivity;
        }
        if(enumoptions == EnumOptions.BRIGHTNESS)
        {
            return ofBrightness;
        }
        if(enumoptions == EnumOptions.CLOUD_HEIGHT)
        {
            return ofCloudsHeight;
        }
        if(enumoptions == EnumOptions.AO_LEVEL)
        {
            return ofAoLevel;
        } else
        {
            return 0.0F;
        }
    }

    public boolean getOptionOrdinalValue(EnumOptions enumoptions)
    {
        switch(EnumOptionsMappingHelper.enumOptionsMappingHelperArray[enumoptions.ordinal()])
        {
        case 1: // '\001'
            return invertMouse;

        case 2: // '\002'
            return viewBobbing;

        case 3: // '\003'
            return anaglyph;

        case 4: // '\004'
            return advancedOpengl;

        case 5: // '\005'
            return ambientOcclusion;
        }
        return false;
    }

    public String getKeyBinding(EnumOptions enumoptions)
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        String s = stringtranslate.translateKey(enumoptions.getEnumString());
        if(s == null)
        {
            s = enumoptions.getEnumString();
        }
        String s1 = (new StringBuilder()).append(s).append(": ").toString();
        if(enumoptions.getEnumFloat())
        {
            float f = getOptionFloatValue(enumoptions);
            if(enumoptions == EnumOptions.SENSITIVITY)
            {
                if(f == 0.0F)
                {
                    return (new StringBuilder()).append(s1).append(stringtranslate.translateKey("options.sensitivity.min")).toString();
                }
                if(f == 1.0F)
                {
                    return (new StringBuilder()).append(s1).append(stringtranslate.translateKey("options.sensitivity.max")).toString();
                } else
                {
                    return (new StringBuilder()).append(s1).append((int)(f * 200F)).append("%").toString();
                }
            }
            if(f == 0.0F)
            {
                return (new StringBuilder()).append(s1).append(stringtranslate.translateKey("options.off")).toString();
            } else
            {
                return (new StringBuilder()).append(s1).append((int)(f * 100F)).append("%").toString();
            }
        }
        if(enumoptions == EnumOptions.ADVANCED_OPENGL)
        {
            if(!advancedOpengl)
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
            if(ofOcclusionFancy)
            {
                return (new StringBuilder()).append(s1).append("Fancy").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("Fast").toString();
            }
        }
        if(enumoptions.getEnumBoolean())
        {
            boolean flag = getOptionOrdinalValue(enumoptions);
            if(flag)
            {
                return (new StringBuilder()).append(s1).append(stringtranslate.translateKey("options.on")).toString();
            } else
            {
                return (new StringBuilder()).append(s1).append(stringtranslate.translateKey("options.off")).toString();
            }
        }
        if(enumoptions == EnumOptions.RENDER_DISTANCE)
        {
            return (new StringBuilder()).append(s1).append(stringtranslate.translateKey(RENDER_DISTANCES[renderDistance])).toString();
        }
        if(enumoptions == EnumOptions.DIFFICULTY)
        {
            return (new StringBuilder()).append(s1).append(stringtranslate.translateKey(DIFFICULTIES[difficulty])).toString();
        }
        if(enumoptions == EnumOptions.GUI_SCALE)
        {
            return (new StringBuilder()).append(s1).append(stringtranslate.translateKey(GUISCALES[guiScale])).toString();
        }
        if(enumoptions == EnumOptions.FRAMERATE_LIMIT)
        {
            if(limitFramerate == 3)
            {
                return (new StringBuilder()).append(s1).append("VSync").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append(StatCollector.translateToLocal(LIMIT_FRAMERATES[limitFramerate])).toString();
            }
        }
        if(enumoptions == EnumOptions.FOG_FANCY)
        {
            if(ofFogFancy)
            {
                return (new StringBuilder()).append(s1).append("Fancy").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("Fast").toString();
            }
        }
        if(enumoptions == EnumOptions.FOG_START)
        {
            return (new StringBuilder()).append(s1).append(ofFogStart).toString();
        }
        if(enumoptions == EnumOptions.MIPMAP_LEVEL)
        {
            return (new StringBuilder()).append(s1).append(ofMipmapLevel).toString();
        }
        if(enumoptions == EnumOptions.MIPMAP_TYPE)
        {
            if(ofMipmapLinear)
            {
                return (new StringBuilder()).append(s1).append("Linear").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("Nearest").toString();
            }
        }
        if(enumoptions == EnumOptions.LOAD_FAR)
        {
            if(ofLoadFar)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.PRELOADED_CHUNKS)
        {
            if(ofPreloadedChunks == 0)
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append(ofPreloadedChunks).toString();
            }
        }
        if(enumoptions == EnumOptions.SMOOTH_FPS)
        {
            if(ofSmoothFps)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.SMOOTH_INPUT)
        {
            if(ofSmoothInput)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.CLOUDS)
        {
            switch(ofClouds)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Fast").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("Fancy").toString();

            case 3: // '\003'
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
            return (new StringBuilder()).append(s1).append("Default").toString();
        }
        if(enumoptions == EnumOptions.TREES)
        {
            switch(ofTrees)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Fast").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("Fancy").toString();
            }
            return (new StringBuilder()).append(s1).append("Default").toString();
        }
        if(enumoptions == EnumOptions.GRASS)
        {
            switch(ofGrass)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Fast").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("Fancy").toString();
            }
            return (new StringBuilder()).append(s1).append("Default").toString();
        }
        if(enumoptions == EnumOptions.RAIN)
        {
            switch(ofRain)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Fast").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("Fancy").toString();

            case 3: // '\003'
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
            return (new StringBuilder()).append(s1).append("Default").toString();
        }
        if(enumoptions == EnumOptions.WATER)
        {
            switch(ofWater)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Fast").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("Fancy").toString();

            case 3: // '\003'
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
            return (new StringBuilder()).append(s1).append("Default").toString();
        }
        if(enumoptions == EnumOptions.ANIMATED_WATER)
        {
            switch(ofAnimatedWater)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Dynamic").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
            return (new StringBuilder()).append(s1).append("ON").toString();
        }
        if(enumoptions == EnumOptions.ANIMATED_LAVA)
        {
            switch(ofAnimatedLava)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Dynamic").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
            return (new StringBuilder()).append(s1).append("ON").toString();
        }
        if(enumoptions == EnumOptions.ANIMATED_FIRE)
        {
            if(ofAnimatedFire)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.ANIMATED_PORTAL)
        {
            if(ofAnimatedPortal)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.ANIMATED_REDSTONE)
        {
            if(ofAnimatedRedstone)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.ANIMATED_EXPLOSION)
        {
            if(ofAnimatedExplosion)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.ANIMATED_FLAME)
        {
            if(ofAnimatedFlame)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.ANIMATED_SMOKE)
        {
            if(ofAnimatedSmoke)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.FAST_DEBUG_INFO)
        {
            if(ofFastDebugInfo)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.AUTOSAVE_TICKS)
        {
            if(ofAutoSaveTicks <= 40)
            {
                return (new StringBuilder()).append(s1).append("Default (2s)").toString();
            }
            if(ofAutoSaveTicks <= 400)
            {
                return (new StringBuilder()).append(s1).append("20s").toString();
            }
            if(ofAutoSaveTicks <= 4000)
            {
                return (new StringBuilder()).append(s1).append("3min").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("30min").toString();
            }
        }
        if(enumoptions == EnumOptions.BETTER_GRASS)
        {
            switch(ofBetterGrass)
            {
            case 1: // '\001'
                return (new StringBuilder()).append(s1).append("Fast").toString();

            case 2: // '\002'
                return (new StringBuilder()).append(s1).append("Fancy").toString();
            }
            return (new StringBuilder()).append(s1).append("OFF").toString();
        }
        if(enumoptions == EnumOptions.WEATHER)
        {
            if(ofWeather)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.SKY)
        {
            if(ofSky)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.STARS)
        {
            if(ofStars)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.CHUNK_UPDATES)
        {
            return (new StringBuilder()).append(s1).append(ofChunkUpdates).toString();
        }
        if(enumoptions == EnumOptions.CHUNK_UPDATES_DYNAMIC)
        {
            if(ofChunkUpdatesDynamic)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.FAR_VIEW)
        {
            if(ofFarView)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.TIME)
        {
            if(ofTime == 1)
            {
                return (new StringBuilder()).append(s1).append("Day Only").toString();
            }
            if(ofTime == 2)
            {
                return (new StringBuilder()).append(s1).append("Night Only").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("Default").toString();
            }
        }
        if(enumoptions == EnumOptions.CLEAR_WATER)
        {
            if(ofClearWater)
            {
                return (new StringBuilder()).append(s1).append("ON").toString();
            } else
            {
                return (new StringBuilder()).append(s1).append("OFF").toString();
            }
        }
        if(enumoptions == EnumOptions.GRAPHICS)
        {
            if(fancyGraphics)
            {
                return (new StringBuilder()).append(s1).append(stringtranslate.translateKey("options.graphics.fancy")).toString();
            } else
            {
                return (new StringBuilder()).append(s1).append(stringtranslate.translateKey("options.graphics.fast")).toString();
            }
        } else
        {
            return s1;
        }
    }

    public void loadOptions()
    {
        try
        {
            if(!optionsFile.exists())
            {
                return;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            String s = "";
            do
            {
                String s1;
                if((s1 = bufferedreader.readLine()) == null)
                {
                    break;
                }
                try
                {
                    String as[] = s1.split(":");
                    if(as[0].equals("music"))
                    {
                        musicVolume = parseFloat(as[1]);
                    }
                    if(as[0].equals("sound"))
                    {
                        soundVolume = parseFloat(as[1]);
                    }
                    if(as[0].equals("mouseSensitivity"))
                    {
                        mouseSensitivity = parseFloat(as[1]);
                    }
                    if(as[0].equals("invertYMouse"))
                    {
                        invertMouse = as[1].equals("true");
                    }
                    if(as[0].equals("viewDistance"))
                    {
                        renderDistance = Integer.parseInt(as[1]);
                    }
                    if(as[0].equals("guiScale"))
                    {
                        guiScale = Integer.parseInt(as[1]);
                    }
                    if(as[0].equals("bobView"))
                    {
                        viewBobbing = as[1].equals("true");
                    }
                    if(as[0].equals("anaglyph3d"))
                    {
                        anaglyph = as[1].equals("true");
                    }
                    if(as[0].equals("advancedOpengl"))
                    {
                        advancedOpengl = as[1].equals("true");
                    }
                    if(as[0].equals("fpsLimit"))
                    {
                        limitFramerate = Integer.parseInt(as[1]);
                        Display.setVSyncEnabled(limitFramerate == 3);
                    }
                    if(as[0].equals("difficulty"))
                    {
                        difficulty = Integer.parseInt(as[1]);
                    }
                    if(as[0].equals("fancyGraphics"))
                    {
                        fancyGraphics = as[1].equals("true");
                    }
                    if(as[0].equals("ao"))
                    {
                        ambientOcclusion = as[1].equals("true");
                        if(ambientOcclusion)
                        {
                            ofAoLevel = 1.0F;
                        } else
                        {
                            ofAoLevel = 0.0F;
                        }
                    }
                    if(as[0].equals("skin"))
                    {
                        skin = as[1];
                    }
                    if(as[0].equals("lastServer") && as.length >= 2)
                    {
                        lastServer = as[1];
                    }
                    for(int i = 0; i < keyBindings.length; i++)
                    {
                        if(as[0].equals((new StringBuilder()).append("key_").append(keyBindings[i].keyDescription).toString()))
                        {
                            keyBindings[i].keyCode = Integer.parseInt(as[1]);
                        }
                    }

                    if(as[0].equals("ofFogFancy") && as.length >= 2)
                    {
                        ofFogFancy = as[1].equals("true");
                    }
                    if(as[0].equals("ofFogStart") && as.length >= 2)
                    {
                        ofFogStart = Float.valueOf(as[1]).floatValue();
                        if(ofFogStart < 0.2F)
                        {
                            ofFogStart = 0.2F;
                        }
                        if(ofFogStart > 0.81F)
                        {
                            ofFogStart = 0.8F;
                        }
                    }
                    if(as[0].equals("ofMipmapLevel") && as.length >= 2)
                    {
                        ofMipmapLevel = Integer.valueOf(as[1]).intValue();
                        if(ofMipmapLevel < 0)
                        {
                            ofMipmapLevel = 0;
                        }
                        if(ofMipmapLevel > 4)
                        {
                            ofMipmapLevel = 4;
                        }
                    }
                    if(as[0].equals("ofMipmapLinear") && as.length >= 2)
                    {
                        ofMipmapLinear = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofLoadFar") && as.length >= 2)
                    {
                        ofLoadFar = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofPreloadedChunks") && as.length >= 2)
                    {
                        ofPreloadedChunks = Integer.valueOf(as[1]).intValue();
                        if(ofPreloadedChunks < 0)
                        {
                            ofPreloadedChunks = 0;
                        }
                        if(ofPreloadedChunks > 8)
                        {
                            ofPreloadedChunks = 8;
                        }
                    }
                    if(as[0].equals("ofOcclusionFancy") && as.length >= 2)
                    {
                        ofOcclusionFancy = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofSmoothFps") && as.length >= 2)
                    {
                        ofSmoothFps = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofSmoothInput") && as.length >= 2)
                    {
                        ofSmoothInput = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofBrightness") && as.length >= 2)
                    {
                        ofBrightness = Float.valueOf(as[1]).floatValue();
                        ofBrightness = Config.limit(ofBrightness, 0.0F, 1.0F);
                        updateWorldLightLevels();
                    }
                    if(as[0].equals("ofAoLevel") && as.length >= 2)
                    {
                        ofAoLevel = Float.valueOf(as[1]).floatValue();
                        ofAoLevel = Config.limit(ofAoLevel, 0.0F, 1.0F);
                        ambientOcclusion = ofAoLevel > 0.0F;
                    }
                    if(as[0].equals("ofClouds") && as.length >= 2)
                    {
                        ofClouds = Integer.valueOf(as[1]).intValue();
                        ofClouds = Config.limit(ofClouds, 0, 3);
                    }
                    if(as[0].equals("ofCloudsHeight") && as.length >= 2)
                    {
                        ofCloudsHeight = Float.valueOf(as[1]).floatValue();
                        ofCloudsHeight = Config.limit(ofCloudsHeight, 0.0F, 1.0F);
                    }
                    if(as[0].equals("ofTrees") && as.length >= 2)
                    {
                        ofTrees = Integer.valueOf(as[1]).intValue();
                        ofTrees = Config.limit(ofTrees, 0, 2);
                    }
                    if(as[0].equals("ofGrass") && as.length >= 2)
                    {
                        ofGrass = Integer.valueOf(as[1]).intValue();
                        ofGrass = Config.limit(ofGrass, 0, 2);
                    }
                    if(as[0].equals("ofRain") && as.length >= 2)
                    {
                        ofRain = Integer.valueOf(as[1]).intValue();
                        ofRain = Config.limit(ofRain, 0, 3);
                    }
                    if(as[0].equals("ofWater") && as.length >= 2)
                    {
                        ofWater = Integer.valueOf(as[1]).intValue();
                        ofWater = Config.limit(ofWater, 0, 3);
                    }
                    if(as[0].equals("ofAnimatedWater") && as.length >= 2)
                    {
                        ofAnimatedWater = Integer.valueOf(as[1]).intValue();
                        ofAnimatedWater = Config.limit(ofAnimatedWater, 0, 2);
                    }
                    if(as[0].equals("ofAnimatedLava") && as.length >= 2)
                    {
                        ofAnimatedLava = Integer.valueOf(as[1]).intValue();
                        ofAnimatedLava = Config.limit(ofAnimatedLava, 0, 2);
                    }
                    if(as[0].equals("ofAnimatedFire") && as.length >= 2)
                    {
                        ofAnimatedFire = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofAnimatedPortal") && as.length >= 2)
                    {
                        ofAnimatedPortal = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofAnimatedRedstone") && as.length >= 2)
                    {
                        ofAnimatedRedstone = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofAnimatedExplosion") && as.length >= 2)
                    {
                        ofAnimatedExplosion = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofAnimatedFlame") && as.length >= 2)
                    {
                        ofAnimatedFlame = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofAnimatedSmoke") && as.length >= 2)
                    {
                        ofAnimatedSmoke = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofFastDebugInfo") && as.length >= 2)
                    {
                        ofFastDebugInfo = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofAutoSaveTicks") && as.length >= 2)
                    {
                        ofAutoSaveTicks = Integer.valueOf(as[1]).intValue();
                        ofAutoSaveTicks = Config.limit(ofAutoSaveTicks, 40, 40000);
                    }
                    if(as[0].equals("ofBetterGrass") && as.length >= 2)
                    {
                        ofBetterGrass = Integer.valueOf(as[1]).intValue();
                        ofBetterGrass = Config.limit(ofBetterGrass, 1, 3);
                    }
                    if(as[0].equals("ofWeather") && as.length >= 2)
                    {
                        ofWeather = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofSky") && as.length >= 2)
                    {
                        ofSky = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofStars") && as.length >= 2)
                    {
                        ofStars = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofChunkUpdates") && as.length >= 2)
                    {
                        ofChunkUpdates = Integer.valueOf(as[1]).intValue();
                        ofChunkUpdates = Config.limit(ofChunkUpdates, 1, 5);
                    }
                    if(as[0].equals("ofChunkUpdatesDynamic") && as.length >= 2)
                    {
                        ofChunkUpdatesDynamic = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofFarView") && as.length >= 2)
                    {
                        ofFarView = Boolean.valueOf(as[1]).booleanValue();
                    }
                    if(as[0].equals("ofTime") && as.length >= 2)
                    {
                        ofTime = Integer.valueOf(as[1]).intValue();
                        ofTime = Config.limit(ofTime, 0, 2);
                    }
                    if(as[0].equals("ofClearWater") && as.length >= 2)
                    {
                        ofClearWater = Boolean.valueOf(as[1]).booleanValue();
                        updateWaterOpacity();
                    }
                }
                catch(Exception exception1)
                {
                    System.out.println((new StringBuilder()).append("Skipping bad option: ").append(s1).toString());
                }
            } while(true);
            bufferedreader.close();
        }
        catch(Exception exception)
        {
            System.out.println("Failed to load options");
            exception.printStackTrace();
        }
    }

    private float parseFloat(String s)
    {
        if(s.equals("true"))
        {
            return 1.0F;
        }
        if(s.equals("false"))
        {
            return 0.0F;
        } else
        {
            return Float.parseFloat(s);
        }
    }

    public void saveOptions()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(optionsFile));
            printwriter.println((new StringBuilder()).append("music:").append(musicVolume).toString());
            printwriter.println((new StringBuilder()).append("sound:").append(soundVolume).toString());
            printwriter.println((new StringBuilder()).append("invertYMouse:").append(invertMouse).toString());
            printwriter.println((new StringBuilder()).append("mouseSensitivity:").append(mouseSensitivity).toString());
            printwriter.println((new StringBuilder()).append("viewDistance:").append(renderDistance).toString());
            printwriter.println((new StringBuilder()).append("guiScale:").append(guiScale).toString());
            printwriter.println((new StringBuilder()).append("bobView:").append(viewBobbing).toString());
            printwriter.println((new StringBuilder()).append("anaglyph3d:").append(anaglyph).toString());
            printwriter.println((new StringBuilder()).append("advancedOpengl:").append(advancedOpengl).toString());
            printwriter.println((new StringBuilder()).append("fpsLimit:").append(limitFramerate).toString());
            printwriter.println((new StringBuilder()).append("difficulty:").append(difficulty).toString());
            printwriter.println((new StringBuilder()).append("fancyGraphics:").append(fancyGraphics).toString());
            printwriter.println((new StringBuilder()).append("ao:").append(ambientOcclusion).toString());
            printwriter.println((new StringBuilder()).append("skin:").append(skin).toString());
            printwriter.println((new StringBuilder()).append("lastServer:").append(lastServer).toString());
            for(int i = 0; i < keyBindings.length; i++)
            {
                printwriter.println((new StringBuilder()).append("key_").append(keyBindings[i].keyDescription).append(":").append(keyBindings[i].keyCode).toString());
            }

            printwriter.println((new StringBuilder()).append("ofFogFancy:").append(ofFogFancy).toString());
            printwriter.println((new StringBuilder()).append("ofFogStart:").append(ofFogStart).toString());
            printwriter.println((new StringBuilder()).append("ofMipmapLevel:").append(ofMipmapLevel).toString());
            printwriter.println((new StringBuilder()).append("ofMipmapLinear:").append(ofMipmapLinear).toString());
            printwriter.println((new StringBuilder()).append("ofLoadFar:").append(ofLoadFar).toString());
            printwriter.println((new StringBuilder()).append("ofPreloadedChunks:").append(ofPreloadedChunks).toString());
            printwriter.println((new StringBuilder()).append("ofOcclusionFancy:").append(ofOcclusionFancy).toString());
            printwriter.println((new StringBuilder()).append("ofSmoothFps:").append(ofSmoothFps).toString());
            printwriter.println((new StringBuilder()).append("ofSmoothInput:").append(ofSmoothInput).toString());
            printwriter.println((new StringBuilder()).append("ofBrightness:").append(ofBrightness).toString());
            printwriter.println((new StringBuilder()).append("ofAoLevel:").append(ofAoLevel).toString());
            printwriter.println((new StringBuilder()).append("ofClouds:").append(ofClouds).toString());
            printwriter.println((new StringBuilder()).append("ofCloudsHeight:").append(ofCloudsHeight).toString());
            printwriter.println((new StringBuilder()).append("ofTrees:").append(ofTrees).toString());
            printwriter.println((new StringBuilder()).append("ofGrass:").append(ofGrass).toString());
            printwriter.println((new StringBuilder()).append("ofRain:").append(ofRain).toString());
            printwriter.println((new StringBuilder()).append("ofWater:").append(ofWater).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedWater:").append(ofAnimatedWater).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedLava:").append(ofAnimatedLava).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedFire:").append(ofAnimatedFire).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedPortal:").append(ofAnimatedPortal).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedRedstone:").append(ofAnimatedRedstone).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedExplosion:").append(ofAnimatedExplosion).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedFlame:").append(ofAnimatedFlame).toString());
            printwriter.println((new StringBuilder()).append("ofAnimatedSmoke:").append(ofAnimatedSmoke).toString());
            printwriter.println((new StringBuilder()).append("ofFastDebugInfo:").append(ofFastDebugInfo).toString());
            printwriter.println((new StringBuilder()).append("ofAutoSaveTicks:").append(ofAutoSaveTicks).toString());
            printwriter.println((new StringBuilder()).append("ofBetterGrass:").append(ofBetterGrass).toString());
            printwriter.println((new StringBuilder()).append("ofWeather:").append(ofWeather).toString());
            printwriter.println((new StringBuilder()).append("ofSky:").append(ofSky).toString());
            printwriter.println((new StringBuilder()).append("ofStars:").append(ofStars).toString());
            printwriter.println((new StringBuilder()).append("ofChunkUpdates:").append(ofChunkUpdates).toString());
            printwriter.println((new StringBuilder()).append("ofChunkUpdatesDynamic:").append(ofChunkUpdatesDynamic).toString());
            printwriter.println((new StringBuilder()).append("ofFarView:").append(ofFarView).toString());
            printwriter.println((new StringBuilder()).append("ofTime:").append(ofTime).toString());
            printwriter.println((new StringBuilder()).append("ofClearWater:").append(ofClearWater).toString());
            printwriter.close();
        }
        catch(Exception exception)
        {
            System.out.println("Failed to save options");
            exception.printStackTrace();
        }
    }

    private static final String RENDER_DISTANCES[] = {
        "options.renderDistance.far", "options.renderDistance.normal", "options.renderDistance.short", "options.renderDistance.tiny"
    };
    private static final String DIFFICULTIES[] = {
        "options.difficulty.peaceful", "options.difficulty.easy", "options.difficulty.normal", "options.difficulty.hard"
    };
    private static final String GUISCALES[] = {
        "options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"
    };
    private static final String LIMIT_FRAMERATES[] = {
        "performance.max", "performance.balanced", "performance.powersaver"
    };
    public float musicVolume;
    public float soundVolume;
    public float mouseSensitivity;
    public boolean invertMouse;
    public int renderDistance;
    public boolean viewBobbing;
    public boolean anaglyph;
    public boolean advancedOpengl;
    public int limitFramerate;
    public boolean fancyGraphics;
    public boolean ambientOcclusion;
    public boolean ofFogFancy;
    public float ofFogStart;
    public int ofMipmapLevel;
    public boolean ofMipmapLinear;
    public boolean ofLoadFar;
    public int ofPreloadedChunks;
    public boolean ofOcclusionFancy;
    public boolean ofSmoothFps;
    public boolean ofSmoothInput;
    public float ofBrightness;
    public float ofAoLevel;
    public int ofClouds;
    public float ofCloudsHeight;
    public int ofTrees;
    public int ofGrass;
    public int ofRain;
    public int ofWater;
    public int ofBetterGrass;
    public int ofAutoSaveTicks;
    public boolean ofFastDebugInfo;
    public boolean ofWeather;
    public boolean ofSky;
    public boolean ofStars;
    public int ofChunkUpdates;
    public boolean ofChunkUpdatesDynamic;
    public boolean ofFarView;
    public int ofTime;
    public boolean ofClearWater;
    public int ofAnimatedWater;
    public int ofAnimatedLava;
    public boolean ofAnimatedFire;
    public boolean ofAnimatedPortal;
    public boolean ofAnimatedRedstone;
    public boolean ofAnimatedExplosion;
    public boolean ofAnimatedFlame;
    public boolean ofAnimatedSmoke;
    public static final int DEFAULT = 0;
    public static final int FAST = 1;
    public static final int FANCY = 2;
    public static final int OFF = 3;
    public static final int ANIM_ON = 0;
    public static final int ANIM_GENERATED = 1;
    public static final int ANIM_OFF = 2;
    public KeyBinding ofKeyBindZoom;
    public String skin;
    public KeyBinding keyBindForward;
    public KeyBinding keyBindLeft;
    public KeyBinding keyBindBack;
    public KeyBinding keyBindRight;
    public KeyBinding keyBindJump;
    public KeyBinding keyBindInventory;
    public KeyBinding keyBindDrop;
    public KeyBinding keyBindChat;
    public KeyBinding keyBindToggleFog;
    public KeyBinding keyBindSneak;
    public KeyBinding keyBindings[];
    protected Minecraft mc;
    private File optionsFile;
    public int difficulty;
    public boolean hideGUI;
    public boolean thirdPersonView;
    public boolean showDebugInfo;
    public String lastServer;
    public boolean field_22275_C;
    public boolean smoothCamera;
    public boolean field_22273_E;
    public float field_22272_F;
    public float field_22271_G;
    public int guiScale;

}
