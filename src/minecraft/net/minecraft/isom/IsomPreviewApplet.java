// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.isom;

import java.applet.Applet;
import java.awt.BorderLayout;
import net.minecraft.src.CanvasIsomPreview;

public class IsomPreviewApplet extends Applet
{

    public IsomPreviewApplet()
    {
        minecraftIsomPreview = new CanvasIsomPreview();
        setLayout(new BorderLayout());
        add(minecraftIsomPreview, "Center");
    }

    public void start()
    {
        minecraftIsomPreview.func_1272_b();
    }

    public void stop()
    {
        minecraftIsomPreview.exit();
    }

    private CanvasIsomPreview minecraftIsomPreview;
}
