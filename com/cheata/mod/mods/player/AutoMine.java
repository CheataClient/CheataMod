/*
 * Copyright © 2015 - 2017 Lunix, 
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.cheata.mod.mods.player;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;

import com.cheata.event.Event;
import com.cheata.event.events.EventUpdate;
import com.cheata.mod.Category;
import com.cheata.mod.Mod;

public class AutoMine extends Mod{

	public AutoMine(String name, Category category) {
		super(name, category);
	}

	@Override
	public void onEvent(Event e) {
		if(e instanceof EventUpdate){
			if(mc.gameSettings.keyBindAttack.isKeyDown() == false){
				this.sendClickBlockToController(true);
			}
		}
	}

	private void sendClickBlockToController(boolean leftClick) {
		if (!mc.thePlayer.isUsingItem())
        {
            if (leftClick && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos blockpos = mc.objectMouseOver.getBlockPos();

                if (mc.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air && mc.playerController.func_180512_c(blockpos, mc.objectMouseOver.sideHit))
                {
                	mc.effectRenderer.addBlockHitEffects(blockpos, mc.objectMouseOver);
                	mc.thePlayer.swingItem();
                }
            }
            else
            {
            	mc.playerController.resetBlockRemoving();
            }
        }
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}
}
