package com.latmod.betterutilities.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author LatvianModder
 */
public class ItemEnergyContainer extends ItemBetterUtilities
{
	public class EnergyContainer implements IEnergyStorage, ICapabilityProvider
	{
		public final ItemStack stack;

		public EnergyContainer(ItemStack is)
		{
			stack = is;
		}

		@Override
		public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
		{
			return capability == CapabilityEnergy.ENERGY;
		}

		@Nullable
		@Override
		@SuppressWarnings("unchecked")
		public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
		{
			return hasCapability(capability, facing) ? (T) this : null;
		}

		@Override
		public int receiveEnergy(int maxReceive, boolean simulate)
		{
			if (!canReceive())
			{
				return 0;
			}

			int e = getEnergyStored();
			int r = Math.min(getMaxEnergyStored() - e, maxReceive);

			if (!simulate && r > 0)
			{
				setEnergy(stack, e + r);
			}

			return r;
		}

		@Override
		public int extractEnergy(int maxExtract, boolean simulate)
		{
			if (!canExtract())
			{
				return 0;
			}

			int e = getEnergyStored();
			int r = Math.min(e, maxExtract);

			if (!simulate && r > 0)
			{
				setEnergy(stack, e - r);
			}

			return r;
		}

		@Override
		public int getEnergyStored()
		{
			return ItemEnergyContainer.this.getEnergy(stack);
		}

		@Override
		public int getMaxEnergyStored()
		{
			return ItemEnergyContainer.this.getMaxEnergy(stack);
		}

		@Override
		public boolean canExtract()
		{
			return ItemEnergyContainer.this.canExtract(stack);
		}

		@Override
		public boolean canReceive()
		{
			return ItemEnergyContainer.this.canReceive(stack);
		}
	}

	public ItemEnergyContainer(String id)
	{
		super(id);
		setMaxStackSize(1);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
	{
		return new EnergyContainer(stack);
	}

	public int getEnergy(ItemStack stack)
	{
		int e = stack.hasTagCompound() ? stack.getTagCompound().getInteger("Energy") : 0;
		return e == -1 ? getMaxEnergy(stack) : e;
	}

	public void setEnergy(ItemStack stack, int energy)
	{
		if (energy > 0)
		{
			stack.setTagInfo("Energy", new NBTTagInt(energy));
		}
		else if (stack.hasTagCompound())
		{
			stack.getTagCompound().removeTag("Energy");

			if (stack.getTagCompound().hasNoTags())
			{
				stack.setTagCompound(null);
			}
		}
	}

	@Nullable
	public final IEnergyStorage getEnergyStorage(ItemStack stack)
	{
		return stack.hasCapability(CapabilityEnergy.ENERGY, null) ? stack.getCapability(CapabilityEnergy.ENERGY, null) : null;
	}

	public int getMaxEnergy(ItemStack stack)
	{
		return 1000;
	}

	public boolean canExtract(ItemStack stack)
	{
		return true;
	}

	public boolean canReceive(ItemStack stack)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flagIn)
	{
		IEnergyStorage storage = getEnergyStorage(stack);

		if (storage != null)
		{
			tooltip.add(storage.getEnergyStored() + " / " + storage.getMaxEnergyStored() + " FE");
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (isInCreativeTab(tab))
		{
			items.add(new ItemStack(this));

			ItemStack stack = new ItemStack(this);
			IEnergyStorage storage = getEnergyStorage(stack);

			if (storage != null)
			{
				storage.receiveEnergy(storage.getMaxEnergyStored(), false);
			}

			items.add(stack);
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		IEnergyStorage storage = getEnergyStorage(stack);

		if (storage != null)
		{
			return storage.getEnergyStored() < storage.getMaxEnergyStored();
		}

		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		IEnergyStorage storage = getEnergyStorage(stack);

		if (storage != null)
		{
			return 1D - MathHelper.clamp(storage.getEnergyStored() / (double) storage.getMaxEnergyStored(), 0D, 1D);
		}

		return 0D;
	}
}