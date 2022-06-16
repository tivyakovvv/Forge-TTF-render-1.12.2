# Help


```Java
//Usage
    
	public UnicodeFontRenderer exampleFont;
	public String fontName = "Font";
	public float fontSize = 20.0F;

    @Mod.EventHandler
    public void onInitialization(FMLInitializationEvent event) {
    	//you can register fonts here.
		exampleFont = createFont(exampleFont, fontName, fontSize);
    }

//In gui

    @Override
	public void drawScreen(int i1, int i2, float f) {
	    super.drawScreen(i1, i2, f);
		TTFRenderMod.INSTANCE.drawCenteredString(TTFRenderMod.INSTANCE.exampleFont, "Hello fonts!",  x, y, -65794);
	}


```

___OR___

```Java

//In gui

//use font without register in "onInitialization"

    public String fontName = "Font";
	public float fontSize = 20.0F;

    @Override
	public void drawScreen(int i1, int i2, float f) {
	    super.drawScreen(i1, i2, f);
		TTFRenderMod.INSTANCE.drawCenteredString(TTFRenderMod.INSTANCE.exampleFont, , "Hello fonts!",  x, y, fontSize, -65794);
	}
	

```

