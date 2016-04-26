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
import net.minecraft.network.play.client.C0BPacketEntityAction;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class Sneak extends Mod{

	public Sneak(String name, Category category) {
		super(name, category);
	}

	public static boolean legit;
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			legit = Client.getValueManager().getValueByName("SneakLegit").getValueBoolean();
			if(legit){
				mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
			}else{
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
				mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
			}
		}
	}
	
	
	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
		mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
	}


	@Override
	public void onEnable() {}
}
