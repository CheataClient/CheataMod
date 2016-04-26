/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.misc;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.gui.CheataIngameGui;
import com.cheata.mod.Mod;
import com.cheata.mod.Category;

public class Legit extends Mod{
	
	public Legit(String name, int bind, Category category) {
		super(name, bind, category);
	}

	private final ArrayList<Mod> previousMods = new ArrayList();

	@Override
	public void onDisable() {
		CheataIngameGui.shouldRender = true;
		for (Mod mod : previousMods)
			mod.setEnabled(true);
		previousMods.clear();

		Display.setTitle("[" + Client.name + "] " + Display.getTitle());
	}

	@Override
	public void onEnable() {
		CheataIngameGui.shouldRender = false;
		previousMods.clear();
		for (Mod mod : Client.getModManager().mods){
			if (!mod.getClass().equals(this.getClass()) && mod.isEnabled()) {
				previousMods.add(mod);
				mod.setEnabled(false);
			}
		}

		Display.setTitle(Display.getTitle().substring(("[" + Client.name + "] ").length()));
	}

	@Override
	public void onEvent(Event event) {}
}
