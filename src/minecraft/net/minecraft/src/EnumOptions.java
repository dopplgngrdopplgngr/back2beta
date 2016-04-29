// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


public enum EnumOptions
{
    MUSIC("MUSIC", 0, "MUSIC", 0, "options.music", true, false),
    SOUND ("SOUND", 1, "SOUND", 1, "options.sound", true, false),
    INVERT_MOUSE ("INVERT_MOUSE", 2, "INVERT_MOUSE", 2, "options.invertMouse", false, true),
    SENSITIVITY ("SENSITIVITY", 3, "SENSITIVITY", 3, "options.sensitivity", true, false),
    RENDER_DISTANCE ("RENDER_DISTANCE", 4, "RENDER_DISTANCE", 4, "options.renderDistance", false, false),
    VIEW_BOBBING ("VIEW_BOBBING", 5, "VIEW_BOBBING", 5, "options.viewBobbing", false, true),
    ANAGLYPH ("ANAGLYPH", 6, "ANAGLYPH", 6, "options.anaglyph", false, true),
    ADVANCED_OPENGL ("ADVANCED_OPENGL", 7, "ADVANCED_OPENGL", 7, "options.advancedOpengl", false, true),
    FRAMERATE_LIMIT ("FRAMERATE_LIMIT", 8, "FRAMERATE_LIMIT", 8, "options.framerateLimit", false, false),
    DIFFICULTY ("DIFFICULTY", 9, "DIFFICULTY", 9, "options.difficulty", false, false),
    GRAPHICS ("GRAPHICS", 10, "GRAPHICS", 10, "options.graphics", false, false),
    AMBIENT_OCCLUSION ("AMBIENT_OCCLUSION", 11, "AMBIENT_OCCLUSION", 11, "options.ao", false, true),
    GUI_SCALE ("GUI_SCALE", 12, "GUI_SCALE", 12, "options.guiScale", false, false),
    FOG_FANCY ("FOG_FANCY", 13, "FOG_FANCY", 13, "Fog", false, false),
    FOG_START ("FOG_START", 14, "FOG_START", 14, "Fog Start", false, false),
    MIPMAP_LEVEL ("MIPMAP_LEVEL", 15, "MIPMAP_LEVEL", 15, "Mipmap Level", false, false),
    MIPMAP_TYPE ("MIPMAP_TYPE", 16, "MIPMAP_TYPE", 16, "Mipmap Type", false, false),
    LOAD_FAR ("LOAD_FAR", 17, "LOAD_FAR", 18, "Load Far", false, false),
    PRELOADED_CHUNKS ("PRELOADED_CHUNKS", 18, "PRELOADED_CHUNKS", 19, "Preloaded Chunks", false, false),
    SMOOTH_FPS ("SMOOTH_FPS", 19, "SMOOTH_FPS", 20, "Smooth FPS", false, false),
    BRIGHTNESS ("BRIGHTNESS", 20, "BRIGHTNESS", 21, "Brightness", true, false),
    CLOUDS ("CLOUDS", 21, "CLOUDS", 22, "Clouds", false, false),
    CLOUD_HEIGHT ("CLOUD_HEIGHT", 22, "CLOUD_HEIGHT", 23, "Cloud Height", true, false),
    TREES ("TREES", 23, "TREES", 24, "Trees", false, false),
    GRASS ("GRASS", 24, "GRASS", 25, "Grass", false, false),
    RAIN ("RAIN", 25, "RAIN", 27, "Rain & Snow", false, false),
    WATER ("WATER", 26, "RAIN", 28, "Water", false, false),
    ANIMATED_WATER ("ANIMATED_WATER", 27, "ANIMATED_WATER", 29, "Water Animated", false, false),
    ANIMATED_LAVA ("ANIMATED_LAVA", 28, "ANIMATED_LAVA", 30, "Lava Animated", false, false),
    ANIMATED_FIRE ("ANIMATED_FIRE", 29, "ANIMATED_FLAMES", 31, "Fire Animated", false, false),
    ANIMATED_PORTAL ("ANIMATED_PORTAL", 30, "ANIMATED_PORTAL", 32, "Portal Animated", false, false),
    AO_LEVEL ("AO_LEVEL", 31, "AO_LEVEL", 33, "Smooth Lighting", true, false),
    FAST_DEBUG_INFO ("FAST_DEBUG_INFO", 32, "FAST_DEBUG_INFO", 34, "Fast Debug Info", false, false),
    AUTOSAVE_TICKS ("AUTOSAVE_TICKS", 33, "AUTOSAVE_TICKS", 35, "Autosave", false, false),
    BETTER_GRASS ("BETTER_GRASS", 34, "BETTER_GRASS", 36, "Better Grass", false, false),
    ANIMATED_REDSTONE ("ANIMATED_REDSTONE", 35, "ANIMATED_REDSTONE", 37, "Redstone Animated", false, false),
    ANIMATED_EXPLOSION ("ANIMATED_EXPLOSION", 36, "ANIMATED_EXPLOSION", 38, "Explosion Animated", false, false),
    ANIMATED_FLAME ("ANIMATED_FLAME", 37, "ANIMATED_FLAME", 39, "Flame Animated", false, false),
    ANIMATED_SMOKE ("ANIMATED_SMOKE", 38, "ANIMATED_SMOKE", 40, "Smoke Animated", false, false),
    WEATHER ("WEATHER", 39, "WEATHER", 41, "Weather", false, false),
    SKY ("SKY", 40, "SKY", 42, "Sky", false, false),
    STARS ("STARS", 41, "STARS", 43, "Stars", false, false),
    FAR_VIEW ("FAR_VIEW", 42, "FAR_VIEW", 44, "Far View", false, false),
    CHUNK_UPDATES ("CHUNK_UPDATES", 43, "CHUNK_UPDATES", 45, "Chunk Updates", false, false),
    CHUNK_UPDATES_DYNAMIC ("CHUNK_UPDATES_DYNAMIC", 44, "CHUNK_UPDATES_DYNAMIC", 46, "Dynamic Updates", false, false),
    TIME ("TIME", 45, "TIME", 47, "Time", false, false),
    CLEAR_WATER ("CLEAR_WATER", 46, "CLEAR_WATER", 48, "Clear Water", false, false),
    SMOOTH_INPUT ("SMOOTH_INPUT", 47, "SMOOTH_INPUT", 49, "Smooth Input", false, false);	
  
