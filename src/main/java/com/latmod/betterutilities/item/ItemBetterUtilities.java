package com.latmod.betterutilities.item;

import com.feed_the_beast.ftblib.lib.item.ItemBase;
import com.latmod.betterutilities.BetterUtilities;

/**
 * @author LatvianModder
 */
public class ItemBetterUtilities extends ItemBase
{
	public ItemBetterUtilities(String id)
	{
		super(BetterUtilities.MOD_ID, id);
		setCreativeTab(BetterUtilities.TAB);
	}
}