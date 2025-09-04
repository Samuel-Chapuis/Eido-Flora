package fr.Eidolyth.worldgen;

import fr.Eidolyth.ModFeatures;
import fr.Eidolyth.worldgen.NbtTreeFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModWorldGen {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
        DeferredRegister.create(Registries.CONFIGURED_FEATURE, fr.Eidolyth.EidoPlants.MODID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
        DeferredRegister.create(Registries.PLACED_FEATURE, fr.Eidolyth.EidoPlants.MODID);

    // Register the configured feature for custom_oak
    @SuppressWarnings("unchecked")
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_OAK =
        CONFIGURED_FEATURES.register("custom_oak", () -> {
            // Explicitly create typed instances to help the compiler with generics
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:oak1"),
                    ResourceLocation.tryParse("eidoplants:oak2"),
                    ResourceLocation.tryParse("eidoplants:oak3")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres (placement à y-1 pour les racines)
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Acacia trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_ACACIA =
        CONFIGURED_FEATURES.register("custom_acacia", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:acacia1"),
                    ResourceLocation.tryParse("eidoplants:acacia2"),
                    ResourceLocation.tryParse("eidoplants:acacia3"),
                    ResourceLocation.tryParse("eidoplants:acacia4")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Birch trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_BIRCH =
        CONFIGURED_FEATURES.register("custom_birch", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:birch1"),
                    ResourceLocation.tryParse("eidoplants:birch2"),
                    ResourceLocation.tryParse("eidoplants:birch3"),
                    ResourceLocation.tryParse("eidoplants:birch4"),
                    ResourceLocation.tryParse("eidoplants:bushy_birch1"),
                    ResourceLocation.tryParse("eidoplants:bushy_birch2"),
                    ResourceLocation.tryParse("eidoplants:bushy_birch3"),
                    ResourceLocation.tryParse("eidoplants:bushy_birch4"),
                    ResourceLocation.tryParse("eidoplants:bushy_birch5")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Dark Oak trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_DARK_OAK =
        CONFIGURED_FEATURES.register("custom_dark_oak", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:dark_oak1"),
                    ResourceLocation.tryParse("eidoplants:dark_oak2"),
                    ResourceLocation.tryParse("eidoplants:dark_oak3")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Jungle trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_JUNGLE =
        CONFIGURED_FEATURES.register("custom_jungle", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:jungle1"),
                    ResourceLocation.tryParse("eidoplants:jungle2"),
                    ResourceLocation.tryParse("eidoplants:jungle3")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Mangrove trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_MANGROVE =
        CONFIGURED_FEATURES.register("custom_mangrove", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:mangrove1"),
                    ResourceLocation.tryParse("eidoplants:mangrove2"),
                    ResourceLocation.tryParse("eidoplants:mangrove3")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Palm trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_PALM =
        CONFIGURED_FEATURES.register("custom_palm", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:palm1"),
                    ResourceLocation.tryParse("eidoplants:palm2"),
                    ResourceLocation.tryParse("eidoplants:palm3"),
                    ResourceLocation.tryParse("eidoplants:palm4"),
                    ResourceLocation.tryParse("eidoplants:palm5"),
                    ResourceLocation.tryParse("eidoplants:palm6")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Plume trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_PLUME =
        CONFIGURED_FEATURES.register("custom_plume", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:plume1"),
                    ResourceLocation.tryParse("eidoplants:plume2"),
                    ResourceLocation.tryParse("eidoplants:plume3")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Sakura trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_SAKURA =
        CONFIGURED_FEATURES.register("custom_sakura", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:sakura1"),
                    ResourceLocation.tryParse("eidoplants:sakura2"),
                    ResourceLocation.tryParse("eidoplants:sakura3"),
                    ResourceLocation.tryParse("eidoplants:sakura4")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Sequoia trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_SEQUOIA =
        CONFIGURED_FEATURES.register("custom_sequoia", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:sequoia1"),
                    ResourceLocation.tryParse("eidoplants:sequoia2"),
                    ResourceLocation.tryParse("eidoplants:sequoia3"),
                    ResourceLocation.tryParse("eidoplants:sequoia4")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    // Spruce trees
    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> CUSTOM_SPRUCE =
        CONFIGURED_FEATURES.register("custom_spruce", () -> {
            NbtTreeFeature feature = (NbtTreeFeature) ModFeatures.NBT_TREE.get();
            NbtTreeFeatureConfig cfg = new NbtTreeFeatureConfig(
                java.util.Arrays.asList(
                    ResourceLocation.tryParse("eidoplants:spruce1"),
                    ResourceLocation.tryParse("eidoplants:spruce2"),
                    ResourceLocation.tryParse("eidoplants:spruce3"),
                    ResourceLocation.tryParse("eidoplants:spruce4"),
                    ResourceLocation.tryParse("eidoplants:spruce5"),
                    ResourceLocation.tryParse("eidoplants:spruce6")
                ),
                true,
                true,
                true,
                -1  // y_offset pour les arbres
            );
            return new ConfiguredFeature<NbtTreeFeatureConfig, NbtTreeFeature>(feature, cfg);
        });

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_OAK_PLACED =
        PLACED_FEATURES.register("custom_oak_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_OAK.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(5),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_ACACIA_PLACED =
        PLACED_FEATURES.register("custom_acacia_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_ACACIA.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(2),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_BIRCH_PLACED =
        PLACED_FEATURES.register("custom_birch_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_BIRCH.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(3),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_DARK_OAK_PLACED =
        PLACED_FEATURES.register("custom_dark_oak_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_DARK_OAK.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(2),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_JUNGLE_PLACED =
        PLACED_FEATURES.register("custom_jungle_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_JUNGLE.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(4),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_MANGROVE_PLACED =
        PLACED_FEATURES.register("custom_mangrove_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_MANGROVE.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(3),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_PALM_PLACED =
        PLACED_FEATURES.register("custom_palm_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_PALM.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(1),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_PLUME_PLACED =
        PLACED_FEATURES.register("custom_plume_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_PLUME.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(2),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_SAKURA_PLACED =
        PLACED_FEATURES.register("custom_sakura_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_SAKURA.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(2),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_SEQUOIA_PLACED =
        PLACED_FEATURES.register("custom_sequoia_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_SEQUOIA.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(1),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static final DeferredHolder<PlacedFeature, PlacedFeature> CUSTOM_SPRUCE_PLACED =
        PLACED_FEATURES.register("custom_spruce_placed", () ->
            new PlacedFeature(
                net.minecraft.core.Holder.direct(CUSTOM_SPRUCE.get()),
                java.util.Arrays.asList(
                    net.minecraft.world.level.levelgen.placement.CountPlacement.of(3),
                    net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread(),
                    net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING),
                    net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth(0),
                    net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
                )
            )
        );

    public static void register(net.neoforged.bus.api.IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
        PLACED_FEATURES.register(bus);
    }
}