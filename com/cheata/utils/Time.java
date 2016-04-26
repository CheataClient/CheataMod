/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.utils;

public class Time {
	public static short convert(float perSecond) {
		return (short) (1000 / perSecond);
	}

	public static long getCurrentTime() {
		return System.nanoTime() / 1000000;
	}

	private long previousTime;

	public Time() {
		previousTime = -1L;
	}

	public long get() {
		return previousTime;
	}

	public boolean check(float milliseconds) {
		return Time.getCurrentTime() - previousTime >= milliseconds;
	}

	public void reset() {
		previousTime = Time.getCurrentTime();
	}
}
