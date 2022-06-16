package net.TheSameGema.ttf;

import java.awt.Font;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import net.TheSameGema.ttf.example.GuiFontExample;
import net.TheSameGema.ttf.render.UnicodeFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;;

@Mod(modid = "ttf", version = TTFRenderMod.MOD_VERSION)
public class TTFRenderMod  {

    public static final String MOD_VERSION = "1.0";

    @Mod.Instance(owner = "ttf")
    public static TTFRenderMod INSTANCE;
    private final Minecraft mc = Minecraft.getMinecraft();
    
    private HashMap<String, HashMap<Float, UnicodeFontRenderer>> fonts = new HashMap<>();
    
    public UnicodeFontRenderer exampleFont1;
    public UnicodeFontRenderer exampleFont2;
    public UnicodeFontRenderer exampleFont3;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent ev) {MinecraftForge.EVENT_BUS.register(this);}
   
    @Mod.EventHandler
    public void onInitialization(FMLInitializationEvent event) {
    	//you can register fonts here.
    }
    
    public void drawString(UnicodeFontRenderer ufont, String fontName, String text, int x, int y, float size, int color) {
    	ufont = createFont(ufont, fontName, size);
    	ufont.drawString(text, x, y, color);  
    }
    
    public void drawCenteredString(UnicodeFontRenderer ufont, String fontName, String text, int x, int y, float size, int color) {
    	ufont = createFont(ufont, fontName, size);
    	ufont.drawCenteredString(text, x, y, color);  
    }
    
    public void drawStringWithShadow(UnicodeFontRenderer ufont, String fontName, String text, int x, int y, float size, int color) {
    	ufont = createFont(ufont, fontName, size);
    	ufont.drawStringWithShadow(text, x, y, color);  
    }
    
    //for already registered fonts
    public void drawString(UnicodeFontRenderer font, String text, int x, int y, float size, int color) {
    	font.drawString(text, x, y, color);  
    }
    
    public void drawCenteredString(UnicodeFontRenderer font, String text, int x, int y, float size, int color) {
    	font.drawCenteredString(text, x, y, color);  
    }
    
    public void drawStringWithShdaow(UnicodeFontRenderer font, String text, int x, int y, float size, int color) {
    	font.drawString(text, x, y, color);  
    }
    
    private UnicodeFontRenderer createFont(UnicodeFontRenderer font, String fontName, float size) {
    	font = getFont(fontName, size,  -1, -1, false, false);
    	return font;
    }
  
    
    public UnicodeFontRenderer getFont(String s, float n, int n2, int n3, boolean b, boolean b2) {
        UnicodeFontRenderer unicodeFontRenderer = null;
        try {
          String s2;
          if (this.fonts.containsKey(s) && (this.fonts.get(s)).containsKey(Float.valueOf(n)))
          return (UnicodeFontRenderer)(this.fonts.get(s)).get(Float.valueOf(n)); 
          Class<? extends TTFRenderMod> class1 = getClass();
          StringBuilder append = (new StringBuilder()).append("/assets/fonts/").append(s);
          if (b2) {
            s2 = ".otf";
          } else {
            s2 = ".ttf";
          } 
          unicodeFontRenderer = new UnicodeFontRenderer(Font.createFont(0, class1.getResourceAsStream(append.append(s2).toString())).deriveFont(n), n, n2, n3, b);
          unicodeFontRenderer.setUnicodeFlag(true);
          unicodeFontRenderer.setBidiFlag(Minecraft.getMinecraft().getLanguageManager().isCurrentLanguageBidirectional());
          HashMap<Float, UnicodeFontRenderer> hashMap = new HashMap<>();
          if (this.fonts.containsKey(s))
            hashMap.putAll(this.fonts.get(s)); 
          hashMap.put(Float.valueOf(n), unicodeFontRenderer);
          this.fonts.put(s, hashMap);
        } catch (Exception ex) {
          ex.printStackTrace();
        } 
        return unicodeFontRenderer;
      }
  
       @SubscribeEvent
       public void keyEvent(KeyInputEvent event) {
       	 if(mc.world == null || mc.player == null)
                return;
       	
           try {
           	if(Keyboard.isCreated()) {
           		int keyCode = Keyboard.getEventKey();
           		if (keyCode <= 0)
           			return;
   
           		if (keyCode == Keyboard.KEY_F9) {
           			mc.displayGuiScreen(new GuiFontExample("Hello fonts!"));
           		}
           		
           	}
           } catch(Exception e) {
           	e.printStackTrace();
           }
       }  
}

