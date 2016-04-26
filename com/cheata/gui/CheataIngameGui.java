/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

import com.cheata.Client;
import com.cheata.mod.Mod;
import com.cheata.mod.Category;

public class CheataIngameGui extends GuiIngame{

	public static boolean shouldRender = true;
	protected Minecraft mc = Minecraft.getMinecraft();
	
	public CheataIngameGui(Minecraft mcIn) {
		super(mcIn);
	}

	@Override
	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		if(shouldRender){
			drawString(mc.fontRendererObj, Client.name + " - " + Client.ver, 2, 2, Client.getColorDarker());
			
			int countY = 1;
			for(Mod mod : Client.getModManager().mods){
				if(mod.isEnabled() && !mod.isCategory(Category.NONE)){
					drawString(Minecraft.getMinecraft().fontRendererObj, mod.getName(), 1, 12 * countY, Client.getColor());
					countY++;
				}
			}
		}
	}
}
