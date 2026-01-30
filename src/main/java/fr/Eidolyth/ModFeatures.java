package fr.Eidolyth;

import fr.Eidolyth.worldgen.NbtTreeFeature;
import fr.Eidolyth.worldgen.NbtTreeFeatureConfig;
import fr.Eidolyth.worldgen.TallWaterPlantFeature;
import fr.Eidolyth.worldgen.TallKelpFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, EidoPlants.MODID);

    public static final DeferredHolder<Feature<?>, Feature<?>> NBT_TREE = FEATURES.register("nbt_tree", () -> new NbtTreeFeature());
    public static final DeferredHolder<Feature<?>, Feature<?>> TALL_WATER_PLANT = FEATURES.register(
        "tall_water_plant",
        () -> new TallWaterPlantFeature(SimpleBlockConfiguration.CODEC)
    );
    public static final DeferredHolder<Feature<?>, Feature<?>> BULL_KELP_COLUMN = FEATURES.register(
        "bull_kelp_column",
        () -> new TallKelpFeature(SimpleBlockConfiguration.CODEC, 8, 16)
    );
    public static final DeferredHolder<Feature<?>, Feature<?>> SARGASSUM_COLUMN = FEATURES.register(
        "sargassum_column",
        () -> new TallKelpFeature(SimpleBlockConfiguration.CODEC, 5, 12)
    );

    public static void register(net.neoforged.bus.api.IEventBus bus) {
        FEATURES.register(bus);
    }
}
