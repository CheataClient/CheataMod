/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.movement;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;
import com.cheata.utils.packet.PacketUtils;

public class WaterWalk extends Mod{

	public WaterWalk(String name, Category category) {
		super(name, category);
	}
	
	public static boolean legit = false;
	
	@Override
	public void onEnable() {}
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			legit = Client.getValueManager().getValueByName("WaterWalkLegit").getValueBoolean();
			PacketUtils.sendPacket(new C03PacketPlayer(true));
			if(legit){
				if(mc.thePlayer.isInWater()){
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
				}else{
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
				}
			}else{
				if(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.1, mc.thePlayer.posZ)).getBlock() == Blocks.water){
					mc.thePlayer.motionY = 0;
					mc.thePlayer.onGround = true;
				}
				if(mc.thePlayer.isInWater() || mc.thePlayer.isInLava()){
					mc.thePlayer.motionY = 0.1;
					mc.thePlayer.onGround = true;
				}
			}
		}
	}
	
	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
	}

}
