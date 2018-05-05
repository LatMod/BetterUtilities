package com.latmod.betterutilities;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BetterUtilities.MOD_ID)
@Config(modid = BetterUtilities.MOD_ID, category = "")
public class BetterUtilitiesConfig
{
	@Config.LangKey("stat.generalButton")
	public static final General general = new General();

	public static class General
	{
		@Config.Comment("Max FE of Elytra Booster")
		public int elytra_booster_max = 6400;

		@Config.Comment("FE used by one click of Elytra Booster")
		public int elytra_booster_use = 100;
	}

	public static void sync()
	{
		ConfigManager.sync(BetterUtilities.MOD_ID, Config.Type.INSTANCE);
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(BetterUtilities.MOD_ID))
		{
			sync();
		}
	}
}