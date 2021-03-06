/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.utils.mod;

import java.util.ArrayList;


import com.cheata.mod.mods.combat.AutoDisconnect;
import com.cheata.mod.mods.misc.Derp;
import com.cheata.mod.mods.movement.BunnyHop;
import com.cheata.mod.mods.movement.FastLadder;
import com.cheata.mod.mods.movement.Flight;
import com.cheata.mod.mods.movement.Glide;
import com.cheata.mod.mods.movement.Sneak;
import com.cheata.mod.mods.movement.Speed;
import com.cheata.mod.mods.movement.Spider;
import com.cheata.mod.mods.movement.Sprint;
import com.cheata.mod.mods.movement.Step;
import com.cheata.mod.mods.movement.WaterWalk;
import com.cheata.mod.mods.player.NerdPole;


public class ModValueManager {
	public static ArrayList<ModValue> values = new ArrayList<ModValue>(); 
	
	public static void setup(){
		values.add(new ModValue("BhopHeight", 0.25, 1.5, 0.125, BunnyHop.height));
		values.add(new ModValue("BhopLegit", BunnyHop.jump));
		values.add(new ModValue("DerpLegit", Derp.mode));
		values.add(new ModValue("FastLadderNCP", FastLadder.ncp));
		values.add(new ModValue("FlightSpeed", 5, 50, 5, Flight.flySpeed));
		values.add(new ModValue("GlideSpeed", 0,  0.875, 0.125, Glide.speed));
		values.add(new ModValue("LeaveHealth", 1, 19, 1, AutoDisconnect.leaveHealth));
		values.add(new ModValue("SneakLegit", Sneak.legit));
		values.add(new ModValue("SpeedSpeed", 1, 5, 0.5, Speed.speed));
		values.add(new ModValue("StepHeight", 1, 5, 0.5, Step.height));
		values.add(new ModValue("SpiderSpeed", 0.125, 2, 0.125, Spider.speed));
		values.add(new ModValue("SprintLegit", Sprint.legit));
		values.add(new ModValue("StepLegit", Step.legit));
		values.add(new ModValue("WaterWalkLegit", WaterWalk.legit));
	}
	
	public static ModValue getValueByName(String valueName){
		for(ModValue value : values){
			if(value.getValueName().equalsIgnoreCase(valueName)){
				return value;
			}
		}
		return null;
	}
}
