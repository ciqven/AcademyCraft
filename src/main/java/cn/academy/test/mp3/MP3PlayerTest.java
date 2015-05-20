/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.li-dev.cn/
 *
 * This project is open-source, and it is distributed under  
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 本项目是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.test.mp3;

import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cn.academy.core.registry.RegKeyHandler;
import cn.academy.core.util.KeyHandler;
import cn.academy.knowledge.KnowledgeData;
import cn.academy.misc.media.AppMediaPlayer;
import cn.annoreg.core.Registrant;
import cn.annoreg.mc.network.RegNetworkCall;
import cn.annoreg.mc.s11n.StorageOption.Target;
import cpw.mods.fml.relauncher.Side;

/**
 * @author WeAthFolD
 *
 */
@Registrant
public class MP3PlayerTest {
	
	@RegKeyHandler(name = "mediaPlayerTest", defaultKey = Keyboard.KEY_I)
	public static KeyHandler handler = new KeyHandler() {
		
		AppMediaPlayer mediaPlayer = new AppMediaPlayer();

		@Override
		public void onKeyDown() {
//			if(mediaPlayer.isPlaying()) {
//				mediaPlayer.resume();
//			} else {
//				mediaPlayer.startPlay("only_my_railgun");
//			}
			
			kkk(this.getPlayer());
		}

		@Override
		public void onKeyUp() {
//			mediaPlayer.pause();
		}
		
		@Override
		public void onKeyTick() {
		}
		
		@Override
		public void onKeyAbort() {
//			mediaPlayer.stop();
		}
		
	};
	
	@RegNetworkCall(side = Side.SERVER)
	static void kkk(@Target EntityPlayer player) {
		KnowledgeData.get(player).learn("bubble");
	}
	
}