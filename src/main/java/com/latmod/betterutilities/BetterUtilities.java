package com.latmod.betterutilities;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author LatvianModder
 */
@Mod(
		modid = BetterUtilities.MOD_ID,
		name = BetterUtilities.MOD_NAME,
		version = BetterUtilities.VERSION,
		acceptedMinecraftVersions = "[1.12,)",
		dependencies = "required-after:ftblib"
)
public class BetterUtilities
{
	public static final String MOD_ID = "betterutilities";
	public static final String MOD_NAME = "Better Utilities";
	public static final String VERSION = "@VERSION@";

	@SidedProxy(serverSide = "com.latmod.betterutilities.BetterUtilitiesCommon", clientSide = "com.latmod.betterutilities.client.BetterUtilitiesClient")
	public static BetterUtilitiesCommon PROXY;

	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static final CreativeTabs TAB = new CreativeTabs(MOD_ID)
	{
		@Override
		public ItemStack getTabIconItem()
		{
			ItemStack stack = new ItemStack(BetterUtilitiesItems.ELYTRA_BOOSTER);
			stack.setTagInfo("Energy", new NBTTagInt(-1));
			return stack;
		}
	};

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		PROXY.preInit();
	}
}