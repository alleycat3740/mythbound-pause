package com.mythbound.pause;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.GameMenuScreen;
public final class MythboundPauseClient implements ClientModInitializer {
  @Override public void onInitializeClient() {
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (client.world != null && client.currentScreen != null && client.currentScreen.getClass() == GameMenuScreen.class) {
        client.setScreen(new MythboundPauseScreen());
      }
    });
  }
}
