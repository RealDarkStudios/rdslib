package net.realdarkstudios.rdslib.registry;

import java.util.HashMap;
import java.util.Objects;

public class RegistryManagers {
    private HashMap<String, RegistryManager> registryManagerMap = new HashMap<>();
    private static RegistryManagers INSTANCE = null;

    private RegistryManagers() {
        INSTANCE = this;
    }

    public boolean containsRegistry(String modid) {
        return registryManagerMap.containsKey(modid);
    }

    public void addManager(String modid, RegistryManager helper) {
        registryManagerMap.put(modid, helper);
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
}
