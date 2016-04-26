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

public class NoKnockback extends Mod {

	public NoKnockback(String name, Category category) {
		super(name, category);
	}

	public double divider = 0D;
	private double lastHealth = -19.0D;
	boolean g = false;

	private Time time = new Time();

	@Override
	public void onEvent(Event e) {
		if (e instanceof EventUpdate) {
			
			/**  Code under here is no longer written by me  **/
			/**  All credits go to thehen101  **/
			
			if (lastHealth == -19.0D) {
				lastHealth = this.mc.thePlayer.getHealth();
				System.out.println("Intial health setting");
			}
			if (lastHealth > this.mc.thePlayer.getHealth()) {
				System.out.println("Initial anti");
				this.mc.thePlayer.setVelocity(this.mc.thePlayer.motionX * divider, this.mc.thePlayer.motionY * divider, this.mc.thePlayer.motionZ * divider);
				this.g = true;
			}
			if ((g) && ((this.mc.thePlayer.motionX != 0.0D) || (this.mc.thePlayer.motionZ != 0.0D))) {
				System.out.println("Forced with G");
				this.mc.thePlayer.setVelocity(this.mc.thePlayer.motionX * divider, this.mc.thePlayer.motionY * divider, this.mc.thePlayer.motionZ * divider);
				g = false;
				lastHealth = this.mc.thePlayer.getHealth();
			}
			
			/**  Coded under in now written by me  **/
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
