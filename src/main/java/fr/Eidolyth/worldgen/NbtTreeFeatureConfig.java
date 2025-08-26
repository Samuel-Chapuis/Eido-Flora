package fr.Eidolyth.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import java.util.List;

public record NbtTreeFeatureConfig(List<ResourceLocation> templates, boolean rotate, boolean mirror, boolean ignore_air) implements FeatureConfiguration {
    public static final Codec<NbtTreeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.listOf().fieldOf("templates").forGetter(NbtTreeFeatureConfig::templates),
            Codec.BOOL.fieldOf("rotate").orElse(false).forGetter(NbtTreeFeatureConfig::rotate),
            Codec.BOOL.fieldOf("mirror").orElse(false).forGetter(NbtTreeFeatureConfig::mirror),
            Codec.BOOL.fieldOf("ignore_air").orElse(true).forGetter(NbtTreeFeatureConfig::ignore_air)
    ).apply(instance, NbtTreeFeatureConfig::new));
}
