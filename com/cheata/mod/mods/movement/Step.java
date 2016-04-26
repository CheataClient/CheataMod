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

public class Step extends Mod{

	public Step(String name, Category category) {
		super(name, category);
	}

	public static double height = 1F;
	public static boolean legit = false;
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			height = Client.getValueManager().getValueByName("StepHeight").getValueDouble();
			legit = Client.getValueManager().getValueByName("StepLegit").getValueBoolean();
			if(mc.thePlayer.isInWater() && mc.thePlayer.isCollidedHorizontally){
				mc.thePlayer.motionY = 0.4;
			}else if(mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround){
				if(legit){
					mc.thePlayer.jump();
				}else{
					mc.thePlayer.stepHeight = (float) height;
				}
			}else{
				mc.thePlayer.stepHeight = 0.5F;
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

}
