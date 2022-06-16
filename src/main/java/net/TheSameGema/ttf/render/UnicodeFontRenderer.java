package net.TheSameGema.ttf.render;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import net.TheSameGema.ttf.utils.GLUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class UnicodeFontRenderer extends FontRenderer {

private final UnicodeFont font;
  
  public HashMap<String, Integer> widthMap;
  
  public HashMap<String, Integer> heightMap;
  
  private int texId;

@SuppressWarnings("unchecked")
public UnicodeFontRenderer(Font awtFont, float n, int p_addGlyphs_1_, int p_addGlyphs_2_, boolean b) {
    super(new GameSettings(), new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
    this.widthMap = new HashMap<>();
    this.heightMap = new HashMap<>();
    (this.font = new UnicodeFont(awtFont)).addAsciiGlyphs();
    this.font.getEffects().add(new ColorEffect(Color.WHITE));
    if (p_addGlyphs_2_ > -1 && p_addGlyphs_1_ > -1)
      this.font.addGlyphs(p_addGlyphs_1_, p_addGlyphs_2_); 
    if (b)
      this.font.addGlyphs(0, 65535); 
    try {
      this.font.loadGlyphs();
    } catch (SlickException class1245) {
      throw new RuntimeException(class1245);
    } 
    this.FONT_HEIGHT = this.font.getHeight("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789") / 2;
  }
  
  public int drawStringWithColor(String text, float x, float y, int color, int alpha) {
    text = "" + text;
    float len = -1.0F;
    byte b;
    int i;
    String[] arrayOfString1;
    for (i = (arrayOfString1 = text.split("")).length, b = 0; b < i; ) {
      String str = arrayOfString1[b];
      if (str.length() >= 1) {
        switch (str.charAt(0)) {
          case '0':
            color = (new Color(0, 0, 0)).getRGB();
            break;
          case '1':
            color = (new Color(0, 0, 170)).getRGB();
            break;
          case '2':
            color = (new Color(0, 170, 0)).getRGB();
            break;
          case '3':
            color = (new Color(0, 170, 170)).getRGB();
            break;
          case '4':
            color = (new Color(170, 0, 0)).getRGB();
            break;
          case '5':
            color = (new Color(170, 0, 170)).getRGB();
            break;
          case '6':
            color = (new Color(255, 170, 0)).getRGB();
            break;
          case '7':
            color = (new Color(170, 170, 170)).getRGB();
            break;
          case '8':
            color = (new Color(85, 85, 85)).getRGB();
            break;
          case '9':
            color = (new Color(85, 85, 255)).getRGB();
            break;
          case 'a':
            color = (new Color(85, 255, 85)).getRGB();
            break;
          case 'b':
            color = (new Color(85, 255, 255)).getRGB();
            break;
          case 'c':
            color = (new Color(255, 85, 85)).getRGB();
            break;
          case 'd':
            color = (new Color(255, 85, 255)).getRGB();
            break;
          case 'e':
            color = (new Color(255, 255, 85)).getRGB();
            break;
          case 'f':
            color = (new Color(255, 255, 255)).getRGB();
            break;
          case 'r':
            color = (new Color(255, 255, 255)).getRGB();
            break;
        } 
        Color col = new Color(color);
        str = str.substring(1, str.length());
        drawStr(str, x + len + 0.5F, y + 0.5F, -16711423);
        drawStr(str, x + len, y, getColor(col.getRed(), col.getGreen(), col.getBlue(), alpha));
        len += (getStringWidth(str) + 1);
      } 
      b++;
    } 
    return (int)len;
  }
  
  public int getColor(int red, int green, int blue, int alpha) {
    byte color = 0;
    int color1 = color | alpha << 24;
    color1 |= red << 16;
    color1 |= green << 8;
    color1 |= blue;
    return color1;
  }
  
  private int drawStr(String string, float x, float y, int color) {
    if (string == null)
      return 0; 
    GL11.glPushMatrix();
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    boolean blend = GL11.glIsEnabled(3042);
    boolean lighting = GL11.glIsEnabled(2896);
    boolean texture = GL11.glIsEnabled(3553);
    if (!blend)
      GL11.glEnable(3042); 
    if (lighting)
      GL11.glDisable(2896); 
    if (texture)
      GL11.glDisable(3553); 
    this.font.drawString(x *= 2.0F, y *= 2.0F, string);
    if (texture)
      GL11.glEnable(3553); 
    if (lighting)
      GL11.glEnable(2896); 
    if (!blend)
      GL11.glDisable(3042); 
    GlStateManager.color(0.0F, 0.0F, 0.0F);
    GL11.glPopMatrix();
    texId = GLUtils.getTexture();
    GlStateManager.bindTexture(texId);
    
    return (int)x;
  }
  
 
 
  
  public int drawStringWithShadow(String text, float x, float y, int color) {
    drawStr(text, x + 1.0F, y + 1.0F, -16777216);
    return drawStr(text, x, y, color);
  }
  
  public int getCharWidth(char c) {
    return getStringWidth(Character.toString(c));
  }
  
  public int getStringWidth(String string) {
    return this.font.getWidth(string) / 2;
  }
  
  public int getStringHeight(String string) {
    return this.font.getHeight(string) / 2;
  }
  
  public void drawString(String text, float x, float y, int color) {
	    drawStr(text, x, y, color);
  }
  
  public void drawCenteredString(String text, float x, float y, int color) {
    drawStr(text, x - (getStringWidth(text) / 2), y, color);
  }
}
