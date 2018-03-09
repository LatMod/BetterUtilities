package com.latmod.betterutilities.client;

import com.latmod.betterutilities.BetterUtilitiesCommon;

/**
 * @author LatvianModder
 */
public class BetterUtilitiesClient extends BetterUtilitiesCommon
{
	@Override
	public void preInit()
	{
		super.preInit();
		BetterUtilitiesClientConfig.sync();
	}
}