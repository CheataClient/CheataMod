/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.combat;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

import com.cheata.event.Event;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class Aimbot extends Mod {

	public Aimbot(String name, Category category) {
		super(name, category);
	}

	@Override
	public void onEvent(Event e) {
		List list = mc.theWorld.playerEntities;

		for (int k = 0; k < list.size(); k++) {
			if (((EntityPlayer) list.get(k)).getName() == mc.thePlayer
					.getName()) {
				continue;
			}

			EntityPlayer entPlayer = (EntityPlayer) list.get(k);

			if (mc.thePlayer.getDistanceToEntity(entPlayer) > mc.thePlayer
					.getDistanceToEntity((Entity) list.get(k))) {
				entPlayer = (EntityPlayer) list.get(k);
			}

			float f = mc.thePlayer.getDistanceToEntity(entPlayer);
			if (f < 5F && mc.thePlayer.canEntityBeSeen(entPlayer)) {
				this.faceEntity(entPlayer);
			}
		}
	}

	public static synchronized void faceEntity(Entity entity) {
		final float[] rotations = getRotationsNeeded(entity);

		if (rotations != null) {
			mc.thePlayer.rotationYaw = rotations[0];
			mc.thePlayer.rotationPitch = rotations[1] + 1.0F;
		}
	}

	public static float[] getRotationsNeeded(Entity entity) {
		if (entity == null) {
			return null;
		}

		final double diffX = entity.posX - mc.thePlayer.posX;
		final double diffZ = entity.posZ - mc.thePlayer.posZ;
		double diffY;

		if (entity instanceof EntityPlayer) {
			final EntityPlayer entityLivingBase = (EntityPlayer) entity;
			diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight()
					- (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
		} else if (entity instanceof EntityMob) {
			final EntityMob entityLivingBase = (EntityMob) entity;
			diffY = (entity.getBoundingBox().minY + entity.getBoundingBox().maxY)
					/ 2.0D - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
		} else {
			final EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			diffY = (entity.getBoundingBox().minY + entity.getBoundingBox().maxY)
					/ 2.0D - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
		}

		final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ
				* diffZ);
		final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] {
				mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw),
				mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch) };
	}

	@Override
	public void onEnable() {
	}

	@Override
	public void onDisable() {
	}
}