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

public class GuiDetailSettingsOF extends GuiScreen
{

    public GuiDetailSettingsOF(GuiScreen guiscreen, GameSettings gamesettings)
    {
        lastMouseX = 0;
        lastMouseY = 0;
        mouseStillTime = 0L;
        title = "Detail Settings";
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
        if(s.equals("Clouds"))
        {
            return (new String[] {
                "Clouds", "  Default - as set by setting Graphics", "  Fast - lower quality, faster", "  Fancy - higher quality, slower", "  OFF - no clouds, fastest", "Fast clouds are rendered 2D.", "Fancy clouds are rendered 3D."
            });
        }
        if(s.equals("Cloud Height"))
        {
            return (new String[] {
                "Cloud Height", "  OFF - default height", "  100% - above world height limit"
            });
        }
        if(s.equals("Trees"))
        {
            return (new String[] {
                "Trees", "  Default - as set by setting Graphics", "  Fast - lower quality, faster", "  Fancy - higher quality, slower", "Fast trees have opaque leaves.", "Fancy trees have transparent leaves."
            });
        }
        if(s.equals("Grass"))
        {
            return (new String[] {
                "Grass", "  Default - as set by setting Graphics", "  Fast - lower quality, faster", "  Fancy - higher quality, slower", "Fast grass uses default side texture.", "Fancy grass uses biome side texture."
            });
        }
        if(s.equals("Water"))
        {
            return (new String[] {
                "Water", "  Default - as set by setting Graphics", "  Fast  - lower quality, faster", "  Fancy - higher quality, slower", "Fast water (1 pass) has some visual artifacts", "Fancy water (2 pass) has no visual artifacts"
            });
        }
        if(s.equals("Rain & Snow"))
        {
            return (new String[] {
                "Rain & Snow", "  Default - as set by setting Graphics", "  Fast  - light rain/snow, faster", "  Fancy - heavy rain/snow, slower", "  OFF - no rain/snow, fastest", "When rain is OFF the splashes and rain sounds", "are still active."
            });
        }
        if(s.equals("Sky"))
        {
            return (new String[] {
                "Sky", "  ON - sky is visible, slower", "  OFF  - sky is not visible, faster", "When sky is OFF the moon and sun are still visible."
            });
        }
        if(s.equals("Stars"))
        {
            return (new String[] {
                "Stars", "  ON - stars are visible, slower", "  OFF  - stars are not visible, faster"
            });
        }
        if(s.equals("Autosave"))
        {
            return (new String[] {
                "Autosave interval", "Default autosave interval (2s) is NOT RECOMMENDED.", "Autosave causes the famous Lag Spike of Death."
            });
        }
        if(s.equals("Fast Debug Info"))
        {
            return (new String[] {
                "Fast Debug Info", " OFF - default debug info screen, slower", " ON - debug info screen without lagometer, faster", "Removes the lagometer from the debug screen (F3)."
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
            EnumOptions.CLOUDS, EnumOptions.CLOUD_HEIGHT, EnumOptions.TREES, EnumOptions.GRASS, EnumOptions.WATER, EnumOptions.RAIN, EnumOptions.SKY, EnumOptions.STARS, EnumOptions.CLEAR_WATER
        });
    }
}
