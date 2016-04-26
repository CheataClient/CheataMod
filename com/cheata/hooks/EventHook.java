/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.hooks;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

import com.cheata.Client;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Mod;

public class EventHook {

	Map<String, KeyBinding> binds = new HashMap<String, KeyBinding>();
	
	public EventHook()
	{
		for (Mod mod : Client.getModManager().mods){
			binds.put(mod.getName() + "Bind", new KeyBinding(mod.getName(), mod.getBind(), mod.getCategory().toString()));
		}
		for(Mod mod : Client.getModManager().mods){
			if(binds.get(mod.getName() + "Bind") != null){
				ClientRegistry.registerKeyBinding(binds.get(mod.getName() + "Bind"));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if (event.side == Side.SERVER) return;
		if (event.phase == Phase.START)
		{
			for (Mod mod : Client.getModManager().mods){
				if(binds.get(mod.getName() + "Bind") != null){
					if(binds.get(mod.getName() + "Bind").isKeyDown()){
						mod.toggle();
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onWorldRender(RenderWorldLastEvent e) {
		if (Minecraft.getMinecraft().theWorld != null) {
			for(Mod mod : Client.getModManager().mods){
				if(mod.isEnabled())mod.onRender();
			}
		}
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		if (Minecraft.getMinecraft().theWorld != null) {
			for (Mod m : Client.getModManager().mods) {
				if (m.isEnabled()) m.onEvent(new EventUpdate());
			}
			if(Client.getGuiManager().flySpeed.getParent() != null && Client.getGuiManager().flySpeed.getParent().isVisible()){
				Client.getGuiManager().flySpeed.getParent().update();
			}
			if(Client.getGuiManager().flySpeed != null){
				Client.getGuiManager().flySpeed.update();
			}
		}
	}

	@SubscribeEvent
	public void onGuiRender(RenderGameOverlayEvent.Chat e){
		if (Minecraft.getMinecraft().theWorld != null){
			if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
				Minecraft.getMinecraft().displayGuiScreen(Client.getGui());
			}
		}
	}

	@SubscribeEvent
	public void onChatSend(ServerChatEvent e) {
		Client.getCmdManager();
		if (e.message.startsWith(Client.getCmdManager().getPrefix())) {
			Client.getCmdManager().runCommands(e.message);
			e.setCanceled(true);
		}
	}
}
