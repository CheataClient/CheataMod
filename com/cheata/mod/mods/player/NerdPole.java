/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.player;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.C03PacketPlayer;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class NerdPole extends Mod {

	public NerdPole(String name, Category category) {
		super(name, category);
	}

	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			if(mc.inGameHasFocus){
				if(!isHeldItem(Blocks.ladder) && 
						!isHeldItem(Blocks.vine)){
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
					mc.thePlayer.setAngles(mc.thePlayer.cameraYaw, -90);
					if(mc.thePlayer.onGround){
						mc.thePlayer.jump();
						if(isInteractable()){
							KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
						}
						KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
						KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
					}
				}
			}
		}
	}
	
	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
	}

	private boolean isHeldItem(Block itemIn){
		if(!(mc.thePlayer.getHeldItem() == null)){
			if(Item.getIdFromItem(mc.thePlayer.getHeldItem().getItem()) == Item.getIdFromItem(Item.getItemFromBlock(itemIn))){
				return true;
			}
		}
		return false;
	}
	
	private boolean isInteractable(){
		if(isHeldItem(Blocks.chest) || isHeldItem(Blocks.ender_chest) || 
				isHeldItem(Blocks.trapped_chest) ||
				isHeldItem(Blocks.noteblock) ||
				isHeldItem(Blocks.furnace) ||
				isHeldItem(Blocks.dispenser) ||
				isHeldItem(Blocks.crafting_table) ||
				isHeldItem(Blocks.command_block) ||
				isHeldItem(Blocks.anvil) ||
				isHeldItem(Blocks.bed)  ||
				isHeldItem(Blocks.dropper) ||
				isHeldItem(Blocks.end_portal_frame)){
			return true;
		}
		return false;
	}

	@Override
	public void onEnable() {}
}
