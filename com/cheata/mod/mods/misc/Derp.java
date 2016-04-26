/*
 * Copyright © 2015 - 2017 Lunix,
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.misc;

import java.util.Random;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0BPacketEntityAction.Action;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Mod;
import com.cheata.mod.Category;

public class Derp extends Mod{


	public Derp(String name, Category category) {
		super(name, category);
	}

	public static boolean mode = false;
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			mode = Client.getValueManager().getValueByName("DerpLegit").getValueBoolean();
			Random rand = new Random();
			int yaw = rand.nextInt((1000 - 0) + 1) + 0;
			int pitch = rand.nextInt((1000 - 0) + 1) + 0;
			if(mode){
				mc.thePlayer.rotationYaw = yaw;
				mc.thePlayer.rotationPitch = pitch;
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
			}else{
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(yaw, pitch, mc.thePlayer.onGround));
				mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, Action.STOP_SNEAKING));
				mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, Action.START_SNEAKING));
			}
		}
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
