![CurseForge Downloads](https://img.shields.io/curseforge/dt/880829?logo=curseforge "RDSLib Curseforge Downloads")
![Modrinth Downloads](https://img.shields.io/modrinth/dt/rdslib?logo=modrinth "RDSLib Modrinth Downloads")
![Latest Maven Release](https://img.shields.io/maven-metadata/v?logo=apache&metadataUrl=https%3A%2F%2Fmaven.digitalunderworlds.com%2Freleases%2Fnet%2Frealdarkstudios%2Frdslib%2Fmaven-metadata.xml&versionPrefix=4&label=release "Latest Release")
![Latest Maven Snapshot](https://img.shields.io/maven-metadata/v?logo=apache&metadataUrl=https%3A%2F%2Fmaven.digitalunderworlds.com%2Fsnapshots%2Fnet%2Frealdarkstudios%2Frdslib%2Fmaven-metadata.xml&versionPrefix=4&label=snapshot "Latest Snapshot")

**This mod adds nothing on its own, rather, it is required by other mods!**

## What is RDSLib?

RDSLib is a simple library that adds many systems that are primarily used in RDS mods such as Masterful Mines.

Some examples are:
- A simplified Rarity system in which you can change the style of an item, tab, etc.
- A custom Armor system in which you can apply effects to a player based off the armor type they're wearing
- A fully-fledged custom furnace system with integrations for JEI, EMI, and REI.

There is also a [wiki](https://github.com/RealDarkStudios/rdslib/wiki) which is a Work in Progress

## Adding RDSLib as a dependency

With the release of vX.0.3.0, there is now a [maven](https://maven.digitalunderworlds.com/#/).

To use the maven, add
```groovy
    maven {
        name = "Digitalunderworlds"
        url = "https://maven.digitalunderworlds.com/releases"
    }
    // For SNAPSHOT releases, such as v4.0.3.0-SNAPSHOT-rc3
    maven {
        name = "Digitalunderworlds Snapshots"
        url = "https://maven.digitalunderworlds.com/snapshots"
    }
```
to your `repositories` block.

Then you can implement `"net.realdarkstudios:rdslib:${rdslib_ver}"` in your `dependencies` block

