/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.player;

import net.minecraft.client.entity.EntityOtherPlayerMP;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;
import com.cheata.mod.mods.movement.Flight;

public class FreeCam extends Mod{

	public FreeCam(String name, Category category) {
		super(name, category);
	}
	
	double oldX;
	double oldY;
	double oldZ;
	EntityOtherPlayerMP fakePlayer;
	
	@Override
	public void onEnable() {
		fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
		oldX = mc.thePlayer.posX;
		oldY = mc.thePlayer.posY;
		oldZ = mc.thePlayer.posZ;
		fakePlayer.setEntityId(-1881);
		fakePlayer.clonePlayer(mc.thePlayer, true);
		fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
		fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
		mc.theWorld.addEntityToWorld(fakePlayer.getEntityId(), fakePlayer);
	}
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			mc.thePlayer.motionX = 0;
			mc.thePlayer.motionY = 0;
			mc.thePlayer.motionZ = 0;
			if(mc.gameSettings.keyBindJump.isKeyDown()){
				mc.thePlayer.motionY += (Flight.flySpeed / 25);
			}
			if(mc.gameSettings.keyBindSneak.isKeyDown()){
				mc.thePlayer.motionY -= (Flight.flySpeed / 25);
			}
			mc.thePlayer.capabilities.setFlySpeed((float) (Flight.flySpeed/75));
			mc.thePlayer.jumpMovementFactor = ((float) (Flight.flySpeed/75));
		}
	}
	
	@Override
	public void onDisable() {
		if(mc.theWorld != null){
			mc.thePlayer.setLocationAndAngles(oldX, oldY, oldZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
			mc.theWorld.removeEntityFromWorld(fakePlayer.getEntityId());
		}
	}
}
