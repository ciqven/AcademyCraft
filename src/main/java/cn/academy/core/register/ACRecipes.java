package cn.academy.core.register;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Recipes and smeltings registry
 */
public class ACRecipes {

	public static void regRecipe() {
		GameRegistry.addRecipe(new ItemStack(ACItems.coin, 12), "aaa", "bbb", "aaa", 
				'a', ACItems.ingotNi, 'b', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(ACItems.humanMachine), "aba", "aca", "ada",
				'a', ACItems.ingotNi, 'b', Blocks.glass_pane, 'c', ACItems.pcb, 'd', ACItems.crystal);
		GameRegistry.addRecipe(new ItemStack(ACItems.ioPort), "aaa", "aba", " a ",
				'a', ACItems.ingotNi, 'b', ACItems.pcb);
		GameRegistry.addRecipe(new ItemStack(ACItems.brainBeta), "aba", "bcb", "cbd",
				'a', Items.repeater, 'b', Items.gold_ingot, 'c', Items.ender_eye, 'd', ACItems.crystal,
				'e', ACItems.ingotNi);
		GameRegistry.addRecipe(new ItemStack(ACBlocks.solarGen), "aaa", "bcb", "ded",
				'a', ACItems.siliconRod, 'b', ACItems.ingotMg, 'c', ACItems.pcb, 'd', ACItems.ingotAl,
				'e', ACItems.ingotNi);
		GameRegistry.addRecipe(new ItemStack(ACItems.alPlate, 3), "aaa", "   ", "   ",
				'a', ACItems.ingotAl);
		GameRegistry.addRecipe(new ItemStack(ACItems.alPlate, 3), "   ", "aaa", "   ",
				'a', ACItems.ingotAl);
		GameRegistry.addRecipe(new ItemStack(ACItems.alPlate, 3), "   ", "   ", "aaa",
				'a', ACItems.ingotAl);
		GameRegistry.addRecipe(new ItemStack(ACItems.mgPlate, 3), "aaa", "   ", "   ",
				'a', ACItems.ingotMg);
		GameRegistry.addRecipe(new ItemStack(ACItems.mgPlate, 3), "   ", "aaa", "   ",
				'a', ACItems.ingotMg);
		GameRegistry.addRecipe(new ItemStack(ACItems.mgPlate, 3), "   ", "   ", "aaa",
				'a', ACItems.ingotMg);	
		GameRegistry.addShapelessRecipe(new ItemStack(ACItems.almgPlate, 2), 
				ACItems.alPlate, ACItems.mgPlate);
		GameRegistry.addRecipe(new ItemStack(ACBlocks.node), "aaa", "bcb", "ded",
				'a', ACItems.siliconRod, 'b', ACItems.ingotMg, 'c', ACItems.pcb, 'd', ACItems.ingotAl,
				'e', ACItems.ingotNi);
		GameRegistry.addRecipe(new ItemStack(ACItems.copperCoil, 6), "aba", "b a", "aba",
				'a', Items.iron_ingot, 'b', ACItems.ingotNi);
		GameRegistry.addRecipe(new ItemStack(ACBlocks.magInducer), " a ", "aba", " a ",
				'a', ACItems.copperCoil, 'b', ACItems.pcb);
		GameRegistry.addRecipe(new ItemStack(ACBlocks.grid), "aba", "cdc", "aba",
				'a', Blocks.glass_pane, 'b', ACItems.pcb, 'c', ACItems.almgPlate, 'd', ACItems.energyCrystal);
		GameRegistry.addShapelessRecipe(new ItemStack(ACItems.compWood), new ItemStack(Blocks.planks, 3));
		GameRegistry.addRecipe(new ItemStack(ACItems.expNail, 8), "aba", "bcb", "dad",
				'a', Items.iron_ingot, 'b', ACItems.compWood, 'c', Items.gunpowder, 'd', Items.slime_ball);
		GameRegistry.addRecipe(new ItemStack(ACItems.pcb), "aba", "cdc", "aba",
				'a', ACItems.ingotNi, 'b', ACItems.crystal, 'c', Items.redstone, 'd', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(ACItems.devModule), "aba", "cdc", "efe",
				'a', ACItems.ioPort, 'b', ACItems.humanMachine, 'c', ACItems.brainBeta, 'd', ACItems.pcb,
				'e', ACBlocks.node, 'f', ACItems.almgPlate);
	}
	
	public static void regSmelting() {
		GameRegistry.addSmelting(new ItemStack(Blocks.glass, 3), new ItemStack(ACItems.siliconRod), 0f);
		//Ore Smelting
		GameRegistry.addSmelting(ACBlocks.oreAl, new ItemStack(ACItems.ingotAl), 0.7f);
		GameRegistry.addSmelting(ACBlocks.oreCopper, new ItemStack(ACItems.ingotCu), 0.7f);
		GameRegistry.addSmelting(ACBlocks.oreMg, new ItemStack(ACItems.ingotMg), 0.8f);
		GameRegistry.addSmelting(ACBlocks.oreNi, new ItemStack(ACItems.ingotNi), 0.8f);
		GameRegistry.addSmelting(ACBlocks.oreTin, new ItemStack(ACItems.ingotTin), 0.7f);
		GameRegistry.addSmelting(ACBlocks.oreCrystal, new ItemStack(ACItems.crystal), 1.2f);
		GameRegistry.addSmelting(ACBlocks.oreTin, new ItemStack(ACItems.ingotTin), 0.7f);
		GameRegistry.addSmelting(ACBlocks.oreShadow, new ItemStack(ACItems.ingotShadow), 1.2f);
	}
	
}