    public static EnumOptions getEnumOptions(int i)
    {
        EnumOptions aenumoptions[] = values();
        int j = aenumoptions.length;
        for(int k = 0; k < j; k++)
        {
            EnumOptions enumoptions = aenumoptions[k];
            if(enumoptions.returnEnumOrdinal() == i)
            {
                return enumoptions;
            }
        }

        return null;
    }

    private EnumOptions(String s, int i, String s1, int j, String s2, boolean flag, boolean flag1)
    {
//      super(s, i);
        enumString = s2;
        enumFloat = flag;
        enumBoolean = flag1;
    }

    public boolean getEnumFloat()
    {
        return enumFloat;
    }

    public boolean getEnumBoolean()
    {
        return enumBoolean;
    }

    public int returnEnumOrdinal()
    {
        return ordinal();
    }

    public String getEnumString()
    {
        return enumString;
    }
/*
    public static final EnumOptions MUSIC;
    public static final EnumOptions SOUND;
    public static final EnumOptions INVERT_MOUSE;
    public static final EnumOptions SENSITIVITY;
    public static final EnumOptions RENDER_DISTANCE;
    public static final EnumOptions VIEW_BOBBING;
    public static final EnumOptions ANAGLYPH;
    public static final EnumOptions ADVANCED_OPENGL;
    public static final EnumOptions FRAMERATE_LIMIT;
    public static final EnumOptions DIFFICULTY;
    public static final EnumOptions GRAPHICS;
    public static final EnumOptions AMBIENT_OCCLUSION;
    public static final EnumOptions GUI_SCALE;
    public static final EnumOptions FOG_FANCY;
    public static final EnumOptions FOG_START;
    public static final EnumOptions MIPMAP_LEVEL;
    public static final EnumOptions MIPMAP_TYPE;
    public static final EnumOptions LOAD_FAR;
    public static final EnumOptions PRELOADED_CHUNKS;
    public static final EnumOptions SMOOTH_FPS;
    public static final EnumOptions BRIGHTNESS;
    public static final EnumOptions CLOUDS;
    public static final EnumOptions CLOUD_HEIGHT;
    public static final EnumOptions TREES;
    public static final EnumOptions GRASS;
    public static final EnumOptions RAIN;
    public static final EnumOptions WATER;
    public static final EnumOptions ANIMATED_WATER;
    public static final EnumOptions ANIMATED_LAVA;
    public static final EnumOptions ANIMATED_FIRE;
    public static final EnumOptions ANIMATED_PORTAL;
    public static final EnumOptions AO_LEVEL;
    public static final EnumOptions FAST_DEBUG_INFO;
    public static final EnumOptions AUTOSAVE_TICKS;
    public static final EnumOptions BETTER_GRASS;
    public static final EnumOptions ANIMATED_REDSTONE;
    public static final EnumOptions ANIMATED_EXPLOSION;
    public static final EnumOptions ANIMATED_FLAME;
    public static final EnumOptions ANIMATED_SMOKE;
    public static final EnumOptions WEATHER;
    public static final EnumOptions SKY;
    public static final EnumOptions STARS;
    public static final EnumOptions FAR_VIEW;
    public static final EnumOptions CHUNK_UPDATES;
    public static final EnumOptions CHUNK_UPDATES_DYNAMIC;
    public static final EnumOptions TIME;
    public static final EnumOptions CLEAR_WATER;
    public static final EnumOptions SMOOTH_INPUT;
    */
    private final boolean enumFloat;
    private final boolean enumBoolean;
    private final String enumString;
    private static final EnumOptions $VALUES[]; /* synthetic field */

