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

public class GuiOtherSettingsOF extends GuiScreen
{

    public GuiOtherSettingsOF(GuiScreen guiscreen, GameSettings gamesettings)
    {
        lastMouseX = 0;
        lastMouseY = 0;
        mouseStillTime = 0L;
        title = "Other Settings";
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
        if(s.equals("Smooth FPS"))
        {
            return (new String[] {
                "Stabilizes FPS by flushing the graphic driver buffers", "  OFF - no stabilization, FPS may fluctuate", "  ON - FPS stabilization", "This option is graphic driver dependant and its effect", "is not always visible"
            });
        }
        if(s.equals("Smooth Input"))
        {
            return (new String[] {
                "Fixes stuck keys, slow input response and sound lag", "  OFF - no fix for stuck keys", "  ON - fixes stuck keys", "This option adds a small delay (1ms) to the game loop", "which fixes the stuck keys, slow input and sound lag."
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
            EnumOptions.SMOOTH_FPS, EnumOptions.SMOOTH_INPUT, EnumOptions.AUTOSAVE_TICKS, EnumOptions.FAST_DEBUG_INFO
        });
    }
}
