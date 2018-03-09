package com.latmod.betterutilities.item;

import com.latmod.betterutilities.BetterUtilitiesConfig;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * @author LatvianModder
 */
public class ItemElytraBooster extends ItemEnergyContainer
{
	public ItemElytraBooster(String id)
	{
		super(id);
	}

	@Override
	public int getMaxEnergy(ItemStack stack)
	{
		return BetterUtilitiesConfig.general.elytra_booster_max;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		IEnergyStorage storage = getEnergyStorage(stack);

		if (storage != null && player.isElytraFlying())
		{
			if (player.capabilities.isCreativeMode || storage.extractEnergy(BetterUtilitiesConfig.general.elytra_booster_use, true) >= BetterUtilitiesConfig.general.elytra_booster_use)
			{
				if (!world.isRemote)
				{
					EntityFireworkRocket rocket = new EntityFireworkRocket(world, stack, player);
					rocket.setSilent(true);
					world.spawnEntity(rocket);
				}

				if (!player.capabilities.isCreativeMode)
				{
					storage.extractEnergy(BetterUtilitiesConfig.general.elytra_booster_use, false);
				}
			}

			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}

		return new ActionResult<>(EnumActionResult.PASS, stack);
	}
}