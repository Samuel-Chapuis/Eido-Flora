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
                true
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

    public static void register(net.neoforged.bus.api.IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
        PLACED_FEATURES.register(bus);
    }
}