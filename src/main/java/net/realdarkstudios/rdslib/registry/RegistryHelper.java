package net.realdarkstudios.rdslib.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryHelper<T> {
    private final DeferredRegister<T> deferredRegister;
    private final IForgeRegistry<T> registry;
    private final String modid;

    private RegistryHelper(IForgeRegistry<T> registry, String modid) {
        this.deferredRegister = DeferredRegister.create(registry.getRegistryName(), modid);
        this.registry = registry;
        this.modid = modid;
    }

    public static <B> RegistryHelper<B> create(IForgeRegistry<B> reg, String modid) {
        RegistryHelper<B> registryHelper = new RegistryHelper<>(reg, modid);
        RegistryManager.getOrCreate(modid).addRegistryHelper(registryHelper);
        return registryHelper;
    }

    public static <B> RegistryHelper<B> create(ResourceKey<? extends Registry<B>> key, String modid) {
        RegistryHelper<B> registryHelper = new RegistryHelper<>(getRegistryFromKey(key), modid);
        RegistryManager.getOrCreate(modid).addRegistryHelper(registryHelper);
        return registryHelper;
    }

    public static <B> RegistryHelper<B> create(ResourceLocation resource, String modid) {
        RegistryHelper<B> registryHelper = new RegistryHelper<>(getRegistryFromKey(ResourceKey.createRegistryKey(resource)), modid);
        RegistryManager.getOrCreate(modid).addRegistryHelper(registryHelper);
        return registryHelper;
    }

    public static <T> IForgeRegistry<T> getRegistryFromKey(ResourceKey<? extends Registry<T>> resourceKey) {
        return net.minecraftforge.registries.RegistryManager.ACTIVE.getRegistry(resourceKey);
    }

    public DeferredRegister<T> getDeferredRegister() {
        return deferredRegister;
    }

    public IForgeRegistry<T> getRegistry() {
        return registry;
    }


    public <I extends T> RegistryObject<I> register(String pName, Supplier<? extends I> pSupplier) {
        return deferredRegister.register(pName, pSupplier);
    }

    public void register(IEventBus eventBus) {
        deferredRegister.register(eventBus);
    }
}
