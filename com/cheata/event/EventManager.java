/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.event;

import com.cheata.Client;
import com.cheata.mod.Mod;

public class EventManager {

	public void call(Event event){
		for(Mod mod : Client.getModManager().mods){
			if(mod.isEnabled()){
				mod.onEvent(event);
			}
		}
	}
}
