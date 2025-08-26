package fr.Eidolyth;

import fr.Eidolyth.worldgen.NbtTreeFeature;
import fr.Eidolyth.worldgen.NbtTreeFeatureConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, EidoPlants.MODID);

    public static final DeferredHolder<Feature<?>, Feature<?>> NBT_TREE = FEATURES.register("nbt_tree", () -> new NbtTreeFeature());

    public static void register(net.neoforged.bus.api.IEventBus bus) {
        FEATURES.register(bus);
    }
}
