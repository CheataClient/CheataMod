/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.cmd;

public abstract class Cmd {
	
	private static String[] commands;
	private static String description = "", syntax = "";

	public Cmd(String[] commands, String desc, String syntax) {
		this.commands = commands;
		this.description = desc;
		this.syntax = syntax;
	}

	public String[] getCommands() {
		return commands;
	}

	public String getDescription() {
		return description;
	}

	public String getSyntax() {
		return syntax;
	}

	public abstract void runCommand(String message, String[] args);

	public void setCommands(String[] commands) {
		this.commands = commands;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}
}
