package fr.Eidolyth.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;

import java.io.InputStream;
import java.util.List;
// ...existing code...

public class NbtTreeFeature extends Feature<NbtTreeFeatureConfig> {
    public static final Codec<NbtTreeFeatureConfig> CONFIG_CODEC = NbtTreeFeatureConfig.CODEC;

    public NbtTreeFeature() {
        super(CONFIG_CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NbtTreeFeatureConfig> ctx) {
        var level = ctx.level();                 // LevelAccessor
        var random = ctx.random();
        var pos = ctx.origin();
        var cfg = ctx.config();

        // 1) Avoir un "server-like" utilisable partout
        final net.minecraft.world.level.ServerLevelAccessor srv;
        final net.minecraft.server.level.ServerLevel server; // pour StructureManager / registry
        if (level instanceof net.minecraft.server.level.ServerLevel sl) {
            srv = sl;
            server = sl;
        } else if (level instanceof net.minecraft.world.level.WorldGenLevel wgl) {
            srv = wgl;                // WorldGenLevel implémente ServerLevelAccessor
            server = wgl.getLevel();  // ServerLevel
        } else {
            System.out.println("[EidoPlants] place(): not server-like, skip. pos=" + pos);
            return false;
        }

        // 2) Choisir un template
        var list = cfg.templates();
        if (list == null || list.isEmpty()) {
            System.out.println("[EidoPlants] No templates in config");
            return false;
        }
        var chosen = list.get(random.nextInt(list.size()));
        System.out.println("[EidoPlants] place at " + pos + " chosen=" + chosen);

        // 3) Essayer StructureManager d'abord
        net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate template = null;
        var opt = server.getStructureManager().get(chosen);
        if (opt.isPresent()) {
            template = opt.get();
            System.out.println("[EidoPlants] Loaded via StructureManager size=" + template.getSize());
        } else {
            // 3bis) Fallback: lecture manuelle comme ton ancien code
            try {
                var rm = server.getServer().getResourceManager();
                ResourceLocation fileLoc = ResourceLocation.fromNamespaceAndPath(
                        chosen.getNamespace(),
                        "structures/" + chosen.getPath() + ".nbt"
                );
                var res = rm.getResource(fileLoc);
                if (res.isPresent()) {
                    try (var is = res.get().open()) {
                        var nbt = net.minecraft.nbt.NbtIo.readCompressed(is, net.minecraft.nbt.NbtAccounter.unlimitedHeap());
                        template = new net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate();
                        template.load(server.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.BLOCK), nbt);
                        System.out.println("[EidoPlants] Loaded via ResourceManager size=" + template.getSize());
                    }
                } else {
                    System.out.println("[EidoPlants] Not found (SM+RM): " + fileLoc);
                    return false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

        // 4) Settings
        var settings = new net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings();
        if (cfg.rotate()) settings.setRotation(net.minecraft.world.level.block.Rotation.getRandom(random));
        if (cfg.mirror()) settings.setMirror(random.nextBoolean()
                ? net.minecraft.world.level.block.Mirror.FRONT_BACK
                : net.minecraft.world.level.block.Mirror.LEFT_RIGHT);
        if (cfg.ignore_air()) {
            settings.addProcessor(net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor.STRUCTURE_AND_AIR);
        }

        // 4bis) Vérifier que le centre de la structure est sur un bloc de terre approprié
        var templateSize = template.getSize();
        var centerPos = pos.offset(templateSize.getX() / 2, 0, templateSize.getZ() / 2);
        var blockBelowCenter = level.getBlockState(centerPos.below());

        // Liste des blocs appropriés pour les arbres
        var acceptableBlocks = java.util.Set.of(
            net.minecraft.world.level.block.Blocks.GRASS_BLOCK,
            net.minecraft.world.level.block.Blocks.DIRT,
            net.minecraft.world.level.block.Blocks.PODZOL,
            net.minecraft.world.level.block.Blocks.COARSE_DIRT,
            net.minecraft.world.level.block.Blocks.ROOTED_DIRT
        );

        // Vérifier si le bloc sous le centre est un bloc de terre approprié
        if (!acceptableBlocks.contains(blockBelowCenter.getBlock())) {
            System.out.println("[EidoPlants] Center position not on acceptable ground block: " + blockBelowCenter.getBlock() + ", skipping placement at " + pos);
            return false;
        }

        // 5) Centrer le placement de l'arbre
        // centre horizontal du template (au niveau Y=0)
        var sz = template.getSize();
        var half = new net.minecraft.core.BlockPos(sz.getX() / 2, 1, sz.getZ() / 2);

        // Transformer ce vecteur selon Mirror/Rotation pour connaître le décalage réel
        var halfTransformed = net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
                .transform(half, settings.getMirror(), settings.getRotation(), net.minecraft.core.BlockPos.ZERO);

        // Décaler l'origine pour que halfTransformed tombe sur 'pos' (position de base)
        var origin = pos.subtract(halfTransformed);

        // Placement centré sur ServerLevelAccessor
        boolean placed = template.placeInWorld(srv, origin, origin, settings, random, 2);
        System.out.println("[EidoPlants] placeInWorld=" + placed + " at " + origin + " (centered from " + pos + ")");
        return placed;
    }
}
