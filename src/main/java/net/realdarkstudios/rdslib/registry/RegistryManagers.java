package net.realdarkstudios.rdslib.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RegistryManagers {
    private final HashMap<String, RegistryManager> registryManagerMap = new HashMap<>();
    private static RegistryManagers INSTANCE = null;

    private RegistryManagers() {
        INSTANCE = this;
    }

    public boolean containsRegistry(String modid) {
        return registryManagerMap.containsKey(modid);
    }

    public RegistryManager newManager(String modid) {
        if (!containsRegistry(modid)) {
            RegistryManager manager = RegistryManager.getOrCreate(modid);
            registryManagerMap.put(modid, manager);
            return manager;
        } else return registryManagerMap.get(modid);
    }

    public void addManager(String modid, RegistryManager manager) {
        registryManagerMap.put(modid, manager);
    }

    public static RegistryManagers get() {
        return Objects.requireNonNullElseGet(INSTANCE, RegistryManagers::new);
    }

    public RegistryManager getRegistryHelper(String modid) {
        if(containsRegistry(modid)) {
            return registryManagerMap.get(modid);
        } else {
            throw new NullPointerException("The Registry Manager belonging to " + modid + "does not exist!");
        }
    }

    public List<RegistryManager> getRegistryManagers() {
        return registryManagerMap.values().stream().toList();
    }
}
