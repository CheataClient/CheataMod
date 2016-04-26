/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.cheata.mod.mods.combat.Aimbot;
import com.cheata.mod.mods.combat.AutoDisconnect;
import com.cheata.mod.mods.combat.AutoRespawn;
import com.cheata.mod.mods.combat.ChestStealer;
import com.cheata.mod.mods.combat.KillAura;
import com.cheata.mod.mods.combat.NoKnockback;
import com.cheata.mod.mods.combat.TriggerBot;
import com.cheata.mod.mods.misc.AdvancedTooltips;
import com.cheata.mod.mods.misc.Derp;
import com.cheata.mod.mods.misc.Drop;
import com.cheata.mod.mods.misc.Legit;
import com.cheata.mod.mods.misc.Panic;
import com.cheata.mod.mods.movement.AutoWalk;
import com.cheata.mod.mods.movement.BunnyHop;
import com.cheata.mod.mods.movement.FastFall;
import com.cheata.mod.mods.movement.FastLadder;
import com.cheata.mod.mods.movement.Flight;
import com.cheata.mod.mods.movement.Glide;
import com.cheata.mod.mods.movement.Jetpack;
import com.cheata.mod.mods.movement.NoFall;
import com.cheata.mod.mods.movement.Sneak;
import com.cheata.mod.mods.movement.Speed;
import com.cheata.mod.mods.movement.Spider;
import com.cheata.mod.mods.movement.Sprint;
import com.cheata.mod.mods.movement.Step;
import com.cheata.mod.mods.movement.WaterWalk;
import com.cheata.mod.mods.player.AntiAfk;
import com.cheata.mod.mods.player.AutoMine;
import com.cheata.mod.mods.player.FreeCam;
import com.cheata.mod.mods.player.FullBright;
import com.cheata.mod.mods.world.Nuker;
import com.cheata.mod.mods.world.SpeedMine;

public class ModManager {
	public static ArrayList<Mod> mods = new ArrayList<Mod>();

	public static void setup(){
		/**  Combat  **/
		mods.add(new Aimbot("Aimbot", Category.COMBAT));
		mods.add(new AutoDisconnect("AutoDisconnect", Category.COMBAT));
		mods.add(new AutoRespawn("AutoRespawn", Category.COMBAT));
		mods.add(new ChestStealer("ChestStealer", Category.COMBAT));
		mods.add(new KillAura("KillAura", Category.COMBAT));
		mods.add(new NoKnockback("NoKnockback", Category.COMBAT));
		mods.add(new TriggerBot("TriggerBot", Category.COMBAT));
		
		/**  Misc  **/
		mods.add(new AdvancedTooltips("AdvancedTooltips", Category.MISC));
		mods.add(new Derp("Derp", Category.MISC));
		mods.add(new Drop("Drop", Category.MISC));
		mods.add(new Legit("Legit", Keyboard.KEY_L, Category.MISC));
		mods.add(new Panic("Panic", Keyboard.KEY_P, Category.MISC));
		
		/**  Movement  **/
		mods.add(new AutoWalk("AutoWalk", Category.MOVEMENT));
		mods.add(new BunnyHop("BunnyHop", Category.MOVEMENT));
		mods.add(new FastFall("FastFall", Category.MOVEMENT));
		mods.add(new FastLadder("FastLadder", Category.MOVEMENT));
		mods.add(new Flight("Flight", Keyboard.KEY_R, Category.MOVEMENT));
		mods.add(new Glide("Glide", Category.MOVEMENT));
		mods.add(new Jetpack("Jetpack", Category.MOVEMENT));
		mods.add(new NoFall("NoFall", Category.MOVEMENT));
		mods.add(new Sneak("Sneak", Category.MOVEMENT));
		mods.add(new Speed("Speed", Category.MOVEMENT));
		mods.add(new Spider("Spider", Category.MOVEMENT));
		mods.add(new Sprint("Sprint", Category.MOVEMENT));
		mods.add(new Step("Step", Category.MOVEMENT));
		mods.add(new WaterWalk("WaterWalk", Category.MOVEMENT));
		
		/**  Player  **/
		mods.add(new AntiAfk("AntiAfk", Category.PLAYER));
		mods.add(new AutoMine("AutoMine", Category.PLAYER));
		mods.add(new FreeCam("Freecam", Category.PLAYER));
		mods.add(new FullBright("FullBright", Keyboard.KEY_B, Category.PLAYER));
		
		/**  World  **/
		mods.add(new Nuker("Nuker", Category.WORLD));
		mods.add(new SpeedMine("SpeedMine", Category.WORLD));
		
		for(Mod mod : mods){
			mod.setEnabled(false);
		}
	}
	
	public static Mod getMod(Class<? extends Mod> modClass){
		for(Mod mod : mods){
			if(mod.getClass() == modClass){
				return mod;
			}
		}
		return null;
	}
	
	public static Mod getModByName(String modName){
		for(Mod mod : mods){
			if(mod.getName() == modName){
				return mod;
			}
		}
		return null;
	}
	
	public static ArrayList<Mod> getModsInCategory(Category category){
		ArrayList<Mod> modsInCategory = new ArrayList<Mod>();
		modsInCategory.clear();
		for(Mod mod : mods){
			if(mod.isCategory(category)){
				modsInCategory.add(mod);
			}
		}
		return modsInCategory;
	}
}
