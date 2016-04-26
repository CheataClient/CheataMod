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
import com.cheata.mod.Mod;
import com.cheata.mod.Category;

public class BunnyHop extends Mod{

	public BunnyHop(String name, Category category) {
		super(name, category);
	}

	public static double height = 1;
	public static boolean jump = false;

	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			height = Client.getValueManager().getValueByName("BhopHeight").getValueDouble();
			jump = Client.getValueManager().getValueByName("BhopLegit").getValueBoolean();
			if(mc.thePlayer.onGround && mc.thePlayer.moveForward > 0 && !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava()){
				if(jump){
					mc.thePlayer.jump();
				}else{
					mc.thePlayer.motionY = height;
				}
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
