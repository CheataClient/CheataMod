/*
 * Copyright © 2015 - 2017 Lunix,
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.misc;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.mod.Mod;
import com.cheata.mod.Category;

public class Panic extends Mod{

	public Panic(String name, int bind, Category category) {
		super(name, bind, category);
	}

	@Override
	public void onToggle() {
		for(Mod mod : Client.getModManager().mods){
			if(mod.isEnabled() && !(mod.getClass() == Panic.class)){
				mod.setEnabled(false);
			}
		}
	}

	@Override
	public void onEvent(Event event) {}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
