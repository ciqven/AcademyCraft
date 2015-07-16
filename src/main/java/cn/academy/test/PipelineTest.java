package cn.academy.test;

import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cn.academy.core.registry.RegACKeyHandler;
import cn.academy.core.util.RangedRayDamage;
import cn.annoreg.core.Registrant;
import cn.annoreg.mc.network.RegNetworkCall;
import cn.annoreg.mc.s11n.StorageOption.Instance;
import cn.liutils.util.helper.KeyHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * ValuePipeline unittest
 * @author WeAthFolD
 */
@Registrant
public class PipelineTest {
	
	@RegACKeyHandler(name = "miku", defaultKey = Keyboard.KEY_K)
	public static KeyHandler key = new KeyHandler() {
		@Override
		public void onKeyDown() {
			rangeDmgAtServer(getPlayer());
		}
	};
	
	@RegNetworkCall(side = Side.SERVER)
	public static void rangeDmgAtServer(@Instance EntityPlayer player) {
		RangedRayDamage rrd = new RangedRayDamage(player, 5, 1);
		rrd.perform();
	}
	
	
}
