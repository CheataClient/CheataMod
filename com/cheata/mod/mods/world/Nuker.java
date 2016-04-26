/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.world;

import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class Nuker extends Mod{

	public Nuker(String name, Category category) {
		super(name, category);
	}

	public static int size = 5;
	public static int sizeOther = Math.round(size / 2);
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			if(mc.thePlayer.capabilities.isCreativeMode){
				for(int x = -size; x < size + sizeOther; x++){
					for(int z = -size; z < size + sizeOther; z++){
						for(int y = -size; y < size + sizeOther; y++){
							boolean shouldBreakBlock = true;
		        			int blockX = (int) (mc.thePlayer.posX + x);
		        			int blockY = (int) (mc.thePlayer.posY + y);
		        			int blockZ = (int) (mc.thePlayer.posZ + z);
		        			if (Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) != 0){
		        				mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(blockX, blockY, blockZ), EnumFacing.UP));
		         			}
		 				}
		 			}
		 		}
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
