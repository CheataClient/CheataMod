/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.combat;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class AutoRespawn extends Mod{

	public AutoRespawn(String name, Category category) {
		super(name, category);
	}
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			if(mc.thePlayer.getHealth() <= 0){
				mc.thePlayer.respawnPlayer();
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
