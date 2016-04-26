/*
 * Copyright © 2015 - 2017 Lunix,
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.combat;

import java.util.Random;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.EntityLiving;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;
import com.cheata.utils.Time;

public class TriggerBot extends Mod {

	public TriggerBot(String name, Category category) {
		super(name, category);
	}

	public static boolean hitMobs = true;
	public static boolean hitPlayers = true;
	public static int delay = 150;
	
	private Time time = new Time();

	@Override
	public void onEvent(Event e) {
		if (e instanceof EventUpdate) {
			try {
				int max = 50;
				int min = 0;
				if ((this.time.check(this.delay + (Random.class.newInstance().nextInt(max - min + 1) + min))) && (this.mc.objectMouseOver != null) && (this.mc.objectMouseOver.entityHit != null) && (((this.mc.objectMouseOver.entityHit instanceof EntityLiving) && hitMobs) || ((this.mc.objectMouseOver.entityHit instanceof EntityOtherPlayerMP) && hitPlayers))) {
					this.mc.thePlayer.swingItem();
					this.mc.playerController.attackEntity(this.mc.thePlayer,
							this.mc.objectMouseOver.entityHit);
					this.time.reset();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}
}
