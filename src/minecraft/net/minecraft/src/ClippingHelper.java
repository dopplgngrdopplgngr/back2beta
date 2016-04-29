// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


public class ClippingHelper
{

    public ClippingHelper()
    {
        frustum = new float[16][16];
        projectionMatrix = new float[16];
        modelviewMatrix = new float[16];
        clippingMatrix = new float[16];
    }

    public boolean isBoxInFrustum(double d, double d1, double d2, double d3, double d4, double d5)
    {
        for(int i = 0; i < 6; i++)
        {
            float f = (float)d;
            float f1 = (float)d1;
            float f2 = (float)d2;
            float f3 = (float)d3;
            float f4 = (float)d4;
            float f5 = (float)d5;
            if(frustum[i][0] * f + frustum[i][1] * f1 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f1 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f + frustum[i][1] * f4 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f4 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f + frustum[i][1] * f1 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f1 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F && frustum[i][0] * f + frustum[i][1] * f4 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f4 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F)
            {
                return false;
            }
        }

        return true;
    }

    public boolean isBoxInFrustumFully(double d, double d1, double d2, double d3, double d4, double d5)
    {
        for(int i = 0; i < 6; i++)
        {
            float f = (float)d;
            float f1 = (float)d1;
            float f2 = (float)d2;
            float f3 = (float)d3;
            float f4 = (float)d4;
            float f5 = (float)d5;
            if(i < 4)
            {
                if(frustum[i][0] * f + frustum[i][1] * f1 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F || frustum[i][0] * f3 + frustum[i][1] * f1 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F || frustum[i][0] * f + frustum[i][1] * f4 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F || frustum[i][0] * f3 + frustum[i][1] * f4 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F || frustum[i][0] * f + frustum[i][1] * f1 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F || frustum[i][0] * f3 + frustum[i][1] * f1 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F || frustum[i][0] * f + frustum[i][1] * f4 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F || frustum[i][0] * f3 + frustum[i][1] * f4 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F)
                {
                    return false;
                }
                continue;
            }
            if(frustum[i][0] * f + frustum[i][1] * f1 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f1 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f + frustum[i][1] * f4 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f4 + frustum[i][2] * f2 + frustum[i][3] <= 0.0F && frustum[i][0] * f + frustum[i][1] * f1 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f1 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F && frustum[i][0] * f + frustum[i][1] * f4 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F && frustum[i][0] * f3 + frustum[i][1] * f4 + frustum[i][2] * f5 + frustum[i][3] <= 0.0F)
            {
                return false;
            }
        }

        return true;
    }

    public float frustum[][];
    public float projectionMatrix[];
    public float modelviewMatrix[];
    public float clippingMatrix[];
}
