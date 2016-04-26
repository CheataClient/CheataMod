/*
 * Copyright © 2015 - 2017 Lunix,
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.combat;

import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;
import com.cheata.utils.Time;

public class ChestStealer extends Mod{

	public ChestStealer(String name, Category category) {
		super(name, category);
	}
	
	Time timer = new Time();
	public static double delay = 100;

	/**  TODO NOT CRASH  */
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			if(mc.thePlayer.openContainer instanceof ContainerChest && mc.thePlayer.openContainer != null){
				ContainerChest chest = (ContainerChest) mc.thePlayer.openContainer;
				int i;
				for(i = 0; i<chest.inventorySlots.size();i++){
					Slot slot =(Slot)chest.inventorySlots.get(i);
					if(slot.getStack() == null)
						continue;
					if(!timer.check((float)(delay == 0 ? 1 : delay))){
						return;
					}else{
						timer.reset();
					}
					mc.playerController.windowClick(chest.windowId, i, 0, 1, mc.thePlayer);
				}
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
