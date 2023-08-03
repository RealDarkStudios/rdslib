package net.realdarkstudios.rdslib.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryHelper<T> {
    private final DeferredRegister<T> deferredRegister;
    private final IForgeRegistry<T> registry;
    private final String modid;

    private RegistryHelper(ResourceKey<? extends Registry<T>> registryKey, IForgeRegistry<T> registry, String modid)
    {
        this.deferredRegister = DeferredRegister.create(registryKey, modid);
        this.registry = registry;
        this.modid = modid;
    }

    private RegistryHelper(IForgeRegistry<T> reg, String modid)
    {
        this(reg.getRegistryKey(), reg, modid);
    }

    public static <B> RegistryHelper<B> create(IForgeRegistry<B> reg, String modid)
    {
        return new RegistryHelper<>(reg, modid);
    }

    public static <B> RegistryHelper<B> create(ResourceKey<? extends Registry<B>> key, String modid)
    {
        return new RegistryHelper<>(key, getRegistryFromKey(key), modid);
    }

    private static  <T> IForgeRegistry<T> getRegistryFromKey(ResourceKey<? extends Registry<T>> resourceKey) {
        return RegistryManager.ACTIVE.getRegistry(resourceKey);
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
