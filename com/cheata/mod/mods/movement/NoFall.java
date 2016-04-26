/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.movement;

import net.minecraft.network.play.client.C03PacketPlayer;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;
import com.cheata.utils.packet.PacketUtils;

public class NoFall extends Mod{

	public NoFall(String name, Category category) {
		super(name, category);
	}
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			if(mc.thePlayer.fallDistance != 0){
				PacketUtils.sendPacket(new C03PacketPlayer(false));
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

}
