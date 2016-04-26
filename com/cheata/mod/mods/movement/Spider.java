/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.movement;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class Spider extends Mod{

	public Spider(String name, Category category) {
		super(name, category);
	}
	
	public static double speed = 0.25;
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			speed = Client.getValueManager().getValueByName("SpiderSpeed").getValueDouble();
			if(mc.thePlayer.isCollidedHorizontally){
				mc.thePlayer.motionY = speed;
			}
		}
	}

	@Override
	public void onEnable() {}
	
	@Override
	public void onDisable() {}
}