    static 
    {
 /*     MUSIC ("MUSIC", 0, "MUSIC", 0, "options.music", true, false);
        SOUND ("SOUND", 1, "SOUND", 1, "options.sound", true, false);
        INVERT_MOUSE ("INVERT_MOUSE", 2, "INVERT_MOUSE", 2, "options.invertMouse", false, true);
        SENSITIVITY ("SENSITIVITY", 3, "SENSITIVITY", 3, "options.sensitivity", true, false);
        RENDER_DISTANCE ("RENDER_DISTANCE", 4, "RENDER_DISTANCE", 4, "options.renderDistance", false, false);
        VIEW_BOBBING ("VIEW_BOBBING", 5, "VIEW_BOBBING", 5, "options.viewBobbing", false, true);
        ANAGLYPH ("ANAGLYPH", 6, "ANAGLYPH", 6, "options.anaglyph", false, true);
        ADVANCED_OPENGL ("ADVANCED_OPENGL", 7, "ADVANCED_OPENGL", 7, "options.advancedOpengl", false, true);
        FRAMERATE_LIMIT ("FRAMERATE_LIMIT", 8, "FRAMERATE_LIMIT", 8, "options.framerateLimit", false, false);
        DIFFICULTY ("DIFFICULTY", 9, "DIFFICULTY", 9, "options.difficulty", false, false);
        GRAPHICS ("GRAPHICS", 10, "GRAPHICS", 10, "options.graphics", false, false);
        AMBIENT_OCCLUSION ("AMBIENT_OCCLUSION", 11, "AMBIENT_OCCLUSION", 11, "options.ao", false, true);
        GUI_SCALE ("GUI_SCALE", 12, "GUI_SCALE", 12, "options.guiScale", false, false);
        FOG_FANCY ("FOG_FANCY", 13, "FOG_FANCY", 13, "Fog", false, false);
        FOG_START ("FOG_START", 14, "FOG_START", 14, "Fog Start", false, false);
        MIPMAP_LEVEL ("MIPMAP_LEVEL", 15, "MIPMAP_LEVEL", 15, "Mipmap Level", false, false);
        MIPMAP_TYPE ("MIPMAP_TYPE", 16, "MIPMAP_TYPE", 16, "Mipmap Type", false, false);
        LOAD_FAR ("LOAD_FAR", 17, "LOAD_FAR", 18, "Load Far", false, false);
        PRELOADED_CHUNKS ("PRELOADED_CHUNKS", 18, "PRELOADED_CHUNKS", 19, "Preloaded Chunks", false, false);
        SMOOTH_FPS ("SMOOTH_FPS", 19, "SMOOTH_FPS", 20, "Smooth FPS", false, false);
        BRIGHTNESS ("BRIGHTNESS", 20, "BRIGHTNESS", 21, "Brightness", true, false);
        CLOUDS ("CLOUDS", 21, "CLOUDS", 22, "Clouds", false, false);
        CLOUD_HEIGHT ("CLOUD_HEIGHT", 22, "CLOUD_HEIGHT", 23, "Cloud Height", true, false);
        TREES ("TREES", 23, "TREES", 24, "Trees", false, false);
        GRASS ("GRASS", 24, "GRASS", 25, "Grass", false, false);
        RAIN ("RAIN", 25, "RAIN", 27, "Rain & Snow", false, false);
        WATER ("WATER", 26, "RAIN", 28, "Water", false, false);
        ANIMATED_WATER ("ANIMATED_WATER", 27, "ANIMATED_WATER", 29, "Water Animated", false, false);
        ANIMATED_LAVA ("ANIMATED_LAVA", 28, "ANIMATED_LAVA", 30, "Lava Animated", false, false);
        ANIMATED_FIRE ("ANIMATED_FIRE", 29, "ANIMATED_FLAMES", 31, "Fire Animated", false, false);
        ANIMATED_PORTAL ("ANIMATED_PORTAL", 30, "ANIMATED_PORTAL", 32, "Portal Animated", false, false);
        AO_LEVEL ("AO_LEVEL", 31, "AO_LEVEL", 33, "Smooth Lighting", true, false);
        FAST_DEBUG_INFO ("FAST_DEBUG_INFO", 32, "FAST_DEBUG_INFO", 34, "Fast Debug Info", false, false);
        AUTOSAVE_TICKS ("AUTOSAVE_TICKS", 33, "AUTOSAVE_TICKS", 35, "Autosave", false, false);
        BETTER_GRASS ("BETTER_GRASS", 34, "BETTER_GRASS", 36, "Better Grass", false, false);
        ANIMATED_REDSTONE ("ANIMATED_REDSTONE", 35, "ANIMATED_REDSTONE", 37, "Redstone Animated", false, false);
        ANIMATED_EXPLOSION ("ANIMATED_EXPLOSION", 36, "ANIMATED_EXPLOSION", 38, "Explosion Animated", false, false);
        ANIMATED_FLAME ("ANIMATED_FLAME", 37, "ANIMATED_FLAME", 39, "Flame Animated", false, false);
        ANIMATED_SMOKE ("ANIMATED_SMOKE", 38, "ANIMATED_SMOKE", 40, "Smoke Animated", false, false);
        WEATHER ("WEATHER", 39, "WEATHER", 41, "Weather", false, false);
        SKY ("SKY", 40, "SKY", 42, "Sky", false, false);
        STARS ("STARS", 41, "STARS", 43, "Stars", false, false);
        FAR_VIEW ("FAR_VIEW", 42, "FAR_VIEW", 44, "Far View", false, false);
        CHUNK_UPDATES ("CHUNK_UPDATES", 43, "CHUNK_UPDATES", 45, "Chunk Updates", false, false);
        CHUNK_UPDATES_DYNAMIC ("CHUNK_UPDATES_DYNAMIC", 44, "CHUNK_UPDATES_DYNAMIC", 46, "Dynamic Updates", false, false);
        TIME ("TIME", 45, "TIME", 47, "Time", false, false);
        CLEAR_WATER ("CLEAR_WATER", 46, "CLEAR_WATER", 48, "Clear Water", false, false);
        SMOOTH_INPUT ("SMOOTH_INPUT", 47, "SMOOTH_INPUT", 49, "Smooth Input", false, false);
        */
        $VALUES = (new EnumOptions[] {
            MUSIC, SOUND, INVERT_MOUSE, SENSITIVITY, RENDER_DISTANCE, VIEW_BOBBING, ANAGLYPH, ADVANCED_OPENGL, FRAMERATE_LIMIT, DIFFICULTY, 
            GRAPHICS, AMBIENT_OCCLUSION, GUI_SCALE, FOG_FANCY, FOG_START, MIPMAP_LEVEL, MIPMAP_TYPE, LOAD_FAR, PRELOADED_CHUNKS, SMOOTH_FPS, 
            BRIGHTNESS, CLOUDS, CLOUD_HEIGHT, TREES, GRASS, RAIN, WATER, ANIMATED_WATER, ANIMATED_LAVA, ANIMATED_FIRE, 
            ANIMATED_PORTAL, AO_LEVEL, FAST_DEBUG_INFO, AUTOSAVE_TICKS, BETTER_GRASS, ANIMATED_REDSTONE, ANIMATED_EXPLOSION, ANIMATED_FLAME, ANIMATED_SMOKE, WEATHER, 
            SKY, STARS, FAR_VIEW, CHUNK_UPDATES, CHUNK_UPDATES_DYNAMIC, TIME, CLEAR_WATER, SMOOTH_INPUT
        });
    }
}
