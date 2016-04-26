/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.combat;

import java.util.Random;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

import com.cheata.Client;
import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;
import com.cheata.utils.Time;

public class KillAura extends Mod {

	public KillAura(String name, Category category) {
		super(name, category);
	}

	private Time timer = new Time();
	private Random r = new Random();
	public float auraFov = 50.0F;
	private boolean justClicked = false;

	public void hitEntity(Entity e) {
		try {
			if (e != null) {
				this.mc.playerController.attackEntity(this.mc.thePlayer, e);
				this.mc.thePlayer.swingItem();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onEvent(Event e) {
		if (e instanceof EventUpdate) {
			try {
				if ((mc.gameSettings.keyBindAttack.isKeyDown())
						&& (!justClicked)) {
					justClicked = true;
				} else {
					justClicked = false;
				}
				int max = 50;
				int min = 0;
				if (timer.check(TriggerBot.delay
						+ (r.nextInt(max - min + 1) + min))) {
					if (Client.getModManager().getMod(TriggerBot.class)
							.isEnabled()
							|| justClicked) {
						if ((mc.objectMouseOver.entityHit instanceof EntityOtherPlayerMP)) {
							if (TriggerBot.hitPlayers) {
							}
						} else if ((mc.objectMouseOver.entityHit instanceof EntityLiving)) {
							if (TriggerBot.hitMobs) {
							}
						} else {
							hitEntity(getEntityClosestToCursor((int) auraFov));
							timer.reset();
							justClicked = false;
						}
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private final float[] getAngles(Entity entityLiving) {
		double x = entityLiving.posX - mc.thePlayer.posX;
		double z = entityLiving.posZ - mc.thePlayer.posZ;
		double y = mc.thePlayer.posY + mc.thePlayer.getEyeHeight()
				- (entityLiving.posY + entityLiving.getEyeHeight());
		double helper = MathHelper.sqrt_double(x * x + z * z);

		float newYaw = (float) (Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
		float newPitch = (float) (Math.atan2(y, helper) * 180.0D / 3.141592653589793D);

		return new float[] { newYaw, newPitch + 10.0F };
	}

	private Entity getEntityClosestToCursor(int maxRotation) {
		Entity tempEntity = null;
		double distance = maxRotation;
		for (int i = 0; i < mc.theWorld.loadedEntityList.size(); i++) {
			Entity e = (Entity) mc.theWorld.loadedEntityList.get(i);
			if ((e instanceof EntityLiving)) {
				if (TriggerBot.hitMobs) {
				}
			} else {
				if (!(e instanceof EntityOtherPlayerMP)) {
					continue;
				}
				if (!TriggerBot.hitPlayers) {
					continue;
				}
			}
			Entity entity = e;
			if (mc.thePlayer.getDistanceToEntity(entity) < 3.7D) {
				if (mc.thePlayer.canEntityBeSeen(entity)) {
					float[] aimAngles = getAngles(entity);
					double curDistance = getDistanceBetweenAngles(aimAngles[0],
							mc.thePlayer.rotationYaw);
					if (curDistance < distance) {
						tempEntity = entity;
						distance = curDistance;
					}
				}
			}
		}
		return tempEntity;
	}

	private float getDistanceBetweenAngles(float par1, float par2) {
		float angle = Math.abs(par1 - par2) % 360.0F;
		if (angle > 180.0F) {
			angle = 360.0F - angle;
		}
		return angle;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
