// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.PrintStream;
import java.nio.*;
import org.lwjgl.opengl.*;

// Referenced classes of package net.minecraft.src:
//            GLAllocation

public class Tessellator
{

    public Tessellator(int i)
    {
        renderingChunk = false;
        vertexCount = 0;
        hasColor = false;
        hasTexture = false;
        hasNormals = false;
        rawBufferIndex = 0;
        addedVertices = 0;
        isColorDisabled = false;
        isDrawing = false;
        useVBO = false;
        vboIndex = 0;
        vboCount = 10;
        bufferSize = i;
        byteBuffer = GLAllocation.createDirectByteBuffer(i * 4);
        intBuffer = byteBuffer.asIntBuffer();
        floatBuffer = byteBuffer.asFloatBuffer();
        useVBO = tryVBO && GLContext.getCapabilities().GL_ARB_vertex_buffer_object;
        if(useVBO)
        {
            vertexBuffers = GLAllocation.createDirectIntBuffer(vboCount);
            ARBVertexBufferObject.glGenBuffersARB(vertexBuffers);
        }
    }

    public void draw()
    {
        if(!isDrawing)
        {
            throw new IllegalStateException("Not tesselating!");
        }
        isDrawing = false;
        if(!renderingChunk)
        {
            GL11.glEnd();
            checkOpenGlError();
        } else
        if(vertexCount > 0)
        {
            byteBuffer.position(0);
            byteBuffer.limit(rawBufferIndex * 4);
            GL11.glEnableClientState(32888 /*GL_TEXTURE_COORD_ARRAY_EXT*/);
            GL11.glEnableClientState(32886 /*GL_COLOR_ARRAY_EXT*/);
            GL11.glEnableClientState(32884 /*GL_VERTEX_ARRAY_EXT*/);
            if(useVBO)
            {
                vboIndex = (vboIndex + 1) % vboCount;
                ARBVertexBufferObject.glBindBufferARB(35044 /*GL_STATIC_DRAW_ARB*/, vertexBuffers.get(vboIndex));
                ARBVertexBufferObject.glBufferDataARB(35044 /*GL_STATIC_DRAW_ARB*/, byteBuffer, 35040 /*GL_STREAM_DRAW_ARB*/);
                GL11.glTexCoordPointer(2, 5126 /*GL_FLOAT*/, 32, 12L);
                GL11.glColorPointer(4, 5121 /*GL_UNSIGNED_BYTE*/, 32, 20L);
                GL11.glVertexPointer(3, 5126 /*GL_FLOAT*/, 32, 0L);
            } else
            {
                floatBuffer.position(3);
                GL11.glTexCoordPointer(2, 32, floatBuffer);
                byteBuffer.position(20);
                GL11.glColorPointer(4, true, 32, byteBuffer);
                floatBuffer.position(0);
                GL11.glVertexPointer(3, 32, floatBuffer);
            }
            if(drawMode == 7 && convertQuadsToTriangles)
            {
                GL11.glDrawArrays(4, 0, vertexCount);
            } else
            {
                GL11.glDrawArrays(drawMode, 0, vertexCount);
            }
            GL11.glDisableClientState(32888 /*GL_TEXTURE_COORD_ARRAY_EXT*/);
            GL11.glDisableClientState(32886 /*GL_COLOR_ARRAY_EXT*/);
            GL11.glDisableClientState(32884 /*GL_VERTEX_ARRAY_EXT*/);
        }
        reset();
    }

    private void reset()
    {
        vertexCount = 0;
        byteBuffer.clear();
        intBuffer.clear();
        rawBufferIndex = 0;
        addedVertices = 0;
    }

    public void startDrawingQuads()
    {
        startDrawing(7);
    }

    public void startDrawing(int i)
    {
        if(isDrawing)
        {
            throw new IllegalStateException("Already tesselating!");
        }
        if(!renderingChunk)
        {
            GL11.glBegin(i);
        }
        isDrawing = true;
        reset();
        drawMode = i;
        hasNormals = false;
        hasColor = false;
        hasTexture = false;
        isColorDisabled = false;
    }

    public void setTextureUV(double d, double d1)
    {
        hasTexture = true;
        textureU = d;
        textureV = d1;
        if(!renderingChunk)
        {
            GL11.glTexCoord2f((float)d, (float)d1);
        }
    }

    public void setColorOpaque_F(float f, float f1, float f2)
    {
        setColorOpaque((int)(f * 255F), (int)(f1 * 255F), (int)(f2 * 255F));
    }

    public void setColorRGBA_F(float f, float f1, float f2, float f3)
    {
        setColorRGBA((int)(f * 255F), (int)(f1 * 255F), (int)(f2 * 255F), (int)(f3 * 255F));
    }

    public void setColorOpaque(int i, int j, int k)
    {
        setColorRGBA(i, j, k, 255);
    }

