/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.world;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class SpeedMine extends Mod {

	public SpeedMine(String name, Category category) {
		super(name, category);
	}

	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			mc.thePlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, Integer.MAX_VALUE, 0));
		}
		
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
