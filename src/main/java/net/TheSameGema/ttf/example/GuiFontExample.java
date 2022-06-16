package net.TheSameGema.ttf.example;

import net.TheSameGema.ttf.TTFRenderMod;
import net.minecraft.client.gui.GuiScreen;

public class GuiFontExample extends GuiScreen {

	 String displayText;
	
	 public GuiFontExample(String text) {
		 displayText = text;
	  }
	 
	  @Override
	    public void drawScreen(int i1, int i2, float f) {
	        super.drawScreen(i1, i2, f);
	        drawDefaultBackground();
	        TTFRenderMod.INSTANCE.drawCenteredString(TTFRenderMod.INSTANCE.exampleFont1, "example1", displayText,  this.width / 2, this.height / 4 + 50, 35.0F, -65794);
	        TTFRenderMod.INSTANCE.drawCenteredString(TTFRenderMod.INSTANCE.exampleFont2, "example2", displayText,  this.width / 2, this.height / 4 + 70, 25.0F, -65794);
	        TTFRenderMod.INSTANCE.drawCenteredString(TTFRenderMod.INSTANCE.exampleFont3, "example3", displayText,  this.width / 2, this.height / 4 + 85, 15.0F, -65794);
	  }	
	  
		 @Override
		 public boolean doesGuiPauseGame() {
		     return false;
		 }
}