    public void setColorRGBA(int i, int j, int k, int l)
    {
        if(isColorDisabled)
        {
            return;
        }
        if(i > 255)
        {
            i = 255;
        }
        if(j > 255)
        {
            j = 255;
        }
        if(k > 255)
        {
            k = 255;
        }
        if(l > 255)
        {
            l = 255;
        }
        if(i < 0)
        {
            i = 0;
        }
        if(j < 0)
        {
            j = 0;
        }
        if(k < 0)
        {
            k = 0;
        }
        if(l < 0)
        {
            l = 0;
        }
        hasColor = true;
        if(!renderingChunk)
        {
            GL11.glColor4ub((byte)i, (byte)j, (byte)k, (byte)l);
        } else
        if(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
        {
            color = l << 24 | k << 16 | j << 8 | i;
        } else
        {
            color = i << 24 | j << 16 | k << 8 | l;
        }
    }

    public void addVertexWithUV(double d, double d1, double d2, double d3, double d4)
    {
        setTextureUV(d3, d4);
        addVertex(d, d1, d2);
    }

    public void addVertex(double d, double d1, double d2)
    {
        if(!renderingChunk)
        {
            GL11.glVertex3f((float)(d + xOffset), (float)(d1 + yOffset), (float)(d2 + zOffset));
            return;
        }
        addedVertices++;
        if(drawMode == 7 && convertQuadsToTriangles && addedVertices % 4 == 0)
        {
            for(int i = 0; i < 2; i++)
            {
                int j = 8 * (3 - i);
                intBuffer.put(intBuffer.get((rawBufferIndex - j) + 0));
                intBuffer.put(intBuffer.get((rawBufferIndex - j) + 1));
                intBuffer.put(intBuffer.get((rawBufferIndex - j) + 2));
                intBuffer.put(intBuffer.get((rawBufferIndex - j) + 3));
                intBuffer.put(intBuffer.get((rawBufferIndex - j) + 4));
                intBuffer.put(intBuffer.get((rawBufferIndex - j) + 5));
                intBuffer.put(0);
                intBuffer.put(0);
                vertexCount++;
                rawBufferIndex += 8;
            }

        }
        intBuffer.put(Float.floatToRawIntBits((float)(d + xOffset)));
        intBuffer.put(Float.floatToRawIntBits((float)(d1 + yOffset)));
        intBuffer.put(Float.floatToRawIntBits((float)(d2 + zOffset)));
        intBuffer.put(Float.floatToRawIntBits((float)textureU));
        intBuffer.put(Float.floatToRawIntBits((float)textureV));
        intBuffer.put(color);
        intBuffer.put(0);
        intBuffer.put(0);
        rawBufferIndex += 8;
        vertexCount++;
        if(renderingChunk && addedVertices % 4 == 0 && rawBufferIndex >= bufferSize - 32)
        {
            draw();
            isDrawing = true;
        }
    }

    public void setColorOpaque_I(int i)
    {
        int j = i >> 16 & 0xff;
        int k = i >> 8 & 0xff;
        int l = i & 0xff;
        setColorOpaque(j, k, l);
    }

    public void setColorRGBA_I(int i, int j)
    {
        int k = i >> 16 & 0xff;
        int l = i >> 8 & 0xff;
        int i1 = i & 0xff;
        setColorRGBA(k, l, i1, j);
    }

    public void disableColor()
    {
        isColorDisabled = true;
    }

    public void setNormal(float f, float f1, float f2)
    {
        if(!isDrawing)
        {
            System.out.println("Error: Not drawing !!!");
        }
        hasNormals = true;
        byte byte0 = (byte)(int)(f * 128F);
        byte byte1 = (byte)(int)(f1 * 127F);
        byte byte2 = (byte)(int)(f2 * 127F);
        if(!renderingChunk)
        {
            GL11.glNormal3b(byte0, byte1, byte2);
        } else
        {
            System.out.println("ERROR: NORMALS IN CHUNK MODE !!!");
        }
    }

    public void setTranslationD(double d, double d1, double d2)
    {
        xOffset = d;
        yOffset = d1;
        zOffset = d2;
    }

    public void setTranslationF(float f, float f1, float f2)
    {
        xOffset += f;
        yOffset += f1;
        zOffset += f2;
    }

    public void setRenderingChunk(boolean flag)
    {
        renderingChunk = flag;
    }

    private void checkOpenGlError()
    {
        int i = GL11.glGetError();
        if(i != 0)
        {
            String s = (new StringBuilder()).append("OpenGL Error: ").append(i).append(" ").append(Util.translateGLErrorString(i)).toString();
            Exception exception = new Exception(s);
            exception.printStackTrace();
        }
    }

    private static boolean convertQuadsToTriangles = true;
    private static boolean tryVBO = false;
    private ByteBuffer byteBuffer;
    private IntBuffer intBuffer;
    private FloatBuffer floatBuffer;
    private int vertexCount;
    private double textureU;
    private double textureV;
    private int color;
    private boolean hasColor;
    private boolean hasTexture;
    private boolean hasNormals;
    private int rawBufferIndex;
    private int addedVertices;
    private boolean isColorDisabled;
    private int drawMode;
    private double xOffset;
    private double yOffset;
    private double zOffset;
    private int normal;
    public static final int BUFFER_SIZE = 0x40000;
    public static volatile Tessellator instance = new Tessellator(0x40000);
    private boolean isDrawing;
    private boolean useVBO;
    private IntBuffer vertexBuffers;
    private int vboIndex;
    private int vboCount;
    private int bufferSize;
    private boolean renderingChunk;

}
