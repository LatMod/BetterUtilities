package com.latmod.betterutilities;

import com.latmod.betterutilities.item.ItemElytraBooster;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author LatvianModder
 */
@GameRegistry.ObjectHolder(BetterUtilities.MOD_ID)
@Mod.EventBusSubscriber(modid = BetterUtilities.MOD_ID)
public class BetterUtilitiesItems
{
	public static final Item ELYTRA_BOOSTER = Items.AIR;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				new ItemElytraBooster("elytra_booster")
		);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event)
	{
		ModelLoader.setCustomModelResourceLocation(ELYTRA_BOOSTER, 0, new ModelResourceLocation(ELYTRA_BOOSTER.getRegistryName(), "inventory"));
	}
}