package net.realdarkstudios.rdslib.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;
import java.util.List;


public class RegistryManager {
    private String modid;
    private HashMap<ResourceKey<?>, RegistryHelper<?>> registryHelpers = new HashMap<>();

    private RegistryManager(String modid) {
        this.modid = modid;
        RegistryManagers.get().addManager(modid, this);
    }

    public void addRegistryHelper(RegistryHelper<?> helper) {
        registryHelpers.put(helper.getRegistry().getRegistryKey(), helper);
    }

    public <T> RegistryHelper<T> createRegister(ResourceKey<Registry<T>> registry) {
        return RegistryHelper.create(registry, modid);
    }

    public <T> RegistryHelper<T> createRegister(IForgeRegistry<T> registry) {
        return RegistryHelper.create(registry, modid);
    }

    public <T> ResourceLocation getKey(IForgeRegistry<T> registry, T key) {
        return registry.getKey(key);
    }

    public <T> ResourceLocation getKey(RegistryHelper<T> registryHelper, T key) {
        return registryHelper.getRegistry().getKey(key);
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

    public List<RegistryHelper<?>>  getRegistryHelpers() {
        return registryHelpers.values().stream().toList();
    }
}
