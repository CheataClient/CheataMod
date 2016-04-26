/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;

import com.cheata.event.Event;

public abstract class Mod {

	protected static Minecraft mc = Minecraft.getMinecraft();
	
	private String name;
	private int bind;
	private int bindMask;
	private Category category;
	private boolean isEnabled;

	public boolean hasBind;
	public boolean hasBindMask;

	public Mod(String name, int bind, Category category) {
		this.name = name;
		this.bind = bind;
		this.hasBind = true;
		this.hasBindMask = false;
		this.category = category;
	}

	public Mod(String name, int bind, int bindMask, Category category) {
		this.name = name;
		this.bind = bind;
		this.hasBind = true;
		this.bindMask = bindMask;
		this.hasBindMask = true;
		this.category = category;
	}
	
	public Mod(String name, Category category) {
		this.name = name;
		this.bind = 0;
		this.hasBind = false;
		this.hasBindMask = false;
		this.category = category;
	}
	
	private void onToggle(boolean isEnabled){
		if(mc.theWorld != null){
			this.onToggle();
			if(isEnabled){
				this.onEnable();
			}
			if(!isEnabled){
				this.onDisable();
			}
		}
	}
	
	public void toggle(){
		this.isEnabled = !this.isEnabled;
		this.onToggle(this.isEnabled);
	}

	public abstract void onEvent(Event e);
	public abstract void onEnable();
	public abstract void onDisable();
	public void onToggle() {}
	public void onRender() {}
	
	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		if(bind == 0){
			this.bind = 0;
			this.hasBind = false;
			this.bindMask = 0;
			this.hasBindMask = false;
		}else{
			this.bind = bind;
			this.hasBind = true;
		}
	}
	
	public int getBindMask() {
		return bindMask;
	}

	public void setBindMask(int mask) {
		if(mask == 0){
			this.bindMask = 0;
			this.hasBindMask = false;
		}else{
			this.bindMask = mask;
			this.hasBindMask = true;
		}
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public boolean isCategory(Category catIn) {
		if(category == catIn){
			return true;
		}
		return false;
	}

	public void setEnabled(boolean state) {
		this.isEnabled = state;
	}
}
