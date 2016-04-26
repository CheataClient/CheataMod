/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.player;

import net.minecraft.client.settings.KeyBinding;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;
import com.cheata.utils.Time;

public class AntiAfk extends Mod{

	public AntiAfk(String name, Category category) {
		super(name, category);
	}
	
	int tickCount = 1;
	int afkCount = 1;
	Time timer = new Time();
	Time timer2 = new Time();
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
			if(timer.check(500)){
				mc.thePlayer.rotationYaw = mc.thePlayer.rotationYaw -= 90;
				timer.reset();
			}
		}
	}
	
	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
	}

	@Override
	public void onEnable() {}

}
