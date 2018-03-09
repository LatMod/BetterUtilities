package com.latmod.betterutilities.block;

import com.feed_the_beast.ftblib.lib.block.BlockBase;
import com.latmod.betterutilities.BetterUtilities;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * @author LatvianModder
 */
public class BlockBetterUtilities extends BlockBase
{
	public BlockBetterUtilities(String id, Material material, MapColor color)
	{
		super(BetterUtilities.MOD_ID, id, material, color);
		setCreativeTab(BetterUtilities.TAB);
	}
}