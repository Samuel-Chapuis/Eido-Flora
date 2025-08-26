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
    public boolean place(FeaturePlaceContext<NbtTreeFeatureConfig> context) {
        NbtTreeFeatureConfig config = context.config();
        RandomSource random = context.random();
        BlockPos pos = context.origin();
        LevelAccessor world = context.level();

        List<ResourceLocation> templates = config.templates();
        if (templates.isEmpty()) return false;

        ResourceLocation chosen = templates.get(random.nextInt(templates.size()));

    System.out.println("[EidoPlants] NbtTreeFeature.place called at " + pos + " chosen=" + chosen + " isServer=" + (world instanceof ServerLevel));

        // Try to load the NBT structure from resource manager if possible
        try {
            if (!(world instanceof ServerLevel server)) return false;
            ResourceManager rm = server.getServer().getResourceManager();
            // Try pack resource lookup by building a resource location for the structure file
            ResourceLocation fileLoc = ResourceLocation.tryParse(chosen.getNamespace() + ":structures/" + chosen.getPath() + ".nbt");
            InputStream is = null;
            if (fileLoc != null) {
                System.out.println("[EidoPlants] Trying resource location: " + fileLoc);
                var resourceOpt = rm.getResource(fileLoc);
                if (resourceOpt.isPresent()) {
                    System.out.println("[EidoPlants] Found resource at: " + fileLoc);
                    is = resourceOpt.get().open();
                } else {
                    System.out.println("[EidoPlants] Resource not present at: " + fileLoc);
                }
            }
            // fallback: try without namespace path formatting
            if (is == null) {
                ResourceLocation alt = ResourceLocation.tryParse(chosen.getNamespace() + ":" + "structures/" + chosen.getPath() + ".nbt");
                if (alt != null) {
                    var resourceOpt = rm.getResource(alt);
                    if (resourceOpt.isPresent()) is = resourceOpt.get().open();
                }
            }

            if (is == null) {
                System.out.println("[EidoPlants] Could not find NBT resource for template " + chosen + " in resource manager");
                return false;
            }
            CompoundTag nbt = NbtIo.readCompressed(is, NbtAccounter.unlimitedHeap());
            is.close();

            StructureTemplate template = new StructureTemplate();
            template.load(server.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.BLOCK), nbt);

            System.out.println("[EidoPlants] Template loaded for " + chosen + " size=" + template.getSize());

            StructurePlaceSettings settings = new StructurePlaceSettings().addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
            boolean placed = template.placeInWorld(server, pos, pos, settings, random, 2);
            System.out.println("[EidoPlants] Template placeInWorld result=" + placed + " at " + pos);
            return placed;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
