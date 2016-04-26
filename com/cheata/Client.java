/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.opengl.Display;

import com.cheata.cmd.CmdManager;
import com.cheata.event.EventManager;
import com.cheata.gui.CheataIngameGui;
import com.cheata.gui.GuiManager;
import com.cheata.hooks.EventHook;
import com.cheata.mod.ModManager;
import com.cheata.utils.mod.ModValueManager;

@Mod(modid = Client.id, version = Client.ver, name = Client.name)
public class Client
{
	public static Logger logger = LogManager.getLogger();

	public static final String id = "cheata";
    public static final String ver = "1.0";
    public static final String name = "Cheata";
    
	private static int color = 0x75ffb825;
	private static int colorDarker = 0xffb82500;

    private static ModManager modManager;
    private static CmdManager cmdManager;
    private static EventManager eventManager;
    private static ModValueManager valueManager;
    private static EventHook eventHook;
    
    private static GuiManager guiManager;
    private static GuiManagerDisplayScreen gui;
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	eventHook = new EventHook();
    	guiManager = new GuiManager();
    	FMLCommonHandler.instance().bus().register(eventHook);
        MinecraftForge.EVENT_BUS.register(eventHook);
    	FMLCommonHandler.instance().bus().register(guiManager);
        MinecraftForge.EVENT_BUS.register(guiManager);

        Display.setTitle("[Cheata] " + Display.getTitle());
        Minecraft.getMinecraft().ingameGUI = new CheataIngameGui(Minecraft.getMinecraft());
        
        modManager = new ModManager();
        modManager.setup();
        cmdManager = new CmdManager();
        cmdManager.setup();
        eventManager = new EventManager();
        valueManager = new ModValueManager();
        valueManager.setup();
    }
    
    public static GuiManager getGuiManager(){
		if(guiManager.getTheme() == null){
			guiManager = new GuiManager();
			guiManager.setTheme(new SimpleTheme());
			guiManager.setup();
			guiManager.update();
		}
		return guiManager;
	}
	
	public static GuiManagerDisplayScreen getGui(){
		if(gui == null){
			gui = new GuiManagerDisplayScreen(getGuiManager());
		}
		return gui;
	}

    
    public static ModManager getModManager()
    {
      return modManager;
    }
    
    public static EventManager getEventManager()
    {
      return eventManager;
    }
    
    public static CmdManager getCmdManager()
    {
      return cmdManager;
    }
    
    public static void addChatMessage(String msg) {
      Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentTranslation("[" + Client.name + "] " + msg, new Object[0]));
    }
    
	public static ModValueManager getValueManager() {
		return valueManager;
	}

	public static int getColor() {
		return color;
	}

	public static int getColorDarker() {
		return colorDarker;
	}
}
