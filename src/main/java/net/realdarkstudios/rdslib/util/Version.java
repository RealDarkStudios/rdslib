package net.realdarkstudios.rdslib.util;

import net.minecraftforge.fml.ModList;

import java.util.Objects;

public class Version {
    public int majormod;
    public int majorapi;
    public int minor;
    public int patch;

    public Version(int majormod, int majorapi, int minor, int patch) {
        this.majormod = majormod;
        this.majorapi = majorapi;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     *
     * @param modid The Mod ID of the mod
     * @param versionSpot Where the actual version is located based off of dashes. For example, 1.20.X-forge-0.3.0 would have an index of 2, while 1.20.X-0.0.2 would be 1.
     * @return Version
     */
    public static Version fromModId(String modid, int versionSpot) {
        String modVersion = ModList.get().getModFileById(modid).versionString();
        String toConvert = modVersion.split("-")[versionSpot];

        return Version.fromString(toConvert);
    }

    public static Version fromString(String verString) {
        String[] split = verString.replace('.', ':').split(":");
        if (split.length != 4) {
            return null;
        }

        int[] digits = new int[4];
        for (int i = 0; i < digits.length; i++) {
            try {
                digits[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return new Version(digits[0], digits[1], digits[2], digits[3]);
    }

    public void reset() {
        majormod = 0;
        majorapi = 0;
        minor = 0;
        patch = 0;
    }

    public byte compare(Version other) {
        return other.majormod > majormod ? -1 : other.majormod == majormod ? other.majorapi > majorapi ? -1 : other.majorapi == majorapi ? other.minor > minor ? -1 : other.minor == minor ? (byte) Integer.compare(patch, other.patch) : 1 : 1 : 1;
    }

    public int getMajorApi() {
        return majorapi;
    }

    public int getMajorMod() {
        return majormod;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    @Override
    public String toString() {
        if (majormod == 0 && majorapi == 0 && minor == 0 && patch == 0) {
            return "Version EMPTY";
        }
        return String.format("%d.%d.%d.%d", majormod, majorapi, minor, patch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return majormod == version.majormod && majorapi == version.majorapi && minor == version.minor && patch == version.patch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(majormod, majorapi, minor, patch);
    }
}
