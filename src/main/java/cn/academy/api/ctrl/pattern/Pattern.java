/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.lambdacraft.cn/
 *
 * AcademyCraft is open-source, and it is distributed under 
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * AcademyCraft是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.api.ctrl.pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cn.academy.api.ctrl.SkillEventType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Pattern {
	
	long cd = 500;
	
	/**
	 * The client system time last time activated an event. Subclasses are responsible for updating this,
	 * if they want to use cool-down mechanism.
	 */
	@SideOnly(Side.CLIENT)
	protected long lastActiveEvent;
	
	public Pattern() {}
	
	public void setCooldown(long cd) {
		this.cd = cd;
	}
	
	/**
	 * On receiving a raw event from EventHandler.
	 * @param type The type of the raw event.
	 * @param rawTime If on server, sometimes the client time received. Use this time to determine pattern.
	 * @param time The time of RawEventHandler of this side. Use this time to trigger skill event.
	 * @return Return true to indicate that the skill needs to reset patterns. 
	 */
	public abstract boolean onRawEvent(EntityPlayer player, SkillEventType type, int rawTime, int time);
	
	/**
	 * Return if the pattern receives event at all now. Used to filter some control.
	 */
	@SideOnly(Side.CLIENT)
	public boolean receivesEvent() {
		return Minecraft.getSystemTime() - lastActiveEvent > cd;
	}
	
	/**
	 * A hack to let SkillStateManager inform Pattern that a SkillState has ended in client side.
	 * @param response
	 */
	@SideOnly(Side.CLIENT)
	public void onStateEnd(boolean response) {
		if(response) {
			this.lastActiveEvent = Minecraft.getSystemTime();
		}
	}
	
}
