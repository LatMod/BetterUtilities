package com.latmod.betterutilities.client;

import com.feed_the_beast.ftblib.lib.gui.GuiLang;
import com.latmod.betterutilities.BetterUtilities;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BetterUtilities.MOD_ID, value = Side.CLIENT)
@Config(modid = BetterUtilities.MOD_ID + "_client", category = "", name = "../local/client/" + BetterUtilities.MOD_ID)
public class BetterUtilitiesClientConfig
{
	@Config.LangKey(GuiLang.LANG_GENERAL)
	public static final General general = new General();

	public static class General
	{
	}

	public static void sync()
	{
		ConfigManager.sync(BetterUtilities.MOD_ID + "_client", Config.Type.INSTANCE);
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(BetterUtilities.MOD_ID + "_client"))
		{
			sync();
		}
	}
}