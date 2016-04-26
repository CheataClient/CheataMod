/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.movement;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockVine;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;



public class FastLadder extends Mod{

	public FastLadder(String name, Category category) {
		super(name, category);
	}
	
	public static boolean ncp = false;
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			ncp = Client.getValueManager().getValueByName("FastLadderNCP").getValueBoolean();
			if(mc.thePlayer.isCollidedHorizontally & mc.thePlayer.isOnLadder()){
				if(ncp){
					mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.169, mc.thePlayer.posZ);
				}else{
					mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + (getLadderHeight(mc.thePlayer, 0) > 9.7 ? 9.7 : getLadderHeight(mc.thePlayer, 0)) + .22, mc.thePlayer.posZ);
				}
			}
		}
	}
	
	@Override
	public void onDisable() {}
	
	@SuppressWarnings("unused")
	private int getLadderHeight(Entity e, int offset) {
		int ladders = 0;
		for(int i = (int) e.posY; i < 256; i++){
			Block var1 = mc.theWorld.getBlockState(new BlockPos(e.posX, i + offset, e.posZ)).getBlock();
			if(var1 instanceof BlockAir){
				break;
			}else if(var1 instanceof BlockLadder || var1 instanceof BlockVine){
				ladders++;
			}
			return ladders;
		}
		return -1;
	}
	
	private boolean isMoving(){
		if(mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()){
			return true;
		}
		return false;
	}

	@Override
	public void onEnable() {}

}
