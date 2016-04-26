/*
 * Copyright © 2016 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.cmd;

import java.util.ArrayList;
import java.util.List;

import com.cheata.Client;

public class CmdManager {
	
	private static List<Cmd> cmd = new ArrayList<Cmd>();
	private static String prefix = ".";

	public static void setup() {
		//cmd.add(new BindCmd());
	}
	
	public static List<Cmd> getCommands() {
		return CmdManager.cmd;
	}

	public static String getPrefix() {
		return CmdManager.prefix;
	}

	public Cmd getCommandByClass(Class command) {
		for (Cmd command2 : cmd)
			if (command2.getClass().equals(command))
				return command2;
		return null;
	}

	public void runCommands(String message) {
		boolean hasArguments = message.contains(" ");
		String[] arguments = hasArguments ? message.split(" ") : new String[] { message };

		boolean foundCommand = false;
		for (Cmd command : getCommands()){
			for (String alias : command.getCommands()){
				if (message.split(" ")[0].substring(1).equalsIgnoreCase(alias)) {
					foundCommand = true;
					command.runCommand(message, arguments);
					break;
				}
			}
		}

		if (!foundCommand){
			Client.logger.info("Not found");
			Client.addChatMessage("Unknown command - " + (message.length() > 1 ? message.substring(1) : message));
		}
	}

	public void setPrefix(String prefix) {
		CmdManager.prefix = prefix;
	}
}
