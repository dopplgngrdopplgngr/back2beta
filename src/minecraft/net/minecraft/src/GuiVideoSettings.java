// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            GuiScreen, StringTranslate, EnumOptions, GuiSmallButton, 
//            GameSettings, GuiSlider, GuiButton, GuiAnimationSettingsOF, 
//            GuiDetailSettingsOF, GuiWorldSettingsOF, GuiOtherSettingsOF, ScaledResolution, 
//            FontRenderer

public class GuiVideoSettings extends GuiScreen
{

    public GuiVideoSettings(GuiScreen guiscreen, GameSettings gamesettings)
    {
        lastMouseX = 0;
        lastMouseY = 0;
        mouseStillTime = 0L;
        field_22107_a = "Video Settings";
        field_22110_h = guiscreen;
        guiGameSettings = gamesettings;
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        field_22107_a = stringtranslate.translateKey("options.videoTitle");
        int i = 0;
        EnumOptions aenumoptions[] = field_22108_k;
        int j = aenumoptions.length;
        for(int k = 0; k < j; k++)
        {
            EnumOptions enumoptions = aenumoptions[k];
            int j1 = (width / 2 - 155) + (i % 2) * 160;
            int k1 = (height / 6 + 21 * (i / 2)) - 10;
            if(!enumoptions.getEnumFloat())
            {
                controlList.add(new GuiSmallButton(enumoptions.returnEnumOrdinal(), j1, k1, enumoptions, guiGameSettings.getKeyBinding(enumoptions)));
            } else
            {
                controlList.add(new GuiSlider(enumoptions.returnEnumOrdinal(), j1, k1, enumoptions, guiGameSettings.getKeyBinding(enumoptions), guiGameSettings.getOptionFloatValue(enumoptions)));
            }
            i++;
        }

        int l = (height / 6 + 21 * (i / 2)) - 10;
        int i1 = 0;
        i1 = (width / 2 - 155) + 0;
        controlList.add(new GuiSmallButton(100, i1, l, "Animations..."));
        i1 = (width / 2 - 155) + 160;
        controlList.add(new GuiSmallButton(101, i1, l, "Details..."));
        l += 21;
        i1 = (width / 2 - 155) + 0;
        controlList.add(new GuiSmallButton(102, i1, l, "World..."));
        i1 = (width / 2 - 155) + 160;
        controlList.add(new GuiSmallButton(103, i1, l, "Other..."));
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
            guiGameSettings.setOptionValue(((GuiSmallButton)guibutton).returnEnumOptions(), 1);
            guibutton.displayString = guiGameSettings.getKeyBinding(EnumOptions.getEnumOptions(guibutton.id));
        }
        if(guibutton.id == 200)
        {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(field_22110_h);
        }
        if(guibutton.id == 100)
        {
            mc.gameSettings.saveOptions();
            GuiAnimationSettingsOF guianimationsettingsof = new GuiAnimationSettingsOF(this, guiGameSettings);
            mc.displayGuiScreen(guianimationsettingsof);
        }
        if(guibutton.id == 101)
        {
            mc.gameSettings.saveOptions();
            GuiDetailSettingsOF guidetailsettingsof = new GuiDetailSettingsOF(this, guiGameSettings);
            mc.displayGuiScreen(guidetailsettingsof);
        }
        if(guibutton.id == 102)
        {
            mc.gameSettings.saveOptions();
            GuiWorldSettingsOF guiworldsettingsof = new GuiWorldSettingsOF(this, guiGameSettings);
            mc.displayGuiScreen(guiworldsettingsof);
        }
        if(guibutton.id == 103)
        {
            mc.gameSettings.saveOptions();
            GuiOtherSettingsOF guiothersettingsof = new GuiOtherSettingsOF(this, guiGameSettings);
            mc.displayGuiScreen(guiothersettingsof);
        }
        if(guibutton.id == EnumOptions.BRIGHTNESS.ordinal() || guibutton.id == EnumOptions.AO_LEVEL.ordinal())
        {
            return;
        } else
        {
            ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int i = scaledresolution.getScaledWidth();
            int j = scaledresolution.getScaledHeight();
            setWorldAndResolution(mc, i, j);
            return;
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, field_22107_a, width / 2, 20, 0xffffff);
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
        if(s.equals("Graphics"))
        {
            return (new String[] {
                "Visual quality", "  Fast  - lower quality, faster", "  Fancy - higher quality, slower", "Changes the appearance of clouds, leaves, water,", "shadows and grass sides."
            });
        }
        if(s.equals("Render Distance"))
        {
            return (new String[] {
                "Visible distance", "  Far - 256m (slower)", "  Normal - 128m", "  Short - 64m (faster)", "  Tiny - 32m (fastest)"
            });
        }
        if(s.equals("Smooth Lighting"))
        {
            return (new String[] {
                "Smooth lighting", "  OFF - no smooth lighting (faster)", "  1% - light smooth lighting (slower)", "  100% - dark smooth lighting (slower)"
            });
        }
        if(s.equals("Performance"))
        {
            return (new String[] {
                "FPS Limit", "  Max FPS - no limit (fastest)", "  Balanced - limit 120 FPS (slower)", "  Power saver - limit 40 FPS (slowest)", "  VSync - limit to monitor framerate (60, 30, 20)", "Balanced and Power saver decrease the FPS even if", "the limit value is not reached."
            });
        }
        if(s.equals("3D Anaglyph"))
        {
            return (new String[] {
                "3D mode used with red-cyan 3D glasses."
            });
        }
        if(s.equals("View Bobbing"))
        {
            return (new String[] {
                "More realistic movement.", "When using mipmaps set it to OFF for best results."
            });
        }
        if(s.equals("GUI Scale"))
        {
            return (new String[] {
                "GUI Scale", "Smaller GUI might be faster"
            });
        }
        if(s.equals("Advanced OpenGL"))
        {
            return (new String[] {
                "Detect and render only visible geometry", "  OFF - all geometry is rendered (slower)", "  Fast - ony visible geometry is rendered (fastest)", "  Fancy - conservative, avoids visual artifacts (faster)", "The option is available only if it is supported by the ", "graphic card."
            });
        }
        if(s.equals("Fog"))
        {
            return (new String[] {
                "Fog type", "  Fast - faster fog", "  Fancy - slower fog, looks better", "The fancy fog is available only if it is supported by the ", "graphic card."
            });
        }
        if(s.equals("Fog Start"))
        {
            return (new String[] {
                "Fog start", "  0.2 - the fog starts near the player", "  0.8 - the fog starts far from the player", "This option usually does not affect the performance."
            });
        }
        if(s.equals("Mipmap Level"))
        {
            return (new String[] {
                "Visual effect which makes distant objects look better", "by smoothing the texture details", "  OFF - no smoothing", "  1 - minimum smoothing", "  4 - maximum smoothing", "This option usually does not affect the performance."
            });
        }
        if(s.equals("Mipmap Type"))
        {
            return (new String[] {
                "Visual effect which makes distant objects look better", "by smoothing the texture details", "  Nearest - rough smoothing", "  Linear - fine smoothing", "This option usually does not affect the performance."
            });
        }
        if(s.equals("Better Grass"))
        {
            return (new String[] {
                "Better Grass", "  OFF - default side grass texture, fastest", "  Fast - full side grass texture, slower", "  Fancy - dynamic side grass texture, slowest"
            });
        }
        if(s.equals("Brightness"))
        {
            return (new String[] {
                "Increases the brightness of darker objects", "  OFF - standard brightness", "  100% - maximum brightness for darker objects", "This options does not change the brightness of ", "fully black objects"
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

    private GuiScreen field_22110_h;
    protected String field_22107_a;
    private GameSettings guiGameSettings;
    private static EnumOptions field_22108_k[];
    private int lastMouseX;
    private int lastMouseY;
    private long mouseStillTime;

    static 
    {
        field_22108_k = (new EnumOptions[] {
            EnumOptions.GRAPHICS, EnumOptions.RENDER_DISTANCE, EnumOptions.AO_LEVEL, EnumOptions.FRAMERATE_LIMIT, EnumOptions.ANAGLYPH, EnumOptions.VIEW_BOBBING, EnumOptions.GUI_SCALE, EnumOptions.ADVANCED_OPENGL, EnumOptions.FOG_FANCY, EnumOptions.FOG_START, 
            EnumOptions.MIPMAP_LEVEL, EnumOptions.MIPMAP_TYPE, EnumOptions.BETTER_GRASS, EnumOptions.BRIGHTNESS
        });
    }
}
