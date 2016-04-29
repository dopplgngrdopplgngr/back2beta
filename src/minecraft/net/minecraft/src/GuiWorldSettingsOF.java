// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            GuiScreen, StringTranslate, EnumOptions, GuiSmallButton, 
//            GameSettings, GuiSlider, GuiButton, ScaledResolution, 
//            FontRenderer

public class GuiWorldSettingsOF extends GuiScreen
{

    public GuiWorldSettingsOF(GuiScreen guiscreen, GameSettings gamesettings)
    {
        lastMouseX = 0;
        lastMouseY = 0;
        mouseStillTime = 0L;
        title = "World Settings";
        prevScreen = guiscreen;
        settings = gamesettings;
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        int i = 0;
        EnumOptions aenumoptions[] = enumOptions;
        int j = aenumoptions.length;
        for(int k = 0; k < j; k++)
        {
            EnumOptions enumoptions = aenumoptions[k];
            int l = (width / 2 - 155) + (i % 2) * 160;
            int i1 = (height / 6 + 21 * (i / 2)) - 10;
            if(!enumoptions.getEnumFloat())
            {
                controlList.add(new GuiSmallButton(enumoptions.returnEnumOrdinal(), l, i1, enumoptions, settings.getKeyBinding(enumoptions)));
            } else
            {
                controlList.add(new GuiSlider(enumoptions.returnEnumOrdinal(), l, i1, enumoptions, settings.getKeyBinding(enumoptions), settings.getOptionFloatValue(enumoptions)));
            }
            i++;
        }

        controlList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168 + 11, stringtranslate.translateKey("gui.done")));
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id < 100 && (guibutton instanceof GuiSmallButton))
        {
            settings.setOptionValue(((GuiSmallButton)guibutton).returnEnumOptions(), 1);
            guibutton.displayString = settings.getKeyBinding(EnumOptions.getEnumOptions(guibutton.id));
        }
        if(guibutton.id == 200)
        {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(prevScreen);
        }
        if(guibutton.id != EnumOptions.CLOUD_HEIGHT.ordinal())
        {
            ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int i = scaledresolution.getScaledWidth();
            int j = scaledresolution.getScaledHeight();
            setWorldAndResolution(mc, i, j);
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, title, width / 2, 20, 0xffffff);
        super.drawScreen(i, j, f);
        if(Math.abs(i - lastMouseX) > 5 || Math.abs(j - lastMouseY) > 5)
        {
            lastMouseX = i;
            lastMouseY = j;
            mouseStillTime = System.currentTimeMillis();
            return;
        }
        int k = 700;
        if(System.currentTimeMillis() < mouseStillTime + (long)k)
        {
            return;
        }
        int l = width / 2 - 150;
        int i1 = height / 6 - 5;
        if(j <= i1 + 98)
        {
            i1 += 105;
        }
        int j1 = l + 150 + 150;
        int k1 = i1 + 84 + 10;
        GuiButton guibutton = getSelectedButton(i, j);
        if(guibutton != null)
        {
            String s = getButtonName(guibutton.displayString);
            String as[] = getTooltipLines(s);
            if(as == null)
            {
                return;
            }
            drawGradientRect(l, i1, j1, k1, 0xe0000000, 0xe0000000);
            for(int l1 = 0; l1 < as.length; l1++)
            {
                String s1 = as[l1];
                fontRenderer.drawStringWithShadow(s1, l + 5, i1 + 5 + l1 * 11, 0xdddddd);
            }

        }
    }

    private String[] getTooltipLines(String s)
    {
        if(s.equals("Load Far"))
        {
            return (new String[] {
                "Loads the world chunks at distance Far.", "Switching the render distance does not cause all chunks ", "to be loaded again.", "  OFF - world chunks loaded up to render distance", "  ON - world chunks loaded at distance Far, allows", "       fast render distance switching"
            });
        }
        if(s.equals("Preloaded Chunks"))
        {
            return (new String[] {
                "Defines an area in which no chunks will be loaded", "  OFF - after 5m new chunks will be loaded", "  2 - after 32m  new chunks will be loaded", "  8 - after 128m new chunks will be loaded", "Higher values need more time to load all the chunks"
            });
        }
        if(s.equals("Chunk Updates"))
        {
            return (new String[] {
                "Chunk updates per frame", " 1 - (default) slower world loading, higher FPS", " 3 - faster world loading, lower FPS", " 5 - fastest world loading, lowest FPS"
            });
        }
        if(s.equals("Dynamic Updates"))
        {
            return (new String[] {
                "Chunk updates per frame", " OFF - (default) standard chunk updates per frame", " ON - more updates while the player is standing still", "Dynamic updates force more chunk updates while", "the player is standing still to load the world faster."
            });
        }
        if(s.equals("Far View"))
        {
            return (new String[] {
                "Far View", " OFF - (default) standard view distance", " ON - 3x view distance", "Far View is very resource demanding!", "3x view distance => 9x chunks to be loaded => FPS / 9", "Standard view distances: 32, 64, 128, 256", "Far view distances: 96, 192, 384, 512"
            });
        }
        if(s.equals("Time"))
        {
            return (new String[] {
                "Time", " Default - normal day/night cycles", " Day Only - day only", " Night Only - night only"
            });
        }
        if(s.equals("Weather"))
        {
            return (new String[] {
                "Weather", "  ON - weather is active, slower", "  OFF  - weather is not active, faster", "The weather controls rain, snow and thunderstorms."
            });
        } else
        {
            return null;
        }
    }

    private String getButtonName(String s)
    {
        int i = s.indexOf(':');
        if(i < 0)
        {
            return s;
        } else
        {
            return s.substring(0, i);
        }
    }

    private GuiButton getSelectedButton(int i, int j)
    {
        for(int k = 0; k < controlList.size(); k++)
        {
            GuiButton guibutton = (GuiButton)controlList.get(k);
            boolean flag = i >= guibutton.xPosition && j >= guibutton.yPosition && i < guibutton.xPosition + guibutton.width && j < guibutton.yPosition + guibutton.height;
            if(flag)
            {
                return guibutton;
            }
        }

        return null;
    }

    private GuiScreen prevScreen;
    protected String title;
    private GameSettings settings;
    private static EnumOptions enumOptions[];
    private int lastMouseX;
    private int lastMouseY;
    private long mouseStillTime;

    static 
    {
        enumOptions = (new EnumOptions[] {
            EnumOptions.LOAD_FAR, EnumOptions.PRELOADED_CHUNKS, EnumOptions.CHUNK_UPDATES, EnumOptions.CHUNK_UPDATES_DYNAMIC, EnumOptions.WEATHER, EnumOptions.TIME, EnumOptions.FAR_VIEW
        });
    }
}
