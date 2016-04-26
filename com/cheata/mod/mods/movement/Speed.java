/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.movement;

import net.minecraft.util.MathHelper;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class Speed extends Mod{

	public Speed(String name, Category category) {
		super(name, category);
	}
	
	public static double speed = 2F;
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			speed = Client.getValueManager().getValueByName("SpeedSpeed").getValueDouble();
			if(mc.gameSettings.keyBindForward.isKeyDown() && mc.thePlayer.onGround){
				float yaw = mc.thePlayer.rotationYaw * 0.017453292F;
				mc.thePlayer.motionX -= MathHelper.sin(yaw) * (speed/5);
				mc.thePlayer.motionZ += MathHelper.cos(yaw) * (speed/5);
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

}
