package net.realdarkstudios.rdslib.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ICapability {
    void saveNBT(CompoundTag nbt);
    void loadNBT(CompoundTag nbt);
}
