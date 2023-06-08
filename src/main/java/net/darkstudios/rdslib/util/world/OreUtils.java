package net.darkstudios.rdslib.util.world;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class OreUtils {
    public static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier spread) {
        return List.of(count, InSquarePlacement.spread(), spread, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier spread) {
        return orePlacement(CountPlacement.of(count), spread);
    }

    public static List<PlacementModifier> rareOrePlacement(int averageCount, PlacementModifier spread) {
        return orePlacement(RarityFilter.onAverageOnceEvery(averageCount), spread);
    }
}
