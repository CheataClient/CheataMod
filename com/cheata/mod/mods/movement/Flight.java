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

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class Flight extends Mod{

	public Flight(String name, int bind, Category category) {
		super(name, bind, category);
	}
	
	public static double flySpeed = 20.0D;

	public static double increment = 5;

	boolean wasFlying;
	boolean canFly;
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			flySpeed = Client.getValueManager().getValueByName("FlightSpeed").getValueDouble();
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			mc.thePlayer.motionY = 0;
			mc.thePlayer.isAirBorne = false;
			if(mc.gameSettings.keyBindJump.isKeyDown()){
				mc.thePlayer.motionY += (flySpeed / 25);
			}
			if(mc.gameSettings.keyBindSneak.isKeyDown()){
				mc.thePlayer.motionY -= (flySpeed / 25);
			}
			mc.thePlayer.capabilities.setFlySpeed((float) (flySpeed/75));
			mc.thePlayer.jumpMovementFactor = ((float) (flySpeed/75));
		}
	}
	
	@Override
	public void onEnable(){
		if(mc.inGameHasFocus){
			if(mc.thePlayer.capabilities.isFlying){
				wasFlying = true;
			}else{
				wasFlying = false;
			}
			if(mc.thePlayer.capabilities.allowFlying){
				canFly = true;
			}else{
				canFly = false;
			}
			mc.thePlayer.capabilities.setFlySpeed((float) (flySpeed/100));
		}
	}
	
	@Override
	public void onDisable() {
		if(mc.inGameHasFocus){
			if(wasFlying){
				if(!mc.thePlayer.isAirBorne){
					mc.thePlayer.motionY = 0.05;
				}
				mc.thePlayer.capabilities.isFlying = true;
			}else{
				mc.thePlayer.capabilities.isFlying = false;
			}
			if(canFly){
				mc.thePlayer.capabilities.allowFlying = true;
			}else{
				mc.thePlayer.capabilities.allowFlying = false;
			}
			mc.thePlayer.capabilities.setFlySpeed(0.05F);
		}
	}
}
