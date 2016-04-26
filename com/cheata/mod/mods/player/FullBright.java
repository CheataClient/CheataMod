/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.player;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class FullBright extends Mod {

	private float startBright;
	private float fullBright = 1000000000;
	
	public FullBright(String name, int bind, Category category) {
		super(name, bind, category);
	}

	@Override
	public void onEnable() {
		startBright = mc.gameSettings.gammaSetting;
	}

	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			if(!(mc.gameSettings.gammaSetting >= fullBright)){
				mc.gameSettings.gammaSetting++;
			}
		}
	}
	
	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = startBright;
	}
}
