package com.latmod.betterutilities;

import com.feed_the_beast.ftblib.events.ServerReloadEvent;
import com.feed_the_beast.ftblib.lib.EventHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author LatvianModder
 */
@EventHandler
public class BetterUtilitiesEventHandler
{
	public static final ResourceLocation RELOAD_CONFIG = new ResourceLocation(BetterUtilities.MOD_ID, "config");

	@SubscribeEvent
	public static void registerReloadIds(ServerReloadEvent.RegisterIds event)
	{
		event.register(RELOAD_CONFIG);
	}

	@SubscribeEvent
	public static void onServerReload(ServerReloadEvent event)
	{
		if (event.reload(RELOAD_CONFIG))
		{
			BetterUtilitiesConfig.sync();
		}
	}
}