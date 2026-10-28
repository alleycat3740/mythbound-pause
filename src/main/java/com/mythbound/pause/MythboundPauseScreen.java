package com.mythbound.pause;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

/** Art remains whole: no 256px atlas slicing or rescaled characters. */
public final class MythboundPauseScreen extends Screen {
  private static final Identifier ART = Identifier.of("mythbound_pause", "textures/gui/pause_menu.png");
  private static final int W=1358, H=1158;
  private static final String DISCORD="https://discord.gg/E4JVh5YUYc";
  private int left, top, drawW, drawH;
  private double scale;
  public MythboundPauseScreen(){super(Text.literal("Mythbound"));}
  @Override protected void init(){
    scale=Math.min((double)width/W,(double)height/H);
    drawW=(int)Math.round(W*scale);drawH=(int)Math.round(H*scale);
    left=(width-drawW)/2;top=(height-drawH)/2;
  }
  @Override
public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    renderBackground(context, mouseX, mouseY, delta);

    context.getMatrices().push();

    context.getMatrices().translate(left, top, 0);
    context.getMatrices().scale((float) scale, (float) scale, 1.0F);

    context.drawTexture(ART, 0, 0, 0.0F, 0.0F, W, H, W, H);

    context.getMatrices().pop();
  }
  private boolean hit(double x,double y,int a,int b,int c,int d){return x>=a&&x<=c&&y>=b&&y<=d;}
  @Override public boolean mouseClicked(double mx,double my,int button){
    if(button!=0 || client==null) return super.mouseClicked(mx,my,button);
    double x=(mx-left)/scale,y=(my-top)/scale;
    if(hit(x,y,230,185,1105,266)){close();return true;}
    if(hit(x,y,232,278,665,365)){if(client.player!=null)client.setScreen(new AdvancementsScreen(client.player.networkHandler.getAdvancementHandler()));return true;}
    if(hit(x,y,677,278,1104,365)){if(client.player!=null)client.setScreen(new StatsScreen(this,client.player.getStatHandler()));return true;}
    if(hit(x,y,232,374,665,458)){Util.getOperatingSystem().open("https://feedback.minecraft.net/");return true;}
    if(hit(x,y,677,374,1104,458)){Util.getOperatingSystem().open("https://bugs.mojang.com/");return true;}
    if(hit(x,y,232,469,665,553)){client.setScreen(new OptionsScreen(this,client.options));return true;}
    if(hit(x,y,677,469,1104,553)){
      if(client.isIntegratedServerRunning())client.setScreen(new OpenToLanScreen(this));
      else Util.getOperatingSystem().open("https://www.minecraft.net/en-us/article/player-reporting-tool");
      return true;
    }
    if(hit(x,y,231,565,1104,649)){openMods();return true;}
    if(hit(x,y,231,657,1104,742)){openBerkSettings();return true;}
    if(hit(x,y,280,750,1053,833)){client.world.disconnect();client.disconnect();client.setScreen(new TitleScreen());return true;}
    if(hit(x,y,353,842,990,930)){Util.getOperatingSystem().open(DISCORD);return true;}
    return super.mouseClicked(mx,my,button);
  }
  private void openBerkSettings(){
    try {
      Class<?> cls=Class.forName("com.bda.common.client.gui.BDAClientPauseActions");
      cls.getMethod("openSettings",Screen.class).invoke(null,this);
    }catch(Exception e){System.err.println("[Mythbound] Berk settings unavailable: "+e);}
  }
  private void openMods(){
    try {
      Class<?> cls=Class.forName("com.terraformersmc.modmenu.gui.ModsScreen");
      Object screen=cls.getConstructor(Screen.class).newInstance(this);
      if(screen instanceof Screen s)client.setScreen(s);
    }catch(Exception e){System.err.println("[Mythbound] ModMenu unavailable: "+e);}
  }
  @Override public boolean shouldPause(){return true;}
  @Override public void close(){if(client!=null)client.setScreen(null);}
}
