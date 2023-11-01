package net.realdarkstudios.rdslib.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;


public class RegistryManager {
    private String modid;
    private ArrayList<RegistryHelper<?>> registryHelpers = new ArrayList<>();

    private RegistryManager(String modid) {
        this.modid = modid;
        RegistryManagers.get().addManager(modid, this);
    }

    public <T> RegistryHelper<T> createRegister(ResourceKey<Registry<T>> registry) {
        RegistryHelper<T> registryHelper = RegistryHelper.create(registry, modid);
        registryHelpers.add(registryHelper);
        return registryHelper;
    }

    public <T> RegistryHelper<T> createRegister(IForgeRegistry<T> registry) {
        RegistryHelper<T> registryHelper = RegistryHelper.create(registry, modid);
        registryHelpers.add(registryHelper);
        return registryHelper;
    }

    public <T> ResourceLocation getKey(IForgeRegistry<T> registry, T key) {
        return registry.getKey(key);
    }

    public static RegistryManager getOrCreate(String modid) {
        if (!RegistryManagers.get().containsRegistry(modid)) {
            return new RegistryManager(modid);
        } else {
            return RegistryManagers.get().getRegistryHelper(modid);
        }
    }

    public String getModId() {
        return modid;
    }
}
